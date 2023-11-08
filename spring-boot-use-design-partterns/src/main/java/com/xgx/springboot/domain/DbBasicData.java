package com.xgx.springboot.domain;

import java.util.Date;

public class DbBasicData {
    private static final long serialVersionUID = 1L;

    /**
     * 指标值
     */
    private float targetValue;

    /**
     * 指标id
     */
    private String targetId;

    /**
     * 数据库端口号
     */
    private Long dbPort;

    /**
     * 收集时间
     */
    private Date collectTime;

    /**
     * 时间戳
     */
    private Date stampTime;

    /**
     * 设备ip
     */
    private String deviceIp;

    private String deviceName;

    private String alarmLevel;
    private String targetName;
    private String unit;
    private String alarmLocateinfo;

    public String getAlarmLocateinfo() {
        return alarmLocateinfo;
    }

    public void setAlarmLocateinfo(String alarmLocateinfo) {
        this.alarmLocateinfo = alarmLocateinfo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setTargetValue(float targetValue) {
        this.targetValue = targetValue;
    }

    public float getTargetValue() {
        return targetValue;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setDbPort(Long dbPort) {
        this.dbPort = dbPort;
    }

    public Long getDbPort() {
        return dbPort;
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

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}
