package com.itheima.encapsulation;

public class Student {
    private double score;
    public void setScore(double score){
        if (score <=100 && score >=0) {
            this.score = score;
        }else {
            System.out.println("The score range from 0 to 100");
        }
    }

    public double getScore(){
        return score;
    }
}
