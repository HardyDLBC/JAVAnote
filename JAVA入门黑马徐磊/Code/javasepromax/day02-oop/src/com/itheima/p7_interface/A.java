package com.itheima.p7_interface;

public interface A {
    //成员变量（常量）
    String SCHOOL_NAME = "heimaprogrammer"; //成员变量（Java默认为常量）不用加public static final

    //成员方法(java默认抽象方法)
    void test();

    //不能有其他，e.g.构造器 代码块
    //报错 public A(){}
    //报错 static {}
}
