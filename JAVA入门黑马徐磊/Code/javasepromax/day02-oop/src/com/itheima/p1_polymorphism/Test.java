package com.itheima.p1_polymorphism;

public class Test {
    public static void main(String[] args) {
        //目标：认识多态：对象多态，行为多态
        //1. 对象多态 e.g.人对象，有的是老师，有的是学生
        People p1 = new Teacher(); //人对象可以是老师。老师范围比人范围小/老师也是人
        p1.run(); //行为多态识别技巧：编译看左边，执行看右边
        System.out.println(p1.name); //⚠️：变量没有多态。编译看左边，运行看左边

        People p2 = new Student(); //人对象可以是学生，学生范围比人范围小/学生也是人
        p2.run(); //行为多态识别技巧：编译看左边，执行看右边
        System.out.println(p2.name);//⚠️：变量没有多态。编译看左边，运行看左边
    }
}
