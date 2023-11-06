package com.itheima.loop;

public class WhileDemo1 {
    public static void main(String[] args) {
        //while循环
        double  paper = 0.1;
        int count = 0;

        for (; paper <=8848860; count++  ){
            paper = paper * 2;
        }
        System.out.println(count);
        System.out.println(paper);
    }
}
