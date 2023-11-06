package com.itheima;

public class FindPrimeNumber {
    public static void main(String[] args) {
        int min = 101;
        int max = 200;
        System.out.println(find(min, max));
    }

    public static int find(int min, int max){
        int count = 0;
        //1. 输出101～200每个数
        for (int i=min; i<=max; i++){
            // 信号位思想
            boolean flag = true;
            for (int a=2; a<=i/2; a++ ){
                //2. 判断当前数能被整除 --> 不是素数
                if (i%a ==0){
                    // 记住这个数不是素数
                    flag = false;
                    break;
                }
            }
            // 3. 是素数，才打印
            if (flag){
                System.out.println(i);
                count++;
            }
        }
        return count;
    }
}
