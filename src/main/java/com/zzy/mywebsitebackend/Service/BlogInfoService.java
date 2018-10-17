
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;
import com.zzy.mywebsitebackend.Data.Mapper.BlogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogInfoService {

    @Autowired
    private BlogInfoMapper mapper;

    public int insert(BlogInfo var0){
        return mapper.insert(var0);
    }

    public int updateByPrimaryKey(BlogInfo var0){
        return mapper.updateByPrimaryKey(var0);
    }

    public BlogInfo selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public List selectAll(){
        return mapper.selectAll();
    }

}
