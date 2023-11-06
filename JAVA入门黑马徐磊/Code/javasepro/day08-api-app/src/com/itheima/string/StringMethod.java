package com.itheima.string;

public class StringMethod {
    public static void main(String[] args) {
        // 目标： 快速熟悉String提供的处理字符串的常用方法
        String s1 = "黑马JAVA";
        //1. 获取字符串长度
        System.out.println(s1.length());

        //2. 提取字符串中某个索引位置处的字符
        System.out.println(s1.charAt(1));
        //字符串的遍历
        for (int i=0; i<s1.length(); i++){
            System.out.println(s1.charAt(i));
        }

        //3. 把字符串转换成字符数组，在进行遍历
        char[] c1 = s1.toCharArray();
        for (int i=0; i<c1.length; i++){
            System.out.println(c1[i]);
        }

        //4. 判断字符串内容，内容一样就返回true
        String s2 = new String("黑马667");
        System.out.println(s1.equals(s2));

        //5. 判断字符串内容，内容一样就返回true(忽略大小写)
        String s3 = new String("黑马Java");
        System.out.println(s3.equalsIgnoreCase(s1));

        //6. 截取字符串内容，包前不包后
        System.out.println(s1.substring(1,5));

        //7. 从当前索引位置截取到字符串的末尾
        System.out.println(s1.substring(2));

        //8. 把字符串中的某个内容替换成新内容，并返回新的字符串对象给我们
        System.out.println(s1.replace("A","*"));

        //9. 判断字符串中是否包含某个关键字
        System.out.println(s1.contains("A"));

        //10. 判断字符串是否以某个字符串开头
        System.out.println(s1.startsWith("黑马"));

        //11. 把字符串按照某个指定内容分割成多个字符串，放到一个字符串数组中返回给我们
        String s4 ="倚天剑,屠龙刀,一阳指,降龙掌";
        String[] s5 = s4.split(",");
        for (int i = 0; i < s5.length; i++) {
            System.out.println(s5[i]);
        }

    }
}
