package com.itheima.p9_interface_case;

import java.util.ArrayList;

public class StudentOperatorImpl1 implements StudentOperator{

    @Override
    public void printAllInfo(ArrayList<Student> students) {
        System.out.println("---The information of all students in the class is as following---");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println("Name: "+s.getName()+", Gender: "+ s.getSex()+", Mark: "+ s.getScore());
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void printAverageScore(ArrayList<Student> students) {
        double allScore = 0.0;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            allScore += s.getScore();
        }
        System.out.println("Average mark: "+(allScore)/students.size());
    }
}
