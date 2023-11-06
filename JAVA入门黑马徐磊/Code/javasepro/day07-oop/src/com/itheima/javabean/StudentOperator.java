package com.itheima.javabean;

public class StudentOperator {
    private Student student; //定义一个Student类型的成员变量student
    public StudentOperator(Student student){
        this.student = student;
    }
    public void printPass(){
        if (student.getScore()>=60){
            System.out.println(student.getName()+" passed");
        }else {
            System.out.println(student.getName()+" failed");
        }
    }
}
