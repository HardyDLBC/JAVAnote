package com.tiheima.p6_singleinstance;

public class Test2 {
    public static void main(String[] args) {
        //目标：掌握懒汉式单例的写法
        B b1 = B.getInstance();
        B b2 = B.getInstance();
        System.out.println(b1==b2);
    }
}
