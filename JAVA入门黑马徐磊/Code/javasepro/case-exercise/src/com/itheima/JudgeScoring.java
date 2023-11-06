package com.itheima;

import java.util.Scanner;

public class JudgeScoring {
    public static void main(String[] args) {
        //目标： 完成评委打分案例
        int num = 6;
        System.out.println("The score of player is "+judgeScoring(num));
    }

    public static double judgeScoring(int num){
        //1. 定义一个动态数组，负责存入评委的打分
        double[] arrScores = new double[num];
        //2. 遍历数组的每个位置，依次录入评委的分数
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<num; i++){
            System.out.print("Please enter the score for number "+(i+1)+" referee: ");
            double enterScore = sc.nextDouble();
            arrScores[i] = enterScore;

        }
        //3. 找出最高分和最低分
        double maxscore = arrScores[0];
        double minscore = arrScores[0];
        double total = 0;
        for (int i=0; i< num; i++){
            double score = arrScores[i];
            if (score >= maxscore){
                maxscore = score;
            }
            if (score <= minscore){
                minscore = score;
            }
            total += score;
        }
        //4. 输出最大值、最小值、平均值
        System.out.println("Remove a maximum value: "+maxscore);
        System.out.println("Remove a minimum value: "+minscore);
        double avescore = (total-maxscore-minscore)/(arrScores.length-2);
        return avescore;
    }
}
