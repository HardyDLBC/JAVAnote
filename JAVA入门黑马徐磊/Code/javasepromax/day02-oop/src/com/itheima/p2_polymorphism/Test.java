package com.itheima.p2_polymorphism;

public class Test {
    public static void main(String[] args) {
        //目标：理解多态的好处
        //好处1: 实现解耦合，右边对象可以随时切换，后续业务随机改变。
        People p1 = new Student();
        p1.run();
        //报错 p1.test(); //多态下存在的的问题：无法直接调用子类的独有功能

        //强制类型转换
        if (p1 instanceof Student){
            Student s2 = (Student) p1;
            s2.test();
        }else {
            Teacher t2 = (Teacher) p1;
            t2.teach();
        }

        //强制类型转换可能存在的问题：编译阶段有继承/实现关系就可以强制转换，但是运行时可能出现类型转换异常
        //报错 Teacher t2 = (Teacher) p1;//把学生转成老师，就错了。运行时出现了：ClassCastException

        //好处2：可以使用父类类型的变量作为形参，可以接收一切子类对象。
        Student s1 = new Student();
        go(s1);

        Teacher t1 = new Teacher();
        go(t1);
    }
    public static void go(People p1){

    }
}
