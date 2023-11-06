package com.tiheima.p6_singleinstance;

import java.util.Stack;

public class A {
    //2.定义一个类变量记住一个类的对象
    private static A a = new A();

    //1.私有一个类的构造器
    private A(){

    }

    //3.定义一个类方法，返回对象
    public static A getObject(){
        return a;
    }
}
