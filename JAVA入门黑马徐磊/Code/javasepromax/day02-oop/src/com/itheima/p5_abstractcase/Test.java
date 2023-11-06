package com.itheima.p5_abstractcase;

public class Test {
    public static void main(String[] args) {
        Animal d1 = new Dog();
        d1.setName("WangCai");
        d1.cry(); //更好的支持了多态 抽样类做了抽样方法，子类又必须完成了功能

        Animal c1 = new Cat();
        c1.setName("MiMi");
        c1.cry();
    }
}
