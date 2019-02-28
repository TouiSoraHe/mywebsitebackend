package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Comment;
import com.zzy.mywebsitebackend.Data.Entity.User;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    private static Comment comment = new Comment();

    @Test
    public void Test001(){
        comment.setContent("test content");
        comment.setParentId(-1);
        comment.setBlogId(-1);
        User user = new User();
        user.setId("12345678901234567890123456789012");
        comment.setUser(user);

        commentService.insert(comment);
    }

    @Test
    public void Test002(){
        comment.equals(commentService.selectByPrimaryKey(comment.getBlogId()));
    }

    @Test
    public void Test003(){
        comment.setContent("update comment content test");
        comment.getUser().setUserName("name update test");
        comment.getUser().setEmail("email update");

        comment.setBlogId(-100);
        comment.setParentId(-100);
        comment.setUserId("zxcvbnm");
        comment.getUser().setAvatarImgId(-100);

        commentService.updateByPrimaryKeySelective(comment);

        Comment newComment = commentService.selectByPrimaryKey(comment.getId());

        Assert.assertTrue(newComment.getContent().equals(comment.getContent()));
        Assert.assertTrue(newComment.getUser().getUserName().equals(comment.getUser().getUserName()));
        Assert.assertTrue(newComment.getUser().getEmail().equals(comment.getUser().getEmail()));

        Assert.assertFalse(newComment.getBlogId().equals(comment.getBlogId()));
        Assert.assertFalse(newComment.getParentId().equals(comment.getParentId()));
        Assert.assertFalse(newComment.getUserId().equals(comment.getUserId()));
        Assert.assertFalse(newComment.getUser().getAvatarImgId().equals(comment.getUser().getAvatarImgId()));
    }

    @Test
    public void Test005(){
        Assert.assertNotNull(commentService.selectByPrimaryKey(comment.getId()));
        commentService.deleteByPrimaryKey(comment.getId());
        Assert.assertNull(commentService.selectByPrimaryKey(comment.getId()));
    }
}
