
package com.zzy.mywebsitebackend.Service;

import com.google.common.base.Strings;
import com.zzy.mywebsitebackend.Data.Entity.Img;
import com.zzy.mywebsitebackend.Data.Entity.User;
import com.zzy.mywebsitebackend.Data.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private ImgService imgService;

    public int insert(User user){
        if(Strings.isNullOrEmpty(user.getUserName())){
            user.setUserName("匿名用户");
        }
        imgService.insert(user.getAvatar());
        user.setAvatarImgId(user.getAvatar().getId());
        int ret = 0;
        try {
            ret = mapper.insert(user);
        }catch (org.springframework.dao.DuplicateKeyException e){
            imgService.deleteByPrimaryKey(user.getAvatarImgId());
            User oldUser = selectByPrimaryKey(user.getId());
            user.setAvatarImgId(oldUser.getAvatarImgId());
            user.getAvatar().setId(oldUser.getAvatar().getId());
            updateByPrimaryKeySelective(user);
        }
        return ret;
    }

    public User selectByPrimaryKey(String var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(String userId){
        User user = selectByPrimaryKey(userId);
        if(user == null) return 0;
        int ret = mapper.deleteByPrimaryKey(user.getId());
        imgService.deleteByPrimaryKey(user.getAvatarImgId());
        return ret;
    }

//    public int updateByPrimaryKey(User var0){
//        return mapper.updateByPrimaryKey(var0);
//    }

    public int updateByPrimaryKeySelective(User user){
        if(user.getAvatar().getSmall() != null || user.getAvatar().getMedium() != null ||user.getAvatar().getLarge() != null){
            Img img = imgService.selectByPrimaryKey(selectByPrimaryKey(user.getId()).getAvatarImgId());
            img.setMedium(user.getAvatar().getMedium());
            img.setLarge(user.getAvatar().getLarge());
            img.setSmall(user.getAvatar().getSmall());
            imgService.updateByPrimaryKeySelective(img);
        }
        user.setAvatarImgId(null);
        if(Strings.isNullOrEmpty(user.getEmail())){
            user.setEmail(null);
        }
        return mapper.updateByPrimaryKeySelective(user);
    }

    public List<User> selectAll() {
        return mapper.selectAll();
    }

//    public int insertSelective(User var0){
//        return mapper.insertSelective(var0);
//    }

}
