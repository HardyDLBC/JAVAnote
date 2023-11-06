package com.itheima.loop;

public class LoopNestedDemo7 {
    public static void main(String[] args) {
        //掌握循环嵌套流程
        //假如你有老婆，你犯错了，你老婆罚你说三天，每天说5句我爱你；
        for (int day = 1; day <=3; day++){
            System.out.println("第" +day+ "天");
            for (int say = 1; say <=5; say++){
                System.out.println("第"+say+"遍我爱你");
            }
        }

        int day2 = 1;
        while (day2 <=3){
            System.out.println("第"+day2+"天:");
            day2++;
            int say2 = 1;
            while (say2 <= 5){
                System.out.println("我爱你");
                say2++;
            }
        }
    }
}
