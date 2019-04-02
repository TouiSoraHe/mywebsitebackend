package com.zzy.mywebsitebackend.Service;

import com.alibaba.fastjson.JSON;
import com.zzy.mywebsitebackend.Data.Entity.BloggerInfo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class BloggerInfoService {

    private BloggerInfo bloggerInfo;

    @PostConstruct
    private void Init() {
        this.bloggerInfo = readFromFile();
    }

    public BloggerInfo getBloggerInfo(){
        return bloggerInfo;
    }

    public void updateBloggerInfo(BloggerInfo bloggerInfo) throws IOException {
        this.bloggerInfo = bloggerInfo;
        saveToFile(this.bloggerInfo);
    }

    private BloggerInfo readFromFile() {
        BloggerInfo ret = new BloggerInfo();
        try {
            BloggerInfo temp = JSON.parseObject(Files.readAllBytes(Paths.get("BloggerInfo.json")),BloggerInfo.class);
            if (temp != null){
                ret = temp;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.getStackTrace();
        }
        finally {
            return ret;
        }
    }

    private void saveToFile(BloggerInfo bloggerInfo) throws IOException {
        String json = JSON.toJSONString(bloggerInfo);
        Files.write(Paths.get("BloggerInfo.json"),json.getBytes());
    }
}
