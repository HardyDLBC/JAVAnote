package com.itheima.loop;

public class DoWhileDemo5 {
    public static void main(String[] args) {
        //掌握do while
        //打印多行
        int i = 0;
        do {
            System.out.println("hello");
            i++;
        }while (i < 3);
        System.out.println(i);

        int a = 0;
        for ( ; a<3; a++ ){
            System.out.println(a);
        }
        System.out.println("final a = "+ a);

    }
}
