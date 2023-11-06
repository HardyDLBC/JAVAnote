package com.tiheima.p1_staticdemo;

public class Test {
    public static void main(String[] args) {
        //目标：掌握有无static修饰成员变量的用法，特点

        //1.类变量的用法
        Student.name = "张三";
        Student s1 = new Student();
        s1.name = "李四";

        Student s2 = new Student();
        s2.name = "秋雅";
        System.out.println(s1.name);
        System.out.println(s2.name);
        System.out.println(Student.name);

        //2.实例变量的用法，属于每个对象的变量
        s1.age = 18;
        s2.age = 19;
        System.out.println(s1.age);
        System.out.println(s2.age);
    }
}
