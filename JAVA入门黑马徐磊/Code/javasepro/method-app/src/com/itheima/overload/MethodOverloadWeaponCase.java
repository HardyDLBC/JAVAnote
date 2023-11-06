package com.itheima.overload;

public class MethodOverloadWeaponCase {
    public static void main(String[] args) {
        weapon();
        weapon("Japan");
        weapon("Japan",2);
    }
    public static void weapon(){
        System.out.println("Launch a weapon");
    }
    public static void weapon(String region){
        System.out.println("Launch a weapon in "+ region);
    }
    public static void weapon(String region, int num){
        System.out.println("Launch "+ num +" weaon in "+region);
    }
}
