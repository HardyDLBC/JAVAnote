package com.itheima.p9_interface_case;

import java.util.ArrayList;

public class ClassManager {
    private ArrayList<Student> students = new ArrayList<>();
    private StudentOperator studentOperator = new StudentOperatorImpl2();
    public ClassManager(){ //初始化班级对象时候，同时创建一些学生对象放到集合容器里。相当于把学生准备好
        /*Student s1 = new Student("Lisa", 'Female',99);
        students.add(s1); 要简化*/
        students.add(new Student("Lisa",'F',99));
        students.add(new Student("Lily",'F',100));
        students.add(new Student("Ken",'M',80));
        students.add(new Student("Chris",'M',60));
    }
    //因为要支持灵活切换方案，所以不能在这里写方法。在这里写方法就是固定死的。切换的话只能改方法内部
    //打印全班全部学生的信息
    public void printInfo(){
        studentOperator.printAllInfo(students);

    }
    //打印全班全部学生的平均分
    public void printScore(){
        studentOperator.printAverageScore(students);
    }
}
