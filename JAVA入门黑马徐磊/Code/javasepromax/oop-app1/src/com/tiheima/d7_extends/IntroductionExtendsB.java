package com.tiheima.d7_extends;

public class IntroductionExtendsB extends IntroductionExtendsA {
    public int k;
    //子类可以继承父类非私有成员
    public void print3(){
        System.out.println("print3");
        print1();
        //报错 print2();

    }
}
