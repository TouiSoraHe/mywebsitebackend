package com.zzy.mywebsitebackend.Util;

import com.zzy.mywebsitebackend.Data.Mapper.BlogInfoMapper;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    BlogInfoMapper blogInfoMapper;

    public static void main(String[] args) {
    }

    @org.junit.Test
    public void dummy() {
    }
}
