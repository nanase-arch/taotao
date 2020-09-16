package com.taotao.vo;

import java.io.Serializable;

public class ZtreeResult implements Serializable{
    private Long id;
    private String name;
    //如果为true 则是文件夹 ，如果为false 是文件
    private boolean isParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    @Override
    public String toString() {
        return "ZtreeResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                '}';
    }
}
