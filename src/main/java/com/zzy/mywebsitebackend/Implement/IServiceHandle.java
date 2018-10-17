package com.zzy.mywebsitebackend.Implement;

import java.util.List;

public interface IServiceHandle {

    int deleteByPrimaryKey(Integer id);

    public <T> int insert(T record);

    public <T> T selectByPrimaryKey(Integer id);

    public <T> List<T> selectAll();

    public <T> int updateByPrimaryKey(T record);
}
