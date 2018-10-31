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

    public  String selectByTagIDs(Map map){
        List<Integer> tagIds = (List<Integer>)map.get("tagIds");
        String tagIdsStr = Joiner.on(",").join(tagIds);
        return new SQL()
        {
            {
                SELECT("id, blog_info_id, tag_id");
                FROM("archive");
                WHERE("tag_id in ("+(Strings.isNullOrEmpty(tagIdsStr)?null:tagIdsStr)+")");
            }
        }.toString();
    }

    public String deleteByBlogInfoIDAndTagIDs(Map map){
        List<Integer> tagIds = (List<Integer>)map.get("tagIds");
        final String tagIdsStr = Joiner.on(',').join(tagIds);
        return new SQL()
        {
            {
                DELETE_FROM("archive");
                WHERE("blog_info_id = #{blogInfoId,jdbcType=INTEGER} AND tag_id in ("+ (Strings.isNullOrEmpty(tagIdsStr)?"null":tagIdsStr)+")");
            }
        }.toString();
    }

    public String deleteByTagIDAndBlogInfoIDs(Map map){
        List<Integer> blogInfoIds = (List<Integer>)map.get("blogInfoIds");
        final String blogInfoIdsStr = Joiner.on(',').join(blogInfoIds);
        return new SQL()
        {
            {
                DELETE_FROM("archive");
                WHERE("tag_id = #{tagId,jdbcType=INTEGER} AND blog_info_id in ("+ (Strings.isNullOrEmpty(blogInfoIdsStr)?"null":blogInfoIdsStr)+")");
            }
        }.toString();
    }

}
