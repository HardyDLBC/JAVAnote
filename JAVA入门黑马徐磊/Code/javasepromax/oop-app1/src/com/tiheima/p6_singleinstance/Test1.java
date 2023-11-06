package com.tiheima.p6_singleinstance;

public class Test1 {
    public static void main(String[] args) {
        //目标：掌握单例设计模式的写法
        A a1 = A.getObject();
        System.out.println(a1);
        A a2 = A.getObject();
        System.out.println(a2);

    }
}
