package com.xgx.springboot.domain;

/**
 * @Description: 合并单元格定义
 * @Author: xu.gx
 * @Date: 2023/11/7 19:35
 **/
public class MergedCell {
    /**
     * 单元格名称
     */
    private String name;
    /**
     * 合并单元格占用的单位单元格数量
     */
    private int span;

    public MergedCell(String name, int span) {
        this.name = name;
        this.span = span;
    }

    public String getName() {
        return name;
    }

    public int getSpan() {
        return span;
    }
}
