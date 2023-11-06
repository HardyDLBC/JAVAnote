package com.itheima.p2_polymorphism;

public class Teacher extends People {
    public String name = "The name of subclass Teacher";
    @Override
    public void run() {
        System.out.println("The teacher ran out of breath");
    }

    public void teach(){
        System.out.println("Teacher have to have class");
    }
}
