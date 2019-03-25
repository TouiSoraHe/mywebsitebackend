package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Data.Entity.Tag;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlogServiceTest {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogInfoService blogInfoService;

    @Autowired
    ImgService imgService;

    private static Blog blog = new Blog();

    @Test
    public void Test001()
    {
        blog.setContent("test content");
        blog.getBlogInfo().setTitle("test title");
        Tag tag1 = new Tag();
        tag1.setTagName("Test Tag1");
        Tag tag2 = new Tag();
        tag2.setTagName("Test Tag2");
        blog.getBlogInfo().getTags().add(tag1);
        blog.getBlogInfo().getTags().add(tag2);
        blogService.insert(blog);
        System.out.println(blog);
    }

    @Test
    public void Test002()
    {
        Blog newBlog = blogService.selectByPrimaryKey(blog.getId());
        System.out.println(newBlog);
        System.out.println(blog);
//        Assert.assertTrue(newBlog.equals(blog));
    }

    @Test
    public void Test003()
    {
        blog.setContent("test update content");
        blog.getBlogInfo().setTitle("test update title");
        blog.getBlogInfo().getBgImg().setMedium("test update medium img");
        blog.getBlogInfo().getBgImg().setLarge("test update large img");
        blog.getBlogInfo().getBgImg().setSmall("test update small img");

        Tag tag3 = new Tag();
        tag3.setTagName("Test Tag3");
        blog.getBlogInfo().getTags().add(tag3);
        blog.getBlogInfo().getTags().remove(0);

        blog.getBlogInfo().setImgId(-1);
        blog.getBlogInfo().setBlogId(-1);
        blog.getBlogInfo().setLastModified(new Date(0));
        blog.getBlogInfo().setTime(new Date(0));
        blog.getBlogInfo().setWords(-1);
        blog.getBlogInfo().setViews(-1);
        blogService.updateByPrimaryKeySelective(blog);
        Blog newBlog = blogService.selectByPrimaryKey(blog.getId());
        System.out.println(newBlog);
        System.out.println(blog);

        Assert.assertTrue(newBlog.getBlogInfo().getTitle().equals(blog.getBlogInfo().getTitle()));
        Assert.assertTrue(newBlog.getContent().equals(blog.getContent()));
        Assert.assertTrue(newBlog.getBlogInfo().getBgImg().equals(blog.getBlogInfo().getBgImg()));
//        Assert.assertTrue(newBlog.getBlogInfo().getTags().equals(blog.getBlogInfo().getTags()));

        Assert.assertFalse(newBlog.getBlogInfo().getTime().equals(new Date(0)));
        Assert.assertFalse(newBlog.getBlogInfo().getLastModified().equals(new Date(0)));
        Assert.assertFalse(newBlog.getBlogInfo().getWords().equals(-1));
        Assert.assertFalse(newBlog.getBlogInfo().getImgId().equals(-1));
    }

    @Test
    public void Test004()
    {
        blog = blogService.selectByPrimaryKey(blog.getId());
        blogService.deleteByPrimaryKey(blog.getId());
        Assert.assertNull(blogService.selectByPrimaryKey(blog.getId()));
        Assert.assertNull(blogInfoService.selectByPrimaryKey(blog.getBlogInfo().getId()));
        Assert.assertNull(imgService.selectByPrimaryKey(blog.getBlogInfo().getImgId()));
    }

    @Test
    public void Test005()
    {
        Blog blog = new Blog();
        blog.setContent("test Transactional");
        try{
            blogService.insert(blog);
        }
        catch (org.springframework.dao.DataIntegrityViolationException ex){}
        System.out.println(blog);
        Assert.assertNull(blogService.selectByPrimaryKey(blog.getId()));
    }

}
