package com.tiheima.p3_util;

import java.util.Random;

public class MyUtil {
    private MyUtil(){

    }
    public static String createCode(int n){
        String code = "";
        String data = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random r1 = new Random();
        for (int i = 0; i < n; i++) {
            int index = r1.nextInt(data.length());
            code+= data.charAt(index);
        }
        return code;
    }
}
