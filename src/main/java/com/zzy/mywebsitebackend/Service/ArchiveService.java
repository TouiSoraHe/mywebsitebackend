
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Archive;
import com.zzy.mywebsitebackend.Data.JsonObj.ArchiveJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.ArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveService {
    @Autowired
    private ArchiveMapper mapper;

    @Autowired
    private BlogInfoService blogInfoService;

    /**
     * 插入ArchiveJsonObj到数据库
     * @param archiveJsonObj 需要插入的ArchiveJsonObj
     * @return 返回插入的条数,对于该方法总是返回0或者1
     */
    public int insert(ArchiveJsonObj archiveJsonObj){
        Archive archive = archiveJsonObj.toArchive();
        int ret = mapper.insert(archive);
        archiveJsonObj.setWithArchive(archive);
        return ret;
    }

    public int insertByList(List<ArchiveJsonObj> archiveJsonObjs){
        if(archiveJsonObjs == null || archiveJsonObjs.size() == 0) return 0;
        List<Archive> archives = new ArrayList<>();
        for (ArchiveJsonObj archiveJsonObj:archiveJsonObjs){
            archives.add(archiveJsonObj.toArchive());
        }
        int ret = mapper.insterByList(archives);
        for (int i = 0; i < archiveJsonObjs.size(); i++) {
            archiveJsonObjs.get(i).setWithArchive(archives.get(i));
        }
        return ret;
    }

    public int updateByPrimaryKey(ArchiveJsonObj archiveJsonObj){
        Archive archive = archiveJsonObj.toArchive();
        int ret = mapper.updateByPrimaryKey(archive);
        archiveJsonObj.setWithArchive(archive);
        return ret;
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public int deleteByBlogInfoID(Integer var0){
        return mapper.deleteByBlogInfoID(var0);
    }

    public int deleteByTagID(Integer var0){
        return mapper.deleteByTagID(var0);
    }

    public int deleteByTagIDAndBlogInfoIDs(Integer tagId,List<Integer> blogInfoIds){
        return mapper.deleteByTagIDAndBlogInfoIDs(tagId,blogInfoIds);
    }

    public int deleteByBlogInfoIDAndTagIDs(Integer blogInfoId,List<Integer> tagIds){
        return mapper.deleteByBlogInfoIDAndTagIDs(blogInfoId,tagIds);
    }

    public ArchiveJsonObj selectByPrimaryKey(Integer var0){
        Archive archive = mapper.selectByPrimaryKey(var0);
        if(archive ==null) return null;
        return new ArchiveJsonObj(archive);
    }

    public List<ArchiveJsonObj> selectByBlogInfoID(Integer blogInfoId){
        List<ArchiveJsonObj> archiveJsonObjs = new ArrayList<>();
        List<Archive> archives = mapper.selectByBlogInfoID(blogInfoId);
        for(Archive archive:archives){
            archiveJsonObjs.add(new ArchiveJsonObj(archive));
        }
        return archiveJsonObjs;
    }

    public List<ArchiveJsonObj> selectByTagID(Integer tagId){
        List<ArchiveJsonObj> archiveJsonObjs = new ArrayList<>();
        List<Archive> archives = mapper.selectByTagID(tagId);
        for(Archive archive:archives){
            archiveJsonObjs.add(new ArchiveJsonObj(archive));
        }
        return archiveJsonObjs;
    }

    public List<ArchiveJsonObj> selectByTagIDs(List<Integer> tagIds){
        List<ArchiveJsonObj> archiveJsonObjs = new ArrayList<>();
        List<Archive> archives = mapper.selectByTagIDs(tagIds);
        for(Archive archive:archives){
            archiveJsonObjs.add(new ArchiveJsonObj(archive));
        }
        return archiveJsonObjs;
    }

    public List<ArchiveJsonObj> selectAll(){
        List<ArchiveJsonObj> archiveJsonObjs = new ArrayList<>();
        List<Archive> archives = mapper.selectAll();
        for(Archive archive:archives){
            archiveJsonObjs.add(new ArchiveJsonObj(archive));
        }
        return archiveJsonObjs;
    }
}
