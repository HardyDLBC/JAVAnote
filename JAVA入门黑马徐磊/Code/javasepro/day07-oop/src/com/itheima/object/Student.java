package com.itheima.object;

//学生对象的模版
public class Student {
    String name;
    double chinese;
    double math;

    public void printTotalScore(){      //面向对象编程，不要加static
        System.out.println(name + "的总成绩是: " + (chinese+math));
    }

    public void printAverageScore() {   //面向对象编程，不要加static
        System.out.println(name + "的平均成绩是 " + (chinese+math)/2);
    }
}
