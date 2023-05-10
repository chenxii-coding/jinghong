package com.chenxii.jinghong.user.service.impl;

import com.chenxii.jinghong.common.dao.CategoryDao;
import com.chenxii.jinghong.common.entity.Category;
import com.chenxii.jinghong.user.service.TestService;
import com.chenxii.jinghong.user.service.UserApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class TestServiceImplTest {

    @Autowired
    private TestService testService;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void addTest() {
        testService.addTest();
    }

    @Test
    public void daoTest() {
        List<Category> categories = categoryDao.queryAll();
        Assert.assertNotNull(categories);
        Assert.assertTrue(categories.size() > 0);
    }
}