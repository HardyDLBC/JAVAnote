package com.itheima.random;

import java.util.Random;

public class RandomDemo1 {
    public static void main(String[] args) {
        //掌握random生成随机数的步骤
        Random r = new Random();
        for (int a =1; a<=100;a++) {
        int i = r.nextInt(27)+65;

            System.out.println(i);
        }
    }
}
