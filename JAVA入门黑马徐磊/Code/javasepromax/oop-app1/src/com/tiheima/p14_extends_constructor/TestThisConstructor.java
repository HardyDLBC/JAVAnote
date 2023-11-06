package com.tiheima.p14_extends_constructor;

public class TestThisConstructor {
    public static void main(String[] args) {
     Student s2 = new Student("张三",18); //当没有填学校的时候，我想自动补充学校为黑马大学
        System.out.println(s2.getName());
        System.out.println(s2.getAge());
        System.out.println(s2.getSchool());
    }
}

class Student{
    private String name;
    private int age;
    private String school;

    public Student() {
    }

    public Student(String name, int age){
        this(name, age, "黑马大学"); //调用三个参数的控制器 => 用兄弟构造器的代码，满足自己需求。可以减少代码量，增加性能
    }

    public Student(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
