
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Tag;
import com.zzy.mywebsitebackend.Data.Mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper mapper;

    public int insert(Tag var0){
        return mapper.insert(var0);
    }

    public int updateByPrimaryKey(Tag var0){
        return mapper.updateByPrimaryKey(var0);
    }

    public Tag selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public List selectAll(){
        return mapper.selectAll();
    }

}
