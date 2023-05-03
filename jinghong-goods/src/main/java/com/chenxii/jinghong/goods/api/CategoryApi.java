package com.chenxii.jinghong.goods.api;

import com.chenxii.jinghong.common.entity.Category;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jinghong/api")
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

    @PutMapping("/category")
    public Response<Void> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PostMapping("/category")
    public Response<Void> updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/category/{categoryNo}")
    public Response<Void> delCategory(@PathVariable String categoryNo) {
        return categoryService.deleteCategory(categoryNo);
    }
}
