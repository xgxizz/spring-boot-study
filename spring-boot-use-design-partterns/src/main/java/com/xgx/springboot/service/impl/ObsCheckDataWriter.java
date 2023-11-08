package com.xgx.springboot.service.impl;

import com.xgx.springboot.domain.MergedCell;
import com.xgx.springboot.mapper.AaaObsBasicDataMapper;
import com.xgx.springboot.service.DataWriter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xu.gx
 */
@Service
public class ObsCheckDataWriter extends DataWriter {

    private final List<MergedCell> mergedHeaders = Arrays.asList(
            new MergedCell("", 1),
            new MergedCell("", 1),
            new MergedCell("部署检查", 5),
            new MergedCell("监控告警检查", 2),
            new MergedCell("日志检查", 49),
            new MergedCell("业务检查", 48),
            new MergedCell("备份检查", 1)
    );
    private final List<String> columnHeaderNames = Arrays.asList("主机名", "设备IP", "设备数量、配置是否符合推荐要求",
            "是否使用HA/集群/负载均衡方式部署", "业务高峰期资源利用率是否超阈值", "程序自启动脚本检查", "是否部署NTP时钟同步",
            "是否纳入监控", "是否有该模块相关的监控告警，进程、日志等", "Corba日志", "数据字典日志", "license服务日志", "会话服务日志",
            "BMS服务日志", "ABS服务日志", "ps开通日志", "ACC服务日志", "自服务日志", "用户带宽服务日志", "用户统计日志", "话单采集服务日志",
            "话单计费日志", "密码检查服务日志", "interface服务日志", "开通接口日志", "691接口日志", "WLAN服务日志", "VPN服务日志",
            "WLAN服务日志", "测速接口日志", "测速服务日志", "客服接口日志", "集中监控日志", "微信接口日志", "宽带专家接口日志",
            "iptv接口日志", "用户踢下线接口日志", "沃守护接口日志", "直通车接口日志", "RMS接口日志", "WLAN服务日志", "WLAN服务日志",
            "VPN服务日志", "话单采集日志", "警用接口日志", "OBS管理页面日志", "97开通接口日志", "IPTV接口日志", "微信接口日志",
            "客服接口日志", "宽带专家接口日志", "RMS接口日志", "集中监控接口日志", "客服集约化接口日志", "直通车接口日志",
            "RMS密码同步服务日志", "RMS密码同步接口日志", "定期日志清除脚本检查", "osagent", "datainit", "ailicserver",
            "BmsSession", "BmsServer", "absStart", "psagent", "acc_server.tcl", "self_server.tcl", "bmsbroadband.tcl",
            "BmsCustat.tcl", "smartcollector", "rbilld", "BmsChkPass.tcl", "bms_interface.tcl", "bms97.tcl",
            "691_interface.tcl", "portal2obs_cufj.tcl", "bms_gateway_vdsp.tcl", "bms_cmccwlan.tcl", "bms_cesu_server.tcl",
            "bms_cusx_cesu.tcl", "hw_interface1.tcl", "jk_interface.tcl", "wx_interface.tcl", "kdzc_interface.tcl",
            "iptv_interface.tcl", "dm103_interface.tcl", "safe-dns.tcl", "bms97ztc1.tcl", "rms_interface.tcl",
            "portal2obs_cusx.tcl", "portal2obs_java.tcl", "vdsp.tcl", "smart_detail_prepay_cubj.tcl", "x1x2.tcl",
            "wwwstart", "97开通接口", "IPTV接口", "微信接口", "客服接口", "宽带专家接口", "RMS接口", "集中监控接口", "客服集约化接口",
            "直通车接口", "RMS密码同步服务", "RMS密码同步接口", "定期备份"
    );
    private final List<String> headerNames = Arrays.asList("主节点主OBS服务器", "主节点接口服务器",
            "主节点备OBS服务器", "备节点服务器");
    private final String tablePrefix = "aaaobsbasicdata_";

    @Autowired
    private AaaObsBasicDataMapper aaaObsBasicDataMapper;

    @Override
    public List<List<String>> getData(String date) {
        String tableName = tablePrefix + date;
        List<String> hosts = aaaObsBasicDataMapper.selectObsHostList(tableName);
        List<List<String>> retList = new ArrayList<>();
        for (String host : hosts) {
            List<String> list = new ArrayList<>();
            list.add(host);
            // 模拟数据
            List<String> imitate = Arrays.asList("符合", "冷备", "否", "是", "否", "是", "有", "正常", "正常", "正常", "正常", "正常",
                    "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常",
                    "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常",
                    "正常", "正常", "正常", "正常", "无", "无", "无", "无", "无", "无", "无", "无", "无", "无", "无", "正常",
                    "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常",
                    "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常",
                    "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "正常", "无", "无", "无", "无", "无", "无",
                    "无", "无", "无", "无", "无", "无");
            list.addAll(imitate);
            retList.add(list);
        }
        return retList;
    }

    @Override
    public void writeData(Sheet sheet, List<List<String>> data) {
        // 第一列写入标题
        writeMergedColumn(sheet, mergedHeaders);
        // 第二列标题
        writeColumnData(sheet, columnHeaderNames, 0, 1);
        // 横向标题
        writeRowData(sheet, headerNames, 0, 2);
        // 从第3列开始写数据
        writeDataVertically(sheet, data, 1, 2);
    }
}