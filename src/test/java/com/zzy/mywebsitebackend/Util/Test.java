package com.zzy.mywebsitebackend.Util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zzy.mywebsitebackend.Data.JsonObj.TagJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import com.zzy.mywebsitebackend.Service.ArchiveService;
import com.zzy.mywebsitebackend.Service.TagService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    ArchiveService archiveService;

    @Autowired
    TagService tagService;

    public static void main(String[] args) {
        TagJsonObj one = new TagJsonObj();
        one.setId(1);
        TagJsonObj two = new TagJsonObj();
        two.setId(2);
        TagJsonObj three = new TagJsonObj();
        three.setId(3);
        TagJsonObj four = new TagJsonObj();
        four.setId(3);
        TagJsonObj five = new TagJsonObj();
        five.setId(5);
        TagJsonObj[] a = new TagJsonObj[]{one,two,three};
        TagJsonObj[] b = new TagJsonObj[]{four,five};
        Set<TagJsonObj> aa = new HashSet<>(Arrays.asList(a));
        Set<TagJsonObj> bb = new HashSet<>(Arrays.asList(b));
        System.out.println(aa);
        System.out.println(bb);
        System.out.println(Sets.difference(aa,bb));
        System.out.println(Sets.difference(bb,aa));
    }

    @org.junit.Test
    public void dummy() {
        
    }
}
