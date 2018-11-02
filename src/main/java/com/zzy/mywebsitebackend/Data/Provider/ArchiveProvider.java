package com.zzy.mywebsitebackend.Data.Provider;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zzy.mywebsitebackend.Data.Entity.Archive;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArchiveProvider {

    public String insterByList(Map map){
        List<Archive> archives = (List<Archive>)map.get("archives");
        String sql = "INSERT INTO archive (blog_info_id, tag_id) VALUES ";
        List<String> values = new ArrayList<>();
        for (int i = 0; i < archives.size(); i++) {
            values.add("(#{archives["+i+"].blog_info_id,jdbcType=INTEGER}, #{archives["+i+"].tag_id,jdbcType=INTEGER})");
        }
        sql += Joiner.on(",").join(values);
        return sql;
    }
}
