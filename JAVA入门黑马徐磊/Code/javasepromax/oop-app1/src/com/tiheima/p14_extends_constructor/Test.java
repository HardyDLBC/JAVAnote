package com.tiheima.p14_extends_constructor;

class F{
//    public F(){
//        System.out.println("===父类F的无参数构造器执行了===");
//    }

    public F(String name){ //只注释无参构造器，java会默认有一个无参数构造器。只有当存在有参数构造器时，才没有无参数构造器。
                            //但是没有无参数构造器就会报错。 因为子类的构造器第一行默认会访问父类的无参构造器。
    }
}

class Z extends F{
    public Z(){
        super("播妞"); //默认存在的，写不写都有 => 父类的无参构造器
        System.out.println("===子类Z的无参数构造器执行了===");
    }
    public Z(String name){
        super("播妞"); //默认存在的，写不写都有 => 父类的无参构造器
        System.out.println("===子类Z的有参数构造器执行了===");
    }
}

public class Test {
    public static void main(String[] args) {
        //目标：认识子类构造器的特点，掌握这个特点的常见应用场景
        Z z1 = new Z();
        Z z2 = new Z("张三");
    }
}
