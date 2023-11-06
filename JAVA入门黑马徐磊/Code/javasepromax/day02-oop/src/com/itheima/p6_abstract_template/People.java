package com.itheima.p6_abstract_template;

public abstract class People {
    /*
    * 设计模版方法设计模式
    * 1. 定义一个模版方法出来
    */
    public final void write(){
        System.out.println("\t\t\t\tMy father");
        System.out.println("My father is kind, 666");
        //2. 模版方法并不清楚正文部分到底应该怎么写，但是它知道子类肯定要写。
        System.out.println(writeMain());
        System.out.println("It is great to have such a dad.");
    }
    //3.设计一个抽样方法写正文，具体的实现交给子类来完成
    public abstract String writeMain();
}
