package com.zzy.mywebsitebackend.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zzy.mywebsitebackend.AOP.Entity.ExceptionInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Service
public class ExceptionInfoService {

    private List<ExceptionInfo> exceptionInfos = new ArrayList<>();
    private Boolean updated = false;//代表exceptionInfos已经被修改了,但是尚未保存
    private Timer timer = new Timer(true);
    private long period = 1000;//每隔period毫秒,执行一次

    private TimerTask task = new TimerTask() {
        public void run() {
            if(updated){
                updated = !saveToFile(exceptionInfos);
            }
        }
    };

    @PostConstruct
    private void Init() {
        exceptionInfos.addAll(readFromFile());
        timer.schedule(task,period,period);
    }

    public void addExceptionInfo(ExceptionInfo exceptionInfo){
        if(exceptionInfo.getId() == null){
            exceptionInfo.setId(new Date().getTime());
        }
        exceptionInfos.add(exceptionInfo);
        updated = true;
    }

    /**
     * note:该方法直接返回源对象,不应该修改该对象
     * @param id
     * @return
     */
    public ExceptionInfo getExceotionInfo(long id){
        for (ExceptionInfo exceptionInfo : exceptionInfos) {
            if(exceptionInfo.getId() == id){
                return exceptionInfo;
            }
        }
        return null;
    }

    public List<ExceptionInfo> getAll(){
        return exceptionInfos;
    }

    public boolean removeExceptionInfo(long id){
        if(exceptionInfos.removeIf(item -> {
            return item.getId() == id;
        })){
            updated = true;
            return true;
        }
        return false;
    }

    private static List<ExceptionInfo> readFromFile() {
        List<ExceptionInfo> ret = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:ExceptionInfo.json");
            List<ExceptionInfo> temp = JSON.parseObject(Files.readAllBytes(file.toPath()),new TypeReference<List<ExceptionInfo>>() {}.getType());
            if (temp!=null){
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

    private static boolean saveToFile(List<ExceptionInfo> exceptionInfos) {
        try {
            File file = ResourceUtils.getFile("classpath:ExceptionInfo.json");
            String json = JSON.toJSONString(exceptionInfos);
            Files.write(file.toPath(),json.getBytes());
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
