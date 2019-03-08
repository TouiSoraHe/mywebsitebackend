
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Archive;
import com.zzy.mywebsitebackend.Data.Mapper.ArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveService {

    @Autowired
    private ArchiveMapper mapper;

    public int insert(Archive var0){
        int ret = 0;
        try{
            ret = mapper.insert(var0);
        } catch (org.springframework.dao.DuplicateKeyException e){

        }
        return ret;
    }

    public Archive selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

//    public int updateByPrimaryKey(Archive var0){
//        return mapper.updateByPrimaryKey(var0);
//    }

    public int updateByPrimaryKeySelective(Archive var0){
        return mapper.updateByPrimaryKeySelective(var0);
    }

//    public int insertSelective(Archive var0){
//        return mapper.insertSelective(var0);
//    }

    public int insertAll(List<Archive> archives) {
        int ret = 0;
        try{
            ret = mapper.insertAll(archives);
        } catch (org.springframework.dao.DuplicateKeyException e){

        }
        return ret;
    }

    public List<Archive> selectByBlogInfoId(Integer id) {
        return mapper.selectByBlogInfoId(id);
    }

    public int deleteAll(ArrayList<Archive> archives) {
        return mapper.deleteAll(archives);
    }

    public List<Archive> selectByTagId(Integer id) {
        return mapper.selectByTagId(id);
    }
}
