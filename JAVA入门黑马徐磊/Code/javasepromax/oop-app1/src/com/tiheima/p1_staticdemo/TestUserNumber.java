package com.tiheima.p1_staticdemo;

public class                                                                                                                                                                                                                                                                                                                                                                                                                                           TestUserNumber {
    public static void main(String[] args) {
        //目标：通过案例理解类变量的应用场景
        User u1 = new User();
        User u2 = new User();
        int number = User.number;
        System.out.println(number);
    }
}
