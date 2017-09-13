package com.doraemon.base.dao;

/**
 * Created by zbs on 2017/9/12.
 */
public enum DataSourceEnum {

    read("slave", "从库"),
    write("master", "主库");

    private String type;
    private String name;

    DataSourceEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
