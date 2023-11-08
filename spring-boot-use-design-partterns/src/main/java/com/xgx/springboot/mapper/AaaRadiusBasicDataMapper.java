package com.xgx.springboot.mapper;

import com.xgx.springboot.domain.AaaRadiusBasicData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * radiusdataMapper接口
 *
 * @author ai
 * @date 2021-12-21
 */
@Mapper
public interface AaaRadiusBasicDataMapper {
    List<AaaRadiusBasicData> selectYesterdayRadiusMetrics(@Param("tableName") String tableName);
}
