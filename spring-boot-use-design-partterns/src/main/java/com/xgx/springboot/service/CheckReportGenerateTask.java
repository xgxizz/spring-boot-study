package com.xgx.springboot.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


/**
 * @Description: 52周巡检定时任务
 * @Author: xu.gx
 * @Date: 2023/11/6 15:46
 **/
@Component
@Configuration
public class CheckReportGenerateTask {

    @Autowired
    private DataWriterFactory dataWriterFactory;

    private final static Logger logger = LoggerFactory.getLogger(CheckReportGenerateTask.class);
    private static final String BIND = "52week";
    private static final String BASE_PATH = "/Users/xgx/IdeaProjects/spring-boot-study/spring-boot-use-design-partterns";
    /**
     * sheet页名集合
     */
    private final List<String> sheetNames = Arrays.asList("主机巡检", "数据库巡检", "数据库业务数据巡检", "OBS&接口服务巡检",
            "Portal服务巡检", "AILM服务巡检", "Radius服务巡检", "Aimdb服务巡检", "cidmdb服务巡检");

    @Scheduled(cron = "${taskTime.checkReportGenerate}")
    public void checkReportGenerate() {
        String yesterday = getYesterdayFormat();
        // 创建一个新的工作簿
        Workbook workbook = new XSSFWorkbook();

        for (int i = 0; i < sheetNames.size(); i++) {
            try {
                String sheetName = sheetNames.get(i);
                // 创建sheet页，并设置sheetName
                Sheet sheet = workbook.createSheet(sheetName);
                // 使用工厂创建相应sheet的writer
                DataWriter writer = dataWriterFactory.createDataWriter(sheetName);
                writer.writeToExcel(sheet, yesterday);
            } catch (Exception e) {
                logger.error("写入Sheet出错", e);
                throw new RuntimeException(e);
            }
        }
        // 指定要保存的文件路径
        String filePath = BASE_PATH + File.separator + BIND + "/report_" + yesterday + ".xlsx";
        // 保存工作簿到文件
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        } catch (IOException e) {
            logger.error("写入Excel出错", e);
            throw new RuntimeException(e);
        }
        logger.info("3A系统巡检报告生成定时任务完成，报告路径{}", filePath);
    }

    /**
     * 获取昨天的格式化日期
     *
     * @return
     */
    private String getYesterdayFormat() {
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 减去一天
        LocalDate yesterday = today.minusDays(1);
        String formattedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return formattedDate;
    }
}
