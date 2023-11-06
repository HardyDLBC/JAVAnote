package com.itheima.random;

import java.util.Random;
import java.util.Scanner;

public class RandomGame {
    public static void main(String[] args) {
        Random r = new Random();
        int number = r.nextInt(100)+1;
        System.out.println(number);

        while (true){
            System.out.println("Please enter your number from 1 to 100:");
            Scanner sc = new Scanner(System.in);
            int guess = sc.nextInt();

            if (guess < number){
                System.out.println("Your number is low");
            }

            if (guess > number){
                System.out.println("Your number is high");
            }

            if (guess == number){
                System.out.println("You are right.");
                break;
            }

        }


    }
}
