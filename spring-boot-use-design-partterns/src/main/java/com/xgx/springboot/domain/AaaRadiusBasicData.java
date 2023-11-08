package com.xgx.springboot.domain;

import java.util.Date;

/**
 * radiusdata对象 aaaradiusbasicdata_20211221
 * 
 * @author ai
 * @date 2021-12-21
 */
public class AaaRadiusBasicData {
    private static final long serialVersionUID = 1L;
    /** 设备名称 */
    private String deviceName;
    /** 设备IP */
    private String deviceIp;

    /** Bras IP */
    private String brasIp;

    private String targetName;

    /** 指标ID */
    private String targetId;

    /** 本次上报值 */
    private Double targetValue;
    private String unit;

    /** 告警类型:0Recover,1EVENT,2ALERT,3SECONDARY,4PRIMARY,5CRITICAL */
//    @Excel(name = "告警级别")
    private String alarmLevel;

    /** 采集时间 */
    private Date collectTime;

    /** 入库时间 */
    private Date stampTime;


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /** 地市 */
    //@Excel(name = "地市")
    private Long nodeId;

    public void setAlarmLevel(String alarmLevel)
    {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmLevel() 
    {
        return alarmLevel;
    }
    public void setTargetValue(Double targetValue) 
    {
        this.targetValue = targetValue;
    }

    public Double getTargetValue() 
    {
        return targetValue;
    }
    public void setTargetId(String targetId) 
    {
        this.targetId = targetId;
    }

    public String getTargetId() 
    {
        return targetId;
    }
    public void setNodeId(Long nodeId) 
    {
        this.nodeId = nodeId;
    }

    public Long getNodeId() 
    {
        return nodeId;
    }
    public void setBrasIp(String brasIp) 
    {
        this.brasIp = brasIp;
    }

    public String getBrasIp() 
    {
        return brasIp;
    }
    public void setCollectTime(Date collectTime) 
    {
        this.collectTime = collectTime;
    }

    public Date getCollectTime() 
    {
        return collectTime;
    }
    public void setStampTime(Date stampTime) 
    {
        this.stampTime = stampTime;
    }

    public Date getStampTime() 
    {
        return stampTime;
    }
    public void setDeviceIp(String deviceIp) 
    {
        this.deviceIp = deviceIp;
    }

    public String getDeviceIp() 
    {
        return deviceIp;
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
