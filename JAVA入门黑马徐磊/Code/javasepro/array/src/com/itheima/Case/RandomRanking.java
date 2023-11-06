package com.itheima.Case;

import java.util.Random;
import java.util.Scanner;

public class RandomRanking {
    public static void main(String[] args) {
        //让用户输入五名员工的工号作为一个数组
        int[] number = new int[5];
        for (int i = 0; i < number.length; i++ ){
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter your job number: ");
            int jobNumber = sc.nextInt();
            number[i] = jobNumber;
            System.out.println("This is number "+(i+1)+" job number");
        }
        //从第一个开始，随机挑一个互换
        for (int a=0; a <number.length; a++){
            Random r = new Random();
            int randomNumber = r.nextInt(number.length);
            int temp = number[a];
            number[a] = number[randomNumber];
            number[randomNumber] = temp;
        }
        //遍历数组
        for (int i= 0; i< number.length; i++){
            System.out.println(number[i]);
        }
    }
}
