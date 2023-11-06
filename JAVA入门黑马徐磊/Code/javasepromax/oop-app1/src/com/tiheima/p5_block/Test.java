package com.tiheima.p5_block;

public class Test {
    public static void main(String[] args) {
        //目标：认识两种代码块，了解他们的特点和基本作用
        System.out.println(Student.i1);
        System.out.println(Student.i1);
        System.out.println(Student.i1);
        System.out.println(Student.s1);

        Student s1 = new Student();
        Student s2 = new Student("a");
        System.out.println(s1.age);
        System.out.println(s2.age);
    }
}
