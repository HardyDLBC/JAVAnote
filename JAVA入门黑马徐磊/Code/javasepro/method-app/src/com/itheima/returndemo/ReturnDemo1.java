package com.itheima.returndemo;

public class ReturnDemo1 {
    public static void main(String[] args) {
        divide(3433,33);
    }
    public static void divide(int a, int b){
        if (b==0){
            System.out.println("b = 0");
            return;
        }
        double c = a/b;
        System.out.println(c);
    }
}
