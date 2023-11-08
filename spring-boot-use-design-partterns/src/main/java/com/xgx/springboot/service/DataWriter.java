package com.xgx.springboot.service;

import com.xgx.springboot.domain.MergedCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xu.gx
 */
public abstract class DataWriter {

    private final static Logger logger = LoggerFactory.getLogger(DataWriter.class);

    public void writeToExcel(Sheet sheet, String date) {
        List<List<String>> data = getData(date);
        writeData(sheet, data);
    }

    protected abstract List<List<String>> getData(String date);


    /**
     * 从指定行，指定列开始写入Excel
     *
     * @param sheet
     * @param data
     * @param rowIndex 行下标
     * @param rowIndex 行下标
     */
    protected void writeData(Sheet sheet, List<List<String>> data, int rowIndex, int colIndex) {
        int rowNum = rowIndex;
        if (!CollectionUtils.isEmpty(data)) {
            for (int i = 0; i < data.size(); i++) {
                writeRowData(sheet, data.get(i), rowNum, colIndex);
                rowNum++;
            }
            logger.info("{} Sheet页数据写入完成，共写入{}条数据", sheet.getSheetName(), data.size());
        } else {
            logger.info("{} Sheet页无数据写入...", sheet.getSheetName());
        }
    }

    /**
     * 从指定行数据写入Excel,第0列开始写
     *
     * @param sheet
     * @param data
     */
    protected void writeData(Sheet sheet, List<List<String>> data, int rowIndex) {
        writeData(sheet, data, rowIndex, 0);
    }


    /**
     * 将数据写入Excel，默认从第0行，第0列开始写
     *
     * @param sheet
     * @param data
     */
    protected void writeData(Sheet sheet, List<List<String>> data) {
        writeData(sheet, data, 0);
    }

    /**
     * 按列写数（纵向写数据）
     *
     * @param sheet
     * @param data
     * @param rowIndex 行下标
     * @param rowIndex 行下标
     */
    protected void writeDataVertically(Sheet sheet, List<List<String>> data, int rowIndex, int colIndex) {
        if (!CollectionUtils.isEmpty(data)) {
            for (int i = 0; i < data.size(); i++) {
                writeColumnData(sheet, data.get(i), rowIndex, colIndex);
                colIndex++;
            }
            logger.info("{} Sheet页数据写入完成，共写入{}条数据", sheet.getSheetName(), data.size());
        } else {
            logger.info("{} Sheet页无数据写入...", sheet.getSheetName());
        }
    }

    /**
     * 写指定行开头数据
     *
     * @param sheet
     * @param rowData
     * @param rowIndex
     */
    protected void writeRowData(Sheet sheet, List<String> rowData, int rowIndex) {
        writeRowData(sheet, rowData, rowIndex, 0);
    }

    /**
     * 写指定行数据
     *
     * @param sheet
     * @param rowData
     * @param rowIndex
     */
    protected void writeRowData(Sheet sheet, List<String> rowData, int rowIndex, int colIndex) {
        Row excelRow = sheet.getRow(rowIndex);
        if (excelRow == null) {
            excelRow = sheet.createRow(rowIndex);
        }
        int cellNum = colIndex;
        for (int i = 0; i < rowData.size(); i++) {
            Cell cell = excelRow.createCell(cellNum);
            cell.setCellValue(rowData.get(i));
            sheet.autoSizeColumn(cellNum);
            cellNum++;
        }
    }

    /**
     * 从指定行、列开始写数据（纵向写）
     *
     * @param sheet
     * @param columnData
     * @param rowIndex
     * @param colIndex
     */
    protected void writeColumnData(Sheet sheet, List<String> columnData, int rowIndex, int colIndex) {
        for (int i = 0; i < columnData.size(); i++) {
            Row excelRow = sheet.getRow(rowIndex);
            if (excelRow == null) {
                excelRow = sheet.createRow(rowIndex);
            }
            Cell cell = excelRow.createCell(colIndex);
            cell.setCellValue(columnData.get(i));
            rowIndex++;
        }
        sheet.autoSizeColumn(colIndex);
    }

    /**
     * 写包含合并单元格的行
     *
     * @param sheet
     * @param mergedHeaders
     */
    protected void writeMergedRow(Sheet sheet, List<MergedCell> mergedHeaders) {
        writeMergedRow(sheet, mergedHeaders, 0, 0);
    }

    /**
     * 写包含合并单元格的行
     *
     * @param sheet
     * @param mergedHeaders
     * @param rowIndex
     * @param colIndex
     */
    protected void writeMergedRow(Sheet sheet, List<MergedCell> mergedHeaders, int rowIndex, int colIndex) {
        Row row0 = sheet.createRow(rowIndex);
        for (MergedCell mergedCell : mergedHeaders) {
            String headerText = mergedCell.getName();
            int colspan = mergedCell.getSpan();
            Cell cell = row0.createCell(colIndex);
            cell.setCellValue(headerText);
            // 设置水平垂直居中
            setCellStyle(sheet, cell, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            if (colspan > 1) {
                CellRangeAddress cellRange = new CellRangeAddress(0, 0, colIndex, colIndex + colspan - 1);
                sheet.addMergedRegion(cellRange);
            }
            colIndex += colspan;
        }
    }

    /**
     * 写包含合并单元格的列, 默认0行0列开始写
     *
     * @param sheet
     * @param mergedHeaders
     */
    protected void writeMergedColumn(Sheet sheet, List<MergedCell> mergedHeaders) {
        writeMergedColumn(sheet, mergedHeaders, 0, 0);
    }

    /**
     * 写包含合并单元格的列
     *
     * @param sheet
     * @param mergedHeaders
     * @param rowIndex      行下表
     * @param colIndex      列下标
     */
    protected void writeMergedColumn(Sheet sheet, List<MergedCell> mergedHeaders, int rowIndex, int colIndex) {

        for (MergedCell mergedCell : mergedHeaders) {
            String title = mergedCell.getName();
            int rowspan = mergedCell.getSpan();

            for (int i = 0; i < rowspan; i++) {
                Row row = sheet.getRow(rowIndex + i);
                if (row == null) {
                    row = sheet.createRow(rowIndex + i);
                }

                Cell cell = row.createCell(colIndex);
                cell.setCellValue(title);
                // 设置水平垂直居中
                setCellStyle(sheet, cell, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            if (rowspan > 1) {
                CellRangeAddress cellRange = new CellRangeAddress(rowIndex, rowIndex + rowspan - 1, colIndex, colIndex);
                sheet.addMergedRegion(cellRange);
            }

            rowIndex += rowspan;
        }
    }

    /**
     * 设置单元格格式
     *
     * @param sheet
     * @param cell
     * @param horizontalAlignment
     * @param verticalAlignment
     */
    private void setCellStyle(Sheet sheet, Cell cell, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        CellStyle centerAlignStyle = sheet.getWorkbook().createCellStyle();
        centerAlignStyle.setAlignment(horizontalAlignment);
        centerAlignStyle.setVerticalAlignment(verticalAlignment);
        cell.setCellStyle(centerAlignStyle);
    }

}