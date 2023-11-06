package com.itheima;

import java.util.Scanner;

public class PlaneTicketCase1 {
    public static void main(String[] args) {
        //目标： 完成买飞机票的案例
        System.out.print("Please enter the month you want to buy: ");
        Scanner sc = new Scanner(System.in);
        int month = sc.nextInt();
        System.out.print("What is the type of plane? First or Economy: ");
        String type = sc.next();
        System.out.print("How much is the original price: ");
        double price = sc.nextInt();

        double discountPrice = ticket(month, type, price);
        System.out.print("The discounted price is " + discountPrice);

    }

    public static double ticket(int month, String type, double price){
        if ( month<=10 && month>=5){
            switch (type){
                case"First":
                    price *= 0.9;
                    break;
                case "Economy":
                    price *= 0.85;
                    break;
            }
        }

        if ( (month <=12 && month>=11) || (month >=1 && month<=4)){
            switch (type){
                case "First":
                    price *= 0.7;
                    break;
                case "Economy":
                    price *= 0.65;
                    break;
            }
        }
        return price;
    }
}
