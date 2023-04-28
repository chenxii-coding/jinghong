package com.chenxii.jinghong.common.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDaoTest {

    @Autowired
    private TestDao testDao;

    @org.junit.Test
    public void insert() {
        Test test = new Test();
        test.setName("u2");
        test.setComment("好的");
        testDao.insert(test);
    }
}