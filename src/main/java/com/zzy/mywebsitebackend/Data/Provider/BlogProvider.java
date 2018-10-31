package com.zzy.mywebsitebackend.Data.Provider;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.zzy.mywebsitebackend.Data.Entity.Blog;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BlogProvider {
    public String selectByPrimaryKeyList(Map map){
        List<Integer> ids = (List<Integer>)map.get("ids");
        final String idStr = Joiner.on(',').join(ids);
        return new SQL()
        {
            {
                SELECT("id, title, time, views, words, last_modified, deleted, content, img_url, tags");
                FROM("blog");
                WHERE("id in ("+ (Strings.isNullOrEmpty(idStr)?"null":idStr)+")");
            }
        }.toString();
    }

    public String updateByPrimaryKeyList(Map map){
        List<Blog> blogs = (List<Blog>)map.get("blogs");
        StringBuilder setStr = new StringBuilder();

        StringBuilder titleStr = new StringBuilder();
        StringBuilder timeStr = new StringBuilder();
        StringBuilder viewsStr = new StringBuilder();
        StringBuilder wordsStr = new StringBuilder();
        StringBuilder lastModifiedStr = new StringBuilder();
        StringBuilder deletedStr = new StringBuilder();
        StringBuilder contentStr = new StringBuilder();
        StringBuilder imgUrlStr = new StringBuilder();
        StringBuilder tagsStr = new StringBuilder();

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < blogs.size(); i++) {
            ids.add(blogs.get(i).getId().toString());
            titleStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].title,jdbcType=VARCHAR} ");
            timeStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].time,jdbcType=TIMESTAMP} ");
            viewsStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].views,jdbcType=INTEGER} ");
            wordsStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].words,jdbcType=INTEGER} ");
            lastModifiedStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].last_modified,jdbcType=TIMESTAMP} ");
            deletedStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].deleted,jdbcType=BIT} ");
            contentStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].content,jdbcType=LONGVARCHAR} ");
            imgUrlStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].img_url,jdbcType=LONGVARBINARY} ");
            tagsStr.append(" WHEN "+blogs.get(i).getId()+" THEN #{blogs["+i+"].tags,jdbcType=LONGVARBINARY} ");
        }

        if(blogs.size()>0){
            setStr.append("title = CASE id");
            setStr.append(titleStr);
            setStr.append("END,");

            setStr.append("time = CASE id");
            setStr.append(timeStr);
            setStr.append("END,");

            setStr.append("views = CASE id");
            setStr.append(viewsStr);
            setStr.append("END,");

            setStr.append("words = CASE id");
            setStr.append(wordsStr);
            setStr.append("END,");

            setStr.append("last_modified = CASE id");
            setStr.append(lastModifiedStr);
            setStr.append("END,");

            setStr.append("deleted = CASE id");
            setStr.append(deletedStr);
            setStr.append("END,");

            setStr.append("content = CASE id");
            setStr.append(contentStr);
            setStr.append("END,");

            setStr.append("img_url = CASE id");
            setStr.append(imgUrlStr);
            setStr.append("END,");

            setStr.append("tags = CASE id");
            setStr.append(tagsStr);
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

    public static void main(String[] args) {
    }
}
