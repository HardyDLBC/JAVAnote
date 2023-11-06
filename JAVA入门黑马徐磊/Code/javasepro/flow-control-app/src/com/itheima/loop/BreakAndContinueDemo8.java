package com.itheima.loop;

public class BreakAndContinueDemo8 {
    public static void main(String[] args) {
         //掌握break和continue作用
        //假如你犯错了，你老婆让你说5句我爱你。但是说到第三句心软了，让你别说了。
        for (int i = 1; i <=5; i++){
            System.out.println("i love you");
            if (i ==3){
                break;
            }
        }

        //假如你老婆罚你洗碗5天。但第3天表现很好，不用洗。但是之后觉得不解恨，第4天以后继续洗碗
        for (int a = 1; a <= 5; a++){

            if (a == 3){
                System.out.println("do not wash bowls");
                continue;
            }
            System.out.println("number "+ a +" day wash bowls");
        }
    }
}
