package com.zzy.mywebsitebackend.Util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {

    @Test
    public void ObjectMutualTransferByte(){
        String test = "hello world";
        byte[] bytes = Util.ObjectToByte(test);
        String result = Util.ByteToObject(bytes);
        Assert.assertEquals(test,result);
    }
}
