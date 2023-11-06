package com.itheima.p9_interface_case;

import java.util.ArrayList;

public class StudentOperatorImpl2 implements StudentOperator {

    @Override
    public void printAllInfo(ArrayList<Student> students) {
        System.out.println("---The information of all students in the class is as following---");
        int countF = 0;
        int countM = 0;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println("Name: "+s.getName()+", Gender: "+ s.getSex()+", Mark: "+ s.getScore());
            if (s.getSex() == 'M'){ //用equal更稳妥
                countM++;
            }else {
                countF++;
            }
        }
        System.out.println("The number of male: "+countM+", THe number of female: "+countF);
        System.out.println("The total number of students: "+ students.size());
        System.out.println("--------------------------------");
    }

    @Override
    public void printAverageScore(ArrayList<Student> students) {
        double allScore = 0.0;
        double max = students.get(0).getScore();
        double min = students.get(0).getScore();
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getScore() > max){
                max = s.getScore();
            }
            if (s.getScore() < min){
                min = s.getScore();
            }
            allScore += s.getScore();
        }
        System.out.println("The highest score: "+max);
        System.out.println("The lowest score: " +min);
        System.out.println("Average mark: "+(allScore-max-min)/(students.size()-2));
    }
}
