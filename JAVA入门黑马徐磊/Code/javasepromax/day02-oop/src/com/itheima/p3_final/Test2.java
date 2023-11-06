package com.itheima.p3_final;

public class Test2 {
    public static final String SCHOOL_NAME ="heima"; //在这里做集中配置，不用在下面每个print都改 => 可维护性好
    public static void main(String[] args) {
        //目标：认识常量
        System.out.println(SCHOOL_NAME); //SCHOOL_NAME编译后会储存成heima,不用去上面调用
        System.out.println(SCHOOL_NAME);
        System.out.println(SCHOOL_NAME);
        System.out.println(SCHOOL_NAME);
    }
}
