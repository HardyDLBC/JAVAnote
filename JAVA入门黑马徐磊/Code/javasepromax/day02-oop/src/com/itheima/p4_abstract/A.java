package com.itheima.p4_abstract;

public abstract class A {
    private String name;
    public static String schoolName;
    public abstract void run(); //抽象方法：必须用abstract修饰，只有方法签名，一定不能有方法体{}

    public A() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
