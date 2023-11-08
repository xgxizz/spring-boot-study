package com.xgx.springboot.mapper;

import com.xgx.springboot.domain.AaaObsBasicData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * obsMapper接口
 *
 * @author ai
 * @date 2021-12-22
 */
@Mapper
public interface AaaObsBasicDataMapper {
    List<String> selectObsHostList(@Param("tableName") String tableName);
}
