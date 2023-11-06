package com.itheima.thisdemo;

public class Student {
    int score;
    public void printPass(int score){
        if (this.score > score){    //当score冲突时，this的score代表对象的score
            System.out.println("Congratulation! You passed.");
        }else {
            System.out.println("You failed");
        }
    }
}
