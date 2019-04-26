package com.zzy.mywebsitebackend.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class BloggerInfoService {

    private JSONObject bloggerInfo;

    @PostConstruct
    private void Init() {
        this.bloggerInfo = readFromFile();
    }

    public JSONObject getBloggerInfo(){
        return bloggerInfo;
    }

    public void updateBloggerInfo(JSONObject bloggerInfo) throws IOException {
        this.bloggerInfo = bloggerInfo;
        saveToFile(this.bloggerInfo);
    }

    private JSONObject readFromFile() {
        JSONObject ret = new JSONObject();
        try {
            JSONObject temp = JSON.parseObject(new String(Files.readAllBytes(Paths.get("BloggerInfo.json"))));
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

    private void saveToFile(JSONObject bloggerInfo) throws IOException {
        String json = JSON.toJSONString(bloggerInfo);
        Files.write(Paths.get("BloggerInfo.json"),json.getBytes());
    }
}
