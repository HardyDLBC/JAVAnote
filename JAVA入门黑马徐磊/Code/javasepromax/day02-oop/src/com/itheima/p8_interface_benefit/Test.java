package com.itheima.p8_interface_benefit;

public class Test {
    public static void main(String[] args) {
        //目标：搞清楚使用接口的好处
        Driver s1 = new A();
        s1.drive();

        Driver d1 = new A(); //有接口可以随时换A，觉得A不顺眼。不用改下面程序就可以跑
        d1.drive();
    }
}
class B implements Driver{

    @Override
    public void drive() {

    }
}
class A extends Student implements Driver, Singer{ /*用接口而不是直接创建方法，可以从implements直接看出这个对象是什么角色，会什么技能
                                                     从而也就可以放心的把你当作谁来用了 =>专业*/
    @Override
    public void drive() {

    }

    @Override
    public void sing() {

    }
}

class Student{

}

//还想让A扩展其他特征，不只是学生 => 用接口
interface Driver{
    void drive();
}

interface Singer{
    void sing();
}
