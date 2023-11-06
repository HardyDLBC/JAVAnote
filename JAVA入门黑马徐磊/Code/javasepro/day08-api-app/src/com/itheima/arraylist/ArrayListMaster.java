package com.itheima.arraylist;

import java.util.ArrayList;

public class ArrayListMaster {
    public static void main(String[] args) {
        //目标： 掌握如何创建ArrayList集合的对象，并熟悉ArrayList提供的常用方法

        //1. 创建一个ArrayList的集合对象
        ArrayList<String> list = new ArrayList<String>(); //理解成厕所：不加标签男女都能进。加了男标签只能男的进

        list.add("heima");
        list.add("aa");
        System.out.println(list); //没有打印地址，而是像String一样通过地址找到值   vs  数组会打印地址

        //2.往集合中的某个索引位置处添加一个数据
        list.add(1, "bbb");
        System.out.println(list);

        //3.根据索引获取集合中某个索引位置处的恶值
        System.out.println(list.get(1));

        //4. 获取集合的大小（返回集合中存储的元素个数）
        int listLength = list.size();
        System.out.println(listLength);

        //5. 根据索引删除集合中的某个元素值，并返回被删除的元素给我们
        System.out.println(list.remove(1));
        System.out.println(list);

        //6. 直接删除某个元素值，并删除成功返回true，反之返回false。 如果有多个一样的元素，默认只删除第一次出现的
        System.out.println(list.remove("aa"));
        System.out.println(list);

        //7. 修改某个索引位置处的数据，并修改后会返回原来的值
        System.out.println(list.set(0,"heima666"));
        System.out.println(list);

    }
}
