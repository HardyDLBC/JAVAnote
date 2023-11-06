package com.itheima.p7_interface;

public class Test {
    public static void main(String[] args) {
        //目标：认识接口
        System.out.println(A.SCHOOL_NAME); //可以用类名直接访问常量
        //报错 A a1 = new A(); 没有构造器不能创建对象

        D d = new D();
        d.testb1();
    }
}
