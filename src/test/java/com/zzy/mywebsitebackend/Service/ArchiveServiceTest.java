package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.JsonObj.ArchiveJsonObj;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArchiveServiceTest {
    @Autowired
    ArchiveService archiveService;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogInfoService blogInfoService;

    @Autowired
    TagService tagService;

    @Test
    public void intserTest(){
        ArchiveJsonObj archiveJsonObj = new ArchiveJsonObj();
        archiveJsonObj.setBlog_info_id(-1);
        archiveJsonObj.setTag_id(-1);
        Assert.assertEquals(archiveService.insert(archiveJsonObj),0);
    }
}
