package com.xgx.springboot.mapper;

import com.xgx.springboot.domain.DbBasicData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DB插片数据明细Mapper接口
 *
 * @author ai
 * @date 2021-11-30
 */
@Mapper
public interface DbBasicDataMapper {

    List<DbBasicData> selectYesterdayDbMetrics(@Param("tableName") String tableName);
}
