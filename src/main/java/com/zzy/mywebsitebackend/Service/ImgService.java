
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Img;
import com.zzy.mywebsitebackend.Data.Mapper.ImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService {

    @Autowired
    private ImgMapper mapper;

    public int insert(Img var0){
        return mapper.insert(var0);
    }

    public int insertAll(List<Img> imgs) { return mapper.insertAll(imgs);}

    public Img selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

//    public int updateByPrimaryKey(Img var0){
//        return mapper.updateByPrimaryKey(var0);
//    }

    public int updateByPrimaryKeySelective(Img var0){
        return mapper.updateByPrimaryKeySelective(var0);
    }

//    public int insertSelective(Img var0){
//        return mapper.insertSelective(var0);
//    }

}
