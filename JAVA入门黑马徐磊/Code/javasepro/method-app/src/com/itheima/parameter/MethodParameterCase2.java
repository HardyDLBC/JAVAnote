package com.itheima.parameter;

public class MethodParameterCase2 {
    public static void main(String[] args) {
        //目标： 比较两个数组，如果两个int类型的数组，元素个数，对应位置的元素内容都一样 --> 则认为两个数组一摸一样
        int[] arr1 = {10,20,30};
        int[] arr2 = {10,20,30};
        System.out.println(compare(arr1, arr2));
    }
    public static boolean compare(int[] arr1, int[] arr2){
        //1. 判断arr1和arr2是否都是null
        if (arr1 ==null && arr2==null){
            return true; //都null则一样
        }

        //2. 判断arr1是null，或者arr2是null
        if (arr1==null || arr2==null){
            return false;// 其一null则不同
        }

        //3. 判断2个数组长度是否一致。如果长度不一样，直接返回false
        if (arr1.length != arr2.length){
            return false;//长度不一则不同
        }

        //4. 2个数组长度是一样的，接着比较他们的内容是否一样
        for (int i = 0; i < arr1.length; i++) {
            //判断当前2个数组的元素是否一样，不一样直接返回false
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true; //整个for循环执行完都没有false，说明一样
    }
}
