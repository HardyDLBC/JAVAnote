package com.itheima;

public class DigitEncryption {
    public static void main(String[] args) {
        int code = 1983;
        System.out.println(encryption(code));
    }

    //做加密的类
    public static String encryption(int code){
        //1. 把密码拆分成一个一个的数字，才可以加密
        int[] arrCode = split(code);
        //2. 遍历数组，进行加密
        for (int i=0; i<arrCode.length; i++){
            arrCode[i] = (arrCode[i]+5)%10;
        }
        //3. 反转数组
        reverse(arrCode);

        //4. 把加密后的每个数字拼起来，作为加密后的结果
        String data ="";
        for (int i=0; i<arrCode.length; i++){
            data += arrCode[i];
        }
        return data;
    }

    //做一个反转的类
    public static void reverse(int[] arrCode){

        for (int i=0, j=arrCode.length-1; i<j; i++,j--){
            int temp = arrCode[i];
            arrCode[i] =arrCode[j];
            arrCode[j] = temp;
        }
    }

    //做一个拆分整数1983的类
    public static int[] split(int code){
        int[] arrCode = new int[4];
        arrCode[0] = code/1000;
        arrCode[1] = (code/100)%10;
        arrCode[2] = (code/10)%10;
        arrCode[3] = code%10;
        return arrCode;
    }
}
