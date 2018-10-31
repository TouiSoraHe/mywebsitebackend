package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTest {
    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    TagService tagService;

    @Test
    public void insterTest(){
    }
}
