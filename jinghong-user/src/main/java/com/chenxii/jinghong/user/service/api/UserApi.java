package com.chenxii.jinghong.user.service.api;

import com.chenxii.jinghong.common.dao.CategoryDao;
import com.chenxii.jinghong.common.entity.Category;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jinghong/api")
public class UserApi {

    @Autowired
    @Lazy
    private CategoryDao categoryDao;

    @GetMapping("/goods/category")
    public Response<List<Category>> queryCategory() {
        return ResponseUtil.success(categoryDao.queryAll());
    }
}
