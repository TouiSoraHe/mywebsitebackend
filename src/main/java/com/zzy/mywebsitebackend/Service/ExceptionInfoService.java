package com.zzy.mywebsitebackend.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zzy.mywebsitebackend.AOP.Entity.ExceptionInfo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExceptionInfoService {

    private List<ExceptionInfo> exceptionInfos;

    @PostConstruct
    private void Init() {
        exceptionInfos = readFromFile();
    }

    public void addExceptionInfo(ExceptionInfo exceptionInfo){
        if(exceptionInfo.getId() == null){
            exceptionInfo.setId(new Date().getTime());
        }
        exceptionInfos.add(exceptionInfo);
        saveToFile(exceptionInfos);
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
            saveToFile(exceptionInfos);
            return true;
        }
        return false;
    }

    private static List<ExceptionInfo> readFromFile() {
        List<ExceptionInfo> ret = new ArrayList<>();
        try {
            List<ExceptionInfo> temp = JSON.parseObject(Files.readAllBytes(Paths.get("ExceptionInfo.json")),new TypeReference<List<ExceptionInfo>>() {}.getType());
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
            String json = JSON.toJSONString(exceptionInfos);
            Files.write(Paths.get("ExceptionInfo.json"),json.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.getStackTrace();
            return false;
        }
        return true;
    }

}
