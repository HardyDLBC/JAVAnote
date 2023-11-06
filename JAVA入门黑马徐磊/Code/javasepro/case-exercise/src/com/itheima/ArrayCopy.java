package com.itheima;

public class ArrayCopy {
    public static void main(String[] args) {
        int[] arr= {11,22,33};
        int[] arr2 = arrCopy(arr);
        printArray(arr2);
        arr[1] = 66;
        System.out.println(arr[1]);
        System.out.println(arr2[1]);
    }

    //1. 打印数组
    public static void printArray(int[] arr){
        System.out.print("[");
        for (int i=0; i< arr.length; i++){
            System.out.print(i == arr.length-1 ? arr[i] : arr[i] + ", ");
        }
        System.out.println("]");
    }

    //2. 把原数组的元素给对应位置的新数组
    public static int[] arrCopy(int[] arr){
        int[] newArr = new int[arr.length];
        for (int i=0; i< arr.length; i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }
}
