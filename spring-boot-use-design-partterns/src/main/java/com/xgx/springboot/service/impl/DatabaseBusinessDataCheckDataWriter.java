package com.xgx.springboot.service.impl;

import com.xgx.springboot.domain.MergedCell;
import com.xgx.springboot.service.DataWriter;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author xu.gx
 */
@Service
public class DatabaseBusinessDataCheckDataWriter extends DataWriter {

    private final List<MergedCell> mergedHeaders = Arrays.asList(
            new MergedCell("关键数据表数据巡检", 11)
    );
    private final List<String> headerNames01 = Arrays.asList("订购表", "订购历时表", "注册表", "注册历史表", "用户PS表",
            "用户子属性表", "话单表(每月一张)", "ps同步表", "用户资金表", "用户资金历史表", "session数量");
    private final List<String> headerNames02 = Arrays.asList("bms_subscription_bppp", "bms_subscription_history",
            "bms_customer_reg", "BMS_CUSTREG_HISTORY", "ps_radius", "bms_subs_prodfea", "usage_bppp_202301",
            "ps_provision", "abs_user_balance", "abs_user_history", "v$session");

    @Override
    public List<List<String>> getData(String date) {
        return Arrays.asList(
                Arrays.asList("8868162", "25814655", "14009177", "7154993", "17209366", "104473761", "92772039", "35", "14055533", "10575249", "288")
        );
    }

    @Override
    public void writeData(Sheet sheet, List<List<String>> data) {
        // 第一行写入合并单元格的表头
        writeMergedRow(sheet, mergedHeaders);
        // 第二行写入表头（不存在合并单元格）
        writeRowData(sheet, headerNames01, 1);
        // 第三行写入表头（不存在合并单元格）
        writeRowData(sheet, headerNames02, 2);
        writeData(sheet, data, 3);
    }
}