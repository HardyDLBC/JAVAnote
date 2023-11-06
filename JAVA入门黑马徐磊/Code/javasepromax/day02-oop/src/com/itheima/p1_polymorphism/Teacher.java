package com.itheima.p1_polymorphism;

public class Teacher extends People{
    public String name = "The name of subclass Teacher";
    @Override
    public void run() {
        System.out.println("The teacher ran out of breath");
    }
}
