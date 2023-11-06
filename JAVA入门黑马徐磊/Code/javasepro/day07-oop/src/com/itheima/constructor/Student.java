package com.itheima.constructor;

//下面程序理解为用类设计对象，封装数据和处理数据的方法
public class Student {
    String name;
    double score;

    public Student(){
        System.out.println("无参数构造器被触发执行了");
    }

    public Student(String name, int score){
        System.out.println("有参数构造器被执行了");
        this.name = name;
        this.score = score;
    }
    public void setScore(int i) {
    }

}
