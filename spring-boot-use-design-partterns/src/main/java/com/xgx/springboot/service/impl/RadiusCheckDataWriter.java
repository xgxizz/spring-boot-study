package com.xgx.springboot.service.impl;

import com.xgx.springboot.domain.AaaRadiusBasicData;
import com.xgx.springboot.domain.MergedCell;
import com.xgx.springboot.mapper.AaaRadiusBasicDataMapper;
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
public class RadiusCheckDataWriter extends DataWriter {

    private final List<MergedCell> mergedHeaders = Arrays.asList(
            new MergedCell("", 2),
            new MergedCell("部署检查", 5),
            new MergedCell("监控告警检查", 2),
            new MergedCell("日志检查", 5),
            new MergedCell("业务检查", 9),
            new MergedCell("备份检查", 1)
    );
    private final List<String> headerNames = Arrays.asList("主机名", "设备IP", "设备数量、配置是否符合推荐要求",
            "是否使用HA/集群/负载均衡方式部署", "业务高峰期资源利用率是否超阈值", "程序自检重启脚本", "是否部署NTP时钟同步",
            "是否纳入监控", "是否有该模块相关的监控告警，进程、日志等", "access.log", "radius.log", "access_x_agent日志",
            "adsl_x_agent日志", "定期日志清除脚本检查", "radius", "access_x_agent", "wlan_x_agent", "adsl_x_agent",
            "RADIUS业务认证请求量", "RADIUS业务认证成功数", "RADIUS业务认证成功率(排除用户原因)", "MDB连接数连接池占用情况",
            "每100ms发送和处理的数量", "定期备份"

    );

    private final String tablePrefix = "aaaradiusbasicdata_";

    @Autowired
    private AaaRadiusBasicDataMapper aaaRadiusBasicDataMapper;


    @Override
    public List<List<String>> getData(String date) {
        String tableName = tablePrefix + date;
        List<AaaRadiusBasicData> list = aaaRadiusBasicDataMapper.selectYesterdayRadiusMetrics(tableName);
        List<List<String>> retList = new ArrayList<>();
        // 写入数据
        if (!CollectionUtils.isEmpty(list)) {
            // 按照deviceIp分组
            Map<String, List<AaaRadiusBasicData>> deviceMap = list.stream().
                    collect(Collectors.groupingBy(AaaRadiusBasicData::getDeviceIp));
            for (Map.Entry<String, List<AaaRadiusBasicData>> entry : deviceMap.entrySet()) {
                List<AaaRadiusBasicData> metricsDataList = entry.getValue();
                // 获取指标
                // 1. 请求量
                String requestCount = "";
                // 2. 认证成功数
                String successNum = "";
                // 3. 认证成功率
                String successRate = "";
                // 4. 100ms处理量
                String resp100msNum = "";
                // 循环获取并构建指标值
                for (AaaRadiusBasicData metric : metricsDataList) {
                    String targetId = metric.getTargetId();
                    double targetValue = metric.getTargetValue();

                    // 1. Radius认证请求总量
                    if (StringUtils.equals("AAARadiusAccessRequestNum", targetId)) {
                        requestCount = String.valueOf(targetValue);
                    }

                    // 2. Radius认证成功数
                    if (StringUtils.equals("AAARadiusAccessAcceptNum", targetId)) {
                        successNum = String.valueOf(targetValue);
                    }

                    // 3. Radius认证成功率
                    if (StringUtils.equals("AAARadiusAccessAcceptRate", targetId)) {
                        successRate = String.valueOf(targetValue);
                    }

                    // 4. Radius认证响应时间100ms内个数
                    if (StringUtils.equals("AAARadiusResp100msNum", targetId)) {
                        resp100msNum = String.valueOf(targetValue);
                    }
                }

                // 构建行数据
                List<String> rowData = new ArrayList<>();
                AaaRadiusBasicData radiusBasicData = entry.getValue().get(0);
                // 主机名
                rowData.add(radiusBasicData.getDeviceName());
                // 设备IP
                rowData.add(radiusBasicData.getDeviceIp());
                // 设备数量、配置是否符合推荐要求
                rowData.add("符合");
                // 是否使用HA/集群/负载均衡方式部署
                rowData.add("负载均衡");
                // 业务高峰期资源利用率是否超阈值
                rowData.add("否");
                // 程序自检重启脚本
                rowData.add("有");
                // 是否部署NTP时钟同步
                rowData.add("已部署");
                // 是否纳入监控
                rowData.add("是");
                // 是否有该模块相关的监控告警，进程、日志等
                rowData.add("是");
                // access.log
                rowData.add("更新正常");
                // radius.log
                rowData.add("更新正常");
                // access_x_agent日志
                rowData.add("更新正常");
                // adsl_x_agent日志
                rowData.add("有");
                // 定期日志清除脚本检查
                rowData.add("有");
                // radius
                rowData.add("有");
                // access_x_agent
                rowData.add("有");
                // wlan_x_agent
                rowData.add("正常");
                // adsl_x_agent
                rowData.add("正常");
                // RADIUS业务认证请求量
                rowData.add(requestCount);
                // RADIUS业务认证成功数
                rowData.add(successNum);
                // RADIUS业务认证成功率(排除用户原因)
                rowData.add(successRate);
                // MDB连接数连接池占用情况
                rowData.add("");
                // 每100ms发送和处理的数量
                rowData.add(resp100msNum);
                // 定期备份
                rowData.add("主机互备");

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
}