package com.chenxii.jinghong.goods.service;

import com.chenxii.jinghong.common.entity.Category;
import com.chenxii.jinghong.common.entity.Response;

import java.util.List;

public interface CategoryService {

    Response<Void> addCategory(Category category);

    Response<Void> updateCategory(Category category);

    Response<Void> deleteCategory(String categoryNo);

    Response<List<Category>> queryCategory();

    Response<Category> queryCategory(String categoryNo);
}
