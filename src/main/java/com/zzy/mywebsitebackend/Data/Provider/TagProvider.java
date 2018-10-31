package com.zzy.mywebsitebackend.Data.Provider;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.zzy.mywebsitebackend.Data.Entity.Blog;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TagProvider {
    public String selectByPrimaryKeyList(Map map){
        List<Integer> ids = (List<Integer>)map.get("ids");
        final String idStr = Joiner.on(',').join(ids);
        return new SQL()
        {
            {
                SELECT("id, tag_name, img_url");
                FROM("tag");
                WHERE("id in ("+ (Strings.isNullOrEmpty(idStr)?"null":idStr)+")");
            }
        }.toString();
    }

    public String updateByPrimaryKeyList(Map map){
        List<Blog> tags = (List<Blog>)map.get("tags");
        StringBuilder setStr = new StringBuilder();

        StringBuilder tagNameStr = new StringBuilder();
        StringBuilder imgUrlStr = new StringBuilder();
        StringBuilder blogInfoIdStr = new StringBuilder();

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++) {
            ids.add(tags.get(i).getId().toString());
            tagNameStr.append(" WHEN "+tags.get(i).getId()+" THEN #{tags["+i+"].tag_name,jdbcType=VARCHAR} ");
            imgUrlStr.append(" WHEN "+tags.get(i).getId()+" THEN #{tags["+i+"].img_url,jdbcType=LONGVARBINARY} ");
        }

        if(tags.size()>0){
            setStr.append("title = CASE id");
            setStr.append(tagNameStr);
            setStr.append("END,");

            setStr.append("img_url = CASE id");
            setStr.append(imgUrlStr);
            setStr.append("END,");

            setStr.append("tags = CASE id");
            setStr.append(blogInfoIdStr);
            setStr.append("END");
        }else{
            setStr.append("title = CASE id");
            setStr.append(" WHEN null THEN null ");
            setStr.append("END");
        }

        return new SQL()
        {
            {
                UPDATE("blog");
                SET(setStr.toString());
                WHERE("id in ("+(ids.size()==0?"null":String.join(",",ids))+")");
            }
        }.toString();
    }
}
