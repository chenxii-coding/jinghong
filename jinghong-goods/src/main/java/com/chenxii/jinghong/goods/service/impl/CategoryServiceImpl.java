package com.chenxii.jinghong.goods.service.impl;

import com.chenxii.jinghong.common.dao.AutoNoDao;
import com.chenxii.jinghong.common.dao.CategoryDao;
import com.chenxii.jinghong.common.entity.AutoNo;
import com.chenxii.jinghong.common.entity.Category;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.goods.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AutoNoDao autoNoDao;

    public Response<Void> addCategory(Category category) {
        // 新增
        String parentCategoryNo = category.getParentCategory();
        if (StringUtils.isNotBlank(parentCategoryNo)) {
            Category parentCategory = categoryDao.queryByNo(parentCategoryNo);
            if (parentCategory == null) {
                return ResponseUtil.failed("父级目录不存在，请先添加父级目录");
            }
        }
        AutoNo autoNo = new AutoNo("category");
        autoNoDao.updateNo(autoNo);
        category.setCategoryNo("CATEGORY_" + StringUtils.leftPad(autoNo.getNo() + "", 4, '0'));
        categoryDao.insert(category);
        return ResponseUtil.success();
    }

    public Response<Void> updateCategory(Category category) {
        categoryDao.update(category);
        return ResponseUtil.success();
    }

    public Response<Void> deleteCategory(String categoryNo) {
        categoryDao.deleteByCategoryNo(categoryNo);
        return ResponseUtil.success();
    }

    public Response<List<Category>> queryCategory() {
        return ResponseUtil.success(categoryDao.queryAll());
    }

    public Response<Category> queryCategory(String categoryNo) {
        return ResponseUtil.success(categoryDao.queryByNo(categoryNo));
    }
}
