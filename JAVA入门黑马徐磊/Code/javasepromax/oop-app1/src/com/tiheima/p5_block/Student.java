package com.tiheima.p5_block;

public class Student {
    public static int i1=666;
    public static String s1="heima";
    //静态代码块
    static {
        System.out.println("静态代码块执行了");
    }

    //实例代码块
    int age;
    {
        System.out.println("实例代码块执行了");
        age = 18; //不建议为实例变量进行初始化赋值
        System.out.println("有人创建了对象"+this); //记录创建对象的日志
    }

    public Student(){
        System.out.println("无参数构造器执行了");
    }

    public Student(String s2){
        System.out.println("有参数构造器执行了");
    }
}
