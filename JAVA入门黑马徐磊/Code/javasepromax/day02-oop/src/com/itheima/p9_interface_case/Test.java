package com.itheima.p9_interface_case;
//理解： 一个实体类 一个操作类 一个接口 两个实现类 最后一个test调用类
//理解： 接口就是一条一分为二的数据线。一个头操作类，另两个头就是实现类，看你要查插苹果手机还是安卓手机
public class Test {
    public static void main(String[] args) {
        //目标：完成班级学生信息管理的案例
        ClassManager class1 = new ClassManager();
        class1.printInfo();
        class1.printScore();
    }
}
