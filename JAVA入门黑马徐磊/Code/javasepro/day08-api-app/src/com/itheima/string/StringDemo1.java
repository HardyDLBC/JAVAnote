package com.itheima.string;

public class StringDemo1 {
    public static void main(String[] args) {
        // 目标： 掌握创建String对象，并封装要处理的字符串的两种方式

        // 1. 直接双引号得到字符串对象，封装字符串数据
        String name = "itheima";
        System.out.println(name);

        // 2. new String创建字符串对象，并调用构造器初始化字符串
        String s1 = new String();
        System.out.println(s1); // ""什么都没有

        String s2 = new String("itheima666");
        System.out.println(s2);

        char[] c1 = {'a','b','c'};
        String s3 = new String(c1);
        System.out.println(s3);

        byte[] b1 ={97, 98,99};
        String s4 = new String(b1);
        System.out.println(s4);
    }
}
