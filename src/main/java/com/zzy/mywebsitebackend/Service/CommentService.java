
package com.zzy.mywebsitebackend.Service;

import com.google.common.base.Strings;
import com.zzy.mywebsitebackend.Data.Entity.Comment;
import com.zzy.mywebsitebackend.Data.Entity.User;
import com.zzy.mywebsitebackend.Data.Mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper mapper;

    @Autowired
    private UserService userService;

    public int insert(Comment comment){
        comment.setTime(new Date());
        comment.setUserId(comment.getUser().getId());
        if(comment.getDeleted() == null){
            comment.setDeleted(false);
        }
        userService.insert(comment.getUser());
        return mapper.insert(comment);
    }

    public Comment selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

//    public int updateByPrimaryKey(Comment var0){
//        return mapper.updateByPrimaryKey(var0);
//    }

    public int updateByPrimaryKeySelective(Comment comment){
        comment.setUserId(null);
        comment.setTime(null);
        comment.setBlogId(null);
        comment.setParentId(null);
        User user = comment.getUser();
        user.setId(selectByPrimaryKey(comment.getId()).getUser().getId());
        userService.updateByPrimaryKeySelective(user);
        return mapper.updateByPrimaryKeySelective(comment);
    }

    public List<Comment> selectAll() {
        return mapper.selectAll();
    }

    public List<Comment> selectByBlogIDWithRootNode(Integer blogID, String sort, String order, Integer offset, Integer count) {
        return mapper.selectByBlogIDWithRootNode(blogID,getSort(sort),getOrder(order),offset,count);
    }

    private String getSort(String sort){
        if (Strings.isNullOrEmpty(sort)) {
            sort = "time";
        }
        return sort;
    }

    private String getOrder(String order){
        if(Strings.isNullOrEmpty(order)){
            order = "asc";
        }
        else{
            order = order.toLowerCase();
        }
        if (!"desc".equals(order)) {
            order = "asc";
        }
        return order;
    }

    public int selectByBlogIDRootCount(Integer blogID) {
        return mapper.selectByBlogIDRootCount(blogID);
    }

    public List<Comment> selectByParentIDs(List<Integer> parentIds) {
        return mapper.selectByParentIDs(parentIds);
    }

//    public int insertSelective(Comment var0){
//        return mapper.insertSelective(var0);
//    }

}
