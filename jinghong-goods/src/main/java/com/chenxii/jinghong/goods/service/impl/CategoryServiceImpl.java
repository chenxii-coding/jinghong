package com.chenxii.jinghong.goods.service.impl;

import com.chenxii.jinghong.common.dao.CategoryDao;
import com.chenxii.jinghong.common.entity.Category;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public Response<Void> addCategory(Category category) {
        String parentCategoryNo = category.getParentCategory();
        Category parentCategory = categoryDao.queryByNo(parentCategoryNo);
        if (parentCategory == null) {
            return ResponseUtil.failed("父级目录不存在，请先添加父级目录");
        } else {
            categoryDao.insert(category);
            return ResponseUtil.success();
        }
    }

    public Response<Void> updateCategory(Category category) {
        return null;
    }

    public Response<Void> deleteCategory(String categoryNo) {
        return null;
    }

    public Response<List<Category>> queryCategory() {
        return ResponseUtil.success(categoryDao.queryAll());
    }

    public Response<Category> queryCategory(String categoryNo) {
        return ResponseUtil.success(categoryDao.queryByNo(categoryNo));
    }
}
