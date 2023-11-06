package com.itheima;

public class FindPrimeNumberMethod2 {
    //找素数方法2: 指定标签法
    public static void main(String[] args) {
        int min = 101;
        int max = 200;
        System.out.println(find(min, max));
    }

    public static int find(int min, int max){
        int count = 0;
        //1. 输出101～200每个数
        OUT: //为外部循环指定标签
        for (int i=min; i<=max; i++){

            for (int a=2; a<=i/2; a++ ){
                //2. 判断当前数能被整除 --> 不是素数
                if (i%a ==0){
                    // 结束外部循环的当次执行。跳到out下面for循环下一次执行
                    continue OUT;
                }
            }
            count++;
        }
        return count;
    }
}

