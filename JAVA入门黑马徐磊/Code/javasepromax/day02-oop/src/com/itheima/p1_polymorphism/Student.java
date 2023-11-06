package com.itheima.p1_polymorphism;

public class Student extends People{
    public String name = "The name of subclass Student";
    @Override
    public void run() {
        System.out.println("Student can run quickly");
    }
}
