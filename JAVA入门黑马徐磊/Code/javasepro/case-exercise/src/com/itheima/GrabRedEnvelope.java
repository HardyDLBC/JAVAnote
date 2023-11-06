package com.itheima;

import java.util.Random;
import java.util.Scanner;

public class GrabRedEnvelope {
    public static void main(String[] args) {
        int[] redEnvelope = {9,666,188,520,99999};
        grab(redEnvelope);
    }

    public static void grab(int[] redEnvelop){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        //1. 定义for循环抽奖5次
        for (int i=0; i< redEnvelop.length; i++){
            //2. 提示粉丝抽奖
            System.out.print("Please enter any word to draw a raffle: ");
            sc.next();
            //3. 为当前粉丝随机一个红包
            //⚠️： 因为如果抽到0，那么循环要继续。所以用while
            while (true) {
                int number = r.nextInt(5);
                if (redEnvelop[number] != 0){
                    //4. 判断红包不是0
                    System.out.println("Congratulation! You get $"+redEnvelop[number]);
                    redEnvelop[number]=0;
                    break;
                }
            }
        }
        System.out.println("End");
    }
}
