
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Comment;
import com.zzy.mywebsitebackend.Data.Entity.User;
import com.zzy.mywebsitebackend.Data.JsonObj.CommentJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.UserJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentMapper mapper;

    @Autowired
    UserService userService;

    public int insert(CommentJsonObj commentJsonObj){
        Comment comment = commentJsonObj.toComment();
        int ret = mapper.insert(comment);
        if(ret == 1){
            commentJsonObj.setWithComment(mapper.selectByPrimaryKey(comment.getId()));
        }

        //将评论中的用户信息insert或update到User表
        UserJsonObj userJsonObj = commentJsonObj.getUser();
        System.out.println(userJsonObj);
        UserJsonObj oldUserJsonObj = userService.selectByPrimaryKey(userJsonObj.getId());
        if(oldUserJsonObj == null){
            if(userService.insert(userJsonObj) == 0){
                log.error("严重错误,添加User失败!"+commentJsonObj.toString()+","+userJsonObj.toString());
            }
        }else if(!userJsonObj.equals(oldUserJsonObj)){
            if(userService.updateByPrimaryKey(userJsonObj) == 0){
                log.error("严重错误,更新User失败!"+commentJsonObj.toString()+","+userJsonObj.toString());
            }
        }
        commentJsonObj.setUser(userJsonObj);
        return ret;
    }

    public int updateByPrimaryKey(CommentJsonObj commentJsonObj){
        Comment comment = commentJsonObj.toComment();
        int ret = mapper.updateByPrimaryKey(comment);
        if(ret == 1){
            commentJsonObj.setWithComment(mapper.selectByPrimaryKey(comment.getId()));
        }

        //将评论中的用户信息insert或update到User表
        UserJsonObj userJsonObj = commentJsonObj.getUser();
        UserJsonObj oldUserJsonObj = userService.selectByPrimaryKey(userJsonObj.getId());
        if(oldUserJsonObj == null){
            if(userService.insert(userJsonObj) == 0){
                log.error("严重错误,添加User失败!"+commentJsonObj.toString()+","+userJsonObj.toString());
            }
        }else if(!oldUserJsonObj.equals(userJsonObj)){
            if(userService.updateByPrimaryKey(userJsonObj) == 0){
                log.error("严重错误,更新User失败!"+commentJsonObj.toString()+","+userJsonObj.toString());
            }
        }
        commentJsonObj.setUser(userJsonObj);
        return ret;
    }

    public CommentJsonObj selectByPrimaryKey(Integer commentId){
        Comment comment = mapper.selectByPrimaryKey(commentId);
        if(comment == null) return null;
        CommentJsonObj commentJsonObj = new CommentJsonObj(comment);
        UserJsonObj userJsonObj = userService.selectByPrimaryKey(comment.getUser_id());
        if(userJsonObj == null){
            log.error("严重错误,获取User失败!"+commentJsonObj.toString()+",UserID"+comment.getUser_id());
        }
        commentJsonObj.setUser(userJsonObj);
        return commentJsonObj;
    }

    public int deleteByPrimaryKey(Integer commentId){
        return mapper.deleteByPrimaryKey(commentId);
    }

    public List<CommentJsonObj> selectAll(){
        List<Comment> comments = mapper.selectAll();
        if(comments.size() == 0 ) return new ArrayList<>();
        Set<String> userIds = new HashSet<>();
        for (Comment comment:comments){
            userIds.add(comment.getUser_id());
        }
        Map<String,UserJsonObj> userID2UserJsonObj = new HashMap<>();
        for (UserJsonObj userJsonObj:userService.selectByPrimaryKeyList(userIds)){
            userID2UserJsonObj.put(userJsonObj.getId(),userJsonObj);
        }
        List<CommentJsonObj> commentJsonObjs = new ArrayList<>();
        for (Comment comment:comments){
            CommentJsonObj commentJsonObj = new CommentJsonObj(comment);
            if(userID2UserJsonObj.containsKey(comment.getUser_id())){
                commentJsonObj.setUser(userID2UserJsonObj.get(comment.getUser_id()));
            }
            commentJsonObjs.add(commentJsonObj);
        }
        return commentJsonObjs;
    }

}
