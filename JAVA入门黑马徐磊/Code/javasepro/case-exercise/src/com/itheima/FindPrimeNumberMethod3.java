package com.itheima;

public class FindPrimeNumberMethod3 {
    public static void main(String[] args) {
        //拆分法： 把困难的项目拆分成多个类解决需求
        for (int i=101; i<=200; i++){
            //如果i是素数，输出  如果不是，不输出
            if (check(i)){
                System.out.println(i);
            }
        }
    }
    public static boolean check(int data){
        for (int i=2; i<=data/2;i++){
            if (data%i == 0){
                return false;
            }
        }
        return true;
    }
}
