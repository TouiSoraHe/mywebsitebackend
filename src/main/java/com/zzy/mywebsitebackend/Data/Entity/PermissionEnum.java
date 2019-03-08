package com.zzy.mywebsitebackend.Data.Entity;

public enum PermissionEnum {
    Read ("Read",1),Add("Add",2),Edit("Edit",4)
    ,Delete("Delete",8);

    private String name;
    private int value;

    // 构造方法  
    private PermissionEnum(String name,int value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
