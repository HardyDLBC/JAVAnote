package com.tiheima.p8_extends_application;

public class Test {
    public static void main(String[] args) {
        //目标：搞清楚继承的好处
        Teacher t1 = new Teacher();
        t1.setName("张三");
        t1.setSkill("Java");
        System.out.println(t1.getName()+t1.getSkill());
    }
}
