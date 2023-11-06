package com.tiheima.p14_extends_constructor;

public class Test2 {
    public static void main(String[] args) {
        //目标：搞清楚子类构造器为什么要调用父类构造器，有啥应用场景
        Teacher t1 = new Teacher("张三", 18, "java");
        System.out.println(t1.getName());
        System.out.println(t1.getAge());
        System.out.println(t1.getSkill());

    }
}
class Teacher extends People{
    private String skill;

    public Teacher(String name, int age, String skill) {
        super(name, age); //因为子类创建对象要用到父类的成员变量
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
class People{
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
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
}
