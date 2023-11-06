package com.itheima.loop;

public class ForDemo1 {
    public static void main(String[] args) {
        //for 循环
        int sum = 0;
        for (int i = 1; i <= 100 ; i++){
            if (i%2 == 1){
                sum = sum + i;
            }
        }
    System.out.println("0-100的奇数和=" + sum);

    }
}
