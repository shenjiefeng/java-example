package com.fsj.oop;

import lombok.ToString;

import java.io.Serializable;

@ToString
class User1 implements Serializable {
    /**
     * 如果不显示定义serialVersionUID，编译器会自动添加serialVersionUID。
     */
    private static final long serialVersionUID = 1L;
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
