package com.itheima.type;

public class TypeConversionDemo1 {
    public static void main(String[] args) {
        //理解自动类型转换机制
        int a =1;
        byte b = (byte) a;  //发生了自动类型转换
        System.out.println(b);

        int i =1500;
        byte j = (byte) i;
        System.out.println(j);
    }
}
