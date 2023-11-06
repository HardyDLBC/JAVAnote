package com.itheima.string;

import java.io.StringReader;
import java.util.Scanner;

public class StringLogin {
    public static void main(String[] args) {
        //目标： 完成用户登陆案例
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String username = sc.next();
        System.out.print("Please enter your password: ");
        String password = sc.next();
        login(username, password);

    }

    public static void login(String username, String password){
        String rightUsername = "itheima";
        String rightPassword = "123456";
        for (int i = 0; i < 3; i++) {
            if (username.equals(rightUsername) && password.equals(rightPassword)){
                //上面不能用==，因为==是比较地址，cuz双引号的在常量池里，用户输入的存在堆里
                System.out.println("Welcome to system!");
                break;
            }else {
                System.out.println("username or password is wrong");
            }
        }
    }
}

