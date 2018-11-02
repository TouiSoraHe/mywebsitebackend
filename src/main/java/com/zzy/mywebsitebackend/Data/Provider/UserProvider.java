package com.zzy.mywebsitebackend.Data.Provider;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class UserProvider {

    public String selectByPrimaryKeyList(Map map){
        List<String> userIds = (List<String>)map.get("ids");
        String idStr = Joiner.on(",").join(userIds);
        return new SQL(){
            {
                SELECT("id, user_name, email, avatar");
                FROM("user");
                WHERE("id in ("+ (Strings.isNullOrEmpty(idStr)?null:idStr)+")");
            }
        }.toString();
    }
}
