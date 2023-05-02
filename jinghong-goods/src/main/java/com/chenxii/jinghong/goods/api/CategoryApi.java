package com.chenxii.jinghong.goods.api;

import com.chenxii.jinghong.common.entity.Category;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jinghong")
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public Response<List<Category>> queryCategory() {
        return categoryService.queryCategory();
    }

    @GetMapping("/category/{categoryNo}")
    public Response<Category> queryCategory(@PathVariable String categoryNo) {
        return categoryService.queryCategory(categoryNo);
    }
}
