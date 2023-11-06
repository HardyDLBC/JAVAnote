package com.itheima.define;

public class MethodCaseDemo4 {
    public static void main(String[] args) {
        System.out.println("The sum of 1 to 10 is "+sum(10));

        judge(2389794);
    }

    //Case1 目标： 求1到n的和
    public static int sum(int n){
        int sumValue = 0;
        for (int i = 1; i<=n; i++){
            sumValue +=i;
        }
        return sumValue;
    }

    //Case2 目标： 判断一个整数是奇数还是偶数
    public static void judge(int m){
        if (m%2==0){
            System.out.println(m + " is an even number");
        }else {
            System.out.println(m + " is an odd number");
        }
    }

}
