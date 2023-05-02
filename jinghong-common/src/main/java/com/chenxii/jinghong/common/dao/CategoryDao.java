package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【category】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.Category
 */
@Mapper
public interface CategoryDao {

    int insert(Category category);

    int update(Category category);

    Category queryByNo(@Param("categoryNo") String categoryNo);

    List<Category> queryAll();

}
