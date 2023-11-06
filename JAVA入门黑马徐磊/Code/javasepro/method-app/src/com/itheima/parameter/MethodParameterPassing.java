package com.itheima.parameter;

public class MethodParameterPassing {
    public static void main(String[] args) {
        //需求1: 输出一个int类型的数组内容，要求输出格式为： [11, 22, 33, 44, 55]
        int[] arr = {11,22,33,44,55};
        printArray(arr);

        int[] arr2=null;
        printArray(arr2);

        int[] arr3={};
        printArray(arr3);
    }
    public static void printArray(int[] arr){
        //专业程序员要严谨，因为别人用你的方法出bug，算你的锅
        if (arr == null){
            System.out.println(arr);
            return; //return可以跳出当前方法  break是跳出循环
        }

        System.out.print("[");
        //遍历接到的数组元素
        for (int i = 0; i< arr.length; i++){
/*            if (i == arr.length-1) {
                System.out.print(arr[i]);
            }else {
                System.out.print(arr[i] + ",");
            }*/
            //下面三元判断语句等效👆的if语句 --> 优雅专业
            System.out.print(i == arr.length-1? arr[i] : arr[i]+",");
        }
        System.out.println("]");
    }
}
