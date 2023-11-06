package com.itheima.Case;

public class ArrayInversion {
    public static void main(String[] args) {
        int[] array1 = {10, 20, 30, 40,50};
        for (int i = 0, j = array1.length - 1; i<j; i++,j-- ){
            int a = array1[i];
            array1[i] = array1[j];
            array1[j] = a;
        }
        for (int n =0; n<array1.length; n++){
            System.out.print(array1[n]+ " ");//把println的ln去掉，就可以取消换行
        }
    }
}
