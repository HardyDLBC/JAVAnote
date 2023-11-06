package com.itheima;

import java.util.Random;

public class VerificationCodeCase2 {
    public static void main(String[] args) {
        //目标： 开发一个程序，可以生成指定位数的验证码。每位可以是数字、大小写字母
        int codeDigit = 4;
        System.out.println(createCode(codeDigit));
    }

    public static String createCode(int codeDigit){
        Random r = new Random();
        String code ="";
        //for循环控制产生多少位随机字符 main里的codeDigit
        for ( int i =0 ; i < codeDigit; i++){
            //思路： 随机一个0 1 2之间的数字，0代表随机一个数字，1 2代表随机一个大写小写字符
            int type = r.nextInt(3);  //0，1，2
            switch (type){
                case 0:
                    //随机一个数字 0-9
                    code += r.nextInt(10);
                break;
                case 1:
                    //随机一个大写字符A65 Z65+25
                    char ch1 = (char) (r.nextInt(26)+65);
                    code += ch1;
                break;
                case 2:
                    //随机一个小写字符a97 Z97+25
                    char ch2 = (char) (r.nextInt(26)+97);
                    code +=ch2;
                break;
            }
        }
        return code;
    }
}
