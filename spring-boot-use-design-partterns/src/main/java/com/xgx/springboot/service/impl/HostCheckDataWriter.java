package com.xgx.springboot.service.impl;

import com.xgx.springboot.domain.DeviceBasicdata;
import com.xgx.springboot.mapper.DeviceBasicdataMapper;
import com.xgx.springboot.service.DataWriter;
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
 * 主机巡检
 *
 * @author xu.gx
 */
@Service
public class HostCheckDataWriter extends DataWriter {

    @Autowired
    private DeviceBasicdataMapper deviceBasicdataMapper;

    private final List<String> headers = Arrays.asList("主机名", "设备IP", "系统运行时间（天）", "硬盘利用率", "cpu利用率%", "内存利用率", "磁盘IO状态", "进程数", "显示路由表", "主机连接系统网络情况", "主机系统登陆日志", "时钟同步", "定时任务", "定时任务执行情况", "环境变量", "防火墙状态", "是否自动监控");

    private final String tablePrefix = "devicebasicdata_";

    @Override
    public List<List<String>> getData(String date) {
        String tableName = tablePrefix + date;
        List<DeviceBasicdata> list = deviceBasicdataMapper.selectYesterdayHostMetrics(tableName);
        List<List<String>> retList = new ArrayList<>();
        // 写入数据
        if (!CollectionUtils.isEmpty(list)) {
            // 按照deviceIp分组
            Map<String, List<DeviceBasicdata>> deviceMap = list.stream().
                    collect(Collectors.groupingBy(DeviceBasicdata::getDeviceIp));
            for (Map.Entry<String, List<DeviceBasicdata>> entry : deviceMap.entrySet()) {
                List<DeviceBasicdata> hostDataList = entry.getValue();
                // cpu指标
                DeviceBasicdata deviceCpuInfo = hostDataList.get(0);
                // 内存指标
                DeviceBasicdata deviceMemoryInfo = hostDataList.get(1);
                List<String> rowData = new ArrayList<>();
                // 主机名
                rowData.add(deviceCpuInfo.getDeviceName());
                // 设备IP
                rowData.add(deviceCpuInfo.getDeviceIp());
                // 系统运行时间（天）
                rowData.add("");
                // 硬盘利用率
                rowData.add("");
                // CPU利用率%
                rowData.add(String.valueOf(deviceCpuInfo.getTargetValue()));
                // 内存利用率
                rowData.add(String.valueOf(deviceMemoryInfo.getTargetValue()));
                // 磁盘IO状态
                rowData.add("正常");
                // 进程数
                rowData.add("正常");
                // 显示路由表
                rowData.add("正常");
                // 主机连接系统网络情况
                rowData.add("正常");
                // 主机系统登陆日志
                rowData.add("有");
                // 时钟同步
                rowData.add("有");
                // 定时任务
                rowData.add("正常");
                // 定时任务执行情况
                rowData.add("正常");
                // 环境变量
                rowData.add("正常");
                // 防火墙状态
                rowData.add("关闭");
                // 是否自动监控
                rowData.add("是");
                retList.add(rowData);
            }

        }

        return retList;
    }

    @Override
    protected void writeData(Sheet sheet, List<List<String>> data) {
        // 第0行写入表头
        writeRowData(sheet, headers, 0);
        // 从第一行开始写入数据
        writeData(sheet, data, 1);
    }
}