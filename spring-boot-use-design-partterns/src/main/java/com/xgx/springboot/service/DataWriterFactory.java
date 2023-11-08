package com.xgx.springboot.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xu.gx
 * 数据写入工厂
 */
@Service
public class DataWriterFactory {

    @Resource(name = "hostCheckDataWriter")
    private DataWriter hostCheckDataWriter;
    @Resource(name = "databaseCheckDataWriter")
    private DataWriter databaseCheckDataWriter;
    @Resource(name = "databaseBusinessDataCheckDataWriter")
    private DataWriter databaseBusinessDataCheckDataWriter;

    @Resource(name = "obsCheckDataWriter")
    private DataWriter obsCheckDataWriter;
    @Resource(name = "portalCheckDataWriter")
    private DataWriter portalCheckDataWriter;

    @Resource(name = "aiLmCheckDataWriter")
    private DataWriter aiLmCheckDataWriter;

    @Resource(name = "radiusCheckDataWriter")
    private DataWriter radiusCheckDataWriter;

    @Resource(name = "aiMdbCheckDataWriter")
    private DataWriter aiMdbCheckDataWriter;

    @Resource(name = "cidMdbCheckDataWriter")
    private DataWriter cidMdbCheckDataWriter;
    
    public DataWriter createDataWriter(String sheetName) {
        DataWriter dataWriter = null;
        switch (sheetName) {
            case "主机巡检":
                dataWriter = hostCheckDataWriter;
                break;
            case "数据库巡检":
                dataWriter = databaseCheckDataWriter;
                break;
            case "数据库业务数据巡检":
                dataWriter = databaseBusinessDataCheckDataWriter;
                break;
            case "OBS&接口服务巡检":
                dataWriter = obsCheckDataWriter;
                break;
            case "Portal服务巡检":
                dataWriter = portalCheckDataWriter;
                break;
            case "AILM服务巡检":
                dataWriter = aiLmCheckDataWriter;
                break;
            case "Radius服务巡检":
                dataWriter = radiusCheckDataWriter;
                break;
            case "Aimdb服务巡检":
                dataWriter = aiMdbCheckDataWriter;
                break;
            case "cidmdb服务巡检":
                dataWriter = cidMdbCheckDataWriter;
                break;
            default:
                break;
        }
        return dataWriter;
    }
}