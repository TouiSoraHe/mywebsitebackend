package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Img;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImgServiceTest {

    @Autowired
    private ImgService imgService;

    @Test
    public void Test0001()
    {
        List<Img> imgs = new ArrayList<>();
        imgs.add(new Img());
        imgs.add(new Img());
        imgs.add(new Img());
        imgs.add(new Img());
        imgService.insertAll(imgs);
        for (Img img : imgs) {
            Assert.assertNotNull(imgService.selectByPrimaryKey(img.getId()));
            imgService.deleteByPrimaryKey(img.getId());
            Assert.assertNull(imgService.selectByPrimaryKey(img.getId()));
        }
    }

}
