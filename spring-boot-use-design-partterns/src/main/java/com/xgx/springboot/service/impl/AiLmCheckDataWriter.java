package com.xgx.springboot.service.impl;

import com.xgx.springboot.service.DataWriter;
import com.xgx.springboot.domain.MergedCell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author xu.gx
 */
@Service
public class AiLmCheckDataWriter extends DataWriter {

    private final List<MergedCell> mergedHeaders = Arrays.asList(
            new MergedCell("", 2),
            new MergedCell("部署检查", 5),
            new MergedCell("监控告警检查", 2),
            new MergedCell("日志检查", 3),
            new MergedCell("业务检查", 4),
            new MergedCell("备份检查", 1)
    );
    private final List<String> headerNames = Arrays.asList("主机名", "设备IP", "设备数量、配置是否符合推荐要求",
            "是否使用HA/集群/负载均衡方式部署", "业务高峰期资源利用率是否超阈值", "程序自启动脚本检查", "是否部署NTP时钟同步",
            "是否纳入监控", "是否有该模块相关的监控告警，进程、日志等", "lm.log", "lm_detail.log", "定期日志清除脚本检查",
            "ailm", "当前在线用户数", "允许最大在线用户数", "AILM系统容量使用率", "定期备份"
    );

    @Override
    public List<List<String>> getData(String date) {
        // 实现获取数据的逻辑

        // 可根据需要自定义不同的数据获取逻辑
        return null;
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