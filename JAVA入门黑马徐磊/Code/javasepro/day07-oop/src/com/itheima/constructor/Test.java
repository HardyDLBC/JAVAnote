package com.itheima.constructor;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();

        Student s2 = new Student("张三", 90);
        System.out.println(s2.name);
        System.out.println(s2.score);
    }
}
