
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper mapper;

    public int insert(Blog var0){
        return mapper.insert(var0);
    }

    public int updateByPrimaryKey(Blog var0){
        return mapper.updateByPrimaryKey(var0);
    }

    public Blog selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public List selectAll(){
        return mapper.selectAll();
    }

}
