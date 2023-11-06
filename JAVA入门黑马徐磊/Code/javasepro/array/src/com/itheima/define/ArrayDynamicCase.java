package com.itheima.define;

import java.util.Scanner;

public class ArrayDynamicCase {
    public static void main(String[] args) {
        //案例： 某歌唱比赛，开发一个系统： 可以录入6名评委的打分，录入完毕后立即输出平均分作为选手得分
        double[] mark = new double[6];
        double average = 0;
        double total = 0;
        for (int i = 0; i < 6; i++) {
            System.out.println("Please enter your mark: ");
            Scanner sc = new Scanner(System.in);
            double marker = sc.nextDouble();

            mark[i] = marker;
            int a = i+1;
            System.out.println("number "+ a +" reviewer's mark is " + marker);

            total += marker;
            average = total/mark.length;
        }
        System.out.println("The average mark is " + average);
    }
}
