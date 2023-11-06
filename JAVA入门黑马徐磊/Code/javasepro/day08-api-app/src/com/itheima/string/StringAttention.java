package com.itheima.string;

public class StringAttention {
    public static void main(String[] args) {
        //目标：搞清楚String使用时的几个注意事项
        //2. 只要是以"..."（双引号）方式写出的字符串对象，会存储到字符串常量池（堆内存），且相同内容的字符串只存储一份
        String s1 = "abc"; //abc被存在常量池中
        String s2 = "abc"; //发现常量池已经有abc了，不会再多存，只会让s2指向s1中abc的地址。
        System.out.println(s1==s2); //所以两个地址相同

        //3. 通过new方式创建字符串对象，每new一次就会产生一个新的对象放在堆内存中
        String s3 = "qaz";
        String s4 = new String(s3);
        String s5 = new String(s3);
        System.out.println(s4==s5); //两个地址不一样
    }
}
