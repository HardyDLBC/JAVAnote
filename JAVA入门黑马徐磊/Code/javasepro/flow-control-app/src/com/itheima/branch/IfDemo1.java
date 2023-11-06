package com.itheima.branch;

public class IfDemo1 {
    public static void main(String[] args) {
        //掌握if三种形式的用法
        //测量用户体温
        double tem = 35;
        if (tem >= 37 ){
            System.out.println("发烧");
        }else {
            System.out.println("正常");
        }

        int mark = 80;
        if (mark >= 90){
            System.out.println("A");
        }else if (mark >= 80){
            System.out.println("B");
        }else if (mark >=70){
            System.out.println("C");
        } else if (mark >= 60) {
            System.out.println("D");
        }else {
            System.out.println("D");
        }

        String week = "周刘";
        switch (week){
            case "周一":
                System.out.println("埋头苦干");
                break;
            case "周二":
                System.out.println("帮忙");
                break;
            case  "周三":
                System.out.println("啤酒");
                break;
            default:
                System.out.println("啥也不干");
        }
    }
}
