package com.itheima.p3_final;

public class p3_final {
    /*
    * 常量：public static final修饰的成员变量，建议名称全部大写，多个单词下划线连接
    */
    public static final String SCHOOL_NAME = "heima"; //静态变量

    private final String name = "aaa"; //实例变量 相当于所有对象名字都叫猪八戒（没有意义，知道就行）

    public static void main(String[] args) {
        //目标：认识final的作用
        //3.final可以修饰变量。有且仅能赋值一次
        //变量：
        //  （1）局部变量
        //  （2）成员变量：静态成员变量 实例成员变量
        final double r = 3.14; //局部变量
        //报错 r=2;

        final int[] arr = {11,22,33};
        //报错 arr = null; 因为不能改地址
        arr[1] = 222; //不报错。cuz引用类型的变量可以改数据内容

        //报错 schoolName = "baima"; 第二次赋值出错了
    }
    public static void buy(final double z){
        //报错 z = 0.1; 不想让别人给z赋值
    }
}

//1.final修饰类，类不能被继承了
final class A{

}
//报错 class B extends A{}

//2. final修饰方法，方法不能被重写了
class C{
    public final void test(){
    }
}

class D extends C{
    //报错 public void test(){}
}