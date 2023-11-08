package com.xgx.springboot.service.impl;

import com.xgx.springboot.domain.DbBasicData;
import com.xgx.springboot.domain.MergedCell;
import com.xgx.springboot.mapper.DbBasicDataMapper;
import com.xgx.springboot.service.DataWriter;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xu.gx
 */
@Service
public class DatabaseCheckDataWriter extends DataWriter {
    private final List<MergedCell> mergedHeaders = Arrays.asList(
            new MergedCell("数据库部署状态巡检", 8),
            new MergedCell("数据库系统参数巡检", 11),
            new MergedCell("数据库重要指标巡检", 9),
            new MergedCell("数据库运行状态巡检", 6),
            new MergedCell("数据库备份巡检", 8)
    );
    private final List<String> headerNames = Arrays.asList("数据库地址", "数据库用途", "数据库版本", "数据库实例",
            "数据库服务名", "数据库端口号", "数据库字符集检查", "数据库集群", "数据库内存参数", "数据库配置连接数(process)",
            "数据库配置连接数(session)", "集群磁盘组利用率", "数据库归档模式", "数据库监听状态", "redo日志文件", "数据库日志文件",
            "undo表空间参数", "数据库异步IO", "spfile文件备份", "表空间利用率(包含TEMP表空间)", "查看被锁的表", "引起锁表的SQL语句",
            "查看锁的SID和Serial", "查看连接数", "查看并发连接数", "查看连接的进程", "查看正在执行的SQL", "查看表索引，是否生效",
            "数据库连接数是否有波动", "数据库锁是否能正常释放", "数据库CPU利用率是否很高", "数据库错误日志检查", "数据库IO是否正常释放",
            "数据库主机网络质量", "数据库配置备份文件", "数据库配置备份日志", "数据库全库表结构备份", "数据库全库表结构备份日志",
            "数据库应用程序备份", "数据库应用程序备份日志", "数据库关键表备份", "数据库关键表备份日志");

    private final String tablePrefix = "dbbasicdata_";

    @Autowired
    private DbBasicDataMapper dbBasicDataMapper;

