package com.itheima.define;

public class MethodDemo1 {
    public static void main(String[] args) {
        //目标： 掌握定义方法的完整格式，懂使用方法的好处

        //调用方法:
        int rs = sum(10,20);
        System.out.println(rs);
    }
    //定义方法：
    public static int sum(int a, int b){
        int c = a + b;
        return c;
    }
}
