package com.xgx.springboot.mapper;


import com.xgx.springboot.domain.DeviceBasicdata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备监控Mapper接口
 *
 * @author gaohb
 * @date 2021-11-25
 */
@Mapper
public interface DeviceBasicdataMapper {
    /**
     * 主机巡检
     * 获取每个 device_ip 最新的特定 target_id 值以及对应的 target_value 值。
     * 查询结果样例：
     * device_ip	name	target_id	target_value
     * 132.77.94.8	BIAS-GJI7-MDB5280-1	DeviceCPUUtilization	2.87
     * 132.77.94.8	BIAS-GJI7-MDB5280-1	DeviceMemoryUtilization	7.43
     * 132.77.94.8	BIAS-GJI7-MDB5280-1	DeviceProcessesNum	344
     * 132.77.94.9	BIAS-GJI7-MDB5280-2	DeviceCPUUtilization	2.97
     * 132.77.94.9	BIAS-GJI7-MDB5280-2	DeviceMemoryUtilization	7.42
     * 132.77.94.9	BIAS-GJI7-MDB5280-2	DeviceProcessesNum	340
     */
    List<DeviceBasicdata> selectYesterdayHostMetrics(@Param("tableName") String tableName);
}
