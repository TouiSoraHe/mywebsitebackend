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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagServiceTest {

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private ImgService imgService;

    private static List<Tag> tags = new ArrayList<>(
            Arrays.asList(
                    new Tag("test tag1")
                    , new Tag("test tag2")
                    , new Tag("test tag3")
                    , new Tag("test tag4")));

    private static Tag tag = new Tag("test tag5");

    private static Blog blog1 = new Blog();
    private static Blog blog2 = new Blog();
    private static Blog blog3 = new Blog();

    private static boolean equals(List<Tag> tags1, List<Tag> tags2){
        if(tags1.size() != tags2.size()) return false;
        Comparator<Tag> comparator = new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
                return o1.getId()-o2.getId();
            }
        };
        tags1.sort(comparator);
        tags2.sort(comparator);
        for (int i = 0; i < tags1.size(); i++) {
            if(!equals(tags1.get(i),tags2.get(i))) return false;
        }
        return true;
    }

    private static boolean equals(Tag tag1, Tag tag2){
        System.out.println("\n"+tag1+"\n"+tag2);
        if(!tag1.getId().equals(tag2.getId())){
            System.out.println("tag1.getId() != tag2.getId()");
            return false;
        }
        if(!tag1.getTagName().equals(tag2.getTagName())){
            System.out.println("!tag1.getTagName().equals(tag2.getTagName())");
            return false;
        }
        if(tag1.getImgId() !=null && tag2.getImgId()!=null && !tag1.getImgId().equals(tag2.getImgId())) {
            System.out.println("tag1.getImgId() !=null && tag2.getImgId()!=null && !tag1.getImgId().equals(tag2.getImgId())");
            return false;
        }
        if(tag1.getTagImg().getLarge() != null && tag2.getTagImg().getLarge()!=null
            && !tag1.getTagImg().getLarge().equals(tag2.getTagImg().getLarge())){
            System.out.println("getLarge");
            return  false;
        }
        if(tag1.getTagImg().getMedium() != null && tag2.getTagImg().getMedium()!=null
                && !tag1.getTagImg().getMedium().equals(tag2.getTagImg().getMedium())){
            System.out.println("getMedium");
            return  false;
        }
        if(tag1.getTagImg().getSmall() != null && tag2.getTagImg().getSmall()!=null
                && !tag1.getTagImg().getSmall().equals(tag2.getTagImg().getSmall())){
            System.out.println("getSmall");
            return  false;
        }
        if(!tag1.getBlogInfoIDs().equals(tag2.getBlogInfoIDs())){
            return false;
        }
        return true;
    }

    private static List<String> getTagName(List<Tag> tagList){
        List<String> ret = new ArrayList<>();
        for (Tag item:tagList) {
            ret.add(item.getTagName());
        }
        return ret;
    }

    @Test
    public void Test001() {
        blog1.setContent("test content");
        blog1.getBlogInfo().setTitle("test title");
        blog2.setContent("test content");
        blog2.getBlogInfo().setTitle("test title");
        blogService.insert(blog1);
        blogService.insert(blog2);

        for (Tag tag1 : tags) {
            tag1.getBlogInfoIDs().add(blog1.getBlogInfo().getId());
            tag1.getBlogInfoIDs().add(blog2.getBlogInfo().getId());
        }
        tag.getBlogInfoIDs().add(blog1.getBlogInfo().getId());
        tag.getBlogInfoIDs().add(blog2.getBlogInfo().getId());

        tagService.insertAll(tags);
        tagService.insert(tag);
    }

    @Test
    public void Test002() {
        Assert.assertTrue(equals(tags,tagService.selectByTagName(getTagName(tags))));
        Assert.assertTrue(equals(tag,tagService.selectByPrimaryKey(tag.getId())));
    }

    @Test
    public void Test003() {
        tag.setImgId(-1);
        tag.setTagName(tag.getTagName()+" test update");
        tag.getTagImg().setMedium("test update medium img");
        tag.getTagImg().setLarge("test update large img");
        tag.getTagImg().setSmall("test update small img");
        tagService.updateByPrimaryKeySelective(tag);
        Tag newTag = tagService.selectByPrimaryKey(tag.getId());
        System.out.println(newTag);
        System.out.println(tag);

        Assert.assertTrue(tag.getTagName().equals(newTag.getTagName()));
        Assert.assertTrue(tag.getTagImg().getLarge().equals(newTag.getTagImg().getLarge()));
        Assert.assertTrue(tag.getTagImg().getMedium().equals(newTag.getTagImg().getMedium()));
        Assert.assertTrue(tag.getTagImg().getSmall().equals(newTag.getTagImg().getSmall()));
        Assert.assertFalse(newTag.getImgId().equals(-1));
    }

    @Test
    public void Test004(){
        blog3.setContent("test content");
        blog3.getBlogInfo().setTitle("test title");
        blogService.insert(blog3);

        tag.getBlogInfoIDs().remove(0);
        tag.getBlogInfoIDs().add(blog3.getBlogInfo().getId());

        tagService.updateByPrimaryKeySelective(tag);
        Tag newTag = tagService.selectByPrimaryKey(tag.getId());
        System.out.println(newTag);
        System.out.println(tag);

        Assert.assertTrue(tag.getBlogInfoIDs().equals(newTag.getBlogInfoIDs()));
    }

    @Test
    public void Test005() {
        for (Tag oldTag:tags){
            Tag newTag = tagService.selectByPrimaryKey(oldTag.getId());
            Assert.assertNotNull(tagService.selectByPrimaryKey(newTag.getId()));
            Assert.assertNotNull(imgService.selectByPrimaryKey(newTag.getImgId()));
            tagService.deleteByPrimaryKey(newTag.getId());
            Assert.assertNull(tagService.selectByPrimaryKey(newTag.getId()));
            Assert.assertNull(imgService.selectByPrimaryKey(newTag.getImgId()));
        }
        {
            Tag newTag = tagService.selectByPrimaryKey(tag.getId());
            Assert.assertNotNull(tagService.selectByPrimaryKey(newTag.getId()));
            Assert.assertNotNull(imgService.selectByPrimaryKey(newTag.getImgId()));
            tagService.deleteByPrimaryKey(newTag.getId());
            Assert.assertNull(tagService.selectByPrimaryKey(newTag.getId()));
            Assert.assertNull(imgService.selectByPrimaryKey(newTag.getImgId()));
        }
        {
            blogService.deleteByPrimaryKey(blog1.getId());
            blogService.deleteByPrimaryKey(blog2.getId());
            blogService.deleteByPrimaryKey(blog3.getId());
            Assert.assertNull(blogService.selectByPrimaryKey(blog1.getId()));
            Assert.assertNull(blogService.selectByPrimaryKey(blog2.getId()));
            Assert.assertNull(blogService.selectByPrimaryKey(blog3.getId()));
        }
    }
}
