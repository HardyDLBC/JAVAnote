package com.tiheima.p2_static_method;

public class Test {
    public static void main(String[] args) {
        //目标： 掌握有无static修饰方法的用法

        //1. 类方法的使用
        Student.printHelloWorld();
        Student s1 = new Student();
        s1.printHelloWorld();

        //2. 实例方法的使用
        int score = 59;
        s1.printPass(score);
    }
}
