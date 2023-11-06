package com.itheima.object;

public class Test {
    public static void main(String[] args) {
        //目标： 面对对象编程快速入门

        // 1. 创建一个学生对象，封装播妞的数据
        Student s1 = new Student();  //代表得到了一个学生对象 类名可以作为变量类型
        s1.name = "播妞";
        s1.chinese = 100;
        s1.math = 100;
        s1.printTotalScore();   //s1调Student的方法
        s1.printAverageScore();

        // 2. 再创建一个对象，封装波仔的数据
        Student BoZai = new Student();
        BoZai.name = "波仔";
        BoZai.chinese = 59;
        BoZai.math = 100;
        BoZai.printTotalScore();
        BoZai.printAverageScore();
    }
}
