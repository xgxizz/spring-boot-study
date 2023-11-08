package com.xgx.springboot.domain;

import java.util.Date;

public class DeviceBasicdata {
    private static final long serialVersionUID = 1L;
    /**
     * 设备IP
     */
    private String deviceIp;
    private String deviceName;
    private String targetName;
    private Double targetValue;
    private String unit;

    /**
     * 指标ID
     */
    private String targetId;
    /**
     * 指标值
     */

//    @Excel(name = "告警级别")
    private String alarmLevel;
    private String alarmLocateinfo;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 采集时间
     */
    private Date collectTime;

    /**
     * 入库时间
     */
    private Date stampTime;


    private int queryType;
    private Date queryStartTime;
    private Date queryEndTime;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAlarmLocateinfo() {
        return alarmLocateinfo;
    }

    public void setAlarmLocateinfo(String alarmLocateinfo) {
        this.alarmLocateinfo = alarmLocateinfo;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public Date getQueryStartTime() {
        return queryStartTime;
    }

    public void setQueryStartTime(Date queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public Date getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(Date queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }

    public Double getTargetValue() {
        return targetValue;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setStampTime(Date stampTime) {
        this.stampTime = stampTime;
    }

    public Date getStampTime() {
        return stampTime;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }
}
