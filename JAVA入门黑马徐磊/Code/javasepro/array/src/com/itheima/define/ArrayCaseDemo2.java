package com.itheima.define;

public class ArrayCaseDemo2 {
    public static void main(String[] args) {
        //案例：求五名员工的总销售额
        int[] sale = {16, 26, 36, 6, 100};
        int sum = 0;

        for (int i=0; i < sale.length; i++){
            System.out.println(sale[i]);
            sum  += sale[i];
        }
        System.out.println(sum);
    }
}
