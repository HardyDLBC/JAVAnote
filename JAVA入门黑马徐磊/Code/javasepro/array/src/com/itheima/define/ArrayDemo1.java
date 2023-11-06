package com.itheima.define;

public class ArrayDemo1 {
    public static void main(String[] args) {
        //掌握数组的定义方式一： 静态初始化数组
        double[] scores = {99,90,80};
        if (scores.length !=0){
            System.out.println("yes");
        }
        System.out.println(scores[2]);
        System.out.println(scores.length);

        scores[2] =92;
        System.out.println(scores[2]);

        System.out.println(scores[scores.length - 1]);



        for (int a = 0; a< scores.length; a++){
            System.out.println(scores[a]);
        }
    }
}
