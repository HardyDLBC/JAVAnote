package com.itheima.p2_polymorphism;

public class Student extends People {
    public String name = "The name of subclass Student";
    @Override
    public void run() {
        System.out.println("Student can run quickly");
    }

    public void test(){
        System.out.println("Student have to take the test");
    }
}
