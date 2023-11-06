package com.tiheima.p6_singleinstance;

public class B {
    //2.定义一个类变量用于存储对象
    private static B b;
    //1.把类的构造器私有
    private B(){

    }

    //3.提供一个类方法，保证第一次调用时才创建一个对象
    public static B getInstance(){
        if (b==null){
            b=new B();
            System.out.println("第一次创建对象");
        }
        return b;
    }
}
