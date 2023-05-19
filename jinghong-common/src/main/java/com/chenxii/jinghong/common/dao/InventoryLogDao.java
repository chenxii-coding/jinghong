package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.InventoryLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【inventory_log】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.InventoryLog
 */
@Mapper
public interface InventoryLogDao {

    int insert(@Param("inventoryLogList") List<InventoryLog> inventoryLogList);

}
