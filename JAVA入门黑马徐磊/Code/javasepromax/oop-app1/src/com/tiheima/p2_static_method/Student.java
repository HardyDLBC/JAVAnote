package com.tiheima.p2_static_method;

public class Student {
    int score;
    //类方法
    public static void printHelloWorld(){
        System.out.println("Hello World");
        System.out.println("Hello World");
    }

    //实例方法
    public void printPass(int score){
        System.out.println("成绩" + (score>=60 ? "pass":"not pass") );
    }
}
