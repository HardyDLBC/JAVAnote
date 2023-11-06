package com.itheima;

import java.util.Random;
import java.util.Scanner;

public class GrabRedEnvelopeMethod2 {
    public static void main(String[] args) {
        //抢红包方法2: 先把5个红包随机排序，然后依次遍历
        int[] redEnvelope ={9,666,188,520,99999};
        randomEnvelope(redEnvelope);
    }
    public static void randomEnvelope(int[] redEnvelope){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<redEnvelope.length; i++){
            int number = r.nextInt(redEnvelope.length);
            int temp = redEnvelope[i];
            redEnvelope[i] = redEnvelope[number];
            redEnvelope[number] = temp;
        }
        for (int i=0; i<redEnvelope.length; i++){
            System.out.print("Please enter any words to draw a raffle: ");
            sc.next();
            System.out.println("Congratulation! You get $"+ redEnvelope[i]);
            }
        System.out.println("End");
    }
}
