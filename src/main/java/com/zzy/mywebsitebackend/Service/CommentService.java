
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Comment;
import com.zzy.mywebsitebackend.Data.Mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper mapper;

    public int insert(Comment var0){
        return mapper.insert(var0);
    }

    public int updateByPrimaryKey(Comment var0){
        return mapper.updateByPrimaryKey(var0);
    }

    public Comment selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public List selectAll(){
        return mapper.selectAll();
    }

}
