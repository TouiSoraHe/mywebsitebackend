package com.zzy.mywebsitebackend.Service;

import com.alibaba.fastjson.JSON;
import com.zzy.mywebsitebackend.Data.Entity.BloggerInfo;
import org.springframework.core.io.ClassPathResource;
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

    public void updateBloggerInfo(BloggerInfo bloggerInfo){
        this.bloggerInfo = bloggerInfo;
        saveToFile(this.bloggerInfo);
    }

    private static BloggerInfo readFromFile() {
        BloggerInfo ret = null;
        try {
            ClassPathResource resource = new ClassPathResource("BloggerInfo.json");
            ret = JSON.parseObject(Files.readAllBytes(Paths.get(resource.getURI())),BloggerInfo.class);
            if (ret == null){
                ret = new BloggerInfo();
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

    private static boolean saveToFile(BloggerInfo bloggerInfo) {
        try {
            ClassPathResource resource = new ClassPathResource("BloggerInfo.json");
            String json = JSON.toJSONString(bloggerInfo);
            Files.write(Paths.get(resource.getURI()),json.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