    @Override
    public List<List<String>> getData(String date) {
        String tableName = tablePrefix + date;
        List<DbBasicData> list = dbBasicDataMapper.selectYesterdayDbMetrics(tableName);
        List<List<String>> retList = new ArrayList<>();
        // 写入数据
        if (!CollectionUtils.isEmpty(list)) {
            // 按照deviceIp分组
            Map<String, List<DbBasicData>> deviceMap = list.stream().
                    collect(Collectors.groupingBy(DbBasicData::getDeviceIp));
            for (Map.Entry<String, List<DbBasicData>> entry : deviceMap.entrySet()) {
                List<DbBasicData> metricsDataList = entry.getValue();
                // 获取指标
                // 1. 表空间利用率
                StringBuilder tablespaceUtilization = new StringBuilder();
                // 2. 集群磁盘组利用率
                String diskUtilization = "";
                // 3. 连接数
                String connCount = "";
                // 循环获取并构建指标值
                for (DbBasicData metric : metricsDataList) {
                    String targetId = metric.getTargetId();
                    float targetValue = metric.getTargetValue();

                    // 1. 表空间利用率
                    // 1.1 ORACLE表空间利用率-OBS_DATA
                    if (StringUtils.equals("DBoracleTablespaceOBS_DATA", targetId)) {
                        tablespaceUtilization.append("OBS_DATA:").append(targetValue).append(";");
                    }
                    // 1.2 ORACLE表空间利用率-OBS_INDEX2
                    if (StringUtils.equals("DBoracleTablespaceOBS_INDEX2", targetId)) {
                        tablespaceUtilization.append("OBS_INDEX2:").append(targetValue).append(";");
                    }
                    // 1.3 ORACLE表空间利用率-SYSAUX
                    if (StringUtils.equals("DBoracleTablespaceSYSAUX", targetId)) {
                        tablespaceUtilization.append("SYSAUX:").append(targetValue).append(";");
                    }
                    // 1.4 ORACLE表空间利用率-SYSTEM
                    if (StringUtils.equals("DBoracleTablespaceSYSTEM", targetId)) {
                        tablespaceUtilization.append("SYSTEM:").append(targetValue).append(";");
                    }
                    // 1.5 ORACLE表空间利用率-TEMP
                    if (StringUtils.equals("DBoracleTablespaceTEMP", targetId)) {
                        tablespaceUtilization.append("TEMP:").append(targetValue).append(";");
                    }
                    // 1.6 ORACLE表空间利用率-USERS
                    if (StringUtils.equals("DBoracleTablespaceUSERS", targetId)) {
                        tablespaceUtilization.append("USERS:").append(targetValue).append(";");
                    }

                    // 2. 集群磁盘组利用率
                    // Mysql按照磁盘剩余率
                    if (StringUtils.equals("DBmysqldiskRate", targetId)) {
                        diskUtilization = String.valueOf(1 - targetValue);
                    }

                    // 3. 连接数
                    // 3.1 Oracle会话连接数
                    if (StringUtils.equals("DBoracleProcessConnected", targetId)) {
                        connCount = String.valueOf(targetValue);
                    }
                    // 3.2 Mysql当前打开的连接的数量
                    if (StringUtils.equals("DBmysqlThreadsConnected", targetId)) {
                        connCount = String.valueOf(targetValue);
                    }
                }

                // 构建行数据
                List<String> rowData = new ArrayList<>();
                DbBasicData dbBasicData = entry.getValue().get(0);
                // 数据库地址
                rowData.add(dbBasicData.getDeviceIp());
                // 数据库用途
                rowData.add(dbBasicData.getDeviceName());
                // 数据库版本
                rowData.add("");
                // 数据库实例
                rowData.add("");
                // 数据库服务名
                rowData.add("");
                // 数据库端口号
                rowData.add(String.valueOf(dbBasicData.getDbPort()));
                // 数据库字符集检查
                rowData.add("");
                // 数据库集群
                rowData.add("无");
                // 数据库内存参数
                rowData.add("");
                // 数据库配置连接数(process)
                rowData.add("");
                // 数据库配置连接数(session)
                rowData.add("");
                // 集群磁盘组利用率
                rowData.add(diskUtilization);
                // 数据库归档模式
                rowData.add("NOARCHIVELOG");
                // 数据库监听状态
                rowData.add("正常");
                // redo日志文件
                rowData.add("正常");
                // 数据库日志文件
                rowData.add("正常");
                // undo表空间参数
                rowData.add("");
                // 数据库异步IO
                rowData.add("none");
                // spfile文件备份
                rowData.add("");
                // 表空间利用率(包含TEMP表空间)
                rowData.add(tablespaceUtilization.toString());
                // 查看被锁的表
                rowData.add("无");
                // 引起锁表的SQL语句
                rowData.add("无");
                // 查看锁的SID和Serial
                rowData.add("无");
                // 查看连接数
                rowData.add(connCount);
                // 查看并发连接数
                rowData.add("");
                // 查看连接的进程
                rowData.add("");
                // 查看正在执行的SQL
                rowData.add("");
                // 查看表索引，是否生效
                rowData.add("生效");
                // 数据库连接数是否有波动
                rowData.add("无");
                // 数据库锁是否能正常释放
                rowData.add("可以");
                // 数据库CPU利用率是否很高
                rowData.add("");
                // 数据库错误日志检查
                rowData.add("无");
                // 数据库IO是否正常释放
                rowData.add("正常释放");
                // 数据库主机网络质量
                rowData.add("无延迟");
                // 数据库配置备份文件
                rowData.add("无");
                // 数据库配置备份日志
                rowData.add("无");
                // 数据库全库表结构备份
                rowData.add("是");
                // 数据库全库表结构备份日志
                rowData.add("无");
                // 数据库应用程序备份
                rowData.add("无");
                // 数据库应用程序备份日志
                rowData.add("无");
                // 数据库关键表备份
                rowData.add("无");
                // 数据库关键表备份日志
                rowData.add("无");
                retList.add(rowData);

            }
        }
        return retList;
    }

    @Override
    public void writeData(Sheet sheet, List<List<String>> data) {
        // 第一行写入合并单元格的表头
        writeMergedRow(sheet, mergedHeaders);
        // 第二行写入表头（不存在合并单元格）
        writeRowData(sheet, headerNames, 1);
        // 写入数据
        writeData(sheet, data, 2);
    }

    public static void main(String[] args) {
        DatabaseCheckDataWriter databaseCheckDataWriter = new DatabaseCheckDataWriter();
        for (String header : databaseCheckDataWriter.headerNames) {
            System.out.println(header);
        }
    }
}