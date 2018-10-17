package com.zzy.mywebsitebackend.Util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Util {

    public static byte[] ObjectToByte(Object obj) {
        if(obj == null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(baos)){
            out.writeObject(obj);
        } catch (IOException e) {
            log.error("ObjectToByte error!"+e.getMessage());
        }
        return baos.toByteArray();
    }

    public static <T> T ByteToObject(byte[] bytes){
        if(bytes == null) return null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try(
                ObjectInputStream in = new ObjectInputStream(bais)
        ){
            return (T)in.readObject();
        }catch (IOException e) {
            log.error("ByteToObject error!", e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error("ByteToObject error!", e.getMessage());
        }
        return null;
    }
}
