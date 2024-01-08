# D7. 面向对象基础



## 03. 对象在计算机中的执行原理

### 多个对象在计算机中的执行原理

以案例计算成绩为例：

1. 先运行Test类，把Test.class放到方法区里
2. main方法放到栈内存中运行
3. 创建学生对象时用到Student类，把Student.class放到方法区里
4. 变量s1放到栈内存中，但new出来的变量要放到堆内存中。 s1变量里面记住的是学生对象的地址



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-28%2021.24.48.png?raw=true" style="zoom:;" />

补充：引用类型的变量

- s1变量中存储的是对象的地址，因此s1称为引用类型的变量



## 04. 类和对象的一些注意事项

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-28%2022.00.52.png?raw=true" style="height:500px;" />

- 注意点5的原理 

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-28%2022.03.20.png?raw=true)

- 注意点6的原理(堆内存中没有变量指向的变量会被java自动删除)

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-28%2022.05.06.png?raw=true)

## 05. this关键字



### this是什么？

- this就是一个变量。可以用在方法中，拿到当前的对象。
  哪个对象调用方法，this就指向哪个对象（拿到哪个对象）

```java
package com.itheima.thisdemo;

public class Test {
    public static void main(String[] args) {
        // 目标： 认识this，掌握this的应用场景
        Student s1 = new Student();
        System.out.println(s1);
        s1.printThis();

        System.out.println("---------------------");
        Student s2 = new Student();
        System.out.println(s2);
        s2.printThis();
    }
}

```

```java
package com.itheima.thisdemo;

public class Student {
    public void printThis(){    //定义一个方法
        System.out.println(this);
    }
}
```

```
Output

com.itheima.thisdemo.Student@7a81197d
com.itheima.thisdemo.Student@7a81197d
---------------------
com.itheima.thisdemo.Student@5ca881b5
com.itheima.thisdemo.Student@5ca881b5

Process finished with exit code 0
```



### this的执行原理

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-28%2022.37.47.png?raw=true)

### this有啥应用场景？

- this用来解决： <font color ="Blue">变量名称冲突</font>（成员变量和形参变量冲突）

```java
package com.itheima.thisdemo;

public class Test {
    public static void main(String[] args) {
        // 目标： 认识this，掌握this的应用场景
        Student s3 = new Student();
        s3.score = 90;
        s3.printPass(50);
    }
}
```

```java
package com.itheima.thisdemo;

public class Student {
    int score;
    public void printPass(int score){
        if (this.score > score){    //当参数score冲突时，this的score代表对象的score <=> 成员变量 > 形参变量
            System.out.println("Congratulation! You passed.");
        }else {
            System.out.println("You failed");
        }
    }
}
```

```
Output:

Congratulation! You passed.

Process finished with exit code 0
```



## 06. 构造器

### 构造器是什么样子？

```java
public class Student{
  //构造器
  public Student(){	//没有void，没有返回值类型。名字要和类名一样
    ...
  }
}
```



### 构造器有什么特点？

- 创建对象时，对象会去调用匹配的构造器（根据参数）
  ```java
  Student s = new Student();
  Student s = new Student("张三", 90)
  ```



### 构造器的常见应用场景

- 创建对象时，同时完成对对象成员变量（属性）的初始化赋值（不用手动一个一个"s1.name=....")

```java
package com.itheima.constructor;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();	//“（）”要调用无参数构造器

        Student s2 = new Student("张三", 90);
        System.out.println(s2.name);
        System.out.println(s2.score);
    }
}
```

```java
package com.itheima.constructor;

public class Student {
    String name;
    double score;
    public Student(){
        System.out.println("无参数构造器被触发执行了");
    }

    public Student(String name, int score){
        System.out.println("有参数构造器被执行了");
        this.name = name;
        this.score = score;
    }
}

```



### 构造器的注意事项

- 类在设计时，如果不写构造器，在调用类是会自动生成一个无参数构造器
- 一旦定义了有参数构造器，Java就不会自动生成无参数构造器。要手写一个无参数构造器





## 07. 封装

### 什么是封装？

- 就是用类设计对象处理某一个事物的数据时，应该要把处理的数据和处理这些数据的方法，设计到一个对象中去。



### 封装的设计规范

- 合理隐藏，合理暴露
  因为设计对象的class中有多个成员变量，多个成员方法。合理暴露和隐藏
  e.g. 汽车🚗不会把发动机暴露。只会暴露方向盘.



```java
package com.itheima.encapsulation;

import com.itheima.encapsulation.Student;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setScore(90);
        System.out.println(s1.getScore());
    }
}

```

```java
package com.itheima.encapsulation;

public class Student {
    private double score;
    public void setScore(double score){
        if (score <=100 && score >=0) {
            this.score = score;
        }else {
            System.out.println("The score range from 0 to 100");
        }
    }

    public double getScore(){
        return score;
    }
}

```

```java
Output: 
90.0

Process finished with exit code 0
```



## 08. 实体JavaBean(实体类)



### 什么是实体类？

- 是一个特殊形式的类。
  这个类中的成员变量都要私有，并且要对外提供相应的getXxx，SetXxx方法
- 类中必须要有一个公共的无参的构造器

 ```java
 package com.itheima.javabean;
 
 import com.itheima.javabean.Student;
 
 public class Test {
     public static void main(String[] args) {
         // 目标： 掌握实体类的书写要求，特点，应用场景。
         Student s1= new Student();
         s1.setScore(90);
         s1.setName("播妞");
         System.out.println(s1.getName());
         System.out.println(s1.getScore());
     }
 }
 ```

```java
package com.itheima.javabean;

public class Student {
    // 1. 必须私有成员变量，并为每个成员变量都提供get set方法
    private String name;
    private double score;

    // 2. 必须为类提供一个无参数构造器

    public Student() {
    }

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

```

```java
Output:

播妞
90.0

Process finished with exit code 0
```



### 实体类有啥应用场景

- 实体类(左)只负责数据存取，而对数据的处理交给其他类来完成，以实现数据和数据业务处理相分离

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-29%2016.43.04.png?raw=true)



e.g. <font color = 'blue'>左侧调用，右上存储，右下处理</font>

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-29%2017.13.17.png?raw=true)



```java
package com.itheima.javabean;

import com.itheima.javabean.Student;

public class Test {
    public static void main(String[] args) {
        // 目标： 掌握实体类的书写要求，特点，应用场景。
        Student s1= new Student();
        s1.setScore(90);
        s1.setName("播妞");
        System.out.println(s1.getName());
        System.out.println(s1.getScore());

        StudentOperator operator = new StudentOperator(s1);
        operator.printPass();
    }
}
```



```java
package com.itheima.javabean;

public class Student {
    // 1. 必须私有成员变量，并为每个成员变量都提供get set方法
    private String name;
    private double score;

    // 2. 必须为类提供一个无参数构造器

    public Student() {
    }

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

```

```java
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

```

```
播妞
90.0
播妞 passed

Process finished with exit code 0
```

  

## 09. 面向对象综合案例--模仿电影信息系统



### 需求：

- 展示系统中的全部电影（每部电影展示：名称，价格）
  1. 1, "水门桥", 38.9, 9.8, "徐克", "吴京", "12万人想看"
  2. 2, "出拳吧", 39, 7.8, "唐晓白", "田雨", "3.5万人想看"
  3. 3, "月球陨落", 42, 7.9, "罗兰", "贝瑞", "17.9万人想看"
  4. 4, "一点就到家", 35, 8.7, "许宏宇", "刘昊然", "10.8万人想看"
- 允许用户根据电影编号（id）查询出某个电影的详细信息

### 目标

- 使用面向对象编程实现以上2个需求



​	<font color = 'brown'>快捷键：movie.fori => 自动打印movie数组的for循环</font>

```java
package com.itheima.moviecase;

public class Test {
    public static void main(String[] args) {
        // 1. 设计一个电影实体类，用于以后创建电影对象，保存电影数据
        // 2. 设计一个电影操作类，用于处理电影数据
        // 3. 准备全部电影资料
        Movie[] movies = new Movie[4];
        movies[0] = new Movie(1, "水门桥", 38.9, 9.8, "徐克", "吴京", 12);
        movies[1] = new Movie(2, "出拳吧", 39, 7.8, "唐晓白", "田雨", 3.5);
        movies[2] = new Movie(3, "月球陨落", 42, 7.9, "罗兰", "贝瑞", 17.9);
        movies[3] = new Movie(4, "一点就到家", 35, 8.7, "许宏宇", "刘昊然", 10.8);

        MovieOperator operator = new MovieOperator(movies);
        operator.printALlMovies();

        operator.searchMovieById(2);
    }
}

```

```java
package com.itheima.moviecase;

public class Movie {
    private int id;
    private String name;
    private double price;
    private double rating;
    private String director;
    private String actor;
    private double favorite;

    public Movie() {
    }

    //为了方便赋值，创建有参构造器
    public Movie(int id, String name, double price, double rating, String director, String actor, double favorite) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.director = director;
        this.actor = actor;
        this.favorite = favorite;
    }

    //提供get set方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getFavorite() {
        return favorite;
    }

    public void setFavorite(double favorite) {
        this.favorite = favorite;
    }
}

```

```java
package com.itheima.moviecase;

public class MovieOperator {
    //构造器用于初始化成员变量，操作类没有成员变量 + 操作类不是实体类，不用定义构造器
    private Movie[] movies; //定义一个电影类型的数组，用于装电影对象
    public MovieOperator(Movie[] movies){ //定义一个电影方法构造器，直接构建电影方法
        this.movies = movies;
    }

    // 第1个方法，展示全部电影信息
    public void printALlMovies(){
        System.out.println("------The movie information is as follows------ ");
        for (int i = 0; i < movies.length; i++) {
            Movie m = movies[i]; //用m存储第i个movie的地址
            System.out.println("id: "+ m.getId());
            System.out.println("name: "+ m.getName());
            System.out.println("price: "+ m.getPrice());
            System.out.println("----------------------------");
        }
    }

    //第2个方法，根据电影的编号查询出该电影的详情信息
    public void searchMovieById(int id){
        for (int i = 0; i < movies.length; i++) {
            Movie m = movies[i];
            if (m.getId() == id){
                System.out.println("The specific information is as follows:");
                System.out.println("id: "+ m.getId());
                System.out.println("name: "+ m.getName());
                System.out.println("price: "+ m.getPrice());
                System.out.println("rating: "+ m.getRating());
                System.out.println("director: "+ m.getDirector());
                System.out.println("actor: "+ m.getActor());
                System.out.println("favorite: "+ m.getFavorite());
                return; //跳出当前方法，写break不行，跳出当前循环
            }

        }
        System.out.println("Nothing for this movie");
    }
}

```



## 10. 成员变量，局部变量的区别



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-30%2022.13.44.png?raw=true)



### 补充：如何定义类

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-30%2023.31.11.png?raw=true)





# D8. 常用API



## 1. 包



### 什么是包？

- 包是分门别类管理不同程序的，类似于文件夹，建包有利于程序的管理和维护
- 建包的语法格式 (IDEA是自动导入的) 告诉Student类编译以后放到哪个包里面

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2000.52.16.png?raw=true)



## 2. String



### String概述

#### 为什么要学字符串的处理？

- e.g. 登陆时把账号密码和后台正确的账号密码匹配
- e.g. 玩游戏发的脏话编程***

#### String创建对象封装字符串数据的方式

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2014.23.00.png?raw=true)

``` java
package com.itheima.string;

public class StringDemo1 {
    public static void main(String[] args) {
        // 目标： 掌握创建String对象，并封装要处理的字符串的两种方式

        // 1. 直接双引号得到字符串对象，封装字符串数据
        String name = "itheima";
        System.out.println(name);

        // 2. new String创建字符串对象，并调用构造器初始化字符串
        String s1 = new String();
        System.out.println(s1); // ""什么都没有

        String s2 = new String("itheima666");
        System.out.println(s2);

        char[] c1 = {'a','b','c'};
        String s3 = new String(c1);
        System.out.println(s3);

        byte[] b1 ={97, 98,99};
        String s4 = new String(b1);
        System.out.println(s4);
    }
}

```



### String的常用方法



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2021.31.35.png?raw=true)

```java
package com.itheima.string;

public class StringMethod {
    public static void main(String[] args) {
        // 目标： 快速熟悉String提供的处理字符串的常用方法
        String s1 = "黑马JAVA";
        //1. 获取字符串长度
        System.out.println(s1.length());

        //2. 提取字符串中某个索引位置处的字符
        System.out.println(s1.charAt(1));
        //字符串的遍历
        for (int i=0; i<s1.length(); i++){
            System.out.println(s1.charAt(i));
        }

        //3. 把字符串转换成字符数组，在进行遍历
        char[] c1 = s1.toCharArray();
        for (int i=0; i<c1.length; i++){
            System.out.println(c1[i]);
        }

        //4. 判断字符串内容，内容一样就返回true
        String s2 = new String("黑马667");
        System.out.println(s1.equals(s2));

        //5. 判断字符串内容，内容一样就返回true(忽略大小写)
        String s3 = new String("黑马Java");
        System.out.println(s3.equalsIgnoreCase(s1));

        //6. 截取字符串内容，包前不包后
        System.out.println(s1.substring(1,5));

        //7. 从当前索引位置截取到字符串的末尾
        System.out.println(s1.substring(2));

        //8. 把字符串中的某个内容替换成新内容，并返回新的字符串对象给我们
        System.out.println(s1.replace("A","*"));

        //9. 判断字符串中是否包含某个关键字
        System.out.println(s1.contains("A"));

        //10. 判断字符串是否以某个字符串开头
        System.out.println(s1.startsWith("黑马"));

        //11. 把字符串按照某个指定内容分割成多个字符串，放到一个字符串数组中返回给我们
        String s4 ="倚天剑,屠龙刀,一阳指,降龙掌";
        String[] s5 = s4.split(",");
        for (int i = 0; i < s5.length; i++) {
            System.out.println(s5[i]);
        }

    }
}

```

```
Output:

6
马
黑
马
J
A
V
A
黑
马
J
A
V
A
false
true
马JAV
JAVA
黑马J*V*
true
true
倚天剑
屠龙刀
一阳指
降龙掌

Process finished with exit code 0
```



### String使用时的注意事项



1. String对象的内容不可改变，被称为不可变字符串对象

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2023.30.53.png?raw=true)



- 只要是以"..."（双引号）方式写出的字符串对象，会存储到字符串常量池（堆内存），且相同内容的字符串只存储一份



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2023.47.59.png?raw=true)



- 但通过new方式创建字符串对象，每new一次就会产生一个新的对象放在堆内存中



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2023.52.14.png?raw=true)

(可以理解成，每个对象/苹果派都是单独的实体，就算口感外表内在都一样，但仍是两个单独的实体。所以两个拥有不同的地址)



##### 笔试题

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2023.56.29.png?raw=true)



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-10-31%2023.59.54.png?raw=true)

注意： 上面的例子是因为<font color = 'red'>s3是运算出来的，不放在常量池中。放在常量外的堆里</font>

​			下面的例子是因为字符串"a" "b" "c"都是已经确定的，编译器很智能自动转成"abc", 节约计算机性能，减少计算

​			但是上面的例子中的s3不能直接被编译成"abc". 因为s2是个变量，编译时只认为s2是个符号，跑起来时才找到s2的值



### String的应用案例



#### 1. 完成用户登录功能



## ArrayList



### ArrayList快速入门



#### 什么是集合？

- 集合是一种容器，用来装数据的，类似于数组。

  

有数组，为啥还学习集合？

- 数组定义完成并启动后，长度就固定了。

  弊端：e.g. 购物车。如果顾客加购商品到购物车，那么数组长度就要变。需要重新做一个数组； 如果购物车删商品，为了保持连贯性，就要把后边的商品向前移

- 集合大小可变，开发中用的更多。



#### 集合种类有很多

ArrayList集合该怎么学？

1. 创建对象
2. 增删改查的方法
3. 容器的其他特点



范例：

public class ArrayList<E>		//<>代表泛型类 E代表集合对象具体的类型。如果不写<>, 集合就可以存储任意类型的元素



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-01%2022.19.03.png?raw=true)

```java
package com.itheima.arraylist;

import java.util.ArrayList;

public class ArrayListMaster {
    public static void main(String[] args) {
        //目标： 掌握如何创建ArrayList集合的对象，并熟悉ArrayList提供的常用方法

        //1. 创建一个ArrayList的集合对象
        ArrayList<String> list = new ArrayList<String>(); //理解成厕所：不加标签男女都能进。加了男标签只能男的进

        list.add("heima");
        list.add("aa");
        System.out.println(list); //没有打印地址，而是像String一样通过地址找到值   vs  数组会打印地址

        //2.往集合中的某个索引位置处添加一个数据
        list.add(1, "bbb");
        System.out.println(list);

        //3.根据索引获取集合中某个索引位置处的恶值
        System.out.println(list.get(1));

        //4. 获取集合的大小（返回集合中存储的元素个数）
        int listLength = list.size();
        System.out.println(listLength);

        //5. 根据索引删除集合中的某个元素值，并返回被删除的元素给我们
        System.out.println(list.remove(1));
        System.out.println(list);

        //6. 直接删除某个元素值，并删除成功返回true，反之返回false。 如果有多个一样的元素，默认只删除第一次出现的
        System.out.println(list.remove("aa"));
        System.out.println(list);

        //7. 修改某个索引位置处的数据，并修改后会返回原来的值
        System.out.println(list.set(0,"heima666"));
        System.out.println(list);

    }
}

```



# D9. 面对对象高级一



## static

### static修饰成员变量



static 

- 叫静态，可以修饰成员变量、成员方法



成员变量按照有无static修饰，分成两种：

- 类变量
- 实例变量（对象的变量）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-02%2013.53.57.png?raw=true" style="zoom:50%;" />



```java
package com.tiheima.d1_staticdemo;

public class Test {
    public static void main(String[] args) {
        //目标：掌握有无static修饰成员变量的用法，特点

        //1.类变量的用法
        Student.name = "张三";
        Student s1 = new Student();
        s1.name = "李四";

        Student s2 = new Student();
        s2.name = "秋雅";
        System.out.println(s1.name);
        System.out.println(Student.name);

        //2.实例变量的用法，属于每个对象的变量
        s1.age = 18;
        s2.age = 19;
        System.out.println(s1.age);
        System.out.println(s2.age);
    }
}

```

````java
package com.tiheima.d1_staticdemo;

public class Student {
    static String name; //类变量
    int age; //实例变量
}
````

```
Output:

秋雅
秋雅
18
19

Process finished with exit code 0
```





![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-02%2014.15.35.png?raw=true)

​	

### static修饰成员变量的应用场景

类变量的应用场景

- 在开发中，如果某个数据只需要一份，且希望能够被共享（访问、修改），则该数据可以定义成类变量来记住



案例导学：

- 系统启动后，要求用户类可以记住自己创建了多少个用户对象了

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-02%2014.26.23.png?raw=true" style="zoom:50%;" />

```java
package com.tiheima.d1_staticdemo;

public class TestUserNumber {
    public static void main(String[] args) {
        //目标：通过案例理解类变量的应用场景
        User u1 = new User();
        User u2 = new User();
        int number = User.number;
        System.out.println(number);
    }
}

```

```java
package com.tiheima.d1_staticdemo;

public class User {
    public static int number;

    public User(){
        number++;
    }
}

```

```
Output: 

2

Process finished with exit code 0
```



### static修饰成员方法



#### 成员方法的分类

- 类方法
- 实例方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-02%2021.40.37.png?raw=true" style="zoom:50%;" />



```java
package com.tiheima.p2_static_method;

public class Test {
    public static void main(String[] args) {
        //目标： 掌握有无static修饰方法的用法

        //1. 类方法的使用
        Student.printHelloWorld();
        Student s1 = new Student();
        s1.printHelloWorld();

        //2. 实例方法的使用
        int score = 59;
        s1.printPass(score);
    }
}

```

```java
package com.tiheima.p2_static_method;

public class Student {
    int score;
    //类方法
    public static void printHelloWorld(){
        System.out.println("Hello World");
        System.out.println("Hello World");
    }

    //实例方法
    public void printPass(int score){
        System.out.println("成绩" + (score>=60 ? "pass":"not pass") );
    }
}

```



#### 成员方法的执行原理

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-02%2022.00.43.png?raw=true" style="zoom:50%;" />



### static修饰成员方法的应用场景



#### 类方法常见的应用场景

- 做工具类

#### 工具类是什么？

- 工具类中的方法都是一些类方法，每个方法都是用来完成一个功能的，工具类是给开发人员共同使用的。

#### 使用类方法设计工具类有啥好处？

- 提高代码复用（每个程序员都可以用）；调用方便（可以直接用类名调用方法解决问题，不用额外写代码），提高了开发效率

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-02%2022.13.10.png?raw=true)





```java
package com.tiheima.p3_util;

import java.util.Random;

public class LoginDemo {
    public static void main(String[] args) {
        System.out.println(MyUtil.createCode(4));
    }
}

```

```java
package com.tiheima.p3_util;

import java.util.Random;

public class MyUtil {
    public static String createCode(int n){
        String code = "";
        String data = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random r1 = new Random();
        for (int i = 0; i < n; i++) {
            int index = r1.nextInt(data.length());
            code+= data.charAt(index);
        }
        return code;
    }
}

```

```
Output:

cFHH

Process finished with exit code 0
```



#### 为什么工具类中的方法要用类方法，而不用实例方法？

- 实例方法需要创建对象来调用。此时方法只是为了调用方法。对象会占内存，这样会浪费内存。
- 类方法，直接用类名调用即可。调用方便，也能节省内存（一次创立，多次使用）

多学一招：

- 工具类没有创建对象的需求，建议将工具类的构造器进行私有。（因为如果没有写private构造器，main程序里还可以创建对象。如果我们不想让用户创建对象，而是让他直接用类方法，就私有构造器。用户就没办法创建对象 => 专业）

```java
package com.tiheima.p3_util;

import java.util.Random;

public class LoginDemo {
    public static void main(String[] args) {
        System.out.println(MyUtil.createCode(4));
        
        // 报错 MyUtil m1 = new MyUtil();
    }
}

```

```java
package com.tiheima.p3_util;

import java.util.Random;

public class MyUtil {
    private MyUtil(){

    }
    public static String createCode(int n){
        String code = "";
        String data = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random r1 = new Random();
        for (int i = 0; i < n; i++) {
            int index = r1.nextInt(data.length());
            code+= data.charAt(index);
        }
        return code;
    }
}

```



### static的注意事项



#### 使用类方法、实例方法时的几点注意事项

- 类方法中可以直接访问类的成员，不可以直接访问实例成员。
- 实例方法中既可以直接访问类成员，也可以直接访问实例成员。
- 实例方法中可以出现this关键字，类方法中不可以出现this关键字。



### static的应用知识：代码块



#### 代码块概述

- 代码块是类的5大成分之一（成员变量、构造器、方法、代码块、内部类）

#### 代码块分为两种：

- 静态代码块
  格式：static{...}
  特点：类加载时自动执行，由于类只会加载一次，所以静态代码块也只会执行一次。
  作用：完成类的初始化<font color = 'blue'>(优先执行)</font>。例如：对类变量的初始化赋值。
- 实例代码块

  格式：{...}
  特点：每次创建对象时，执行实例代码块，并在构造器前执行
  
  作用：和构造器一样，都是用来完成对象初始化的。e.g. 对实例变量进行初始化赋值（常用于记录创建对象的日志）

```java
package com.tiheima.p5_block;

public class Test {
    public static void main(String[] args) {
        //目标：认识两种代码块，了解他们的特点和基本作用
        System.out.println(Student.i1);
        System.out.println(Student.i1);
        System.out.println(Student.i1);
        System.out.println(Student.s1);

        Student s1 = new Student();
        Student s2 = new Student("a");
        System.out.println(s1.age);
        System.out.println(s2.age);
    }
}

```

```java
package com.tiheima.p5_block;

public class Student {
    public static int i1=666;
    public static String s1="heima";
    //静态代码块
    static {
        System.out.println("静态代码块执行了");
    }

    //实例代码块
    int age;
    {
        System.out.println("实例代码块执行了");
        age = 18; //不建议为实例变量进行初始化赋值
        System.out.println("有人创建了对象"+this); //记录创建对象的日志
    }

    public Student(){
        System.out.println("无参数构造器执行了");
    }

    public Student(String s2){
        System.out.println("有参数构造器执行了");
    }
}

```

````
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=56130:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/oop-app1 com.tiheima.p5_block.Test
静态代码块执行了
666
666
666
heima
实例代码块执行了
有人创建了对象com.tiheima.p5_block.Student@5ca881b5
无参数构造器执行了
实例代码块执行了
有人创建了对象com.tiheima.p5_block.Student@24d46ca6
有参数构造器执行了
18
18

Process finished with exit code 0
````



### static的应用知识：单例设计模式



#### 什么是设计模式(Design pattern) ？

- 一个问题通常有n种解法，其中肯定有一种解法是最优的，这个最优的解法被人总结出来了。称之为设计模式。
- 设计模式有20多种，对应20多种软件开发中会遇到的问题。 

#### 设计模式主要学什么？

1. 解决什么问题
2. 怎么写



#### 单例设计模式

- 确保一个类只有一个对象。（理解：对类做结扎，只能有一个对象）

#### 写法

- 把类的构造器私有
- 定义一个类变量记住类的一个对象
- 定义一个类方法，返回对象

```java
package com.tiheima.p6_singleinstance;

public class Test1 {
    public static void main(String[] args) {
        //目标：掌握单例设计模式的写法
        A a1 = A.getObject();
        System.out.println(a1);
        A a2 = A.getObject();
        System.out.println(a2);
    }
}

```

```java
package com.tiheima.p6_singleinstance;

import java.util.Stack;

public class A {
    //2.定义一个类变量记住一个类的对象
    private static A a = new A();

    //1.私有一个类的构造器
    private A(){

    }

    //3.定义一个类方法，返回对象
    public static A getObject(){
        return a;
    }
}

```

```
Output: 
com.tiheima.p6_singleinstance.A@7a81197d
com.tiheima.p6_singleinstance.A@7a81197d

Process finished with exit code 0

```



#### 单例模式的应用场景和好处？

- e.g. Runtime(代表运行环境	快捷键：ctrl+右键查看源码)
- win电脑的任务管理器 (启动多个任务管理器也只出现一个)
  理解：将任务管理器类比成对象，管理电脑的功能只需要一个就够了。所以设计成单例只有一个对象。



#### 单例设计模式的实现方法很多

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2016.06.47.png?raw=true" style="zoom:50%;" />



#### 懒汉式单例设计模式

- 拿对象时，才开始创建对象



写法

- 把类的构造器私有
- 定义一个类变量用于存储对象
- 提供一个类方法，保证第一次调用时才创建一个对象

```java
package com.tiheima.p6_singleinstance;

public class Test2 {
    public static void main(String[] args) {
        //目标：掌握懒汉式单例的写法
        B b1 = B.getInstance();
        B b2 = B.getInstance();
        System.out.println(b1==b2);
    }
}
```

```java
package com.tiheima.p6_singleinstance;

public class B {
    //2.定义一个类变量用于存储对象
    private static B b;
    //1.把类的构造器私有
    private B(){

    }

    //3.提供一个类方法，保证第一次调用时才创建一个对象
    public static B getInstance(){
        if (b==null){
            b=new B();
            System.out.println("第一次创建对象");
        }
        return b;
    }
}

```

```
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=56012:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/oop-app1 com.tiheima.p6_singleinstance.Test2
第一次创建对象
true

Process finished with exit code 0
```



饿汉式单例 vs 懒汉式单例

频繁用，就饿汉

很少用，就懒汉（节省内存）





## 面对对象的三大特征之二：继承



### 继承快速入门



#### 认识继承

##### 什么是继承？

- Java中提供了一个关键字extends。用这个关键字，可以让一个类和另一个类建立起父子关系。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2021.07.19.png?raw=true" style="zoom:50%;" />

##### 继承的特点

- 子类能继承父类的非私有成员（成员变量、成员方法）。



##### 继承后对象的创建

- 子类的对象是由子类、父类共同完成的。

```java
package com.tiheima.d7_extends;

public class IntroductionExtends {
    public static void main(String[] args) {
        //目标：认识继承，掌握继承的特点
        IntroductionExtendsB i1= new IntroductionExtendsB();
        i1.print1();
        i1.print3();
        //报错i1.print2();
    }
}

```

```java
package com.tiheima.d7_extends;

public class IntroductionExtendsA {
    public int i;
    public void print1(){
        System.out.println("print1");
    }

    private int j;
    private void print2(){
        System.out.println("print2");
    }
}

```

```java
package com.tiheima.d7_extends;

public class IntroductionExtendsB extends IntroductionExtendsA {
    public int k;
    //子类可以继承父类非私有成员
    public void print3(){
        System.out.println("print3");
        print1();
        //报错 print2();

    }
}

```

```
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=63481:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/oop-app1 com.tiheima.d7_extends.IntroductionExtends
print1
print3
print1

Process finished with exit code 0

```



##### 继承的执行原理

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2022.13.38.png?raw=true" style="zoom:50%;" />



##### 继承的好处、应用场景



使用继承有啥好处？

- 减少重复代码的编写。

优化前：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2022.18.05.png?raw=true" style="zoom:50%;" />

优化后：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2022.18.57.png?raw=true" style="zoom:50%;" />



#### 继承相关的注意事项



##### 1. 权限修饰符

- public
- private
- protected
- 缺省（不写）

###### 什么是权限修饰符？

- 就是用来限制类中的成员（成员变量、成员方法、构造器、代码块...）能够被访问的范围

###### 权限修饰符有几种？各自的作用是什么？

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2022.42.48.png?raw=true" style="zoom:50%;" />



##### 2. 单继承、object

###### Java是单继承的。Java中的类不支持多继承，但是支持多层继承

（单继承理解：一个人只能有一个亲爸爸）

（不支持多继承理解：一个类不能同时继承两个父类，一个人不能有两个亲爸爸）

（支持多层继承理解：一个人有爸爸 ，爸爸也有爸爸）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2023.01.43.png?raw=true" style="zoom:50%;" />

为什么Java中的类不支持多继承？

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2023.05.15.png?raw=true" style="zoom:50%;" />



###### Object类

- object类是java所有类的祖宗类。我们写的任何一个类，其实都是object的子类或子孙类。（理解成女娲是所有人的父类）
  => 知道这个，以后创建任何对象时候，这个对象都可以用object类中的方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2023.16.28.png?raw=true)



##### 3. 方法重写



###### 认识方法重写



什么是方法重写？

- 当子类觉得父类中的某个方法不好用，或者无法满足自己的需求时。子类可以重写一个方法名称、参数列表一样的方法，去覆盖父类的这个方法。
- 注意：重写后，方法的访问，Java会遵循就近原则。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2023.24.27.png?raw=true" style="zoom:50%;" />



方法重写的其他注意事项

- 重写小技巧：使用@Override注解，他可以指定java编译器，检查我们方法重写的格式是否正确，代码可读性也会更好。
- 子类重写父类方法时，访问权限必须大于或者等于父类该方法的权限（public > protected > 缺省）。
- 重写的方法返回值类型，必须与被重写方法的返回值类型一样，或者范围更小。
- 私有方法、静态方法不能被重写，如果重写会报错的。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2023.32.51.png?raw=true" style="zoom:50%;" />



###### 方法重写在开发中的常见应用场景

- 子类重写Object类中toString()方法，以便返回对象的内容

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-03%2023.39.40.png?raw=true)

注意：如果发现对象输出的不是地址，而是内容 => 那么这个对象的类肯定是把Object类的toString方法进行了重写。



toString重写快捷键：右键generate toString



##### 4. 子类中访问其他成员的特点

1. 在子类方法中访问其他成员（成员变量、成员方法），是依照<font color="blue">就近原则</font>的。

   ![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2020.42.20.png?raw=true)

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2020.44.14.png?raw=true)



##### 5. 子类构造器的特点



###### 认识子类构造器的特点



子类构造器的特点：

- 子类的全部构造器，都会先调用父类的构造器，再执行自己。



子类构造器是如何实现调用父类构造器的：

- 默认情况下，子类全部构造器的第一行代码都是super() (写不写都有)， 它会调用父类的无参数构造器。
- 如果父类没有无参数构造器，则我们必须在子类构造器的第一行手写super(参数)， 指定去调用父类的有参数构造器。

```java
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

```

```
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=58795:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/oop-app1 com.tiheima.p14_extends_constructor.Test
===子类Z的无参数构造器执行了===
===子类Z的有参数构造器执行了===

Process finished with exit code 0
```



###### 常见的应用场景

super(...)调用父类有参数构造器的常见应用场景是什么？

- 为对象中包含父类这部分的成员变量进行赋值。

```java
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

```

```
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=64191:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/oop-app1 com.tiheima.p14_extends_constructor.Test2
张三
18
java

Process finished with exit code 0
```

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2021.49.57.png?raw=true)



###### 补充知识：this(...)调用兄弟构造器

- 任意类的构造器中，是可以通过this(...)去调用该类的其他构造器的。

```java
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

```

```
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=50834:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/oop-app1 com.tiheima.p14_extends_constructor.TestThisConstructor
张三
18
黑马大学k

Process finished with exit code 0
```

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2022.25.39.png?raw=true" style="zoom:50%;" />



总结：

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2022.29.22.png?raw=true)



# D10. 面向对象高级二



## 面向对象的三大特征之三：多态



### 认识多态

什么是多态？

- 多态是在继承/实现情况下的一种现象，表现为：对象多态、行为多态。

多态的具体代码体现

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2023.10.12.png?raw=true" style="zoom:50%;" />



```java
package com.itheima.p1_polymorphism;

public class Test {
    public static void main(String[] args) {
        //目标：认识多态：对象多态，行为多态
        //1. 对象多态 e.g.人对象，有的是老师，有的是学生
        People p1 = new Teacher(); //人对象可以是老师。老师范围比人范围小/老师也是人
        p1.run(); //行为多态识别技巧：编译看左边，执行看右边
        System.out.println(p1.name); //⚠️：变量没有多态。编译看左边，运行看左边

        People p2 = new Student(); //人对象可以是学生，学生范围比人范围小/学生也是人
        p2.run(); //行为多态识别技巧：编译看左边，执行看右边
        System.out.println(p2.name);//⚠️：变量没有多态。编译看左边，运行看左边
    }
}
```

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2023.22.50.png?raw=true)



多态的前提：

- 有继承/实现关系
- 存在父类引用子类对象
- 存在方法重写



多态的一个注意事项

- 多态是对象、行为的多态，Java中的属性（成员变量）不谈多态。



### 使用多态的好处

使用多态的好处：

- 在多态形式下，右边对象是解耦合的，更便于扩展和维护。（解耦合：模块化       紧耦合：整体化 e.g.汽车轮胎和车焊在一起）
  下面程序中，如果Student()对象有问题，可以直接热插拔。p1.run() 后面的所有代码不受影响

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2023.27.52.png?raw=true" style="zoom:50%;" />



- 定义方法时，使用父类类型的形参，可以接受一切子类对象，扩展性更强、更便利

```java
package com.itheima.p2_polymorphism;

public class Test {
    public static void main(String[] args) {
        //目标：理解多态的好处
        //好处1: 实现解耦合，右边对象可以随时切换，后续业务随机改变。
        People p1 = new Student();
        p1.run();

        //好处2：可以使用父类类型的变量作为形参，可以接收一切子类对象。
        Student s1 = new Student();
        go(s1);

        Teacher t1 = new Teacher();
        go(t1);
    }
    public static void go(People p1){

    }
}

```

```
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=61973:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/day02-oop com.itheima.p2_polymorphism.Test
Student can run quickly

Process finished with exit code 0

```



多态会产生的一个问题，怎么解决？

- 多态下不能使用子类的独有功能。  

  ![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-04%2023.44.52.png?raw=true)
  
  



### 多态下的类型转换问题

（解决多态不能使用子类独有功能问题）

#### 类型转换

- 自动类型转换(多态)：父类 变量名 = new 子类();

- 强制类型转换（父类对象转为子类）：子类 变量名 = (子类)父类变量;`

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2000.00.50.png?raw=true" style="zoom:50%;" />

#### 强制类型转换的注意事项

- 存在继承/实现关系就可以在编译阶段进行强制类型转换，编译阶段不会报错。
- 运行时，如果发现对象的真实类型与强转后的<font color = "green">类型不同，就会报类型转换异常(ClassCastException)的错误出来</font>
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2000.11.29.png?raw=true" style="zoom:50%;" />

强转前，Java建议：

- 使用instanceof关键字，判断当前对象的真实类型，if如果返回true再进行强转。
  instanceof 判断一个对象是否为一个类的实例

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2000.14.30.png?raw=true" style="zoom:50%;" />

```java
package com.itheima.p2_polymorphism;

public class Test {
    public static void main(String[] args) {
        //目标：理解多态的好处
        //好处1: 实现解耦合，右边对象可以随时切换，后续业务随机改变。
        People p1 = new Student();
        p1.run();
        //报错 p1.test(); //多态下存在的的问题：无法直接调用子类的独有功能

        //强制类型转换
        if (p1 instanceof Student){ //instanceof 判断一个对象是否为一个类的实例
            Student s2 = (Student) p1;
            s2.test();
        }else {
            Teacher t2 = (Teacher) p1;
            t2.teach();
        }

        //强制类型转换可能存在的问题：编译阶段有继承/实现关系就可以强制转换，但是运行时可能出现类型转换异常
        //报错 Teacher t2 = (Teacher) p1;//把学生转成老师，就错了。运行时出现了：ClassCastException

        //好处2：可以使用父类类型的变量作为形参，可以接收一切子类对象。
        Student s1 = new Student();
        go(s1);

        Teacher t1 = new Teacher();
        go(t1);
    }
    public static void go(People p1){

    }
}

```

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2000.22.12.png?raw=true" style="zoom:50%;" />

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2000.25.00.png?raw=true)







## final



### 认识final

- final关键字是最终的意思，可以修饰类、方法、变量
- 修饰类：该类被称为最终类，特点是不能被继承了。
- 修饰方法：该方法被称为最终方法，特点是不能被重写了。
- 修饰变量：该变量只能被赋值一次

```java
package com.itheima.p3_final;

public class p3_final {
    /*
    * 常量：public static final修饰的成员变量，建议名称全部大写，多个单词下划线连接
    */
    public static final String SCHOOL_NAME = "heima"; //静态变量

    private final String name = "aaa"; //实例变量 相当于所有对象名字都叫猪八戒（没有意义，知道就行）

    public static void main(String[] args) {
        //目标：认识final的作用
        //3.final可以修饰变量。有且仅能赋值一次
        //变量：
        //  （1）局部变量
        //  （2）成员变量：静态成员变量 实例成员变量
        final double r = 3.14; //局部变量
        //报错 r=2;
        
        final int[] arr = {11,22,33};
        //报错 arr = null; 因为不能改地址
        arr[1] = 222; //不报错。cuz引用类型的变量可以改数据内容

        //报错 schoolName = "baima"; 第二次赋值出错了
    }
    public static void buy(final double z){
        //报错 z = 0.1; 不想让别人给z赋值
    }
}

//1.final修饰类，类不能被继承了
final class A{

}
//报错 class B extends A{}

//2. final修饰方法，方法不能被重写了
class C{
    public final void test(){
    }
}

class D extends C{
    //报错 public void test(){}
}
```



final修饰变量的注意

- final修饰基本类型的变量，变量存储的数据不能被改变。
- final修饰引用类型的变量，变量存储的地址不能被改变，但地址所指向的对象的内容是可以被改变的。



### 补充知识：常量讲解

常量

- 常量：使用了static final修饰的成员变量

- 作用：通常用于记录系统的配置信息

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2015.28.01.png?raw=true" style="zoom:50%;" />

使用常量记录系统配置信息的优势、执行原理

- 代码可读性更好，可维护性也更好。
- 程序编译后，常量会被“宏替换”：出现常量的地方全部会被替换成其记住的字面量，这样可以保证使用常量和直接用字面量的性能是一样。（没有寻找的过程，如果寻找就慢了）

```java
package com.itheima.p3_final;

public class Test2 {
    public static final String SCHOOL_NAME ="heima"; //在这里做集中配置，不用在下面每个print都改 => 可维护性好
    public static void main(String[] args) {
        //目标：认识常量
        System.out.println(SCHOOL_NAME); //SCHOOL_NAME编译后会储存成heima,不用去上面调用
        System.out.println(SCHOOL_NAME);
        System.out.println(SCHOOL_NAME);
        System.out.println(SCHOOL_NAME);
    }
}

```

上面程序编译后的class文件

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2016.18.28.png?raw=true" style="zoom:50%;" />





## 抽象类



### 认识抽象类

什么是抽象类？

- 在java中有一个关键字叫：abstract 它就是抽象类的意思，可以用它修饰类、成员方法。

- abstract修饰类，这个类就是抽象类；修饰方法，这个方法就是抽象方法。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2016.27.21.png?raw=true" style="zoom:50%;" />
  
  
  
  

抽象类的注意事项、特点

- 抽象类中不一定有抽象方法，有抽象方法的类一定是抽象类。
- 类该有的成员（成员变量、方法、构造器）抽象类都有。
- <font color = "blue">抽象类最主要的特点：</font>抽象类不能创建对象，仅作为一种特殊的父类，让子类继承并实现。
  （理解：抽象本身就不是实例 长得抽象 = 长得不是个人）
- 一个类继承抽象类，必须重写完抽象类的全部抽象方法，否则这个类也必须定义成抽象类。



### 使用抽象类的好处

抽象类的场景和好处

- 父类知道每个子类都要做某个行为，但每个子类要做的情况不一样。父类就定义成抽象方法，交给子类去重写实现。<font color="blue">我们设计了这样的抽象类，就是为了更好的支持多态。</font>(通过父类调方法，走子类的行为=>行为多态)（如果父类中没有抽样方法，多态下就调不了这个方法，因为父类没有）
  （理解：就是父类给你指定一个前进方向，具体怎么前进，子类自己想）
  （理解： 一个父亲有两个儿子，各自选择不同）



抽象类应用案例：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2016.58.27.png?raw=true" style="zoom:50%;" />



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2016.59.37.png?raw=true" style="zoom:50%;" />









![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2017.24.41.png?raw=true)









### 抽象类的常见应用场景：模版方法设计模式



模版方法设计模式解决了什么问题？

- 解决方法中存在重复代码的问题

  问题简介图：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2017.36.06.png?raw=true" style="zoom:50%;" />



模版方法设计模式的写法

- 1. 定义一个抽象类。
- 2. 在里面定义2个方法
     - 一个是模版方法：把相同代码放里面去。
     - 一个是抽象方法：具体实现交给子类完成。

模版案例代码：

```java
package com.itheima.p6_abstract_template;

public class Test {
    public static void main(String[] args) {
        //目标：搞清楚抽象类的应用场景之一：经常用来设计模版方法设计模式
        //场景：学生和老师都要写一篇作文；我的爸爸
        //第一段是一样的
        //正文部分自由发挥
        //最后一段也是一样的。
        Teacher t1 = new Teacher(); //为什么不People p1 = new Teacher(); cuz抽象类不可以实例化对象
        t1.write();

        Student s1 = new Student();
        s1.write();
    }
}

```



```java
package com.itheima.p6_abstract_template;

public abstract class People {
    /*
    * 设计模版方法设计模式
    * 1. 定义一个模版方法出来
    */
    public void write(){
        System.out.println("\t\t\t\tMy father");
        System.out.println("My father is kind, 666");
        //2. 模版方法并不清楚正文部分到底应该怎么写，但是它知道子类肯定要写。
        System.out.println(writeMain());
        System.out.println("It is great to have such a dad.");
    }
    //3.设计一个抽样方法写正文，具体的实现交给子类来完成
    public abstract String writeMain();
}

```

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2018.09.18.png?raw=true)





多学一招：使用final关键字修饰模版方法，为什么？

- 专业。模版方法是给对象直接使用的，不能被子类重写。

- 一旦子类重写了模版方法，模版方法就失效了。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-05%2018.12.54.png?raw=true" style="zoom:50%;" />
  
  



## 接口



### 接口概述

认识接口

- Java提供了一个关键字interface，用这个关键字我们可以定义出一个特殊的结构：接口
  理解： 接口就是一条一分为二的数据线。一个头操作类，另两个头就是实现类，看你要查插苹果手机还是安卓手机

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-06%2023.08.17.png?raw=true" style="zoom:33%;" />

  
  
- 注意⚠️：接口不能创建对象；接口是用来被类实现(implements)的，实现接口的类称为<font color="blue">实现类</font>。(可以把实现理解成继承)
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-06%2023.45.47.png?raw=true" style="zoom:33%;" />





- 一个类可以实现多个接口（接口可以理解成干爹），实现类实现多个接口，必须重写完全部接口的全部抽象方法，否则实现类需要定义成抽象类 （理解：亲爹是intends继承的父类，干爹是接口，可以有好几个干爹。 如果继承了抽象类，那就不能继承其他父类了）



认识接口案例

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-06%2023.56.32.png?raw=true" style="zoom:50%;" />





接口好处（重点）

- 弥补了类单继承的不足，一个类同时可以实现多个接口 (理解：e.g. 父类是个学生，那我只能是个学生。但是我又想当司机当程序员，无法扩展)
- 让程序可以面向接口编程，这样程序员就可以灵活方便的切换各种业务实现。一个接口可以被多个类实现

介绍接口好处代码：

```java
package com.itheima.p8_interface_benefit;

public class Test {
    public static void main(String[] args) {
        //目标：搞清楚使用接口的好处
        Driver s1 = new A();
        s1.drive();

        Driver d1 = new A(); //有接口可以随时换A，觉得A不顺眼。不用改下面程序就可以跑
        d1.drive();
    }
}
class B implements Driver{

    @Override
    public void drive() {

    }
}
class A extends Student implements Driver, Singer{ /*用接口而不是直接创建方法，可以从implements直接看出这个对象是什么角色，会什么技能
                                                     从而也就可以放心的把你当作谁来用了 =>专业*/
    @Override
    public void drive() {

    }

    @Override
    public void sing() {

    }
}

class Student{

}

//还想让A扩展其他特征，不只是学生 => 用接口
interface Driver{
    void drive();
}

interface Singer{
    void sing();
}

```



### 接口的综合案例 

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-07%2000.28.40.png?raw=true" style="zoom:50%;" />

JAVA入门黑马徐磊/Code/javasepromax/day02-oop/src/com/itheima/p9_interface_case



### JDK8开始，接口中新增的三种方法

三种方法：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-07%2021.32.36.png?raw=true" style="zoom:50%;" />

```java
package com.itheima.p10_interface_jdk8;

public class Test {
    public static void main(String[] args) {
        //目标：掌握接口新增的三种方法形式。
        B b1 =new B();
        b1.test1();
        A.test3(); //静态方法属于类本身持有，在接口里就要用接口调用
    }
}
```

```java
package com.itheima.p10_interface_jdk8;

public interface A {
    /*
    * 1.默认方法：必须使用default修饰，默认会被public修饰,因为接口本来就是给大家用的
    * 默认方法=实例方法：对象的方法，必须使用实现类的对象来访问。
    * */
    public default void test1(){ //可以有方法体，抽象方法没有方法体
        System.out.println("===默认方法===");
    }

    /*
    * 2.私有方法：必须使用private修饰.(JDK9开始支持的)
    * 实例方法：对象的方法 但接口没有对象
    * */
    private void test2(){
        System.out.println("===私有方法===");
        test2();
    }
    /*
    * 3.静态方法：必须使用static修饰 ，默认会被public修饰，不用打public
    * 静态方法属于类本身持有，在接口里就要用接口调用
    * */
    public static void test3(){
        System.out.println("===static method===");
    }
}

```

```java
package com.itheima.p10_interface_jdk8;

public class B implements A{

}
```



接口中为啥要新增这些方法？

- 增强了接口的能力，更便于项目的扩展和维护。（cuz如果只有接口里的抽象方法，当用户要扩充接口功能时，如果该接口有一百个实现类，就要重写一百次.  有了以上三种方法，可以直接在接口里定义方法体，不用在实现类里写了。）

  

### 接口的多继承、使用接口的注意事项



接口的多继承

- 一个接口可以同时继承多个接口 （理解：多认几个干爹）
  接口多继承展示案例：

  ```java
  package com.itheima.p11_interface_attention;
  
  public class Test {
      public static void main(String[] args) {
          //目标：理解接口的多继承
  
      }
  }
  
  interface A{
      void test1();
  }
  interface B{
      void test2();
  }
  interface C{
  }
  
  //接口的多继承
  interface D extends A,B,C{
  
  }
  
  class E implements D{
      @Override
      public void test1() {
          
      }
  
      @Override
      public void test2() {
  
      }  //= 简化 class E implements A,B,C
  
  }
  ```

  

接口多继承的作用

- 便于实现类去实现。（理解：把多个接口合并）

接口其他注意事项

1. 一个接口继承多个接口，如果多个接口中存在方法签名冲突，则此时不支持多继承。
2. 一个类实现多个接口，如果多个接口中存在方法签名冲突，则此时不支持多实现。
3. 一个类继承了父类，又同时实现了接口，父类中和接口中有同名的默认方法，实现类会优先用父类的。
4. 一个类实现了多个接口，多个接口中存在同名的默认方法，可以不冲突，这个类重写该方法即可。

注意事项介绍案例：

```java
package com.itheima.p11_interface_attention;

public class Test2 {
    public static void main(String[] args) {
        /*接口其他注意事项

        1. 一个接口继承多个接口，如果多个接口中存在方法签名冲突，则此时不支持多继承。
        2. 一个类实现多个接口，如果多个接口中存在方法签名冲突，则此时不支持多实现。
        3. 一个类继承了父类，又同时实现了接口，父类中和接口中有同名的默认方法，实现类会优先用父类的。
        4. 一个类实现了多个接口，多个接口中存在同名的默认方法，可以不冲突，这个类重写该方法即可。*/
        Zi z1 = new Zi();
        z1.run();
    }
}
//1. 一个接口继承多个接口，如果多个接口中存在方法签名冲突，则此时不支持多继承。
interface I{
    void test1();
}
interface J{
    String test1();
}
/*interface K extends I,J{

}*/

//2. 一个类实现多个接口，如果多个接口中存在方法签名冲突，则此时不支持多实现。
// class E implements I,J{} 不知道重写哪个了

//3. 一个类继承了父类，又同时实现了接口，父类中和接口中有同名的默认方法，实现类会优先用父类的。
class Fu{
    public void run(){
        System.out.println("===父类的run方法执行了===");
    }
}
interface IT{
    default void run(){
        System.out.println("===接口IT中的run方法执行了===");
    }
}

class Zi extends Fu implements IT{ //继承要放在实现前面。理解：先有亲爹后有干爹
}

// 4. 一个类实现了多个接口，多个接口中存在同名的默认方法，可以不冲突，这个类重写该方法即可。*/
interface IT1{
    default void test(){
        System.out.println("IT!");
    }
}
interface IT2{
    default void test(){
        System.out.println("IT2");
    }
}
class N implements IT1,IT2{

    @Override
    public void test() {
        System.out.println("重写自己的方法");
    }
}

```



# D11.面向对象高级三

## 内部类

### 内部类概念、成员内部类 [了解]

内部类

- 是类的五大成分之一（成员变量、方法、构造器、内部类、代码块），如果一个类定义在另一个类的内部，这个类就是内部类。
- 场景：当一个类的内部，包含了一个完整的事物，且这个事物没有必要单独设计时，就可以把这个事物设计成内部类。
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-07%2023.59.12.png?raw=true" style="zoom:33%;" />



内部类有四种形式

1. 成员内部类【了解】
2. 静态内部类 【了解】
3. 局部内部类【了解】
4. <font color="blue">匿名内部类</font>【重点】



成员内部类

- 就是类中的一个普通成员，类似前面学过的普通的成员变量、成员方法。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2000.03.05.png?raw=true" style="zoom:33%;" />

  注意：JDK16之前，成员内部类中不能定义静态成员，JDK16开始可以定义静态成员



创建内部类对象的格式

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2000.37.09.png?raw=true" style="zoom:33%;" />

成员内部类中访问其他成员的特点：

1. 和前面学过的实例方法一样，成员内部类的实例方法中，同样可以直接访问外部类的实例成员、静态成员。
2. 可以在成员内部类的实例方法中，拿到当前外部类对象，格式是：外部类名.this。



成员内部类介绍案例

```java
package com.itheima.p1_inner_class1;

public class Test {
    public static void main(String[] args) {
        //目标：了解成员内部类和其特点
        //成员内部类是外面类的成员，要先创建外部类对象，才能创建内部类对象。理解：先有汽车对象，才能创建发动机对象
        Outer.Inner in = new Outer().new Inner();
        in.test();
    }
}
```

```java
package com.itheima.p1_inner_class1;

public class Outer {
    private int age =99;
    public static String a;
    public class Inner{
        private String name;
        public static String schoolName; //JDK16开始才支持定义静态成员的
        private int age =88;
        public void test(){
            System.out.println(age);
            System.out.println(a);

            int age =66;
            System.out.println(age);//66 离得近
            System.out.println(this.age);//88 this指的是成员内部类创建的成员对象
            System.out.println(Outer.this.age); //Outer.this访问的是外部类的成员对象

            //99
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public void test2(){
        System.out.println(age);
        System.out.println(a);
    }
}
```



### 静态内部类【了解】

什么是静态内部类？

- 有static修饰的内部类，属于外部类自己持有。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2000.43.44.png?raw=true" style="zoom:50%;" />

创建对象的格式：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2000.59.24.png?raw=true" style="zoom:50%;" />

静态内部类中访问外部类成员的特点：

- 可以直接访问外部类的静态成员，不可以直接访问外部类的实例成员

```java
package com.itheima.p2_inner_classstatic;

public class Test {
    public static void main(String[] args) {
        //目标：了解静态内部类
        //由于是static，直接用类名.内部类访问。不需要先创建外部类对象，再创建内部类对象
        Outer.Inner in =new Outer.Inner();
        in.test();
    }
}
```

```java
package com.itheima.p2_inner_classstatic;

public class Outer {
    private int age;
    public static String schoolName;
    //静态内部类
    public static class  Inner{ //静态内部类属于外部类自己持有的，特点和外部类没有区别。
        private String name;
        public static int a;
        public void test(){//里面只能访问外部类的静态成员，不能访问实例成员
            System.out.println(schoolName);
            //报错 System.out.println(age); 因为age是实例对象的成员变量，不能直接访问.必须拿外部类的对象访问

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    //回顾静态方法
    public static void test2(){
        System.out.println(schoolName);
        //报错 System.out.println(age); 因为age是实例对象的成员变量，不能直接访问

    }
}
```



### 局部内部类【了解】

局部内部类

- 局部内部类是定义在方法中、代码块中、构造器等执行体中。

「<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2001.03.11.png?raw=true" style="zoom:50%;" />



### <font color = "blue">匿名内部类</font>【重点】



#### 认识匿名内部类

匿名内部类

- 就是一种特殊的局部内部类；所谓匿名：指的是程序员不需要为这个类声明名字。
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2022.25.20.png?raw=true" style="zoom:50%;" />
- 





- 特点：匿名内部类本质就是一个子类，并会立即创建出一个子类对象
- 作用：用于更方便的创造一个子类对象

介绍匿名内部类案例：（右边是编译后的程序）

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2022.47.17.png?raw=true)





#### 匿名内部类的常见使用场景

匿名内部类在开发中的使用场景

- 通常作为一个参数传输给方法
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2023.05.01.png?raw=true" style="zoom:50%;" />

​	

```java
package com.itheima.p3_inner_classanonymous;

public class Test2Case {
    public static void main(String[] args) {
        //目标：掌握匿名内部类的常见使用场景
/*        Swimming s1 = new Swimming(){//该匿名类其实就是接口实现类，本身也是实现类对象
            @Override
            public void swim() {
                System.out.println("Dog can swim quickly");
            }
        };
        go(s1);*/
        go(new Swimming(){//该匿名类其实就是接口实现类，本身也是实现类对象
            @Override
            public void swim() {
                System.out.println("Dog can swim quickly");
            }
        });
    }
    //设计一个方法，可以接收swimming接口的一切实现类对象进来参加游泳比赛
    public static void go(Swimming s){
        System.out.println("Start now");
        s.swim();
    }
}

//猫和狗都要参加游泳比赛（那么猫和狗肯定都要会游泳）
interface Swimming{ //猫和狗必须实现这个接口，从而实现游泳的能力
    void swim();
}
```

```
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=49449:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/Hardy/Desktop/JAVA/Code/javasepromax/out/production/oop-app3 com.itheima.p3_inner_classanonymous.Test2Case
Start now
Dog can swim quickly

Process finished with exit code 0

```



匿名内部类案例2: 桌面编程

```java
package com.itheima.p5_inner_classcase2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        //目标：拓展：搞清楚匿名内部类在开发中的真实使用场景
        //GUI编程（桌面编程）
        //1.创建窗口
        JFrame win = new JFrame("登陆页面");
        JPanel panel = new JPanel();
        win.add(panel);

        JButton btn = new JButton("登陆");
        panel.add(btn);

        //给按钮绑定单击事件监听器。 源码里addActionListener是个接口，所以可以用匿名内部类。不然就要写接口实现类。=>别人API需要一个对象，对象正好是接口类型，所以要用匿名内部类
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(win,"登陆一下");
            }
        });
        //最终核心目的：简化代码

        win.setSize(400,400);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}

```



## 枚举



### 认识枚举

枚举

- 枚举是一种特殊类

枚举类的格式：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2023.45.28.png?raw=true" style="zoom:33%;" />



注意：

- 枚举类中的第一行，只能写一些合法的标识符（名称），多个名称用逗号隔开。
- 这些名称，本质是常量，每个常量都会记住枚举类的一个对象。

枚举的特点：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-08%2023.54.12.png?raw=true" style="zoom:50%;" />



- 枚举类的第一行只能罗列一些名称，这些名称都是常量，并且每个常量记住的都是枚举类的一个对象。
- 枚举类的构造器都是私有的（写不写都只能是私有的），因此，枚举类对外不能创建对象。
- 枚举类都是最终类，不可以被继承。
- 枚举类中，从第二行开始，可以定义类的其他各种成员。
- 编译器为枚举类新增了几个方法，并且枚举类都是继承：java.lang.Enum类的，从enum类也会继承到一些方法。

介绍枚举案例：

```java
package com.itheima.p6_enum;

public class Test {
    public static void main(String[] args) {
        //目标：认识枚举。
        A a1 = A.X;
        System.out.println(a1);

        //1. 枚举类的构造器私有，不能对外创造对象
        //A a2 = new A();

        //2. 枚举类的第一行都是常量，记住的是枚举类的对象
        A a2 = A.Y;

        //3. 枚举类提供了一些额外的API
         A[] as = A.values(); //拿到全部对象
         A a3 = A.valueOf("Z"); //拿单个对象
        System.out.println(a3.name()); //拿name
        System.out.println(a3.ordinal()); //拿索引

        System.out.println("---------------------");
        B y1 = B.Y;
        y1.go();
    }
}
```

```java
package com.itheima.p6_enum;

public enum A {
    //注意：枚举类的第一行必须罗列的是枚举对象的名字。
    X,Y,Z;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
package com.itheima.p6_enum;
//拓展：抽象枚举(枚举类里有抽象方法）
public enum B {
    X(){ //因为抽象类不能创建对象，所以不能直接列出X,Y,Z. 罗列对象时要重写，类似于匿名内部类，调用B()无参构造器创建对象X
        public void go(){

        }
    },Y("张三"){
        public void go(){
            System.out.println(getName()+"run");
        }
    };
    private String name;

    B() {
    }

    B(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void go();
}
```

```
X
Z
2
---------------------
张三run
```



多学一招：使用枚举实现单例模式

```java
package com.itheima.p6_enum;

public enum CAloneCase {
    //设计单例
    X; //单例
}

```



### 枚举的常见应用场景

应用场景

- 用来表示一组信息，然后作为参数进行传输



案例：通过男生和女生进入不同内容的书城。往方法里传入性别参数

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-10%2015.58.28.png?raw=true" style="zoom:33%;" />





选择定义一个一个的常量来表示一组信息，并作为参数传输

- 参数值不受约束

选择定义枚举表示一组信息，并作为参数传输

- 代码可读性好，参数值得到了约束，对使用者更友好，建议使用！



男女生案例代码

```java
package com.itheima.p7_enum2genderbook;

public class Test {
/*    public static final int BOY = 0;   //常量放这里(硬编码)不专业，要放在单独的Constant类里  ⚠️专业
    public static final int GIRL = 1;*/
    public static void main(String[] args) {
        //目标：掌握枚举的运用场景：做信息标志和分类
        //check(1);
        //check(Constant.BOY);
        //报错 check(21) 如果用常量，别人会瞎给参数赋值。所以用枚举
        check(Constant2Enum.BOY); //因为下面check方法限定了只能填Constant2Enum的枚举，乱填其他的就会报错。=>严谨和限制了乱填
    }
    public static void check(Constant2Enum sex) {
        switch (sex) {
            case BOY:
                System.out.println("Male love: beauty pictures, game information");
                break;
            case GIRL:
                System.out.println("Female love: handsome pictures, plutocrat information");
                break;
        }
    }
/*    public static void check(int sex){
        switch (sex){
            case Constant.BOY:
                System.out.println("Male love: beauty pictures, game information");
                break;
            case Constant.GIRL:
                System.out.println("Female love: handsome pictures, plutocrat information");
                break;
        }*/

}
```

```java
package com.itheima.p7_enum2genderbook;

public enum Constant2Enum {
    BOY,GIRL; //只有这两个枚举对象。因为私有构造器，对外也不能构建对象。好处：男和女受到严格约束
              //枚举不能取代常量。cuz常量可以有各种int String变量类型。或者需要具体值进行处理。需要用常量
              //枚举只能作为字符变量 e.g.游戏中的上下左右键用枚举
}
```

```
Male love: beauty pictures, game information

Process finished with exit code 0
```



## 泛型

### 认识泛型                                                                                                                                                                                                                                                                                                                                                                                                        

- 定义类、接口方法时，同时声明了一个或者多个类型变量(如：<E>)， 称为泛型类、泛型接口、泛型方法。它们统称为泛型。
<img src="" style="zoom:50%;" />

- 作用：泛型提供了在编译阶段约束所能操作的数据类型，并自动进行检查的能力。这样可以避免强制类型转换，及其可能出现的异常。
- 泛型的本质：把具体的数据类型作为参数传给类型变量。

认识泛型案例代码

```java
package com.itheima.p8_generics;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class Test1 {
    public static void main(String[] args) {
        //目标：认识泛型
        ArrayList list1 = new ArrayList(); //没有添加泛型符号<>, add方法中就是object类型（代表任何类型的数据）
        list1.add("java1");
        list1.add("java2");
        list1.add("java3");
        //报错 list1.add(new Cat()); 要想new Cat转成String类型，要强转。但强转会报错 =>用泛型解决

        for (int i = 0; i < list1.size(); i++) {
            String s1 = (String) list1.get(i);
            System.out.println(s1);
        }
        System.out.println("-----------------------");
        ArrayList<String> list2 = new ArrayList<>(); //只想装字符串的话  JDK1.7开始，后面的数据类型可以不声明
        list2.add("java1");
        list2.add("java2");
        list2.add("java3");
        //报错 list2.add(new Cat()); cuz只允许String
        for (int i = 0; i < list2.size(); i++) {
            String s2 = list2.get(i);
            System.out.println(s2);
        }
    }
}

class Cat{}

```

```
java1
java2
java3
-----------------------
java1
java2
java3
```



### 泛型类

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-11%2022.35.50.png?raw=true" style="zoom:50%;" />





- 注意：类型变量建议用大写的英文字母，常用的有：E、T、K、V等。

泛型类制作原理代码：

```java
package com.itheima.p9_generics_class;

public class Test {
    public static void main(String[] args) {
        //目标：掌握泛型类的定义和使用。
        MyArrayList<String> list1 = new MyArrayList<>();
        list1.add("java1");
        list1.add("java2");
        String s1 = list1.get(1);
        System.out.println(s1);

        MyClass2<String, String> c2 = new MyClass2();
        
        MyClass3<Dog> c4 = new MyClass3<>();
    }
}

```

```java
package com.itheima.p9_generics_class;
//泛型类
public class MyArrayList<E> {
    private Object[] arr = new Object[10];
    private int size; //记录当前位置的
    public boolean add(E e){
        arr[size++] = e;
        return true;
    }
    public E get(int index){
        return (E) arr[index];
    }
}

```

```java
package com.itheima.p9_generics_class;
public class MyClass2<E,T>{
    public void put(E e, T t){
        
    }
}

```

```java
package com.itheima.p9_generics_class;

public class MyClass3<E extends Animals>{

}

```



### 泛型接口

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-11%2023.20.21.png?raw=true" style="zoom:50%;" /> 





```
package com.itheima.p10_genertics_interface;

public class Test {
    //目标：掌握泛型接口的定义和使用。
    //场景：系统需要处理学生和老师的数据。需要提供2个功能：保存对象数据。根据名称查询数据。
    
}
jc
```

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-11%2023.42.09.png?raw=true" style="zoom:50%;" />









### 泛型方法、泛型通配符、上下限



泛型方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-11%2023.58.06.png?raw=true" style="zoom:50%;" />



最后一个不是泛型方法，因为E是泛型类定义的，它只是使用，并没有自己定义



通配符

- 就是“?”，就可以在“使用泛型”的时候代表一切类型；E T K V是在定义泛型的时候使用。



泛型的上下限

- 泛型上限：? extends Car    //？能接收的必须是Car或者其子类
- 泛型下限：? super Car       //？能接收的必须是Car或者其父类

泛型方法介绍代码（Car BMW Benz类只打了类，什么内容也没有，就没copy在下面）

```java
package com.itheima.p11_generics_method;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //目标：掌握泛型方法的定义和使用
        String s1 = test("java");
        System.out.println(s1);

        Dog d1= test(new Dog());
        System.out.println(d1);

        //需求：所有的汽车可以一起参加比赛
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new BMW());
        cars.add(new Benz());
        go(cars);

        ArrayList<BMW> bmws = new ArrayList<>();
        bmws.add(new BMW());
        bmws.add(new BMW());
        go(bmws);

        ArrayList<Benz> benzs = new ArrayList<>();
        benzs.add(new Benz());
        benzs.add(new Benz());
        go(benzs);

/*        ArrayList<Dog> dogs = new ArrayList<>();
        benzs.add(new Dog());
        benzs.add(new Dog());
        go(dogs);*/
    }

    //？ 通配符，在使用泛型的时候可以代表一切类型  ? extends Car(上限)  ? super Car(下限)-只能是Car或它的父类
    public static void go(ArrayList<? extends Car> cars){ //extend可以把狗排除在外

    }
/*    public static <T extends Car>void go(ArrayList<T> cars){ //泛型方法

    }*/

    //泛型方法
    public static <T> T test(T t){
        return t;
    }
}

```



### 泛型的注意事项：擦除问题、基本数据类型问题



泛型的擦除问题和注意事项

- 泛型是工作在编译阶段的，一旦程序编译成class文件，class文件中就不存在泛型了，这就是泛型擦除。
- 泛型不支持基本数据类型，只能支持对象类型（引用数据类型）。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2000.39.28.png?raw=true" style="zoom:50%;" />



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2000.40.01.png?raw=true" style="zoom:50%;" />





## 常用API



### API概述

什么是API？

- API(Application Programming interface): 应用编程接口
- 就是java帮我们已经写好的一些程序，如：类、方法等。我们直接拿过来用就可以解决一些问题。



为什么要学别人写好的程序？

- 开发效率高（不重复造轮子）

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2014.24.49.png?raw=true)



### Object

Object类的作用：

- Object类是Java中所有类的祖宗类。因此，Java中所有类的对象都可以直接用Object类中提供的一些方法。



Object类的常见方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2014.49.54.png?raw=true" style="zoom:33%;" />

toString存在的意义：

toString()方法存在的意义就是为了被子类重写，一边返回对象具体的内容。



equals存在的意义：

直接比较两个对象的地址是够相同完全可以用“==”替代equals，equals存在的意义就是为了被子类重写，以便子类自己来定制比较规则（e.g. 比较对象内容）。

```java
package com.itheima.p13_api_object;

import java.sql.Struct;

public class Test {
    public static void main(String[] args) {
        //目标：掌握Object类提供的常用方法
        Student s1 = new Student("Zhao Min",23);
        System.out.println(s1.toString());

        Student s2 = new Student("Zhao Min",23);
        System.out.println(s2.equals(s1));
        System.out.println(s2==s1);
    }
}

```

```java
package com.itheima.p13_api_object;

import java.util.Objects;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    //重写toString
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //重写equals方法，比较两个对象的内容一样就返回true。
    //比较者： s2 == this 谁调用的，this就是谁
    //被比较者 s1 == o
    @Override
    public boolean equals(Object o) {
        //1，判断两个对象是否地址一样，一样直接返回true
        if (this == o) return true;
        //2. 判断o是null直接返回false，或者比较者的类型与被比较者的类型不一样，返回false
        //              Student.class != Pig.class
        if (o == null || this.getClass() != o.getClass()) return false;
        //3. o不是null且o一定是学生类型的对象。开始比较内容了！
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }


    @Override
    public String toString() { //父类Object的toString存在的意义是在子类重写，以便返回对象内容
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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

```

```
Student{name='Zhao Min', age=23}
true
false
```



object类提供的对象克隆方法

当某个对象调用这个方法时，这个方法会复制一个一摸一样的新对象返回。



浅克隆

- 拷贝出的新对象，与原对象中的数据一模一样（<font color="blue">引用类型拷贝的只是地址</font>）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2016.05.15.png?raw=true" />



深克隆

- 对象中基本类型的数据直接拷贝。
- 对象中字符串数据拷贝的还是地址。
- 对象中包含的其他对象，不会拷贝地址，会创建新对象。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2016.08.13.png?raw=true)

```java
package com.itheima.p13_api_object;

public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        //目标：掌握objct类提供的对象克隆的方法
        User u1 = new User(1,"Zhangsan","wo666", new double[] {99.0,99.5});
        System.out.println(u1.getId());
        System.out.println(u1.getUsername());
        System.out.println(u1.getPassword());
        System.out.println(u1.getScores());

        User u2 = (User)u1.clone();
        System.out.println(u2.getId());
        System.out.println(u2.getUsername());
        System.out.println(u2.getPassword());
        System.out.println(u2.getScores());
    }
}

```

```java
package com.itheima.p13_api_object;

public class User implements Cloneable{ //Cloneable是一个标记接口（里面什么也没有）
    private int id;
    private String username;
    private String password;
    private double[] scores;

    public User() {
    }

    public User(int id, String username, String password, double[] scores) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.scores = scores;
    }
    //重写clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        //super去调用父类Object中的clone方法。
        User u2 = (User) super.clone();
        u2.scores = u2.scores.clone(); //深克隆
        return u2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double[] getScores() {
        return scores;
    }

    public void setScores(double[] scores) {
        this.scores = scores;
    }
}

```

````
1
Zhangsan
wo666
[D@7a81197d
1
Zhangsan
wo666
[D@5ca881b5
````



### Objects

为什么要学习Objects类

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2016.26.03.png?raw=true" style="zoom:50%;" />



Objects

- Objects是一个工具类，提供了很多操作对象的静态方法给我们使用。



Objects类的常见方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2016.50.22.png?raw=true)

```java
package com.itheima.p14_api_objects;

import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        //目标：掌握objects类提供的常用方法
        String s1 = null;
        String s2 = "itheima";

        //报错 System.out.println(s1.equals(s2)); 因为s1对象是null，调自己的euqals方法会空指针（地址）报错
        System.out.println(Objects.equals(s1, s2)); //不会因为null报错，更安全，更好！Objects是工具类，里面的静态方法可以直接用类名调
/*        源码：
        public static boolean equals(Object a, Object b) {
            return (a == b) || (a != null && a.equals(b));
        }*/

        System.out.println(Objects.isNull(s1));
        System.out.println(s1 == null); //作用一样，但逼格低
        System.out.println(Objects.isNull(s2));

        System.out.println(Objects.nonNull(s2)); //true
        System.out.println(Objects.nonNull(s1)); //false
    }
}

```

```
false
true
true
false
```



### 包装类

为什么要有包装类？

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2016.51.28.png?raw=true" style="zoom:33%;" />

包装类

- 包装类就是把基本类型的数据包装成对象。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2016.59.08.png?raw=true" style="zoom:33%;" />                                                                                                                                                                                                                                                                                                         

自动装箱：基本数据类型可以自动转换为包装类型。

自动拆箱：包装类型可以自动转换为基本数据类型。



包装类的其他常见操作

- 可以把基本类型的数据转换成字符串类型。
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2017.10.41.png?raw=true" style="zoom:33%;" />

- 可以吧字符串类型的数值转换成数值本身对应的数据类型。
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2017.11.06.png?raw=true" style="zoom:33%;" />



```java
package com.itheima.p15_integer;

import java.awt.print.PrinterGraphics;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //目标：掌握包装类的使用
        //报错 Integer i1 = new Integer(12); 过时的
        Integer i2 = Integer.valueOf(12);
        System.out.println(i2);

        //自动装箱：自动把基本类型的数据转换成对象(不需要写上面的代码)
        Integer i3 = 12;

        //自动拆箱：可以自动把包装类型的对象转换成对应的基本数据类型。
        int i4 = i3;

        //泛型和集合不支持基本数据类型，只能支持引用数据类型。
        //报错 ArrayList<int> list = new ArrayList<int>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(12); //自动装箱
        list.add(13); //自动装箱

        int rs = list.get(1); //自动拆箱
        System.out.println("-------------------------------------");

        //包装类提供的方法
        //1.把基本类型的数据转换成字符串
        Integer i5 = 23;
        String s1 = Integer.toString(i5); //“23”
        System.out.println(s1+1); //“231”

        String s2 = i5.toString();//包装类肯定继承Object类，肯定可以用Object类的toString方法
        System.out.println(s2+1);

        String s3 = s2 +1;
        System.out.println(s3);

        //2.把字符串类型的数值转换成对应的基本类型。（重要）
        String ageStr = "29";
        // 下边优化 int ageI = Integer.parseInt(ageStr); //29
        int ageI = Integer.valueOf(ageStr); //29
        System.out.println(ageI + 1); //30

        String scoreStr = "99.5";
        //下边优化 double score = Double.parseDouble(scoreStr); //99.5
        double score = Double.valueOf(scoreStr); //99.5
        System.out.println(score+0.5);
    }
}

```

```
12
-------------------------------------
231
231
231
30
100.0

```



### StringBuilder、StringBuffer



#### StringBuilder

- StringBuilder代表可变字符串对象，相当于是一个容器，它里面装的字符串是可以改变的，就是用来操作字符串的。
- 好处：StringBuilder比String更适合做字符串的修改操作，效率会更高，代码也会更简洁。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2020.47.51.png?raw=true" style="zoom:50%;" />

上方表格的实操代码：

```java
package com.itheima.p16_StringBuilder;

public class Test1 {
    public static void main(String[] args) {
        //目标：搞清楚StringBuilder的用法和作用。
        //StringBuilder s1 = new StringBuilder(); // s1 ""
        StringBuilder s1 = new StringBuilder("itheima"); // s1 "itheima"

        //1. 拼接内容
        s1.append(12);
        s1.append("黑马");
        s1.append(true);
        System.out.println(s1); //没有输出地址，因为StringBuilder方法里重写了toString方法，输出内容

        //支持链式编程
        s1.append(666).append("黑马2").append(666); //因为append源码内部return this;
        System.out.println(s1);

        //2.反转操作
        s1.reverse();
        System.out.println(s1);

        //3.返回字符串的长度
        System.out.println(s1.length());

        //4.把StringBuilder对象又转换成String类型。cuz传给别人时，别人大概率用String类型而不是StringBuilder类型。所以要转
        String s2 = s1.toString();
        System.out.println(s2);

    }
}

```

```
itheima12黑马true
itheima12黑马true666黑马2666
6662马黑666eurt马黑21amiehti
24
6662马黑666eurt马黑21amiehti
```



为啥操作字符串建议使用StringBuilder，而不是原来学过的String？？

- 对于字符串相关的操作，如频繁的拼接、修改等，建议用StringBuilder，效率更高。
- 注意：如果操作字符串较少，或者不需要操作，以及定义字符串变量，还是建议用String。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2021.11.16.png?raw=true)

```java
package com.itheima.p16_StringBuilder;

public class Test2 {
    public static void main(String[] args) {
        //目标：掌握StringBuilder的好处。
        //需求：要拼接100万次abc
/*        String s1 = "";
        for (int i = 0; i < 1000000; i++) {
            s1+="abc"; //每加一次都创建一个新s1，把以前s1扔掉 => 性能差
        }
        System.out.println(s1);*/

        //使用StringBuilder演示
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            s2.append("abc"); //2s跑完
        }
        System.out.println(s2); 
    }
}

```



#### StringBuffer

注意

- StringBuffer的用法与StringBuilder是一模一样的
- 但StringBuilder是线程不安全的 StringBuffer是线程安全的  //很多人一起用时，StringBuilder可能会出bug



StringBuilder练习案例

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2021.37.38.png?raw=true" style="zoom:50%;" />

```java
package com.itheima.p16_StringBuilder;

public class Test3 {
    public static void main(String[] args) {
        //目标：完成遍历数组内容，并拼接成指定格式的案例。
        int[] a1 = new int[]{11,22,33};
        System.out.println(appendArray(a1));

    }

    public static String appendArray(int[] a1){
        //1.判断a1是否为null
        if (a1==null){
            return null;
        }

        //2.a1数组对象存在
        StringBuilder s1 = new StringBuilder();
        s1.append("[");
        for (int i = 0; i < a1.length; i++) {
            if (i == a1.length-1){
                s1.append(a1[i]);
            }else {
                s1.append(a1[i]).append(", ");
            }
        }
        s1.append("]");
        String s2 = s1.toString();
        return s2;
    }
}

```

```
[11, 22, 33]
```



### StringJoiner



为什么学StringJoiner?

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2022.20.17.png?raw=true)



StringJoiner

- JDK8开始才有的，跟StringBuilde一样，也是用来操作字符串的，也可以看成是一个容器，创建之后里面的内容是可变的。
- 好处：不仅能提高字符串的操作效率，并且在有些场景下使用它操作字符串，代码会更简洁
  ![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2022.22.35.png?raw=true)



StringJoiner介绍代码 + 上一个StringBuilder案例优化

```java
package com.itheima.p17_StringJoiner;

import java.util.StringJoiner;

public class Test {
    public static void main(String[] args) {
        //目标：掌握StringJoiner的使用。
        //StringJoiner s1 = new StringJoiner(", "); //间隔符
        StringJoiner s1 = new StringJoiner(", ","[","]"); //间隔符
        s1.add("java1");
        s1.add("java2");
        s1.add("java3");
        System.out.println(s1);
        System.out.println(appendArray(new int[]{11,22,33}));
    }
    
    //用StringJoiner优化StringBuilder案例
    public static String appendArray(int[] a1) {
        //1.判断a1是否为null
        if (a1 == null) {
            return null;
        }

        //2.a1数组对象存在
        StringJoiner s2 = new StringJoiner(", ","[", "]");
        for (int i = 0; i < a1.length; i++) {
            s2.add(a1[i]+""); //加双引号是为了把int数据转成字符串。因为add只能拼接字符串
        }
        return s2.toString();
    }
}

```

```
[java1, java2, java3]
[11, 22, 33]
```



# D12. 常用API（二）



## Math、System、Runtime

### Math

- 代表数学，是一个工具类，里面提供的都是对数据进行操作的 一些静态方法。

Math类提供的常见方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-12%2023.02.42.png?raw=true)

<img src="/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-11-13 17.03.48.png" style="zoom:50%;" /> 



### System

- System代表程序所在的系统，也是一个工具类

System类提供的常见方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2016.17.33.png?raw=true" style="zoom:50%;" />

System方法介绍代码：		（currentTimeMillis常用于程序的性能分析，确定某段程序花费多长时间）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2017.04.47.png?raw=true" style="zoom:50%;" />



时间毫秒值

- 指的是从1970年1月1日 00:00:00 走到此刻的总的毫秒数，应该是很大的。1s = 1000ms。
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2017.08.47.png?raw=true" style="zoom:50%;" />



### Runtime

Runtime

- 代表程序所在的运行环境。
- Runtime是一个单例类。（因为环境只需要一个）

Runtime类提供的常见方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2017.35.47.png?raw=true" style="zoom:50%;" />

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2017.45.08.png?raw=true" style="zoom:50%;" />



## BigDecimal



BigDecimal

- 用于解决浮点型运算时，出现结果失真的问题。(因为计算机是二进制，整数部分没问题，小数部分有问题)
  ![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2019.27.43.png?raw=true)



BigDecimal的常见构造器、常用方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2019.33.28.png?raw=true" style="zoom:50%;" />

```java
package com.itheima.p2_BigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDemo1 {
    public static void main(String[] args) {
        //目标：掌握BigDecimal的使用：解决小数运算失真的问题。
        double a = 0.1;
        double b = 0.2;
        double c = a+b;
        System.out.println(c); //0.30000000000000004
        System.out.println("-------------------");

        //1.把他们变成字符串封装成BigDecimal对象来运算。
/*        BigDecimal a1 = new BigDecimal(Double.toString(a));
        BigDecimal b1 = new BigDecimal(Double.toString(b));*/
        //推荐用一下方式：把小数转换成字符串再得到BigDecimal对象来使用（更简洁）
        BigDecimal a1 = BigDecimal.valueOf(a); //以上代码简化
        BigDecimal b1 = BigDecimal.valueOf(b);

        BigDecimal c1 = a1.add(b1);
        System.out.println(c1);

        BigDecimal i = BigDecimal.valueOf(0.1);
        BigDecimal j = BigDecimal.valueOf(0.3);
        BigDecimal k = i.divide(j, 2, RoundingMode.HALF_UP); //如果没有后面就会报错，因为BigDecimal无限精度，除不尽
        System.out.println(k);

        //把BigDecimal转成double
        Double rs = k.doubleValue();
        System.out.println(rs);


    }
}

```

```
0.30000000000000004
-------------------
0.3
0.33
```



## JDK8之前传统的日期、时间



### Date

Date

- 代表的是日期和时间
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2020.19.32.png?raw=true" style="zoom:33%;" />

```java
package com.itheima.p3_time;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        //目标：掌握Date日期类的使用
        //1.创建一个Date的对象，代表系统当前时间信息
        Date d1 = new Date();
        System.out.println(d1);

        //2.拿到时间毫秒值。
        long time = d1.getTime();
        System.out.println(time);

        //3.把时间毫秒值转换成日期对象：e.g.2s之后的时间是多少
        time += 2 * 1000;
        Date d2 = new Date(time);
        System.out.println(d2);

        //4.直接把日期对象的时间通过setTime方法进行修改
        Date d3 = new Date();
        d3.setTime(time);
        System.out.println(d3);
    }
}

```

```
Mon Nov 13 20:50:39 CST 2023
1699879839650
Mon Nov 13 20:50:41 CST 2023
Mon Nov 13 20:50:41 CST 2023
```



### SimpleDateFormat

为什么要用SimpleDateFormat?

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2020.52.09.png?raw=true" style="zoom:50%;" />



SimpleDateFormat

- 代表简单日期格式化，可以用来把日期对象、时间毫秒值格式化成我们想要的形式。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2020.58.09.png?raw=true" style="zoom:33%;" />

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2020.58.40.png?raw=true" style="zoom:33%;" />

时间格式的常见符号：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2021.07.11.png?raw=true" style="zoom:33%;" />



SimpleDateFormat解析字符串时间称为日期对象

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2021.09.44.png?raw=true" style="zoom:33%;" />



SimpleDateFormat时间介绍代码：

```java
package com.itheima.p3_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2SimpleDateFormat {
    public static void main(String[] args) throws ParseException {
        //目标：掌握SimpleDateFormat的使用
        //1.准备一些时间
        Date d1 = new Date();
        System.out.println(d1);

        long time = d1.getTime();
        System.out.println(time);

        //2.格式化日期对象，和时间毫秒值。
        SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a");
        String rs = sdf.format(d1);
        String rs2 = sdf.format(time);
        System.out.println(rs);
        System.out.println(rs2);
        System.out.println("--------------------------------------------------------");

        //目标：掌握SimpleDateFormat解析字符串时间，成为日期对象
        String dateStr = "2022-12-12 12:12:11";
        //1.创建简单日期格式化对象，指定的时间格式必须与被解析的时间格式一模一样，否则程序会出bug。
        SimpleDateFormat sdf2 = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d2 = sdf2.parse(dateStr); //字符串转成日期对象
        System.out.println(d2);
    }
}

```

```
Mon Nov 13 21:17:39 CST 2023
1699881459627
2023-11-13 21:17:39 周一 下午
2023-11-13 21:17:39 周一 下午
--------------------------------------------------------
Mon Dec 12 12:12:11 CST 2022
```



##  练习 秒杀活动

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2021.20.31.png?raw=true" style="zoom:33%;" />

```java
package com.itheima.p3_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3SaleActivity {
    public static void main(String[] args) throws ParseException {
        //目标：完成秒杀案例。
        //1.把开始时间、结束时间、小贾下单时间、小皮下单时间拿到程序中来。
        String start = "2023年11月11日 0:0:0";
        String end = "2023年11月11日 0:10:0";
        String xj = "2023年11月11日 0:01:18";
        String xp = "2023年11月11日 0:10:52";

        //2.把字符串时间解析成日期对象
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 h:m:s");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 h:mm:s");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 h:mm:ss");

        //3.把日期对象转换成时间毫秒值来判断
        Date startDate = sdf1.parse(start);
        Date endDate = sdf2.parse(end);
        Date xjDate = sdf3.parse(xj);
        Date xpDate = sdf3.parse(xp);

        long startLong = startDate.getTime();
        long endLong = endDate.getTime();
        long xjLong = xjDate.getTime();
        long xpLong = xpDate.getTime();

        if (xjLong >=tartLong && xjLong<=endLong){
            System.out.println("XiaoJia took part in the activity.");
        }else {
            System.out.println("XiaoJia did not take part in the activity.");
        }

        if (xpLong >=startLong && xpLong<=endLong){
            System.out.println("XiaoPi took part in the activity.");
        }else {
            System.out.println("XiaoPi did not take part in the activity.");
        }

    }
}

```

```
XiaoJia took part in the activity.
XiaoPi did not take part in the activity.
```



## Calendar



为什么要学习Calendar?

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-13%2022.08.57.png?raw=true" style="zoom:33%;" />

上面的方法很复杂而且一个月不一定是30天。



Calendar

- 代表的是系统此刻时间对应的日历。
- 通过它可以单独获取、修改时间中的年、月、日、时、分、秒等。



Calendar日历类的常见方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-14%2009.52.06.png?raw=true" style="zoom:33%;" />

注意: Calendar是可变对象，一旦修改后其对象本身表示的时间将产生变化。



Calendar方法代码示例：

```java
package com.itheima.p3_time;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CancellationException;

public class Test4Calendar {
    public static void main(String[] args) {
        //目标：掌握Calendar的使用和特点
        //1.得到系统此刻时间对应的日历对象。
        Calendar now = Calendar.getInstance();
        System.out.println(now);

        //2.获取日历中的某个信息。
        int year = now.get(Calendar.YEAR);
        System.out.println(year);

        int days = now.get(Calendar.DAY_OF_YEAR);
        System.out.println(days);

        //3.拿到日历中记录的日期对象
        Date d = now.getTime();
        System.out.println(d);

        //4.拿到时间毫秒值
        long time = now.getTimeInMillis();
        System.out.println(time);

        //5.修改日历中的某个信息
        now.set(Calendar.MONTH, 1); //修改月份成为2月，因为月份从0开始算
        System.out.println(now);

        //6.为某个信息增加或者减少多少
        now.add(Calendar.DAY_OF_YEAR, 100);
        now.add(Calendar.DAY_OF_MONTH, -10);
        System.out.println(now);
    }
}

```

```
java.util.GregorianCalendar[time=1699927398152,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Macau",offset=28800000,dstSavings=0,useDaylight=false,transitions=73,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2023,MONTH=10,WEEK_OF_YEAR=46,WEEK_OF_MONTH=3,DAY_OF_MONTH=14,DAY_OF_YEAR=318,DAY_OF_WEEK=3,DAY_OF_WEEK_IN_MONTH=2,AM_PM=0,HOUR=10,HOUR_OF_DAY=10,MINUTE=3,SECOND=18,MILLISECOND=152,ZONE_OFFSET=28800000,DST_OFFSET=0]
2023
318
Tue Nov 14 10:03:18 CST 2023
1699927398152
java.util.GregorianCalendar[time=?,areFieldsSet=false,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Macau",offset=28800000,dstSavings=0,useDaylight=false,transitions=73,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2023,MONTH=1,WEEK_OF_YEAR=46,WEEK_OF_MONTH=3,DAY_OF_MONTH=14,DAY_OF_YEAR=318,DAY_OF_WEEK=3,DAY_OF_WEEK_IN_MONTH=2,AM_PM=0,HOUR=10,HOUR_OF_DAY=10,MINUTE=3,SECOND=18,MILLISECOND=152,ZONE_OFFSET=28800000,DST_OFFSET=0]
java.util.GregorianCalendar[time=1684116198152,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Macau",offset=28800000,dstSavings=0,useDaylight=false,transitions=73,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2023,MONTH=4,WEEK_OF_YEAR=20,WEEK_OF_MONTH=3,DAY_OF_MONTH=15,DAY_OF_YEAR=135,DAY_OF_WEEK=2,DAY_OF_WEEK_IN_MONTH=3,AM_PM=0,HOUR=10,HOUR_OF_DAY=10,MINUTE=3,SECOND=18,MILLISECOND=152,ZONE_OFFSET=28800000,DST_OFFSET=0]

```



## JDK8开始新增的日期、时间



### 为什么要学JDK8新增的时间？

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-14%2010.07.21.png?raw=true" style="zoom:33%;" />

JDK8之前API的问题代码介绍：

```java
package com.itheima.p4_jdk8_time;

import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        //目标：搞清楚为什么要用JDK8开始新增的时间类
        //传统的时间类（Date, SimpleDateFormat, Calendar）存在如下问题：
        //1. 设计不合理，使用不方便，很多都被淘汰了。
        Date d = new Date();
        System.out.println(d.getYear()+1900); //要+1900

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR); //需要记住YEAR字段
        System.out.println(year);

        //2. 都是可变对象，修改后会丢失最开始的时间信息。

        //3. 线程不安全。

        //4. 不能精确到纳秒，只能精确到毫秒
        //1s = 1000ms
        //1ms = 1000us
        //1us = 1000ns
    }
}

```

```
2023
2023
```



JDK8新增的时间

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-14%2010.24.01.png?raw=true" style="zoom:33%;" />



### LocalDate, LocalTime, LocalDateTime

LocalDate: 代表本地日期（年、月、日、星期）

LocalTime：代表本地时间（时、分、秒、纳秒）

LocalDateTime：代表本地日期、时间（年、月、日、星期、时、分、秒、纳秒）



它获取对象的方案

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2021.03.50.png?raw=true" style="zoom:33%;" /> 



LocalDate的常用API（都是处理年、月、日、星期相关的）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2021.06.05.png?raw=true" style="zoom:33%;" /> 



LocalTime的常用API（都是处理时、分、秒、纳秒相关的）。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2021.07.49.png?raw=true" style="zoom:33%;" /> 

LocalDateTime的常用API（可以处理年、月、日、星期、时、分、秒、纳秒等信息）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2021.09.35.png?raw=true" style="zoom:33%;" /> 



LocalDate方法演示代码：

```java
package com.itheima.p4_jdk8_time;

import java.time.LocalDate;

public class Test1_LocalDate {
    public static void main(String[] args) {
        //0. 获取本地日期对象
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        //1. 获取日期对象中的信息
        int year = ld.getYear();
        int month = ld.getMonthValue();
        int day = ld.getDayOfMonth();
        int dayOfYear = ld.getDayOfYear();
        int dayOfWeek = ld.getDayOfWeek().getValue();
        System.out.println(year);
        System.out.println(day);
        System.out.println(dayOfWeek);

        //2. 直接修改某个信息: withYear, withMonth, withDayOfMonth, withDayOfYear
        LocalDate ld2 = ld.withYear(2099); //原来的日期对象不会变
        LocalDate ld3 = ld.withMonth(12);
        System.out.println(ld2);
        System.out.println(ld3);
        System.out.println(ld);

        //3. 把某个信息加多少：plusYears, plusMonths, plusDays, plusWeeks
        LocalDate ld4 = ld.plusYears(2);

        //4. 把某个信息减多少：minusYears, minusMonths, minusDays, minusWeeks
        LocalDate ld6 = ld.minusYears(2);

        //5. 获取指定日期的LocalDate对象：public static LocalDate of(int year, int month, int dayOfMonth)
        LocalDate ld8 = LocalDate.of(2099, 12, 12);
        LocalDate ld9 = LocalDate.of(2099, 12, 12);

        //6. 判断2个日期对象，是否相等，在前还是在后: equals isBefore isAfter
        System.out.println(ld8.equals(ld9));
        System.out.println(ld8.isAfter(ld));
        System.out.println(ld8.isBefore(ld));


    }
}

```

```
2023-11-14
2023
14
2
2099-11-14
2023-12-14
2023-11-14
true
true
false
```



LocalTime

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2020.52.28.png?raw=true" style="zoom:30%;" /> <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2020.56.29.png?raw=true" style="zoom:33%;" />



LocalDateTime

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2020.58.12.png?raw=true" style="zoom:33%;" /> 

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2020.58.12.png?raw=true" style="zoom:33%;" /> 



### ZoneID, ZonedDateTime

什么是时区？



世界标准时间（UTC）

中国标准时间：UTC+8小时



ZoneId：代表时区Id

ZoneId时区和ZonedDateTime带时区时间的常见方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2021.47.39.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p4_jdk8_time;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.CancellationException;

public class Test4_ZoneId_ZoneDateTime {
    public static void main(String[] args) {
        //目标：了解时区和带时区的时间。
        //1. ZoneId的常见用法：
        //public static ZoneId SystemDefault(): 获取系统默认的时区
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId.getId());
        System.out.println(zoneId);

        //public static Set<String> getAvailableZoneIds():获取Java支持的全部时区Id
        System.out.println(ZoneId.getAvailableZoneIds());

        //public static ZoneId of(String zoneId): 把某个时区id封装成ZoneId对象。
        ZoneId zoneId1 = ZoneId.of("America/New_York");

        //2.ZonedDateTime:带时区的时间
        //public static ZonedDateTime now(ZoneId zone): 获取某个时区的ZonedDateTime对象。
        ZonedDateTime now = ZonedDateTime.now(zoneId1);
        System.out.println(now);

        //世界标准时间
        ZonedDateTime now1 = ZonedDateTime.now(Clock.systemUTC());
        System.out.println(now1);

        //public static ZonedDateTime now():获取系统默认时区的ZonedDateTime对象
        ZonedDateTime now2 = ZonedDateTime.now();
        System.out.println(now2);

        //其他方法和LocalDateTime一致

        //Calendar也支持zoneId但是可变对象 Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(zoneId1));
        
    }
}

```

```
Asia/Macau
Asia/Macau
[Asia/Aden, America/Cuiaba, Etc/GMT+9, Etc/GMT+8, Africa/Nairobi, America/Marigot, A
2023-11-15T08:46:38.005335-05:00[America/New_York]
2023-11-15T13:46:38.007558Z
2023-11-15T21:46:38.007627+08:00[Asia/Macau]
```



### Instant(代替Date)



Instant时间线上的某个时刻/时间戳

- 通过获取Instant的对象可以拿到此刻的时间，该时间由两部分组成：从1970-01-01 00:00:00 开始走到此刻的总秒数+不够1秒的纳秒数（精确到纳秒级）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2022.14.56.png?raw=true" style="zoom:50%;" /> 

- 作用：可以用来记录代码的执行时间，或用于记录用户操作某个事件的时间点。
- 传统的Date类，只能精确到毫秒，并且是可变对象。
- 新增的Instant类，可以精确到纳秒，并且是不可变对象，推荐用Instant代替Date。



Instant vs LocalDateTime

Instant可以拿到总秒数和纳秒数，但LocalDateTime不能拿总秒数，只有纳秒数。 在进行时间计算时，Instant计算方便。 记录时间点Instant也方便。



Instant方法介绍代码：

```java
package com.itheima.p4_jdk8_time;

import java.time.Instant;

public class Test5_Instant {
    public static void main(String[] args) {
        //目标：掌握Instant的使用
        //1. 创建Instant的对象，获取此刻时间信息
        Instant now = Instant.now(); //不可变对象

        //2. 获取总秒数
        long second = now.getEpochSecond();
        System.out.println(second);

        //3.不够1秒的纳秒数
        int nano = now.getNano();
        System.out.println(nano);

        System.out.println(now);

        Instant instant = now.plusNanos(111); //不可变对象

        //Instant对象的作用：做代码的性能分析，或者记录用户的操作时间点
        Instant now1 = Instant.now();
        //代码执行。。
        Instant now2 = Instant.now();
    }
}

```

```
1700058386
166225000
2023-11-15T14:26:26.166225Z
```



### DateTimeFormatter（代替SimpleDateFormat）



DateTimeFormatter

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2023.06.34.png?raw=true" style="zoom:33%;" /> 



LocalDateTime提供的格式化、解析时间的方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2023.07.19.png?raw=true" style="zoom:33%;" /> 



方法介绍代码：

```java
package com.itheima.p4_jdk8_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test6_DateTimeFormatter {
    public static void main(String[] args) {
        //目标：掌握JDK8新增的DateTimeFormatter格式化器的用法。
        //1.创建一个日期时间格式化器对象出来。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        //2.对时间进行格式化
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        String rs = formatter.format(now); //正向格式化：拿格式化器格式化时间
        System.out.println(rs);

        //3.格式化时间，其实还有一种方案。
        String rs2 = now.format(formatter); //反向格式化：时间自己扔给自己格式化器到里面格式化
        System.out.println(rs2);

        //4.解析时间：解析时间一般使用LocalDateTime提供的解析方法来解析
        String dateStr = "2029年12月12日 12:12:11";
        LocalDateTime ldt = LocalDateTime.parse(dateStr, formatter);
        System.out.println(ldt);

    }
}

```

```
2023-11-15T23:03:59.262978
2023年11月15日 23:03:59
2023年11月15日 23:03:59
2029-12-12T12:12:11
```



### Duration, Period



Period（一段时期）

- 可以用于计算两个LocalDate对象 相差的年数、月数、天数。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2023.11.31.png?raw=true" style="zoom:33%;" /> 



Period方法介绍代码：

```java
package com.itheima.p4_jdk8_time;

import java.time.LocalDate;
import java.time.Period;

public class Test8_Duration {
    public static void main(String[] args) {
        //目标：掌握Period的作用：计算两个日期相差的年数、月数、天数。
        LocalDate start = LocalDate.of(2029, 8, 10);
        LocalDate end = LocalDate.of(2029, 12, 15);

        //1、创建Period对象，封装两个日期对象。
        Period period = Period.between(start, end);

        //2、通过period对象获取两个日期对象相差的信息。
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
}

```

```
0
4
5
```



Duration (持续时间)

- 可以用于计算两个时间对象相差的天数、小时数、分数、秒数、纳秒数；支持LocalTime, LocalDatetTime, Instant等时间。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2023.20.19.png?raw=true" style="zoom:33%;" /> 



Duration方法介绍代码

```java
package com.itheima.p4_jdk8_time;

import java.time.Duration;
import java.time.LocalDateTime;

public class Test8_Duration {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2025,11,11,11,10,10);
        LocalDateTime end = LocalDateTime.of(2025,11,11,11,11,11);
        //1.得到Duration对象
        Duration duration = Duration.between(start, end);

        //2、获取两个时间对象间隔的信息。
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toSeconds());
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());
    }
}

```

```
0
0
1
61
61000
61000000000
```



## Arrays类



Arrays类

- 用来操作数组的一个工具类



Arrays类提供的常见方法 

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-15%2023.31.05.png?raw=true" style="zoom:33%;" /> 




Arrays方法介绍代码

```java
package com.itheima.p5_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntToDoubleFunction;

public class ArrayTest1 {
    public static void main(String[] args) {
        //目标：掌握Arrays类的常用方法。
        //1、public static String toString(类型[] arr): 返回数组的内容 （默认只返回数组地址，这个返回数组内容）
        int[] arr = {10,20,30,40,50,60};
        System.out.println(Arrays.toString(arr));

        //2、public static int[] copyOfRange(类型[] arr,起始索引，结束索引)：拷贝数组（指定范围，包前不包后）
        int[] arr2 = Arrays.copyOfRange(arr, 1, 4);
        System.out.println(Arrays.toString(arr2));

        //3. public static copyOf(类型[] arr, int newLength): 拷贝数组，可以指定新数组的长度。
        int[] arr3 = Arrays.copyOf(arr2, 10);
        System.out.println(Arrays.toString(arr3));

        //4. public static setALL(double[] array, IntToDoubleFunction generator): 把数组中的原数据改为新数据又存进去。
        double[] prices = {99.8,128,100};
        //需要把所有的价格都打八折，然后又存进去。
        Arrays.setAll(prices, new IntToDoubleFunction() {
            @Override
            public double applyAsDouble(int value) {
                //value = 0 1 2 数组的索引
                return prices[value]*0.8;
            }
        });
        System.out.println(Arrays.toString(prices));

        //5. public static void sort(类型[] arr): 对数组进行排序（默认是升序排序）
        Arrays.sort(prices);
        System.out.println(Arrays.toString(prices));


    }
}

```

```
[10, 20, 30, 40, 50, 60]
[20, 30, 40]
[20, 30, 40, 0, 0, 0, 0, 0, 0, 0]
[79.84, 102.4, 80.0]
[79.84, 80.0, 102.4]
```



对数组中的数据进行排序

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-16%2011.17.48.png?raw=true" style="zoom:33%;" /> 





如果数组中存储的是对象，如何排序？

方式一：让该对象的类实现Comparable(比较规则)接口，然后重写compareTo方法，自己来制定比较规则。

方式二：使用下面这个sort方法，创建Comparator比较器接口的匿名内部类对象，然后自己制定比较规则。



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-16%2011.32.05.png?raw=true" style="zoom:33%;" /> 



自定义排序规则时，需要遵循的官方约定如下：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2019.39.46.png?raw=true" style="zoom:33%;" /> 



方式一、二代码展示

```java
package com.itheima.p5_arrays;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class ArrayTest2OderStudent {
    public static void main(String[] args) {
        //目标：掌握如何对数组中的对象进行排序。
        Student[] students = new Student[4];
        students[0] = new Student("蜘蛛精",169.5,23);
        students[1] = new Student("紫霞",163.8,26);
        students[2] = new Student("紫霞",163.8,26);
        students[3] = new Student("至尊宝",167.5,24);

        //1.public static void sort(类型[] arr): 对数组进行排序
        //报错 Arrays.sort(students); 因为不知道按什么规则排，干脆直接报错

        //第一种方法的调用（因为要写第二种，所以注释掉先）：
        //Arrays.sort(students);
        //System.out.println(Arrays.toString(students));

        //2、public static <T> void sort(T[] arr, Comparator<? super T> c)  重载了sort方法
        //参数一：需要排序的数组
        //参数二：Comparator比较器对象（用来制定对象的比较规则） Comparator是个接口
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //制定比较规则：左边对象o1 右边对象o2
                //约定1：左边对象 大于 右边对象 请您返回正整数
                //约定2：左边对象 小于 右边对象 请您返回负整数
                //约定3：左边对象 等于 右边对象 请您返回0
                //报错 return o1.getHeight()-o2.getHeight(); cuz减法的值是小数，而返回值是整数  强转也不行，损失精度

/*                if (o1.getHeight() > o2.getHeight()){
                    return 1;
                } else if (o1.getHeight() < o2.getHeight()) {
                    return -1;
                }else {
                    return 0;
                    下面一行代码简化
                }*/
                return Double.compare(o1.getHeight(), o2.getHeight()); //升序 if的简化

            }
        });
        System.out.println(Arrays.toString(students));
    }
}

```

```java
package com.itheima.p5_arrays;

public class Student implements Comparable<Student> {
    private String name;
    private double height;
    private int age;

    //制定比较规则
    //比较者：this 被比较者：o
    @Override
    public int compareTo(Student o) {
        //约定1：左边对象 大于 右边对象 请您返回正整数
        //约定2：左边对象 小于 右边对象 请您返回负整数
        //约定3：左边对象 等于 右边对象 请您返回0
        //按照年龄升序排序。
/*        if (this.age > o.age){
            return 1;
        }else if (this.age < o.age){
            return -1;
        }else{
        return 0;
        下面一行代码简化
    }*/
        //return this.age - o.age; //升序
        return o.age - this.age; //降序
    }


    public Student() {
    }

    public Student(String name, double height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", age=" + age +
                '}';
    }
}

```

```
[Student{name='紫霞', height=163.8, age=26}, Student{name='紫霞', height=163.8, age=26}, Student{name='至尊宝', height=167.5, age=24}, Student{name='蜘蛛精', height=169.5, age=23}]

```



## JDK8新特性：Lambda表达式

### 认识Lambda表达式

Lambda表达式

- Lambda表达式是JDK8开始新增的一种语法形式；作用：用于简化匿名内部类的代码写法。



格式

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2020.03.12.png?raw=true" style="zoom:50%;" /> 

<font color = "blue">注意：Lambda表达式只能简化函数式接口的匿名内部类！！！</font>



什么是函数式接口？

- 有且仅有一个抽样方法的接口。
- 注意：将来我们见到的大部分函数式接口，上面可能会有一个@FunctionalInterface的注解，有该注解的接口就必定是函数式接口。



Lambda简化匿名抽象类介绍代码

```java
package com.itheima.p6_lambda;

public class LambdaTest1 {
    public static void main(String[] args) {
        //目标：认识Lambda表达式
/*        Animal a = new Animal(){ //用匿名内部类做一个狗对象
            @Override
            public void run() {
                System.out.println("Dog can run quickly");
            }
        };
        a.run();
        简化
        */

        //注意：Lambda表达式并不是说能简化全部匿名内部类的写法，只能简化函数式接口的匿名内部类；（函数式接口：1.接口 2.有且仅有1个抽样方法）
        //错误的代码！因为下面不是接口，是抽象类
/*        Animal a = () -> {
            public void run () {
                System.out.println("Dog can run quickly");

            };
            a.run();*/

/*        Swimming s = new Swimming(){
            @Override
            public void swim() {
                System.out.println("Students swim happily");
            }
        };
        s.swim();
        用Lambda简化
        */

        Swimming s = () ->{
            System.out.println("Students swim happily");
        };
        s.swim();

        }
    }

abstract class Animal{
    public abstract void run();
}

interface Swimming{
    void swim();
}
```

```
Students swim happily
```



Lambda表达式简化setAll方法中匿名内部类

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2020.11.18.png?raw=true)



Lambda表达式简化Comparator接口的匿名形式

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2020.11.32.png?raw=true)



### Lambda表达式的省略规则

Lambda表达式的省略写法（进一步简化Lambda表达式的写法）

- 参数类型可以省略不写。
- 如果只有一个参数，参数类型可以省略，同时()也可以省略。
- 如果Lanbda表达式中的方法体代码只有一行代码，可以省略大括号不写，同时要省略分号！此时，如果这行代码是return语句，也必须去掉return不写。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2020.27.39.png?raw=true" style="zoom:33%;" /> 



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2020.28.34.png?raw=true" style="zoom:33%;" /> 





## JDK8新特性：方法引用

方法引用：

进一步简化Lambda表达式的

方法引用的标志性符号“::”



### 静态方法的引用

静态方法引用

- 类名::静态方法。

使用场景

- 如果某个Lambda表达式里只是调用一个静态方法，并且前后参数的形式一致，就可以使用静态方法引用。

静态方法引用介绍代码

```java
package com.itheima.p7_method_references;

import com.itheima.p5_arrays.Student;

import java.util.Arrays;
import java.util.Comparator;

public class Test1 {
    public static void main(String[] args) {
        Student[] students = new Student[4];
        students[0] = new Student("蜘蛛精",169.5,23);
        students[1] = new Student("紫霞",163.8,26);
        students[2] = new Student("紫霞",163.8,26);
        students[3] = new Student("至尊宝",167.5,24);

        //原始写法
/*        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge(); //按照年龄升序排序
            }
        });*/
        
        //使用Lambda简化后的形式
/*        Arrays.sort(students, (o1, o2) -> o1.getAge()-o2.getAge());*/

        // Arrays.sort(students, (o1, o2) -> CompareByData.compareByAge(o1,o2));
        
        //静态方法引用
        Arrays.sort(students, CompareByData::compareByAge);
        
        
        System.out.println(Arrays.toString(students));
    }
}

```

```java
package com.itheima.p7_method_references;

import com.itheima.p5_arrays.Student;

public class CompareByData {
    public static int compareByAge(Student o1, Student o2){
        return o1.getAge() - o2.getAge(); //升序排序的规则
    }
}

```

```
[Student{name='蜘蛛精', height=169.5, age=23}, Student{name='至尊宝', height=167.5, age=24}, Student{name='紫霞', height=163.8, age=26}, Student{name='紫霞', height=163.8, age=26}]

```



### 实例方法引用

实例方法的引用

- 对象名::实例方法

使用场景

- 如果某个Lanbda表达式里只是调用一个实例方法，并且前后参数的形式一致，就可以使用实例方法引用

实例方法引用介绍代码

```java
package com.itheima.p7_method_references;

import com.itheima.p5_arrays.Student;

import java.util.Arrays;
import java.util.Comparator;

public class Test1 {
    public static void main(String[] args) {
        Student[] students = new Student[4];
        students[0] = new Student("蜘蛛精",169.5,23);
        students[1] = new Student("紫霞",163.8,26);
        students[2] = new Student("紫霞",163.8,26);
        students[3] = new Student("至尊宝",167.5,24);

        //原始写法
/*        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge(); //按照年龄升序排序
            }
        });*/

        //使用Lambda简化后的形式
/*        Arrays.sort(students, (o1, o2) -> o1.getAge()-o2.getAge());*/

        // Arrays.sort(students, (o1, o2) -> CompareByData.compareByAge(o1,o2));

        //静态方法引用
        Arrays.sort(students, CompareByData::compareByAge);

        //Arrays.sort(students, (o1,o2) -> o2.getAge() - o1.getAge()); //降序

        CompareByData compare = new CompareByData();
        // Arrays.sort(students, (o1,o2) -> compare.compareByAgeDesc(o1,o2)); //降序 调用实例方法

        //实例方法引用
        Arrays.sort(students, compare::compareByAgeDesc); //降序

        System.out.println(Arrays.toString(students));
    }
}

```

```java
package com.itheima.p7_method_references;

import com.itheima.p5_arrays.Student;

public class CompareByData {
    public static int compareByAge(Student o1, Student o2){ //静态方法
        return o1.getAge() - o2.getAge(); //升序排序的规则
    }

    public int compareByAgeDesc(Student o1, Student o2){
        return o2.getAge() - o1.getAge(); //降序排序的规则
    }

}


```

```
[Student{name='紫霞', height=163.8, age=26}, Student{name='紫霞', height=163.8, age=26}, Student{name='至尊宝', height=167.5, age=24}, Student{name='蜘蛛精', height=169.5, age=23}]

```



### 特定类型方法的引用

特定类型的方法引用

- 类型::方法

使用场景

- 如果某个Lambda表达式里只是调用一个实例方法，并且前面参数列表中的第一个参数是作为方法的主调，后面的所有参数都是作为该实例方法的入参的，则此时就可以使用特定类型的方法引用。

```java
package com.itheima.p7_method_references;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Test2TypeReference {
    public static void main(String[] args) {
        //目标：掌握特定类型的方法引用。
        String[] names = {"boby","angela", "Andy","caocao", "Babo","jack","Cici"};

        //进行排序(默认是按照字符串的首字符编号进行升序排序的)
        //Arrays.sort(names);

        //要求：忽略首字符大小写进行排序
/*        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //制定比较规则。 e.g. o1 = "Andy" o2 = "angela"
                return o1.compareToIgnoreCase(o2); //String类型提供的对象方法
            }
        });下面简化
        */

        //Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2);

        //特定类型的方法引用
        Arrays.sort(names, String::compareToIgnoreCase);
        
        System.out.println(Arrays.toString(names));
    }
}

```

```
[Andy, angela, Babo, boby, caocao, Cici, jack]
```



### 构造器引用

构造器引用

- 类名::new

使用场景

- 如果某个Lambda表达式里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用。

```java
package com.itheima.p7_method_references;

public class Test3ConstructorReference {
    public static void main(String[] args) {
        //目标：构造器引用（理解语法）
        //1. 创建这个接口的匿名内部类对象。
/*        CreateCar cc = new CreateCar(){
            @Override
            public Car create(String name, double price) {
                return new Car(name,price);
            }
        };*/
        //CreateCar cc = ( name,  price)-> new Car(name,price);
        CreateCar cc = Car::new;
        Car c = cc.create("奔驰",49.9);
        System.out.println(c);
    }
}

interface CreateCar{
    Car create(String name, double price);
}
```

```java
package com.itheima.p7_method_references;

public class Car {
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Car() {
    }

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

```

```
Car{name='奔驰', price=49.9}
```



# D13. 算法 正则 异常



## 常见算法

### 简单认识算法

什么是算法？

- 解决某个实际问题的过程和方法



为什么要学习算法？

- 编程思维
- 面试

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2022.49.11.png?raw=true" style="zoom:33%;" /> 

### 排序算法

学习算法的技巧

1. 先搞清楚算法的流程
2. 直接去推敲如何写代码（直接到IDEA里写）



#### 冒泡排序

- 每次从数组中找出最大值放在数组的后面去。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2023.05.53.png?raw=true" style="zoom:33%;" /> 

实现冒泡排序的关键步骤分析

- 确定总共需要做几轮：数组的长度-1

- 每轮比较几次：

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2023.32.58.png?raw=true" style="zoom:33%;" /> 

- 当前位置大于后一个位置则交换数据

冒泡排序代码

```java
package com.itheima.p1_algorithm;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        //目标：掌握冒泡排序的编写
        //1、准备一个数组
        int[] arr = {5,2,3,1};

        //2. 定义一个循环控制排几轮
        for (int i = 0; i < arr.length-1; i++) {
            //i = 0 1 2     [5, 2,  3,  1]   次数
            //i = 0 第一轮    0  1   2   3     3
            //i = 1 第二轮    0  1             2
            //i = 2 第三轮    0                1

            //3. 定义一个循环控制每轮比较几次。
            for (int j = 0; j < arr.length-i-1; j++) {
                //判断当前位置的元素值，是否大于后一个位置处的元素值，如果大则交换。
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

```

```
[1, 2, 3, 5]
```



#### 选择排序

- 每轮选择当前位置，开始找出后面的较小值与该位置交换

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-17%2023.35.46.png?raw=true" style="zoom:33%;" /> 

选择排序的关键

- 确定总共需要选择几轮：数组的长度-1.
- 控制每轮从以前位置为基准，与后面元素选择几次。
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2000.03.17.png?raw=true" style="zoom:33%;" />

选择排序代码：

```java
package com.itheima.p1_algorithm;

import java.util.Arrays;

public class Test2SelectionSort {
    public static void main(String[] args) {
        //目标：掌握选择排序
        //1. 准备好一个数组
        int[] arr = {5,1,3,2};
        //           0 1 2 3

        //2. 控制选择几轮
        for (int i = 0; i < arr.length -1 ; i++) {
            //i=0 第一轮   j = 1 2 3
            //i=1 第二轮   j = 2 3
            //i=2 第三轮   j = 3
            //3. 控制每轮选择几次
            for (int j = i+1; j <arr.length ; j++) {
                //判断当前位置是否大于后面位置处的元素值，若大于则交换。
                if (arr[i] > arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

```

```
[1, 2, 3, 5]
```



选择排序优化后代码：

```java
package com.itheima.p1_algorithm;

import java.util.Arrays;

public class Test2SelectionSort {
    public static void main(String[] args) {
        //目标：掌握选择排序
        //1. 准备好一个数组
        int[] arr = {5,1,3,2};
        //           0 1 2 3

        //2. 控制选择几轮
        for (int i = 0; i < arr.length -1 ; i++) {
            //i=0 第一轮   j = 1 2 3
            //i=1 第二轮   j = 2 3
            //i=2 第三轮   j = 3
            int minIndex = i;
            //3. 控制每轮选择几次
            for (int j = i+1; j <arr.length ; j++) {
                //找出比当前位置更小值的索引 找完如果有再交换 => 减少了交换次数
                if (arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            //决定是否交换
            if (i != minIndex){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

```

```
[1, 2, 3, 5]

```



### 二分查找算法

#### 基本查找/顺序查找

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2014.19.59.png?raw=true" style="zoom:33%;" /> 

注意：在数据量特别大的时候，基本查找这种从前往后挨个找的的形式，性能是很差的！



#### 二分查找（折半查找）

前提条件：数组中的数据必须是有序的

核心思想：每次排除一半的数据，查询的性能明显提高极多！

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2014.31.56.png?raw=true" style="zoom:33%;" /> 

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2014.32.31.png?raw=true" style="zoom:33%;" />



如果要找的数据不在数列中

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2014.33.55.png?raw=true" style="zoom:33%;" /> 

两者重合了还没找到，就说明数据不存在。



二分查找算法介绍代码：

```java
package com.itheima.p1_algorithm;

public class Test3BinarySearch {
    public static void main(String[] args) {
        //目标：掌握二分查找算法
        //准备好一个数组。
        int[] arr = {7,23,79,81,103,127,131,147};
        System.out.println(binarySearch(arr, 81));
    }
    public static int binarySearch(int[] arr, int data){
        //1. 定义两个变量，一个站在左边位置，一个站在右边位置
        int left = 0;
        int right = arr.length-1;

        //2. 定义一个循环控制折半。(因为不知道折多少次，所以用while循环)
        while (left <= right){
            //3. 每次折半，都算出中间位置处的索引
            int middle = (left+right)/2;
            //4、判断当前要找的元素值，与中间位置处的元素值的大小情况。
            if (data < arr[middle]){
                //往左边找，截止位置（右边位置）= 中间位置-1
                right = middle-1;
            } else if (data > arr[middle]) {
                //往右边找，起始位置（左边位置）= 中间位置+1
                left = middle+1;
            }else {
                //中间位置处的元素值，正好等于我们要找的元素值
                return middle;
            }
        }
        return -1; //-1特殊结果，就代表没有找到数据！数组中不存在该数据！
    }
}

```

```
3
```



## 正则表达式



### 概述、初体验

正则表达式

- 就是由一些特定的字符组成，代表的是一个规则



作用一：用来校验数据格式是否合法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2015.37.12.png?raw=true" style="zoom:33%;" /> 



作用二：在一段文本中查找满足要求的内容

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2015.37.41.png?raw=true" style="zoom:33%;" /> 



正则表达式体验代码

```java
package com.itheima.p2_regex;

public class RegexTest1 {
    public static void main(String[] args) {
        //目标：体验一下使用正则表达式来校验数据格式的合法性。
        //需求：校验QQ号码是否正确，要求全部是数字，长度是（6-20）之间，不能以0开头。
        System.out.println(checkQQ(null));
        System.out.println(checkQQ("31231223"));
        System.out.println(checkQQ("31231223aa"));
        System.out.println("------------------------------------");

        System.out.println(checkQQ(null));
        System.out.println(checkQQRegex("31231223"));
        System.out.println(checkQQRegex("31231223aa"));
    }
    
    //正则表达式
    public static boolean checkQQRegex(String qq){
        return qq !=null && qq.matches("[1-9]\\d{5,19}");
    }
    
    //普通手写
    public static boolean checkQQ(String qq){
        //1. 判断QQ号码是否为null
        if (qq==null || qq.startsWith("0") || qq.length()<6 || qq.length()>20){
            return false;
        }

        //2. QQ号码至少不是null，不是以0开头的，满足6-20之间的长度
        //判断qq号码中是否都是数字
        //qq = 5214ghd234
        for (int i = 0; i < qq.length(); i++) {
            //根据索引提取当前位置处的字符
            char ch = qq.charAt(i);
            //判断ch记住的字符，如果不是数字，qq号码就不合法
            if (ch < '0' || ch > '9'){
                return false;
            }
        }
        //3、说明QQ号肯定是合法的
        return true;
    }
}

```

```
false
true
false
------------------------------------
false
true
false
```



### 书写规则

String提供了一个匹配正则表达式的方法

public boolean <font color="blue">matches(String regex)</font>	判断字符串是否匹配正则表达式，匹配返回true，不匹配返回false。



正则表达式的书写规则

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2016.15.17.png?raw=true)

正则表达式书写规则代码

```java
package com.itheima.p2_regex;

/**
 * 目标：掌握正则表达式的书写规则
 */
public class RegexTest2 {
    public static void main(String[] args) {
        // 1、字符类(只能匹配单个字符)
        System.out.println("a".matches("[abc]"));    // [abc]只能匹配a、b、c
        System.out.println("e".matches("[abcd]")); // false

        System.out.println("d".matches("[^abc]"));   // [^abc] 不能是abc
        System.out.println("a".matches("[^abc]"));  // false

        System.out.println("b".matches("[a-zA-Z]")); // [a-zA-Z] 只能是a-z A-Z的字符
        System.out.println("2".matches("[a-zA-Z]")); // false

        System.out.println("k".matches("[a-z&&[^bc]]")); // ： a到z，除了b和c
        System.out.println("b".matches("[a-z&&[^bc]]")); // false

        System.out.println("ab".matches("[a-zA-Z0-9]")); // false 注意：以上带 [内容] 的规则都只能用于匹配单个字符

        // 2、预定义字符(只能匹配单个字符)  .  \d  \D   \s  \S  \w  \W
        System.out.println("徐".matches(".")); // .可以匹配任意字符
        System.out.println("徐徐".matches(".")); // false

        // \转义
        System.out.println("\"");
        // \n \t
        System.out.println("3".matches("\\d"));  // \d: 0-9
        System.out.println("a".matches("\\d"));  //false

        System.out.println(" ".matches("\\s"));   // \s: 代表一个空白字符
        System.out.println("a".matches("\s")); // false

        System.out.println("a".matches("\\S"));  // \S: 代表一个非空白字符
        System.out.println(" ".matches("\\S")); // false

        System.out.println("a".matches("\\w"));  // \w: [a-zA-Z_0-9]
        System.out.println("_".matches("\\w")); // true
        System.out.println("徐".matches("\\w")); // false

        System.out.println("徐".matches("\\W"));  // [^\w]不能是a-zA-Z_0-9
        System.out.println("a".matches("\\W"));  // false

        System.out.println("23232".matches("\\d")); // false 注意：以上预定义字符都只能匹配单个字符。

        // 3、数量词： ?   *   +   {n}   {n, }  {n, m}
        System.out.println("a".matches("\\w?"));   // ? 代表0次或1次
        System.out.println("".matches("\\w?"));    // true
        System.out.println("abc".matches("\\w?")); // false

        System.out.println("abc12".matches("\\w*"));   // * 代表0次或多次
        System.out.println("".matches("\\w*"));        // true
        System.out.println("abc12张".matches("\\w*")); // false

        System.out.println("abc12".matches("\\w+"));   // + 代表1次或多次
        System.out.println("".matches("\\w+"));       // false
        System.out.println("abc12张".matches("\\w+")); // false

        System.out.println("a3c".matches("\\w{3}"));   // {3} 代表要正好是n次
        System.out.println("abcd".matches("\\w{3}"));  // false
        System.out.println("abcd".matches("\\w{3,}"));     // {3,} 代表是>=3次
        System.out.println("ab".matches("\\w{3,}"));     // false
        System.out.println("abcde徐".matches("\\w{3,}"));     // false
        System.out.println("abc232d".matches("\\w{3,9}"));     // {3, 9} 代表是  大于等于3次，小于等于9次

        // 4、其他几个常用的符号：(?i)忽略大小写 、 或：| 、  分组：()
        System.out.println("abc".matches("(?i)abc")); // true
        System.out.println("ABC".matches("(?i)abc")); // true
        System.out.println("aBc".matches("a((?i)b)c")); // true
        System.out.println("ABc".matches("a((?i)b)c")); // false

        // 需求1：要求要么是3个小写字母，要么是3个数字。
        System.out.println("abc".matches("[a-z]{3}|\\d{3}")); // true
        System.out.println("ABC".matches("[a-z]{3}|\\d{3}")); // false
        System.out.println("123".matches("[a-z]{3}|\\d{3}")); // true
        System.out.println("A12".matches("[a-z]{3}|\\d{3}")); // false

        // 需求2：必须是”我爱“开头，中间可以是至少一个”编程“，最后至少是1个”666“
        System.out.println("我爱编程编程666666".matches("我爱(编程)+(666)+"));
        System.out.println("我爱编程编程66666".matches("我爱(编程)+(666)+"));
    }
}

```

```
true
false
true
false
true
false
true
false
false
true
false
"
true
false
true
false
true
false
true
true
false
true
false
false
true
true
false
true
true
false
true
false
false
true
false
true
false
false
true
true
true
true
false
true
false
true
false
true
false
```



### 应用案例-校验数据

需求

校验用户输入的电话、邮箱、时间是否合法

```java
package com.itheima.p2_regex;

import java.util.Scanner;

/**
 * 目标：校验用户输入的电话、邮箱、时间是否合法。
 */
public class RegexTest3Case1 {
    public static void main(String[] args) {
        // checkPhone();
        checkEmail();
    }

    //校验手机号
    public static void checkPhone(){
        while (true) {
            System.out.println("请您输入您的电话号码(手机|座机): ");
            Scanner sc = new Scanner(System.in);
            String phone = sc.nextLine();
            // 18676769999  010-3424242424 0104644535
            if(phone.matches("(1[3-9]\\d{9})|(0\\d{2,7}-?[1-9]\\d{4,19})")){
                System.out.println("您输入的号码格式正确~~~");
                break;
            }else {
                System.out.println("您输入的号码格式不正确~~~");
            }
        }
    }

    //校验邮箱
    public static void checkEmail(){
        while (true) {
            System.out.println("请您输入您的邮箱： ");
            Scanner sc = new Scanner(System.in);
            String email = sc.nextLine();
            /**
             * dlei0009@163.com
             * 25143242@qq.com
             * itheima@itcast.com.cn
             */
            if(email.matches("\\w{2,}@\\w{2,20}(\\.\\w{2,10}){1,2}")){
                System.out.println("您输入的邮箱格式正确~~~");
                break;
            }else {
                System.out.println("您输入的邮箱格式不正确~~~");
            }
        }
    }
}

```

```
请您输入您的邮箱： 
eqrwpoiu2arutofpi
您输入的邮箱格式不正确~~~
请您输入您的邮箱： 
3144483920@qq.com
您输入的邮箱格式正确~~~
```



### 查找信息

案例：使用正则表达式查找一段文本中的内容

需求：请把下面文本中的电话，邮箱，座机号码，热线都爬取出来。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2021.24.49.png?raw=true)

查找信息案例代码

```java
package com.itheima.p2_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 目标：掌握使用正则表达式查找内容。
 */
public class RegexTest4FindContent {
    public static void main(String[] args) {
        method1();
    }

    // 需求1：从以下内容中爬取出，手机，邮箱，座机、400电话等信息。
    public static void method1(){
        String data = " 来黑马程序员学习Java，\n" +
                "        电话：1866668888，18699997777\n" +
                "        或者联系邮箱：boniu@itcast.cn，\n" +
                "        座机电话：01036517895，010-98951256\n" +
                "        邮箱：bozai@itcast.cn，\n" +
                "        邮箱：dlei0009@163.com，\n" +
                "        热线电话：400-618-9090 ，400-618-4000，4006184000，4006189090";
        // 1、定义爬取规则
        String regex = "(1[3-9]\\d{9})|(0\\d{2,7}-?[1-9]\\d{4,19})|(\\w{2,}@\\w{2,20}(\\.\\w{2,10}){1,2})"
                + "|(400-?\\d{3,7}-?\\d{3,7})";
        // 2、把正则表达式封装成一个Pattern对象
        Pattern pattern = Pattern.compile(regex);
        // 3、通过pattern对象去获取查找内容的匹配器对象。
        Matcher matcher = pattern.matcher(data);
        // 4、定义一个循环开始爬取信息
        while (matcher.find()){
            String rs = matcher.group(); // 获取到了找到的内容了。
            System.out.println(rs);
        }
    }

}

```

```
18699997777
boniu@itcast.cn
01036517895
010-98951256
bozai@itcast.cn
dlei0009@163.com
400-618-9090
400-618-4000
4006184000
4006189090
```



需求2:请把邮箱里的用户名爬取出来

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2021.54.07.png?raw=true" style="zoom:33%;" /> 

````java
package com.itheima.p2_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest5FindUsername {
    public static void main(String[] args) {
        method2();
    }

    // 需求2：把全部邮箱的账户名找出来。
    public static void method1(){
        String data = " 来黑马程序员学习Java，\n" +
                "        电话：1866668888，18699997777\n" +
                "        或者联系邮箱：boniu@itcast.cn，\n" +
                "        座机电话：01036517895，010-98951256\n" +
                "        邮箱：bozai@itcast.cn，\n" +
                "        邮箱：dlei0009@163.com，\n" +
                "        热线电话：400-618-9090 ，heima, 400-618-4000，4006184000，4006189090";
        // 1、定义爬取规则
        String regex = "(\\w{2,})@(\\w{2,20})(\\.\\w{2,10}){1,2}";
        // 2、把正则表达式封装成一个Pattern对象
        Pattern pattern = Pattern.compile(regex);
        // 3、通过pattern对象去获取查找内容的匹配器对象。
        Matcher matcher = pattern.matcher(data);
        // 4、定义一个循环开始爬取信息
        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.group(1)); // 指定获取正则表达式匹配后的第一组内容
            System.out.println(matcher.group(2)); // 指定获取正则表达式匹配后的第二组内容
        }
    }


    // 需求3：某系统的日志文件记录了当天进入系统的全部用户信息，需要把这些用户的名字爬取出来另作他用。
    public static void method2(){
        String data = "欢迎张全蛋光临本系统！他删库并跑路，欢迎李二狗子光临本系统！" +
                "欢迎马六子光临本系统！它浏览了很多好看的照片！欢迎夏洛光临本系统！他在六点钟购买了一台拖拉机！";
        // 1、定义爬取规则
        // String regex = "欢迎(.+)光临"; // 贪婪匹配
        String regex = "欢迎(.+?)光临"; // +? 非贪婪匹配
        // 2、把正则表达式封装成一个Pattern对象
        Pattern pattern = Pattern.compile(regex);
        // 3、通过pattern对象去获取查找内容的匹配器对象。
        Matcher matcher = pattern.matcher(data);
        // 4、定义一个循环开始爬取信息
        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
        }
    }
}

````

```
欢迎张全蛋光临
张全蛋
欢迎李二狗子光临
李二狗子
欢迎马六子光临
马六子
欢迎夏洛光临
夏洛
```





### 用于搜索替换、分割内容

正则表达式用于搜索替换、分割内容，需要结合String提供的如下方法完成：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2021.56.22.png?raw=true" style="zoom:33%;" /> 

介绍代码：

```java
package com.itheima.p2_regex;

import java.util.Arrays;

/**
 * 目标：掌握使用正则表达式做搜索替换，内容分割。
 */
public class RegexTest6SearchReplace {
    public static void main(String[] args) {
        // 1、public String replaceAll(String regex , String newStr)：按照正则表达式匹配的内容进行替换
        // 需求1：请把 古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴，中间的非中文字符替换成 “-”
        String s1 = "古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴";
        System.out.println(s1.replaceAll("\\w+", "-"));

        // 需求2(拓展)：某语音系统，收到一个口吃的人说的“我我我喜欢编编编编编编编编编编编编程程程！”，需要优化成“我喜欢编程！”。
        String s2 = "我我我喜欢编编编编编编编编编编编编程程程";
        /**
         * (.)一组：.匹配任意字符的。
         * \\1 :为这个组声明一个组号：1号
         * +：声明必须是重复的字
         * $1可以去取到第1组代表的那个重复的字
         */
        System.out.println(s2.replaceAll("(.)\\1+", "$1"));

        // 2、public String[] split(String regex)：按照正则表达式匹配的内容进行分割字符串，反回一个字符串数组。
        // 需求3：请把 古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴，中的人名获取出来。
        String s3 = "古力娜扎ai8888迪丽热巴999aa5566马尔扎哈fbbfsfs42425卡尔扎巴";
        String[] names = s3.split("\\w+");
        System.out.println(Arrays.toString(names));
    }
}

```

```
古力娜扎-迪丽热巴-马尔扎哈-卡尔扎巴
我喜欢编程
[古力娜扎, 迪丽热巴, 马尔扎哈, 卡尔扎巴]
```



## 异常



### 认识异常

什么是异常？

异常就是代表程序出现的问题

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2022.31.46.png?raw=true)



异常的体系

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2022.33.42.png?raw=true" style="zoom:33%;" /> 

Error:  代表的系统级别的错误（属于严重问题），也就是说系统一旦出现问题，sun公司会把这些问题封装成Error对象给出来，说白了，Error是给sun公司自己用的，不是给我们程序员用的。因此我们开发人员不用管他。

Exception：叫异常。它代表的才是我们程序可能出现的问题。所以，我们程序员通常会用Exception以及它的孩子来封装程序出现的问题。

- 运行时异常：RuntimeException及其子类，编译阶段不会出现错误提醒，运行时出现的异常（e.g. 数组索引越界异常、分母为0异常） -- （理解：Java认为你是有水平的程序员，这些简单错误不会在写代码时干扰你，运行时报错说你水平有问题）
- 编译时异常：编译阶段就会出现错误提醒的。（e.g. 日期解析异常）



异常处理方式

抛出异常（throws）

- 在方法上使用throws关键字，可以将方法内部出现的异常抛出去给调用者(上一层)处理。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2023.17.39.png?raw=true" style="zoom:33%;" /> 

捕获异常（try...catch）

- 直接捕获程序出现的异常

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2023.21.52.png?raw=true" style="zoom:33%;" /> 



异常认识代码

```java
package com.itheima.p3_exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionTest1 {
    public static void main(String[] args) throws ParseException{
        //目标：认识异常
        //Integer.valueOf("abc"); //运行时异常

/*        int[] arr = {11,22,33}; //运行时异常
        System.out.println(arr[5]);*/

        //try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse("2029-11-11 11:11"); //编译时异常
            System.out.println(d);
/*        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/
    }
}

```

 

### 自定义异常



自定义异常

- Java无法为这个世界上全部的问题都提供异常类来代表。如果企业自己的某种问题，想通过异常来表示，以便用异常来管理该问题，那就需要自己来定义异常类了。



自定义异常的种类

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2023.30.31.png?raw=true" style="zoom:33%;" /> 



异常有什么作用？

1. 异常是用来查寻系统Bug的关键参考信息！
2. 异常可以作为方法内部的一种特殊返回值，以便通知上层调用者底层的执行情况！

提醒：如果问题程序员经常犯，就用编译时异常。如果不经常犯不严重，就用运行时异常



自定义运行时异常介绍代码：

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-18%2023.42.58.png?raw=true)

```java
package com.itheima.p3_exception;

public class ExceptionTest2Customize {
    public static void main(String[] args) {
        //目标：掌握自定义异常，以及异常的应用。
        //需求：保存一个合法的年龄
        try {
            saveAge(16);
            System.out.println("底层执行成功了");
            saveAge(223);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("底层出现了bug！");
        }
    }
    public static void saveAge(int age){
        if (age>0 && age<150){
            System.out.println("Age is saved successfully: " + age);
        }else {
            //不专业 System.out.println("Age is not true"); //不专业 cuz方法无返回值，不能返回结果。上层调用者不知道有错
            //用一个异常对象封装这个问题-让上层调用者知道有问题 知道什么问题
            //一定用throw抛出去这个异常对象 先抛到方法入口处，再抛到调用者处
            throw new AgeIlegalRunTimeException("/age is illegal, your age is " + age);
        }
    }
}

```

```java
package com.itheima.p3_exception;
//1. 必须让这个类继承自RuntimeException，才能成为一个运行时异常类。
public class AgeIlegalRunTimeException extends RuntimeException{
    public AgeIlegalRunTimeException() {
    }

    public AgeIlegalRunTimeException(String message) {
        super(message);
    }
}

```

```
Age is saved successfully: 16
底层执行成功了
底层出现了bug！
com.itheima.p3_exception.AgeIlegalRunTimeException: /age is illegal, your age is 223
	at com.itheima.p3_exception.ExceptionTest2Customize.saveAge(ExceptionTest2Customize.java:23)
	at com.itheima.p3_exception.ExceptionTest2Customize.main(ExceptionTest2Customize.java:10)
```



自定义编译时异常

```java
package com.itheima.p3_exception;

public class ExceptionTest2Customize {
    public static void main(String[] args) {
        //目标：掌握自定义异常，以及异常的应用。
        //需求：保存一个合法的年龄
/*        try {
            saveAge(16);
            System.out.println("底层执行成功了");
            saveAge(223);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("底层出现了bug！");
        }*/

        //提醒更强烈，一定要处理
        try {
            saveAge2(252);
            System.out.println("saveAge2底层执行成功了");
        } catch (AgeIlegalException e) {
            e.printStackTrace();
            System.out.println("saveAge2底层出现了bug！");
        }
    }
    public static void saveAge2(int age) throws AgeIlegalException{
        if (age>0 && age<150){
            System.out.println("Age is saved successfully: " + age);
        }else {
            //不专业 System.out.println("Age is not true"); //不专业 cuz方法无返回值，不能返回结果。上层调用者不知道有错
            //用一个异常对象封装这个问题-让上层调用者知道有问题 知道什么问题
            //一定用throw抛出去这个异常对象 先抛到方法入口处，再抛到调用者处
            //throws 用在方法上，抛出方法内部的异常 抛给方法调用者
            throw new AgeIlegalException("/age is illegal, your age is " + age);
        }
    }

    public static void saveAge(int age){
        if (age>0 && age<150){
            System.out.println("Age is saved successfully: " + age);
        }else {
            //不专业 System.out.println("Age is not true"); //不专业 cuz方法无返回值，不能返回结果。上层调用者不知道有错
            //用一个异常对象封装这个问题-让上层调用者知道有问题 知道什么问题
            //一定用throw抛出去这个异常对象 先抛到方法入口处，再抛到调用者处
            throw new AgeIlegalRunTimeException("/age is illegal, your age is " + age);
        }
    }
}

```

```java
package com.itheima.p3_exception;
//1. 必须让这个类继承自Exception，才能成为一个运行时异常类。
public class AgeIlegalException extends Exception{
    public AgeIlegalException() {
    }

    public AgeIlegalException(String message) {
        super(message);
    }
}

```

```
saveAge2底层出现了bug！
com.itheima.p3_exception.AgeIlegalException: /age is illegal, your age is 252
	at com.itheima.p3_exception.ExceptionTest2Customize.saveAge2(ExceptionTest2Customize.java:33)
	at com.itheima.p3_exception.ExceptionTest2Customize.main(ExceptionTest2Customize.java:18)
```



### 异常的处理

开发中对于异常的常见处理方式

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2000.02.53.png?raw=true" style="zoom:33%;" /> 



抛出异常（throws）

- 在方法上使用throws关键字，可以将方法内部出现的异常抛出去给调用者处理。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2000.39.13.png?raw=true" style="zoom:33%;" /> 



捕获异常（try...catch）

- 直接捕获程序出现的异常

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2000.40.07.png?raw=true" style="zoom:33%;" />  





####  1、捕获异常，记录异常并响应合适的信息给用户 介绍代码：

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2000.20.14.png?raw=true)

```java
package com.itheima.p3_exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class ExceptionTest3Process {
    public static void main(String[] args) {
        //目标：异常的处理
        try {
            test1(); //不要再抛了 处理
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist!");
            e.printStackTrace(); //打印出这个异常对象的信息，记录下来。
        } catch (ParseException e) {
            System.out.println("There is a problem with time you want to parse!");
            e.printStackTrace(); //打印出这个异常对象的信息，记录下来。
        }
    }

    public static void test1() throws FileNotFoundException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse("2029-11-11 11:11"); //编译时异常
        System.out.println(d);

        test2();
    }
    public static void test2() throws FileNotFoundException {
        //读取文件的。
        InputStream is  = new FileInputStream(("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-11-19 00.02.53.png"));

    }
}

```

```
There is a problem with time you want to parse!
java.text.ParseException: Unparseable date: "2029-11-11 11:11"
	at java.base/java.text.DateFormat.parse(DateFormat.java:399)
	at com.itheima.p3_exception.ExceptionTest3Process.test1(ExceptionTest3Process.java:27)
	at com.itheima.p3_exception.ExceptionTest3Process.main(ExceptionTest3Process.java:15)
```



1、捕获异常，记录异常并响应合适的信息给用户 介绍代码优化（不写具体的Exception类型，只写Exception抛出所有异常）：

```java
package com.itheima.p3_exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionTest3Process_2 {
    public static void main(String[] args) {
        //目标：异常的处理优化
        try {
            test1(); //不要再抛了 处理
        } catch (Exception e) {
            System.out.println("Your code has problem");
            e.printStackTrace(); //打印出这个异常对象的信息，记录下来。
        }
    }

    public static void test1() throws Exception, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse("2029-11-11 11:11"); //编译时异常
        System.out.println(d);

        test2();
    }
    public static void test2() throws Exception {
        //读取文件的。
        InputStream is  = new FileInputStream(("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-11-19 00.02.53.png"));

    }
}

```

```
Your code has problem
java.text.ParseException: Unparseable date: "2029-11-11 11:11"
	at java.base/java.text.DateFormat.parse(DateFormat.java:399)
	at com.itheima.p3_exception.ExceptionTest3Process_2.test1(ExceptionTest3Process_2.java:23)
	at com.itheima.p3_exception.ExceptionTest3Process_2.main(ExceptionTest3Process_2.java:14)

```



#### 2. 捕获异常，尝试重新修复

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2000.36.57.png?raw=true)

```java
package com.itheima.p3_exception;

import java.util.Scanner;

public class ExceptionTest4Fix {
    public static void main(String[] args) {
        //目标：掌握异常的处理方式：捕获异常，尝试修复
        //需求：调用一个方法，让用户输入一个合适的价格返回为止。
        //尝试修复
        while (true) {
            try {
                System.out.println(getMoney());
                break;
            } catch (Exception e) {
                System.out.println("Please enter the suitable number!");
            }
        }
    }
    public static double getMoney(){
        Scanner sc = new Scanner(System.in);
        while (true) {
        System.out.println("Please enter suitable price: ");
        double money = sc.nextDouble();
            if (money >= 0){
                return money;
            }else {
                System.out.println("Your price is not suitable");
            }
        }
    }
}

```

```
Please enter suitable price: 
joip
Please enter the suitable number!
Please enter suitable price: 
12
12.0
```



# D14. 集合框架



## 集合体系概述

集合是一种容器，用来装数据的，类似于数组。但集合的大小可变，开发中也非常常用。

为了满足不同的业务场景需求，Java还提供了很多不同特点的集合给我们选择。



集合体系结构

- Collection代表单列集合，每个元素（数据）只包含一个值。
- Map代表双列集合，每个元素包含两个值（键值对）。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2013.56.49.png?raw=true" style="zoom:33%;" />  





Collection集合体系

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2014.00.52.png?raw=true" style="zoom:33%;" /> 



Collection集合特点

- List系列集合：添加的元素是有序、可重复、有索引。
  - ArrayList、LinkedList：有序、可重复、有索引。
- Set系列集合：添加的元素是无序、不重复、无索引。
  - HashSet：无序、不重复、无索引。
  - LinkedHashSet：有序、不重复、无索引。
  - TreeSet：按照大小默认升序排序、不重复、无索引。



Collection介绍代码

```java
package com.itheima.p1_collection;

import java.util.ArrayList;
import java.util.HashSet;

public class CollectionTest1Introduction {
    public static void main(String[] args) {
        //目标：认识Collection体系的特点
        //简单确认一下Collection集合的特点
        ArrayList<String> list = new ArrayList<>(); //有序 可重复 有索引
        list.add("java1");
        list.add("java2");
        list.add("java1");
        list.add("java2");
        System.out.println(list);

        HashSet<String> set = new HashSet<>(); //无序 不重复 无索引
        set.add("java1");
        set.add("java2");
        set.add("java1");
        set.add("java2");
        set.add("java3");
        System.out.println(set);
        
    }
}

```

````
[java1, java2, java1, java2]
[java3, java2, java1]
````



## Collection的常用方法

为啥要先学Collection的常用方法？

- Collection是单列集合的祖宗，它规定的方法（功能）是全部单列集合都会继承的。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2014.24.03.png?raw=true" style="zoom:33%;" /> 







Collection的常见方法如下：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2014.40.06.png?raw=true" style="zoom:33%;" /> 







Collection常用方法介绍代码：

```java
package com.itheima.d1_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.IntFunction;

/**
 目标：掌握Collection集合的常用API.
 Collection是集合的祖宗类，它的功能是全部集合都可以继承使用的，所以要学习它。
 Collection API如下:
 - public boolean add(E e)：  把给定的对象添加到当前集合中 。
 - public void clear() :清空集合中所有的元素。
 - public boolean remove(E e): 把给定的对象在当前集合中删除。
 - public boolean contains(Object obj): 判断当前集合中是否包含给定的对象。
 - public boolean isEmpty(): 判断当前集合是否为空。
 - public int size(): 返回集合中元素的个数。
 - public Object[] toArray(): 把集合中的元素，存储到数组中。
 */
public class CollectionTest2API {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>(); // 多态写法
        // 1.public boolean add(E e)：添加元素, 添加成功返回true。
        c.add("java1");
        c.add("java1");
        c.add("java2");
        c.add("java2");
        c.add("java3");
        System.out.println(c);

        // 2.public void clear()：清空集合的元素。
        //c.clear();
        //System.out.println(c);

        // 3.public boolean isEmpty()：判断集合是否为空 是空返回true,反之。
        System.out.println(c.isEmpty()); // false

        // 4.public int size()：获取集合的大小。
        System.out.println(c.size());

        // 5.public boolean contains(Object obj)：判断集合中是否包含某个元素。
        System.out.println(c.contains("java1")); // true
        System.out.println(c.contains("Java1")); // false

        // 6.public boolean remove(E e)：删除某个元素:如果有多个重复元素默认删除前面的第一个！
        System.out.println(c.remove("java1"));
        System.out.println(c);

        // 7.public Object[] toArray()：把集合转换成数组
        Object[] arr = c.toArray();
        System.out.println(Arrays.toString(arr));

        String[] arr2 = c.toArray(new String[c.size()]);
        System.out.println(Arrays.toString(arr2));

        System.out.println("--------------------------------------------");
        // 把一个集合的全部数据倒入到另一个集合中去。
        Collection<String> c1 = new ArrayList<>();
        c1.add("java1");
        c1.add("java2");
        Collection<String> c2 = new ArrayList<>();
        c2.add("java3");
        c2.add("java4");
        c1.addAll(c2); // 就是把c2集合的全部数据倒入到c1集合中去。
        System.out.println(c1);
        System.out.println(c2);
    }
}

```

````
[java1, java1, java2, java2, java3]
false
5
true
false
true
[java1, java2, java2, java3]
[java1, java2, java2, java3]
[java1, java2, java2, java3]
--------------------------------------------
[java1, java2, java3, java4]
[java3, java4]
````



## Collection的遍历方式



###  迭代器

迭代器概述

- 迭代器是用来遍历集合的专用方式（数组没有迭代器），在Java中迭代器的代表是Iterator。



Collection集合获取迭代器的方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2014.44.42.png?raw=true" style="zoom:50%;" /> 



Iterator迭代器中的常用方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2014.45.26.png?raw=true" style="zoom:50%;" /> 



迭代器执行流程

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2015.00.40.png?raw=true" style="zoom:33%;" /> 





迭代器介绍代码：

```java
package com.itheima.p2_collection_traverse;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionDemo01Iterator {
    public static void main(String[] args) {
        //目标：Collection集合的遍历方式一：使用迭代器Iterator遍历
        ArrayList<String> c = new ArrayList<>();
        c.add("赵敏");
        c.add("小昭");
        c.add("素素");
        //c.add("灭绝");
        System.out.println(c);

        //使用迭代器遍历集合
        //1、从集合对象中获取迭代器对象。
        Iterator<String> it = c.iterator();
/*      System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());*/
        //报错 System.out.println(it.next()); //出现异常的 没有第5个元素

        //2、我们应该使用循环结合迭代器遍历集合。
        while (it.hasNext()){
            String ele = it.next();
            System.out.println(ele);
/*            System.out.println(it.next()); 错误代码风格
            System.out.println(it.next());*/
        }
    }
}

```

```
[赵敏, 小昭, 素素]
赵敏
小昭
素素
```



### 增强for循环

格式：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2015.07.12.png?raw=true" style="zoom:33%;" /> 



- 增强for可以用来遍历集合或者数组。
- 增强for遍历集合，本质就是迭代器遍历集合的简化写法。



增强for介绍代码：

```java
package com.itheima.p2_collection_traverse;

import java.util.ArrayList;

public class CollectionDemo02For {
    public static void main(String[] args) {
        //目标：Collection集合的遍历方式二：增强for
        ArrayList<String> c = new ArrayList<>();
        c.add("赵敏");
        c.add("小昭");
        c.add("素素");
        c.add("灭绝");
        System.out.println(c);

        //使用增强for遍历集合或数组
        for (String s : c) {
            System.out.println(s);
        }

        String[] names = {"迪丽热巴","古力娜扎","玛尔扎哈"};
        for (String name : names){
            System.out.println(name);
        }
    }
}

```

```
[赵敏, 小昭, 素素, 灭绝]
赵敏
小昭
素素
灭绝
迪丽热巴
古力娜扎
玛尔扎哈
```



### lambda表达式

Lambda表达式遍历集合

- 得益于JDK8开始的新技术Lambda表达式，提供了一种更简单、更直接的方式来遍历集合。



需要使用Collection的如下方法来完成

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2015.27.35.png?raw=true" style="zoom:33%;" /> 





Lambda表达式遍历介绍代码：

```java
package com.itheima.p2_collection_traverse;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CollectionDemo03Lambda {
    public static void main(String[] args) {
        //目标：Collection集合的遍历方式三：JDK8开始新增的Lambda表达式。
        ArrayList<String> c = new ArrayList<>();
        c.add("赵敏");
        c.add("小昭");
        c.add("素素");
        c.add("灭绝");
        System.out.println(c);

        //default void forEach(Consumer<? super T> action):结合Lambda表达式遍历集合：
/*        c.forEach(new Consumer<String>() { //匿名内部类
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        c.forEach((String s) -> {
                System.out.println(s);
        });

        c.forEach(s -> {
            System.out.println(s);
        });

        c.forEach(s -> System.out.println(s) );*/

        c.forEach(System.out::println );
    }
}

```



### 案例

遍历集合中的自定义对象。



需求：

- 展示多部电影信息。

分析：

1. 每部电影都是一个对象，多部电影要使用集合封装起来。
2. 遍历集合中的3个电影对象，输出每部电影的详情信息。



集合存储对象的原理

- 集合中存储的是对象的地址。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2015.44.32.png?raw=true)

```java
package com.itheima.p2_collection_traverse;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest04Case {
    public static void main(String[] args) {
        //目标：完成电影信息的展示
        //1、创建一个集合容器负责存储多部电影对象。
        Collection<Movie> movies = new ArrayList<>();
        movies.add(new Movie("肖生克的救赎", 9.7, "罗宾斯"));
        movies.add(new Movie("霸王别姬", 9.6, "张国荣、张丰毅"));
        movies.add(new Movie("阿甘正传", 9.5, "汤姆.汉克斯"));
        System.out.println(movies); //打印的是地址 除非在Movie类里重写toString方法

        for (Movie movie : movies) {
            System.out.println("Movie Name: "+ movie.getName());
            System.out.println("Movie Score: "+movie.getScore());
            System.out.println("Movie Actor: "+movie.getActor());
            System.out.println("-----------------------------");
        }
    }
}

```

```java
package com.itheima.p2_collection_traverse;

public class Movie {
    private String name;
    private double score;
    private String actor;

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", actor='" + actor + '\'' +
                '}';
    }

    public Movie() {
    }

    public Movie(String name, double score, String actor) {
        this.name = name;
        this.score = score;
        this.actor = actor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}

```

```
[Movie{name='肖生克的救赎', score=9.7, actor='罗宾斯'}, Movie{name='霸王别姬', score=9.6, actor='张国荣、张丰毅'}, Movie{name='阿甘正传', score=9.5, actor='汤姆.汉克斯'}]
Movie Name: 肖生克的救赎
Movie Score: 9.7
Movie Actor: 罗宾斯
-----------------------------
Movie Name: 霸王别姬
Movie Score: 9.6
Movie Actor: 张国荣、张丰毅
-----------------------------
Movie Name: 阿甘正传
Movie Score: 9.5
Movie Actor: 汤姆.汉克斯
-----------------------------
```



## List集合

List是个接口

### 特点、特有方法

List系列集合特点：有序、可重复、有索引

- ArrayList：有序、可重复、有索引

- LinkedList：有序、可重复、有索引

  二者底层实现不同！适合场景不同！



List集合的特有方法

- List集合因为支持索引，所以多了很多与索引相关的方法。当然，Collection的功能List也都继承了。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2016.11.46.png?raw=true" style="zoom:33%;" />
  
  
  
  

List方法介绍代码：

```java
package com.itheima.p3_collection_list;

import java.util.ArrayList;
import java.util.List;

/**
 目标：掌握List系列集合的特点，以及其提供的特有方法。
 */
public class ListTest1Introduction {
    public static void main(String[] args) {
        // 1.创建一个ArrayList集合对象（有序、可重复、有索引）
        List<String> list = new ArrayList<>();  // 一行经典代码
        list.add("蜘蛛精");
        list.add("至尊宝");
        list.add("至尊宝");
        list.add("牛夫人");
        System.out.println(list); // [蜘蛛精, 至尊宝, 至尊宝, 牛夫人]

        // 2.public void add(int index, E element): 在某个索引位置插入元素。
        list.add(2, "紫霞仙子");
        System.out.println(list);

        // 3.public E remove(int index): 根据索引删除元素,返回被删除元素
        System.out.println(list.remove(2));
        System.out.println(list);

        // 4.public E get(int index): 返回集合中指定位置的元素。
        System.out.println(list.get(3));

        // 5.public E set(int index, E element): 修改索引位置处的元素,修改成功后，会返回原来的数据
        System.out.println(list.set(3, "牛魔王"));
        System.out.println(list);
    }
}

```

```
[蜘蛛精, 至尊宝, 至尊宝, 牛夫人]
[蜘蛛精, 至尊宝, 紫霞仙子, 至尊宝, 牛夫人]
紫霞仙子
[蜘蛛精, 至尊宝, 至尊宝, 牛夫人]
牛夫人
牛夫人
[蜘蛛精, 至尊宝, 至尊宝, 牛魔王]
```



### 遍历方式

1. for循环（因为List集合有索引）
2. 迭代器
3. 增强for循环
4. Lambda表达式



遍历方式介绍代码：

```java
package com.itheima.p3_collection_list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest2Ergodic {
    public static void main(String[] args) {
        //目标：List遍历
        List<String> list = new ArrayList<>();
        list.add("糖宝宝");
        list.add("蜘蛛精");
        list.add("至尊宝");

        //1.for循环
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
        }

        //2.迭代器
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        //3.增强for循环（foreach遍历）
        for (String s : list) {
            System.out.println(s);
        }

        //4.从JDK1.8开始之后的Lambda表达式
        list.forEach(s -> {
            System.out.println(s);
        });
    }
}

```

```
糖宝宝
蜘蛛精
至尊宝
糖宝宝
蜘蛛精
至尊宝
糖宝宝
蜘蛛精
至尊宝
糖宝宝
蜘蛛精
至尊宝
```



### ArrayList集合的底层原理

- 基于数组实现的。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2016.54.27.png?raw=true" style="zoom:33%;" /> 









特点：

- 查询速度快（注意：是根据索引查询数据快）：查询数据通过地址值和索引定位，查询任意数据耗时相同。
- 删除效率低：可能需要把后面很多的数据进行前移。
- 添加效率极低：可能需要把后面很多的数据后移，再添加元素；或者也可能需要进行数组的扩容。



ArrayList集合的底层原理

1. 利用无参构造器创建的集合，会在底层创建一个默认长度为0的数组
2. 添加第一个元素时，底层会创建一个新的长度为10的数组
3. 存满时，会扩容1.5倍
4. 如果一次添加多个元素，1.5倍还放不下，则新创建数组的长度以实际为准

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2017.28.41.png?raw=true" style="zoom:33%;" /> 





ArrayList集合适合的应用场景

1. ArrayList集合适合：根据索引查询数据
   e.g.根据随机索引数据（高效）！或者数据量不是很大时！
2. ArrayList不适合：数据量大的同时，又要频繁的进行增删操作



### LinkedList集合的底层原理

- 基于双链表实现的
- 查询慢，增删相对较快，但对首尾元素进行增删改查的速度是极快的。

![]()





LinkedList新增了：很多首尾操作的特有方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2021.21.54.png?raw=true" style="zoom:33%;" /> 





1. 什么是链表？有啥特点？

- 链表中的结点是独立的对象，在内存中是不连续的，每个结点包含数据值和下一个结点的地址。





<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2021.09.15.png?raw=true" style="zoom:33%;" /> 







链表的特点：

1. 查询慢（无论查数据还是查索引），无论查询哪个数据都要从头开始找。
2. 链表增删相对快

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2021.14.31.png?raw=true" style="zoom:33%;" /> 







双向链表特点

- 查询慢，增删相对较快，但对首尾元素进行增删改查的速度是极快的。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2021.17.36.png?raw=true)





LinkedList的应用场景之一：可以用来设计队列

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2021.24.45.png?raw=true" style="zoom:33%;" /> 





LinkedList队列介绍代码：

```java
package com.itheima.p3_collection_list;

import java.util.LinkedList;

public class ListTest3LinkedList {
    public static void main(String[] args) {
        //目标：掌握LinkedList集合的使用
        //1.创建一个队列
        LinkedList<String> queue = new LinkedList<>(); //不要写多态。左边如果写Collection定义对象就不了LinkedList的特有方法
        //入队
        queue.addLast("Number 1 person");
        queue.addLast("Number 2 person");
        queue.addLast("Number 3 person");
        queue.addLast("Number 4 person");
        System.out.println(queue);
        //出队
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue);
    }
}

```

```
[Number 1 person, Number 2 person, Number 3 person, Number 4 person]
Number 1 person
Number 2 person
Number 3 person
[Number 4 person]
```



LinkedList的应用场景之二：可以用来设计栈

栈的特点：后进先出，先进后出

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2021.34.19.png?raw=true" style="zoom:33%;" /> 



## Set集合

### 特点

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2014.00.52.png?raw=true" style="zoom:33%;" /> 





Set系列集合特点：无序：添加数据的顺序和获取出的数据顺序不一致；不重复；无索引；

- HashSet: 无序、不重复、无索引
- LinkedHashSet：有序、不重复、无索引
- TreeSet：排序、不重复、无索引

Set家族介绍代码：

```java
package com.itheima.p4_collection_set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest1Introduction {
    public static void main(String[] args) {
        //目标：整体了解一下Set系列集合的特点
        //1.创建一个Set集合的对象
        //Set<Integer> set = new HashSet<>(); //一行经典代码 Set是接口，不能new Set(). 可以创建一个HashSet的集合对象。是Set的一个实现类
        //Set<Integer> set = new LinkedHashSet<>(); //有序 不重复 无索引
        Set<Integer> set = new TreeSet<>(); //可排序（升序） 不重复 无索引
        set.add(666);
        set.add(555);
        set.add(888);
        set.add(888);
        set.add(777);
        set.add(777);
        System.out.println(set);

    }
}

```

```
[555, 666, 777, 888]
```



注意：Set要用到的常用方法，基本上就是Collection提供的！！自己几乎没有额外新增的一些常用功能！



### HashSet集合的底层原理

1. 为什么添加的元素无序、不重复、无索引？
2. 增删改查数据有什么特点，适合什么场景？



注意：在正式了解HashSet集合的底层原理前，我们需要先搞清一个前置知识：哈希值

哈希值

- 就是一个int类型的数值，Java中每个对象都有一个哈希值。

- Java中的所有对象，都可以调用Object类提供的hashCode方法，返回该对象自己的哈希值。
  ```java
  public int hashCode():返回对象的哈希码值
  ```



对象哈希值的特点

- 同一个对象多次调用hashCode()方法返回的哈希值是相通的。
- 不同的对象，它们的哈希值一般不同，但也可能会相同（哈希碰撞）。
  Int(-21亿多 ~ 21亿多)       if 有45亿个对象



哈希值介绍代码：

```java
package com.itheima.p4_collection_set;

public class SetTest2Hash {
    public static void main(String[] args) {
        //目标：了解一下哈希值
        Student s1 = new Student("蜘蛛精", 25,169.5);
        Student s2 = new Student("紫霞", 22,166.5);
        System.out.println(s1.hashCode());
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        String str1 = new String("abc");
        String str2 = new String("acD");
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
    }
}

```

```
2055281021
2055281021
1554547125
96354
96354
```



HashSet集合的底层原理

- 基于哈希表实现
- 哈希表是一种增删改查数据，性能都较好的数据结构。



哈希表

- JDK8之前，哈希表=数组+链表
- JDK8开始，哈希表=数组+链表+红黑树



JDK8之前HashSet集合的底层原理，基于哈希表：数组+链表

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.27.00.png?raw=true)











![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.30.22.png?raw=true)







JDK8开始HashSet集合的底层原理，基于哈希表：数组+链表+红黑树

- JDK8开始，当链表长度超过8，且数组长度>=64时，自动将链表转成红黑树。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.36.13.png?raw=true" style="zoom:33%;" /> 



了解一下数据结构（树）

二叉树

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.39.12.png?raw=true" style="zoom:33%;" /> 



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.39.53.png?raw=true" style="zoom:33%;" /> 



二叉查找树

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.43.05.png?raw=true" style="zoom:33%;" /> 



二叉查找树存在的问题：

- （瘸子现象）：当数据已经是排好序的，导致查询性能与单链表一样，查询速度变慢

解决方法：平衡二叉树

- 在满足查找二叉树的大小规则下，让树尽可能矮小，以此提高查数据的性能。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.48.17.png?raw=true" style="zoom:33%;" />

 

红黑树：就是可以自平衡的二叉树

- 红黑树是一种增删改查数据性能都相对较好的结构。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-19%2022.49.36.png?raw=true" style="zoom:33%;" /> 





深入理解HashSet集合去重复的机制

HashSet集合默认不能对内容一样的两个不同对象去重复！

比如内容一样的两个学生对象存入到HashSet集合中去，HashSet集合是不能去重复的！

```java
package com.itheima.p4_collection_set;

import java.util.HashSet;
import java.util.Set;

public class SetTest3Repeat {
    public static void main(String[] args) {
        //目标：自定义的类型的对象，比如两个内容一样的学生对象，如果让HashSet集合能够去重复!
      //两个相同的紫霞，但是分别存入了
        Set<Student> students = new HashSet<>();
        Student s1 = new Student("蜘蛛精", 25,169.5);
        Student s2 = new Student("紫霞", 22,166.5);
        Student s3 = new Student("紫霞", 22,166.5);
        Student s4 = new Student("牛魔王", 48,169.6);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        System.out.println(students);
    }
}

```

```
[Student{name='紫霞', age=22, height=166.5}, Student{name='牛魔王', age=48, height=169.6}, Student{name='蜘蛛精', age=25, height=169.5}, Student{name='紫霞', age=22, height=166.5}]

```



如何让HashSet集合能够实现对内容一样的两个不同对象也能去重复？

HashSet集合去重复详解

结论：如果希望Set集合认为2个内容一样的对象是重复的，必须重写对象的hashCode()和equals()方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-20%2000.28.26.png?raw=true" style="zoom:33%;" /> 







![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-20%2000.28.55.png?raw=true)







### LinkedHashSet集合的底层原理

LinkedHashSet：有序、不重复、无索引

LinkedHashSet特点介绍代码：

```java
package com.itheima.p4_collection_set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest1Introduction {
    public static void main(String[] args) {
        //目标：整体了解一下Set系列集合的特点
        //1.创建一个Set集合的对象
        //Set<Integer> set = new HashSet<>(); //一行经典代码 Set是接口，不能new Set(). 可以创建一个HashSet的集合对象。是Set的一个实现类
        Set<Integer> set = new LinkedHashSet<>(); //有序 不重复 无索引
        //Set<Integer> set = new TreeSet<>(); //可排序（升序） 不重复 无索引
        set.add(666);
        set.add(555);
        set.add(888);
        set.add(888);
        set.add(777);
        set.add(777);
        System.out.println(set);

    }
}

```

```
[666, 555, 888, 777]
```



LinkedHashSet底层原理

- 依然是基于哈希表（数组、链表、红黑树）实现的。
- 但是，他的每个元素都额外的多了一个双链表的机制记录它前后元素的位置。(红色箭头-第1个元素	黄色箭头-最后一个元素)

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-20%2013.20.15.png?raw=true" style="zoom:33%;" /> 





### TreeSet集合

- 特点：不重复、无索引、可排序（默认升序排序，按照元素大小，由小到大排序）
- 底层是基于红黑树实现的排序。

注意：

- 对于数值类型：Integer, Double,默认按照数值本身的大小进行升序排序。
- 对于字符串类型：默认按照首字符的编号升序排序。
- 对于自定义类型：e.g. Student对象，TreeSet默认是无法直接排序的。



自定义排序规则

- TreeSet集合存储自定义类型的对象时，必须制定排序规则，支持如下两种方式来制定比较规则。

  - 方式一：让自定义的类（学生类）实现Comparable接口，重写里面的compareTo方法来指定比较规则。

  - 方式二：通过调用TreeSet集合有参数构造器，可以设置Comparator对象（比较器对象，用于指定比较规则）。
    ```java
    public TreeSet(Comparator<? super E> comparator)
    ```

- 两种方式中，关于返回值的规则：

  - 如果认为第一个元素 > 第二个元素 返回正整数即可。
  - 如果认为第一个元素 < 第二个元素 返回负整数即可。
  - 如果认为第一个元素 = 第二个元素 返回0即可，此时TreeSet集合只会保留一个元素，认为两者重复

  注意：如果类本身有实现Comparable接口，TreeSet集合同时也自带比较器，默认使用集合自带的比较器排序。 

```java
package com.itheima.p4_collection_set;

import com.sun.source.tree.Tree;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest4TreeSet {
    public static void main(String[] args) {
        //目标：掌握TreeSet集合的使用
        Set<Integer> set1 = new TreeSet<>();
        set1.add(6);
        set1.add(5);
        set1.add(5);
        set1.add(7);
        System.out.println(set1);

    /*    //TreeSet就近选择自己自带的比较器对象进行排序
        Set<Student> students = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //需求：按照身高升序排序
                //报错 return o1.getHeight() - o2.getHeight(); 因为身高是小数，减法后还是小数。而要求return结果是正/负整数/0
                return Double.compare(o1.getHeight(),o2.getHeight());
            }
        });*/

        //简化
        Set<Student> students = new TreeSet<>(( o1,  o2)-> Double.compare(o1.getHeight(),o2.getHeight()) );
        students.add(new Student("蜘蛛精",23,169.7));
        students.add(new Student("紫霞",22,169.8));
        students.add(new Student("至尊宝",26,165.5));
        students.add(new Student("牛魔王",22,183.5));
        System.out.println(students);
    }
}

```

```java
package com.itheima.p4_collection_set;

import java.util.Objects;

public class Student implements Comparable<Student>{
    private String name;
    private int age;
    private double height;

    //主调：this  被比较者：o
    @Override
    public int compareTo(Student o) {
        //如果认为左边对象大于右边对象返回正整数
        //如果认为左边对象小于右边对象返回负整数
        //如果认为左边对象等于右边对象返回0
        //需求：按照年龄升序排序
        return this.age - o.age;
    }

    //只要两个对象内容一样，就返回true
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Double.compare(student.height, height) == 0 && Objects.equals(name, student.name);
    }
    //只要两个对象的内容一样，返回的哈希值就是一样的
    @Override
    public int hashCode() {
        return Objects.hash(name, age, height);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

    public Student() {
    }

    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

```

```
[5, 6, 7]
[Student{name='至尊宝', age=26, height=165.5}, Student{name='蜘蛛精', age=23, height=169.7}, Student{name='紫霞', age=22, height=169.8}, Student{name='牛魔王', age=22, height=183.5}]

```



## Collection集合家族用途总结

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-20%2014.35.09.png?raw=true" style="zoom:33%;" /> 



## 集合的并发修改异常问题

什么是集合的并发修改异常？

- 使用迭代器遍历集合时，又同时在删除集合中的数据，程序就会出现并发修改异常的错误。

```java
package com.itheima.p5_collection_exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionTest1Introduction {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("王麻子");
        list.add("小李子");
        list.add("李爱华");
        list.add("张全蛋");
        list.add("小李");
        list.add("李玉罡");
        System.out.println(list);

        //需求：找出集合中全部带“李”的名字，并从集合中删除。
/*        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String name = it.next();
            if (name.contains("李")){
                list.remove(name);
            }
        }
        System.out.println(list);*/

        //使用for循环遍历集合并删除集合中带李字的名字 --会出漏删bug，因为往后遍历时会跳过
/*        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            if (name.contains("李")){
                list.remove(name);
            }
        }
        System.out.println(list);*/

        System.out.println("-------------------------------");
        //怎么解决呢？ 1.i-- 2.倒着删
/*        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            if (name.contains("李")){
                list.remove(name);
                i--;
            }
        }
        System.out.println(list);*/

        //需求：找出集合中全部带“李”的名字，并从集合中删除。
       Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String name = it.next();
            if (name.contains("李")){
                // list.remove(name); 如果用集合自己删除数据，就会并发修改异常的错误
                it.remove(); //迭代器提供的remove（）：删除迭代器当前遍历到的数据，每删除一个一个数据后，相当于也在底层做了i--
            }
        }
        System.out.println(list);

        //用增强for循环也会出并发修改错误bug，增强for循环相当于迭代器简化写法。--无法解决 cuz拿不到迭代器
/*        for (String name : list) {
            if (name.contains("李")){
                list.remove(name);
            }
        }
        System.out.println(list);*/

        //lambda也报并发修改异常，而且没法改。cuz forEach源代码用的是增强for循环
/*        list.forEach(name -> {
            if (name.contains("李")){
                list.remove(name);
            }
        });
        System.out.println(list);*/
    }
}

```

```
[王麻子, 小李子, 李爱华, 张全蛋, 小李, 李玉罡]
-------------------------------
[王麻子, 张全蛋]

```



# D15. 集合框架 JDK8新特性



## Collection的其他相关知识



### 前置知识：可变函数

可变参数

- 就是一种特殊形参，定义在方法、构造器的形参列表里。格式是：数据类型...参数名称；

可变参数的特点和好处

- 特点：可以不传数据给它；可以传一个或者同时传多个数据给它；也可以传一个数组给它。
- 好处：常常用来灵活的接收数据。

可变参数的注意事项：

- 可变参数在方法内部就是一个数组。
- 一个参数列表中可变参数只能有一个
- 可变参数必须放在形参列表的最后面



可变参数介绍代码：

```java
package com.itheima.p1_parameter;

import java.util.Arrays;

public class ParamTestIntroduction {
    public static void main(String[] args) {
        //目标：认识可变参数，掌握其作用
        //特点
        test(); //不传数据
        test(10); //传一个数据
        test(10,20,30); //传多个数据
        test(new int[]{10,20,30,40}); //传输一个数组给可变参数
    }

    //注意事项1:一个形参列表中，只能有一个可变参数. cuz 两个可变参数不知道怎么分配传入的数据
    //注意事项2:可变参数必须放在形参列表的最后面
    public static void test(int... nums){
        //可变参数在方法内部，本质就是一个数组
        System.out.println(nums.length);
        System.out.println(Arrays.toString(nums));
        System.out.println("--------------------------------");
    }
}

```

```
0
[]
--------------------------------
1
[10]
--------------------------------
3
[10, 20, 30]
--------------------------------
4
[10, 20, 30, 40]
--------------------------------
```



### Collections

Collection<font color= "green">s</font>

- 是一个用来操作集合的工具类

Collections提供的常用静态方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-11-20%2015.48.53.png?raw=true) 



Collections方法介绍代码

```java
package com.itheima.p2_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 目标：掌握Collections集合工具类的使用。
 */
public class CollectionsTest1 {
    public static void main(String[] args) {
        // 1、public static <T> boolean addAll(Collection<? super T> c, T...elements)：为集合批量添加数据
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三", "王五", "李四", "张麻子");
        System.out.println(names);

        // 2、public static void shuffle(List<?> list)：打乱List集合中的元素顺序。
        Collections.shuffle(names);
        System.out.println(names);

        // 3、 public static <T> void sort(List<T> list)：对List集合中的元素进行升序排序。
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(2);
        Collections.sort(list);
        System.out.println(list);

        List<Student> students = new ArrayList<>();
        students.add(new Student("蜘蛛精",23, 169.7));
        students.add(new Student("紫霞",22, 169.8));
        students.add(new Student("紫霞",22, 169.8));
        students.add(new Student("至尊宝",26, 165.5));
        // Collections.sort(students);
        // System.out.println(students);

        // 4、public static <T> void sort(List<T> list， Comparator<? super T> c): 对List集合中元素，按照比较器对象指定的规则进行排序
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });
        System.out.println(students);
    }
}

```

```java
package com.itheima.p2_collections;

import java.util.Objects;

public class Student implements Comparable<Student>{
    private String name;
    private int age;
    private double height;

    // this  o
    @Override
    public int compareTo(Student o) {
        // 如果认为左边对象大于右边对象返回正整数
        // 如果认为左边对象小于右边对象返回负整数
        // 如果认为左边对象等于右边对象返回0
        // 需求：按照年龄升序排序、
        return this.age - o.age;
    }

    public Student() {
    }

    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    // 只要两个对象内容一样就返回true
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Double.compare(student.height, height) == 0 && Objects.equals(name, student.name);
    }

    // 只要两个对象内容一样，返回的哈希值就是一样的。
    @Override
    public int hashCode() {
        // 姓名 年龄 身高计算哈希值的
        return Objects.hash(name, age, height);
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

```

```

[张三, 王五, 李四, 张麻子]
[张三, 王五, 张麻子, 李四]
[2, 3, 5]
[Student{name='至尊宝', age=26, height=165.5}, Student{name='蜘蛛精', age=23, height=169.7}, Student{name='紫霞', age=22, height=169.8}, Student{name='紫霞', age=22, height=169.8}]
Disconnected from the target VM, address: '127.0.0.1:59385', transport: 'socket'

Process finished with exit code 0

```



Collections只能支持对List集合进行排序

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-29%2017.40.24.png?raw=true)



## Map集合

### 概述

认识Map集合

- Map集合称为双列集合，格式：{key1 = value1, key2 = value2,...}, 一次需要存一对数据作为一个元素。
- Map集合的每个元素“key=value”称为一个键值对/键值对对象/一个Entry对象，Map集合也被叫做“键值对集合”。
- Map集合的所有键是不允许重复的，但值可以重复，键和值是一一对应的，每一个键只能找到自己对应的值。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2013.40.51.png?raw=true" style="zoom:30%;" />

Map集合在什么业务场景下使用

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2013.42.54.png?raw=true" style="zoom:33%;" />



Map集合体系



Map集合体系的特点

注意：Map系列集合的特点都是由键决定的，值知识一个附属品，值是不作要求的。

- HashMap(由键决定特点)：无序、不重复、无索引；（用得最多）
- LinkedHashMap（由键决定特点）：有序、不重复、无索引
- TreeMap（由键决定特点）：按照大小默认升序排序、不重复、无索引。



Map介绍代码

```java
package com.itheima.p4_map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

//目标：掌握Map集合的特点
public class MapTest1 {
    public static void main(String[] args) {
        // Map<String, Integer> map = new HashMap<>(); //一行经典代码。 按照键 无序、不重复、无索引
        Map<String, Integer> map = new LinkedHashMap<>(); //有序、不重复、®无索引
        map.put("watch", 100);
        map.put("watch", 220); //后面重复的数据会覆盖前面的数据（键）
        map.put("phone", 2);
        map.put("Java", 2);
        map.put(null, null);
        System.out.println(map);

        Map<Integer, String> map1 = new TreeMap<>(); //可排序、不重复、无索引
        map1.put(23,"Java");
        map1.put(23,"MySQL");
        map1.put(19,"李四");
        map1.put(20,"王五");
        System.out.println(map1);
    }
}

```

```
{watch=220, phone=2, Java=2, null=null}
{19=李四, 20=王五, 23=MySQL}
```





### 常用方法

为什么要先学习Map的常用方法？

- 因为Map是双列集合的祖宗，它的功能是全部双列集合都可以继承过来使用的。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2016.03.40.png?raw=true" style="zoom:33%;" /> 

Map常用方法：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2016.58.28.png?raw=true" style="zoom:33%;" /> 



常用方法介绍代码

```java
package com.itheima.p4_map;

/**
 * 目标：掌握Map集合的常用方法
 */

import java.util.*;

/**
 * 目标：掌握Map集合的常用方法(重点)
 */
public class MapTest2 {
    public static void main(String[] args) {
        // 1.添加元素: 无序，不重复，无索引。
        Map<String, Integer> map = new HashMap<>();
        map.put("手表", 100);
        map.put("4手表", 220);
        map.put("手机", 2);
        map.put("Java", 2);
        map.put(null, null);
        System.out.println(map);
        // map = {null=null, 手表=220, Java=2, 手机=2}

        // 2.public int size():获取集合的大小
        System.out.println(map.size());

        // 3、public void clear():清空集合
        //map.clear();
        //System.out.println(map);

        // 4.public boolean isEmpty(): 判断集合是否为空，为空返回true ,反之！
        System.out.println(map.isEmpty());

        // 5.public V get(Object key)：根据键获取对应值
        int v1 = map.get("手表");
        System.out.println(v1);
        System.out.println(map.get("手机")); // 2
        System.out.println(map.get("张三")); // null

        // 6. public V remove(Object key)：根据键删除整个元素(删除键会返回键的值)
        System.out.println(map.remove("手表"));
        System.out.println(map);

        // 7.public  boolean containsKey(Object key): 判断是否包含某个键 ，包含返回true ,反之
        System.out.println(map.containsKey("手表")); // false
        System.out.println(map.containsKey("手机")); // true
        System.out.println(map.containsKey("java")); // false
        System.out.println(map.containsKey("Java")); // true

        // 8.public boolean containsValue(Object value): 判断是否包含某个值。
        System.out.println(map.containsValue(2)); // true
        System.out.println(map.containsValue("2")); // false

        // 9.public Set<K> keySet(): 获取Map集合的全部键。
        Set<String> keys = map.keySet();
        System.out.println(keys);

        // 10.public Collection<V> values(); 获取Map集合的全部值。
        Collection<Integer> values = map.values();
        System.out.println(values);

        // 11.把其他Map集合的数据倒入到自己集合中来。(拓展)
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("java1",  10);
        map1.put("java2",  20);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("java3",  10);
        map2.put("java2",  222);
        map1.putAll(map2); // putAll：把map2集合中的元素全部倒入一份到map1集合中去。
        System.out.println(map1);
        System.out.println(map2);
    }
}


```

```
{null=null, 手表=220, Java=2, 手机=2}
4
false
220
2
null
220
{null=null, Java=2, 手机=2}
false
true
false
true
true
false
[null, Java, 手机]
[null, 2, 2]
{java3=10, java2=222, java1=10}
{java3=10, java2=222}
```



### 遍历方式

Map集合的遍历方式

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2017.01.37.png?raw=true" style="zoom:33%;" /> 



#### Map集合的遍历方式一：键找值

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2017.03.30.png?raw=true" style="zoom:33%;" /> 

键找值介绍代码：

```java
package com.itheima.p5_map_traverse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//目标：掌握Map集合的遍历方式1: 键找值
public class MapTest1 {
    public static void main(String[] args) {
        //准备一个Map集合
        Map<String, Double> map = new HashMap<>();
        map.put("蜘蛛精", 162.5);
        map.put("蜘蛛精", 169.8);
        map.put("紫霞", 165.8);
        map.put("至尊宝", 169.5);
        map.put("牛魔王", 183.6);
        System.out.println(map);

        // 1. 获取Map集合的全部键
        Set<String> keys = map.keySet();
        System.out.println(keys);
        // 2. 遍历全部的键，根据键获取全部的值
        for (String key : keys){
            // 根据键获取对应的值
            double value = map.get(key);
            System.out.println(key + "=>" + value);
        }
    }
}

```

```
{蜘蛛精=169.8, 牛魔王=183.6, 至尊宝=169.5, 紫霞=165.8}
[蜘蛛精, 牛魔王, 至尊宝, 紫霞]
蜘蛛精=>169.8
牛魔王=>183.6
至尊宝=>169.5
紫霞=>165.8
```



#### Map集合的遍历方式二：键值对

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2021.24.57.png?raw=true)



键值对遍历介绍代码：

```java
package com.itheima.p5_map_traverse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//目标：掌握Map集合的第二种遍历方式：键值对
public class MapTest2 {
    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        map.put("蜘蛛精", 162.5);
        map.put("蜘蛛精", 169.8);
        map.put("紫霞", 165.8);
        map.put("至尊宝", 169.5);
        map.put("牛魔王", 183.6);
        System.out.println(map);

        // 1. 调用Map集合提供entrySet方法，把Map集合转换成键值对类型的Set集合
        Set<Map.Entry<String, Double>> entries = map.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            String key = entry.getKey();
            double value = entry.getValue();
            System.out.println(key +"=>"+ value);
        }
    }
}

```

```
{蜘蛛精=169.8, 牛魔王=183.6, 至尊宝=169.5, 紫霞=165.8}
蜘蛛精=>169.8
牛魔王=>183.6
至尊宝=>169.5
紫霞=>165.8
```



#### Map集合的遍历方式三：Lambda表达式

需要用到Map的如下方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2022.00.18.png?raw=true" style="zoom:33%;" /> 



Lambda表达式代码介绍：

```java
package com.itheima.p5_map_traverse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapTest3Lambda {
    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        map.put("蜘蛛精", 162.5);
        map.put("蜘蛛精", 169.8);
        map.put("紫霞", 165.8);
        map.put("至尊宝", 169.5);
        map.put("牛魔王", 183.6);
        System.out.println(map);

//        map.forEach((k, v)->{
//            System.out.println(k+"->"+v); //优雅
//        });

//        map.forEach(new BiConsumer<String, Double>() {
//            @Override
//            public void accept(String k, Double v) {
//                System.out.println(k+"->"+v);
//            }
//        });

        map.forEach(( k,  v) -> {
                System.out.println(k+"->"+v);

        });

    }
}

```

```
{蜘蛛精=169.8, 牛魔王=183.6, 至尊宝=169.5, 紫霞=165.8}
蜘蛛精->169.8
牛魔王->183.6
至尊宝->169.5
紫霞->165.8
```



#### 案例：Map集合的案例 - 统计投票人数

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-30%2022.28.53.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p5_map_traverse;

import java.util.*;

//目标；完成Map集合的案例：统计投票人数
public class MapDemo4Case {
    public static void main(String[] args) {
        // 1. 把80个学生选择的景点数据拿到程序中来。
        List<String> data = new ArrayList<>();
        String[] selects = {"A","B","C","D"};
        Random r = new Random();
        for (int i = 1; i <= 80; i++) {
            //每次模拟一个学生选择一个景点，存入到集合中去。
            int index = r.nextInt(4); // 0  1 2 3
            data.add(selects[index]);
        }
        System.out.println(data);

        // 2. 开始统计每个景点的投票人数
        // 准备一个Map集合用于统计最终的结果
        Map<String, Integer> result = new HashMap<>();
        // 3. 开始遍历80个景点数据
        for (String s : data) {
            //问问Map集合中是否存在该景点
            if (result.containsKey(s)){
                //说明这个景点之前统计过。其值+1。存入到Map集合中去
                result.put(s, result.get(s)+1);
            }else {
                //说明这个景点第一次统计，存入“景点=1”
                result.put(s,1);
            }
        }
        System.out.println(result);
    }
}

```

```
[C, D, B, A, D, D, D, A, C, C, B, B, C, A, A, A, D, D, A, B, C, A, B, C, A, D, A, C, B, C, C, C, B, A, D, C, A, A, B, B, C, D, C, B, C, B, C, D, A, C, B, B, B, A, B, B, D, C, A, C, C, A, C, C, B, B, C, A, B, C, B, A, B, A, B, A, D, C, D, A]
{A=21, B=22, C=24, D=13}
```



#### HashMap

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2009.59.25.png?raw=true" style="zoom:33%;" /> 



HashMap集合的底层原理

- HashMap跟HashSet的底层原理是一模一样的，都是基于哈希表实现的。
  实际上：原来学的Set系列集合的底层就是基于Map实现的，只是Set集合中的元素只要键数据，不要值数据而已。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2010.10.00.png?raw=true" style="zoom:33%;" /> 

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2010.12.45.png?raw=true" style="zoom:33%;" />

注意：

- HashMap集合是一种增删改查数据，性能都较好的集合
- 但是它是无序，不能重复，没有索引支持的（由键决定特点）
- HashMap的键依赖hashCode方法和equals方法保证键的唯一
- 如果键存储的是自定义类型的对象，可以通过重写hashCode和equals方法，这样可以保证多个对象内容一样时，HashMap集合就能认为是重复的。



重写hashCode和equals演示代码

```java
package com.itheima.p6_map_impl;

import java.util.HashMap;
import java.util.Map;

//目标：掌握Map集合下的实现类：HashMap集合的底层原理
public class Test1HashMap {
    public static void main(String[] args) {
        Map<Student, String> map = new HashMap<>();
        map.put(new Student("蜘蛛精", 25, 168.5), "盘丝洞");
        map.put(new Student("蜘蛛精", 25, 168.5), "水帘洞");
        map.put(new Student("至尊宝", 23, 163.5), "水帘洞");
        map.put(new Student("牛魔王", 28, 183.5), "牛头山");
        System.out.println(map);
    }
}

```

```java
package com.itheima.p6_map_impl;

import java.util.Objects;

public class Student{
    private String name;
    private int age;
    private double height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Double.compare(student.height, height) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height);
    }

    public Student() {
    }

    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

```

```
{Student{name='至尊宝', age=23, height=163.5}=水帘洞, Student{name='牛魔王', age=28, height=183.5}=牛头山, Student{name='蜘蛛精', age=25, height=168.5}=水帘洞}

```





#### LinkedHashMap

- 有序、不重复、无索引

```java
package com.itheima.p6_map_impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// 目标：掌握LinkedHashMap的底层原理。
public class Test2LinkedHashMap {
    public static void main(String[] args) {
        //Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("手表", 100);
        map.put("手表", 220);
        map.put("手机", 2);
        map.put("Java", 2);
        map.put(null, null);
        System.out.println(map);
    }
}

```

```
{手表=220, 手机=2, Java=2, null=null}
```



LinkedHashMap集合的原理

- 底层数据结构依然是基于哈希表实现的，只是每个键值对元素又额外的多了一个双链表的机制记录元素顺序（保证有序）。
  实际上：LinkedHashSet集合的底层原理就是LinkedHashMap。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2010.59.28.png?raw=true)



#### TreeMap

- 按照键的大小默认升序排序、不重复、无索引
- 原理：TreeMap跟TreeSet集合的底层原理是一样的，都是基于红黑树实现的排序。



TreeMap集合同样也支持两种方式来制定排序规则

- 让类实现Comparable接口，重写比较规则。
- TreeMap集合有一个由参数构造器，支持创建Comparator比较器对象，以便用来制定比较规则。

````java
package com.itheima.p6_map_impl;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//目标：掌握TreeMap集合的使用
public class Test3TreeMap {
    public static void main(String[] args) {
        Map<Student, String> map = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o2.getHeight(), o1.getHeight());
            }
        });
        map.put(new Student("蜘蛛精", 25, 168.5), "盘丝洞");
        map.put(new Student("蜘蛛精", 25, 168.5), "水帘洞");
        map.put(new Student("至尊宝", 23, 163.5), "水帘洞");
        map.put(new Student("牛魔王", 28, 183.5), "牛头山");
        System.out.println(map);
    }
}

````

```java
package com.itheima.p6_map_impl;

import java.util.Objects;

public class Student implements Comparable<Student>{
    private String name;
    private int age;
    private double height;

    // this o
    @Override
    public int compareTo(Student o) {
        return this.age - o.age; //年龄升序排序
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Double.compare(student.height, height) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height);
    }

    public Student() {
    }

    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

```

```
{Student{name='牛魔王', age=28, height=183.5}=牛头山, Student{name='蜘蛛精', age=25, height=168.5}=水帘洞, Student{name='至尊宝', age=23, height=163.5}=水帘洞}

```



#### 补充知识：集合的嵌套

- 指的是集合中的元素又是一个集合



Map集合案例-省和市

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2011.19.25.png?raw=true" style="zoom:33%;" /> 

代码：

```java
package com.itheima.p7_collection_nesting;


import java.util.*;
/**
 * 目标：理解集合的嵌套。
 * 江苏省 = "南京市","扬州市","苏州市“,"无锡市","常州市"
 * 湖北省 = "武汉市","孝感市","十堰市","宜昌市","鄂州市"
 * 河北省 = "石家庄市","唐山市", "邢台市", "保定市", "张家口市"
 */
public class Test {
    public static void main(String[] args) {
        //1. 定义一个Map集合存储全部的省份信息，
        Map<String, List<String>> map = new HashMap<>();
        List<String> cities1 = new ArrayList<>();
        Collections.addAll(cities1, "南京市","扬州市","苏州市","无锡市", "常州市");
        map.put("江苏省", cities1);

        List<String> cities2 = new ArrayList<>();
        Collections.addAll(cities2, "武汉市","孝感市","十堰市","宜昌市","鄂州市");
        map.put("湖北省", cities2);

        List<String> cities3 = new ArrayList<>();
        Collections.addAll(cities3, "石家庄市","唐山市", "邢台市", "保定市", "张家口市");
        map.put("河北省", cities3);
        System.out.println(map);

        List<String> cities = map.get("湖北省");
        for (String city : cities) {
            System.out.println(city);
        }

        map.forEach((p, c) -> {
            System.out.println(p+"->"+c);
        });
    }
}

```

```
{江苏省=[南京市, 扬州市, 苏州市, 无锡市, 常州市], 湖北省=[武汉市, 孝感市, 十堰市, 宜昌市, 鄂州市], 河北省=[石家庄市, 唐山市, 邢台市, 保定市, 张家口市]}
武汉市
孝感市
十堰市
宜昌市
鄂州市
江苏省->[南京市, 扬州市, 苏州市, 无锡市, 常州市]
湖北省->[武汉市, 孝感市, 十堰市, 宜昌市, 鄂州市]
河北省->[石家庄市, 唐山市, 邢台市, 保定市, 张家口市]
```



## JDK8新特性：Stream

### 认识Stream

什么是Stream？

- 也叫Stream流，是jdk8开始新增的一套API(java.util.stram.*)，可以用于操作集合或者数组的数据。
- 优势：Stream流大量的结合了Lambda的语法风格来编程，提供了一种更加强大，更加简单的方式操作集合或者数组中的数据，代码更简洁，可读性更好



案例：体验Stream流

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2015.35.06.png?raw=true" style="zoom:33%;" /> 

案例代码：

```java
package com.itheima.p8_stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//目标：初步体验Stream流的方便与快捷
public class StreamTest1 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三丰","张无忌","周芷若","赵敏","张强");
        System.out.println(names);
        // names = [张三丰, 张无忌, 周芷若, 赵敏, 张强]
        //          name

        // 找出姓张，且是3个字的名字，存入到一个新集合中去。
        List<String> list = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("张") && name.length()==3){
                list.add(name);
            }
        }
        System.out.println(list);

        //开始使用Stream流来解决这个需求。
        List<String> list2 =  names.stream().filter(s -> s.startsWith("张")).filter(a -> a.length()==3).collect(Collectors.toList());
        System.out.println(list2);
    }
}

```

```
[张三丰, 张无忌, 周芷若, 赵敏, 张强]
[张三丰, 张无忌]
[张三丰, 张无忌]
```



Stream流的使用步骤

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2015.55.01.png?raw=true)





### Stream流的常用方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2015.56.19.png?raw=true)



#### 1. 获取Stream流？

- 获取集合的Stream流
- 获取数组的Stream流

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2016.29.44.png?raw=true" style="zoom:33%;" /> 



获取Stream流介绍代码：
```java
package com.itheima.p8_stream;

import java.util.*;
import java.util.stream.Stream;

/**
 * 目标：掌握Stream流的创建。
 */
public class StreamTest2 {
    public static void main(String[] args) {
        // 1、如何获取List集合的Stream流？
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "张三丰","张无忌","周芷若","赵敏","张强");
        Stream<String> stream = names.stream();

        // 2、如何获取Set集合的Stream流？
        Set<String> set = new HashSet<>();
        Collections.addAll(set, "刘德华","张曼玉","蜘蛛精","马德","德玛西亚");
        Stream<String> stream1 = set.stream();
        stream1.filter(s -> s.contains("德")).forEach(s -> System.out.println(s));

        // 3、如何获取Map集合的Stream流？
        Map<String, Double> map = new HashMap<>();
        map.put("古力娜扎", 172.3);
        map.put("迪丽热巴", 168.3);
        map.put("马尔扎哈", 166.3);
        map.put("卡尔扎巴", 168.3);

        Set<String> keys = map.keySet();
        Stream<String> ks = keys.stream();

        Collection<Double> values = map.values();
        Stream<Double> vs = values.stream();

        Set<Map.Entry<String, Double>> entries = map.entrySet();
        Stream<Map.Entry<String, Double>> kvs = entries.stream();
        kvs.filter(e -> e.getKey().contains("巴"))
                .forEach(e -> System.out.println(e.getKey()+ "-->" + e.getValue()));

        // 4、如何获取数组的Stream流？
        String[] names2 = {"张翠山", "东方不败", "唐大山", "独孤求败"};
        Stream<String> s1 = Arrays.stream(names2);
        Stream<String> s2 = Stream.of(names2);
    }
}


```

````
马德
德玛西亚
刘德华
迪丽热巴-->168.3
卡尔扎巴-->168.3
````



#### 2. Stream流常见的中间方法

- 中间方法指的是调用完成后会返回新的Stream流，可以继续使用（支持链式编程）。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2016.47.26.png?raw=true)

Stream流方法使用展示代码：

```java
package com.itheima.p8_stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
/**
 * 目标：掌握Stream流提供的常见中间方法。
 */
public class StreamTest3 {
    public static void main(String[] args) {
        List<Double> scores = new ArrayList<>();
        Collections.addAll(scores, 88.5, 100.0, 60.0, 99.0, 9.5, 99.6, 25.0);
        // 需求1：找出成绩大于等于60分的数据，并升序后，再输出。
        scores.stream().filter(s -> s >= 60).sorted().forEach(s -> System.out.println(s));

        List<Student> students = new ArrayList<>();
        Student s1 = new Student("蜘蛛精", 26, 172.5);
        Student s2 = new Student("蜘蛛精", 26, 172.5);
        Student s3 = new Student("紫霞", 23, 167.6);
        Student s4 = new Student("白晶晶", 25, 169.0);
        Student s5 = new Student("牛魔王", 35, 183.3);
        Student s6 = new Student("牛夫人", 34, 168.5);
        Collections.addAll(students, s1, s2, s3, s4, s5, s6);
        // 需求2：找出年龄大于等于23,且年龄小于等于30岁的学生，并按照年龄降序输出.
        students.stream().filter(s -> s.getAge() >= 23 && s.getAge() <= 30)
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(s -> System.out.println(s));

        // 需求3：取出身高最高的前3名学生，并输出。
        students.stream().sorted((o1, o2) -> Double.compare(o2.getHeight(), o1.getHeight()))
                .limit(3).forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");

        // 需求4：取出身高倒数的2名学生，并输出。   s1 s2 s3 s4 s5 s6
        students.stream().sorted((o1, o2) -> Double.compare(o2.getHeight(), o1.getHeight()))
                .skip(students.size() - 2).forEach(System.out::println);

        // 需求5：找出身高超过168的学生叫什么名字，要求去除重复的名字，再输出。
        students.stream().filter(s -> s.getHeight() > 168).map(Student::getName)
                .distinct().forEach(System.out::println);

        // distinct去重复，自定义类型的对象（希望内容一样就认为重复，重写hashCode,equals）
        students.stream().filter(s -> s.getHeight() > 168)
                .distinct().forEach(System.out::println);

        Stream<String> st1 = Stream.of("张三", "李四");
        Stream<String> st2 = Stream.of("张三2", "李四2", "王五");
        Stream<String> allSt = Stream.concat(st1, st2);
        allSt.forEach(System.out::println);
    }
}

```

```java
package com.itheima.p8_stream;

import java.util.Objects;

public class Student {
    private String name;
    private int age;
    private double height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Double.compare(student.height, height) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height);
    }

    public Student() {
    }

    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

```

```
60.0
88.5
99.0
99.6
100.0
Student{name='蜘蛛精', age=26, height=172.5}
Student{name='蜘蛛精', age=26, height=172.5}
Student{name='白晶晶', age=25, height=169.0}
Student{name='紫霞', age=23, height=167.6}
Student{name='牛魔王', age=35, height=183.3}
Student{name='蜘蛛精', age=26, height=172.5}
Student{name='蜘蛛精', age=26, height=172.5}
----------------------------------------------------------------
Student{name='牛夫人', age=34, height=168.5}
Student{name='紫霞', age=23, height=167.6}
蜘蛛精
白晶晶
牛魔王
牛夫人
Student{name='蜘蛛精', age=26, height=172.5}
Student{name='白晶晶', age=25, height=169.0}
Student{name='牛魔王', age=35, height=183.3}
Student{name='牛夫人', age=34, height=168.5}
张三
李四
张三2
李四2
王五

```



#### 3. Stream流常见的终结方法

- 终结方法指的是调用后，不会返回新Stream了，没法继续使用流了。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2017.17.39.png?raw=true)

- 收集Stream流：就是把Stream流操作后的结果转回到集合或者数组中去返回。
- Stream流：方便操作集合/数组的<font color= "blue">手段</font>； 集合/数组：才是开发中的<font color= "blue">目的</font>。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2017.52.27.png?raw=true)



# D16.  File、IO流

存储数据的方案

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2021.47.14.png?raw=true)



有些数据想长久保存起来，咋整？

- 文件是非常重要的存储方式，在计算机硬盘中。
- 即便断电，或者程序终止了，存储在硬盘中文件中的数据也不会丢失。



File

- File是java.io.包下的类，File类的对象，用于代表当前操作系统的文件（可以是文件、或文件夹）。
- 注意：File类只能对文件本身进行操作，不能读写文件里面存储的数据。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2021.51.35.png?raw=true" style="zoom:33%;" /> 



IO流

- 用于读写数据的（可以读写文件，或网络中的数据...）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2023-12-31%2021.54.01.png?raw=true" style="zoom:33%;" /> 



## File

### 创建对象

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2011.34.38.png?raw=true" style="zoom:33%;" /> 

注意：

- File对象既可以代表文件、也可以代表文件夹。
- File封装的对象仅仅是一个路径名，这个路径可以是存在的，也允许是不存在的，



绝对路径、相对路径

- 绝对路径：从盘符开始
  ````java
  File f4 = new File("/Users/Hardy/Desktop/JAVA/Code/javasepromax/file-io-app/src/itheima.txt");
  ````

- 相对路径(重点)：不带盘符，默认直接到当前工程下的目录寻找文件。

  ```java
  File f4 = new File("file-io-app/src/itheima.txt");
  ```



### 常用方法1: 判断文件类型、获取文件信息

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2014.19.38.png?raw=true" style="zoom:33%;" /> 

````java
package com.itheima.p1_file;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 目标：掌握File提供的判断文件类型、获取文件信息功能
 */
public class FileTest2 {
    public static void main(String[] args) {
        // 1.创建文件对象，指代某个文件
        File f1 = new File("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/txt文件/ab.txt");
        //File f1 = new File("D:/resource/");

        // 2、public boolean exists()：判断当前文件对象，对应的文件路径是否存在，存在返回true.
        System.out.println(f1.exists());

        // 3、public boolean isFile() : 判断当前文件对象指代的是否是文件，是文件返回true，反之。
        System.out.println(f1.isFile());

        // 4、public boolean isDirectory()  : 判断当前文件对象指代的是否是文件夹，是文件夹返回true，反之。
        System.out.println(f1.isDirectory());

        // 5.public String getName()：获取文件的名称（包含后缀）
        System.out.println(f1.getName());

        // 6.public long length()：获取文件的大小，返回字节个数
        System.out.println(f1.length());

        // 7.public long lastModified()：获取文件的最后修改时间。
        long time = f1.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(sdf.format(time));

        // 8.public String getPath()：获取创建文件对象时，使用的路径
        File f2 = new File("D:\\resource\\ab.txt");
        File f3 = new File("file-io-app\\src\\itheima.txt");
        System.out.println(f2.getPath());
        System.out.println(f3.getPath());

        // 9.public String getAbsolutePath()：获取绝对路径
        System.out.println(f2.getAbsolutePath());
        System.out.println(f3.getAbsolutePath());
    }
}

````

```
true
true
false
ab.txt
2
2024/01/01 11:53:12
D:\resource\ab.txt
file-io-app\src\itheima.txt
/Users/Hardy/Desktop/JAVA/Code/javasepromax/D:\resource\ab.txt
/Users/Hardy/Desktop/JAVA/Code/javasepromax/file-io-app\src\itheima.txt

```



### 常用方法2: 创建文件、删除文件

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2014.41.52.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p1_file;

import java.io.File;
import java.io.IOException;

/**
 * 目标：掌握File创建和删除文件相关的方法。
 */
public class FileTest3 {
    public static void main(String[] args) throws IOException {
        // 1、public boolean createNewFile()：创建一个新文件（文件内容为空），创建成功返回true,反之。
        File f1 = new File("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/txt文件/itheima2.txt");
        System.out.println(f1.createNewFile());

        // 2、public boolean mkdir()：用于创建文件夹，注意：只能创建一级文件夹
        File f2 = new File("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/txt文件/aaa");
        System.out.println(f2.mkdir());

        // 3、public boolean mkdirs()：用于创建文件夹，注意：可以创建多级文件夹
        File f3 = new File("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/txt文件/bbb/ccc/ddd");
        System.out.println(f3.mkdirs());

        // 3、public boolean delete()：删除文件，或者空文件，注意：不能删除非空文件夹。
        System.out.println(f1.delete());
        System.out.println(f2.delete());
        File f4 = new File("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/txt文件");
        System.out.println(f4.delete());
    }
}

```

```
true
true
false
true
true
false
```



### 常用方法3: 遍历文件夹

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2015.02.02.png?raw=true" style="zoom:33%;" /> 



## 方法递归

### 认识递归的形式

什么是方法递归？

- 递归是一种算法，在程序设计语言中广泛应用
- 从形式上说：方法调用自己的形式称为方法递归 (recursion).

递归的形式

- 直接递归：方法自己调用自己。
- 间接递归：方法调用其他方法，其他方法又回调方法自己。

使用方法递归时需要注意的问题：

- 递归如果没有控制好终止，会出现递归死循环，导致栈内存溢出错误。



方法递归介绍代码：

```java
package com.itheima.p2_recursion;
//目标：认识一下递归的形式
public class RecursionTest1 {
    public static void main(String[] args) {
        test1();
    }

    //直接方法递归
    public static void test1(){
        System.out.println("---test1---");
        test1(); //直接方法递归
    }

    //间接方法递归
    public static void test2(){
        System.out.println("---test2----");
        test3();
    }

    public static void test3(){
        test2(); //间接递归
    }
}

```

```
栈内存溢出错误
```



### 应用、执行流程、算法思想

案例：计算n的阶乘

需求：计算n的阶乘，5的阶乘=1x2x3x4x5;

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2015.25.22.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p2_recursion;
//目标：掌握递归的应用，执行流程和算法思想
public class RecursionTest2 {
    public static void main(String[] args) {
        System.out.println("5的阶乘是："+f(5));
    }

    public static int f(int n){
        //终结点
        if (n==1){
            return 1;
        }else {
            return f(n-1) * n;
        }
    }
}

```

```
5的阶乘是：120
```

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2015.36.21.png?raw=true" style="zoom:33%;" /> 



递归算法三要素：

- 递归的公式：f(n) = f(n-1)*n;
- 递归的终结点：f(1)
- 递归的方向必须走向终结点：
  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2015.38.20.png?raw=true" style="zoom:33%;" />



递归案例2: 求1-n的和

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2015.41.21.png?raw=true" style="zoom:33%;" />  

```java
package com.itheima.p2_recursion;
//目标：掌握递归的应用，执行流程和算法思想
public class RecursionTest3 {
    public static void main(String[] args) {
        System.out.println("1-5的和："+f(5));
    }

    public static int f(int n){
        //终结点
        if (n==1){
            return 1;
        }else {
            return f(n-1) + n;
        }
    }
}

```

```
1-5的和：15
```

 

递归案例3: 猴子吃桃问题

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2015.58.28.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p2_recursion;

public class RecursionTest4 {
    public static void main(String[] args) {
        //目标：猴子吃桃问题
        //f(10) = 1 终结点
        //公式：f(x) - f(x)/2 - 1 = f(x+1)
        //变形：f(x) = 2*f(x+1)+2
        //求 f(1) = ?
        System.out.println(f(1));
        System.out.println(f(2));
    }

    public static int f(int n){
        if (n==10){
            return 1;
        }else {
            return 2*f(n+1)+2;
        }
    }
}

```

```
1534
766
```



### 文件搜索

案例：从桌面中，搜索”ab.txt“这个文件，找到后直接输出其位置。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2016.02.02.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p2_recursion;

import java.io.File;
import java.io.IOException;

// 目标：掌握文件搜索的实现
public class RecursionTest5FileSearch {
    public static void main(String[] args) throws IOException {
        searchFile(new File("/Users/Hardy/Desktop/JAVA"),"ab.txt");
    }

    /**
     * //去目录下搜索某个文件
     * @param dir 目录
     * @param fileName 要搜索的文件名称
     */
    public static void searchFile(File dir, String fileName) throws IOException {
        // 1. 把非法的情况都拦截住
        if (dir == null || !dir.exists() || dir.isFile()){
            return; //代表无法搜索
        }

        // 2. dir不是null，存在，一定是目录对象
        // 获取当前目录下的全部一级文件对象.
        File[] files = dir.listFiles();

        // 3. 判断当前目录下是否存在一级文件对象，一级是否可以拿到一级文件对象。
        if (files != null && files.length > 0){
            // 4. 遍历全部一级文件对象。
            for (File f : files) {
                // 5. 判断文件是否是文件，还是文件夹
                if (f.isFile()){
                    //是文件，判断这个文件名是否是我们要找的
                    if (f.getName().contains(fileName)){
                        System.out.println("Find it! "+ f.getAbsolutePath());
                        Runtime runtime = Runtime.getRuntime();
                        runtime.exec(f.getAbsolutePath());
                    }
                }else {
                    //是文件夹，继续重复这个过程（递归）
                    searchFile(f, fileName);
                }
            }
        }
    }

}

```

```
Find it! /Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/txt文件/ab.txt
```



## 字符集

### 常见字符集介绍

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2016.48.45.png?raw=true" style="zoom:33%;" /> 

标准ASCII字符集

- ASCII(American Standard Code for Information Interchange): 美国信息交换标准代码，包括了英文、符号等。
- 标准ASCII使用1个字节存储一个字符，首位是0，总共可表示128个字符，对美国佬来说完全够用。



GBK（汉字内码扩展规范，国标）

- 汉字编码字符集，包含了2万多个汉字等字符，GBK中一个中文字符编码成两个字节的形式存储。

- 注意：GBK兼容了ASCII字符集。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2016.55.46.png?raw=true" style="zoom:33%;" /> 



Unicode字符集(统一码，也叫万国码)

- Unicode是国际组织制定的，可以容纳世界上所有文字、符号的字符集。
  - e.g. UTF-32 4个字节表示一个字符，有容乃大。但占存储空间，通信效率变低！



UTF-8

- 是Unicode字符集的一种编码方案，采取可变长编码方案，共分四个长度区：1个字节，2个字节，3个字节，4个字节。

- 英文字符、数字等只占1个字节（兼容标准ASCII编码），汉字字符占用3个字节。

- 注意：技术人员在开发时都应该使用UTF-8编码！

  ![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2017.09.38.png?raw=true)





<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2017.14.16.png?raw=true" style="zoom:33%;" /> 



### 字符集的编码、解码操作

编码：把字符按照指定的字符集编码成字节。

解码：把字节按照指定的字符集解码成字符。



Java代码完成了对字符的编码/解码

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2017.23.12.png?raw=true)

```java
package com.itheima.p3_charset;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

//目标：掌握如何使用Java代码完成对字符的编码和解码
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 1. 编码
        String data = "a我b";
        byte[] bytes = data.getBytes(); //默认是按照平台字符集(UTF-8)进行编码的。
        System.out.println(Arrays.toString(bytes));

        //按照指定字符集进行编码。
        byte[] bytes1 = data.getBytes("GBK");
        System.out.println(Arrays.toString(bytes1));

        // 2. 解码
        String s1 = new String(bytes); //按照平台默认编码(UTF-8)解码
        System.out.println(s1);

        String s2 = new String(bytes1, "GBK");
        System.out.println(s2);
    }
}

```

````
[97, -26, -120, -111, 98]
[97, -50, -46, 98]
a我b
a我b
````



## IO流

 IO流概述

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2020.51.44.png?raw=true" style="zoom:33%;" /> 



IO流的应用场景

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2020.54.10.png?raw=true" style="zoom:33%;" /> 



怎么学IO流？

1. 先搞清楚IO流的分类、体系。
2. 再挨个学习每个IO流的作用、用法。



IO流的分类

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2020.56.26.png?raw=true" style="zoom:33%;" /> 

IO流总体来有四大流：

- 字节输入流：以内存为基准，来自磁盘文件/网络中的数据以字节的形式读入到内存中去的流
- 字节输出流
- 字符输入流
- 字符输出流



IO流的体系

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2021.05.12.png?raw=true" style="zoom:33%;" /> 



## IO流-字节流

### 文件字节输入流：每次读取一个字节

FIleInputStream（文件字节输入流）

- 作用：以内存为基准，可以把磁盘文件中的数据以字节的形式读入到内存中去。

- 注意：使用FileInputStream每次读取一个字节，读取性能较差，并且读取汉字输出会乱码。

  <img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2021.18.08.png?raw=true" style="zoom:33%;" /> 

  

每次读取一个字节案例代码：

```java
package com.itheima.p4_byte_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//目标：掌握文件字节输入流，每次读取一个字节
public class FileInputStreamTest1 {
    public static void main(String[] args) throws Exception {
        // 1. 创建文件字节输入流管道，与源文件接通。
        //InputStream is = new FileInputStream(new File("file-io-app/src/itheima.txt"));
        //简化写法：推荐使用
        InputStream is = new FileInputStream("file-io-app/src/itheima.txt");

        // 2. 开始读取文件的字节数据。
        //public int read(): 每次读取一个字节返回，如果没有数据了，返回-1.
//        int b1 = is.read();
//        System.out.println((char)b1);
//
//        int b2 = is.read();
//        System.out.println((char)b2);
//
//        int b3 = is.read();
//        System.out.println(b3);

        // 3. 使用循环改造上述代码
        int b; // 用于记住读取的字节。
        while((b = is.read()) != -1){
            System.out.print((char)b);
        }

        // 读取数据的性能很差！
        // 读取汉字输出会乱码！！中文UTF-8是三个字节，一个字节一个字节读肯定会乱码
        // 流使用完毕之后，必须关闭！释放系统资源！
        is.close();
    }
}

```

```
abxdzfvxzdfsaæ
```



### 文件字节输入流：每次读取多个字节

注意：使用FileInputStream每次读取多个字节，读取性能得到了提升，但读取汉字输出还是会乱码。

解决方案：

1. 使用字节流读取中文，如何保证输出不乱码，怎么解决？
   - 定义一个与文件一样大的字节数组，一次性读取完文件的全部字节。



每次读取多个字符案例代码：

````java
package com.itheima.p4_byte_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//目标：掌握使用FileInputStream每次读取多个字节
public class FileInputStreamTest2 {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个字节输入流对象代表字节输入流管道与源文件接通。
        InputStream is = new FileInputStream("file-io-app/src/itheima.txt");

        // 2. 开始读取文件中的字节数据：每次读取多个字节
        // public int read(byte b[]) throws IOException
        // 每次读取多个字节到字节数组中去，返回读取的字节数量，读取完毕会返回-1.
//        byte[] buffer = new byte[3]; //buffer理解成水桶
//        int len = is.read(buffer);
//        String rs = new String(buffer);
//        System.out.println(rs);
//        System.out.println("The number of byte in this read is : "+ len);
//
//        // buffer = [abc]
//        // buffer = [66c]
//        int len2 = is.read(buffer);
//        // 注意：读取多少，倒出多少
//        String rs2 = new String(buffer, 0, len2);
//        System.out.println(rs2);
//        System.out.println("The number of byte in this read is : "+ len2);

        // 3. 使用循环改造。
        byte[] buffer = new byte[3];
        int len; // 记住每次读取了多少个字节。
        while ((len = is.read(buffer)) != -1){
            // 注意：读取多少，倒出多少
            String rs = new String(buffer, 0, len);
            System.out.print(rs);
        }
        // 性能得到了明显的提升！！之前每取一个字节就用一次系统硬件资源，现在少用三倍
        // 这种方案也不能避免读取汉字输出乱码的问题


        is.close(); //关闭流
    }
}

````

```
我aaaa��
```



### 文件字节输入流：一次读取完全部字节

方式一：自己定义一个字节数组与被读取的文件大小一样大，然后使用该字节数组，一次读完文件的全部字节。

方式二：Java官方为InputStream提供了如下方法，可以直接把文件的全部字节读取到一个字节数组中返回。

注意：如果文件过大，创建的字节数组也会过大，可能引起内存溢出。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2022.51.58.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p4_byte_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//目标：使用文件字节输入流一次读取完文件的全部字节。
public class FileInputStreamTest3 {
    public static void main(String[] args) throws Exception {
        // 1. 一次性读取完文件的全部字节到一个字节数组中去。
        // 创建一个字节输入流管道与源文件接通
        InputStream is = new FileInputStream("file-io-app/src/itheima.txt");

        // 2. 准备一个字节数组，大小与文件的大小正好一样大。
//        File f = new File("file-io-app/src/itheima.txt");
//        long size = f.length();
//        byte[] buffer = new byte[(int) size];
//
//        int len = is.read(buffer);
//        System.out.println(new String(buffer));
//
//        System.out.println(size);
//        System.out.println(len);

        byte[] buffer = is.readAllBytes();
        System.out.println(new String(buffer));
    }
}

```

```
我aaaa爱
```



### 文件字节输出流：写字节出去

FileOutStream(文件字节输出流)

- 作用：以内存为基准，把内存中的数据以字节的形式写出到文件中去。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2023.00.58.png?raw=true" style="zoom:33%;" /> 



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-01%2023.01.41.png?raw=true)

```java
package com.itheima.p4_byte_stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

// 目标：掌握文件字节输出流FileOutputStream的使用
public class FileOutputStreamTest4 {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个字节输出流管道与目标文件接通。
        // 覆盖管道，覆盖之前的数据
        //OutputStream os = new FileOutputStream("file-io-app/src/itheima04out.txt");

        // 追加数据管道
        OutputStream os = new FileOutputStream("file-io-app/src/itheima04out.txt",true);

        // 2. 开始写字节数据出去了
        os.write(97); //97就是一个字节，代表a
        os.write('b'); //'b'底层是98，也是一个字节
        os.write('雨'); // [ooo] 默认只能写出去一个字节，但汉字有三个字节。

        byte[] bytes = "我爱你中国abc".getBytes();
        os.write(bytes);

        os.write(bytes, 0,15);

        // 换行符
        os.write("\r\n".getBytes());

        os.close(); // 关闭流
    }
}

```

```
ab�我爱你中国abc我爱你中国abcab�我爱你中国abc我爱你中国ab�我爱你中国abc我爱你中国ab�我爱你中国abc我爱你中国/r/nab�我爱你中国abc我爱你中国
ab�我爱你中国abc我爱你中国

```



### 案例：文件复制

字节流非常适合做一切文件的复制操作。

任何文件的底层都是字节，字节流做复制，是一字不漏的转移完全部字节，只要复制后的文件格式一致就没问题！

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2016.02.35.png?raw=true)

````java
package com.itheima.p4_byte_stream;

import java.io.*;

//目标：使用字节流完成对文件的复制操作。
public class CopyTest5 {
    public static void main(String[] args) throws Exception {
        // 需求：复制照片
        // 1. 创建一个字节输入流管道与源文件接通
        InputStream is= new FileInputStream("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2024-01-02 16.02.35.png");
        // 2. 创建一个字节输出流管道与目标文件接通
        OutputStream os = new FileOutputStream("/Users/Hardy/Desktop/test1.png");
        // 3. 创建一个字节数组，负责转移字节数据。
        byte[] buffer = new byte[1024]; // 1KB
        // 4. 从字节输入流中读取字节数据，写出去到字节输出流中。读多少写出去多少
        int len; //记住每次读取了多少个字节。
        while ((len = is.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }
        os.close();
        is.close();
        System.out.println("Copied successfully");
    }
}

````

```
Copied successfully
```



## 释放资源的方式

### try-catch-finally

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2016.58.04.png?raw=true" style="zoom:33%;" />

- finally代码区的特点：无论try中的程序是正常执行了，还是出现了异常，最后都一定会执行finally区，除非JVM终止。
- 作用：一般用于在程序执行完成后进行资源的释放操作。（专业）

```java
package com.itheima.p5_resource;
//目标：认识try-catch-finally
public class Test1 {
    public static void main(String[] args) {
        try {
            System.out.println(10/2);
            // return; //跳出方法的执行
            // System.exit(0); //虚拟机挂掉
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("====Program was executed once===");
        }
        System.out.println(chu(10,2));
    }

    public static int chu(int a, int b){
        try {
            return a/b;
        }catch (Exception e){
            e.printStackTrace();
            return -1; //代表的是出现异常，让代码不要因为没有返回值报错
        }finally {
            //千万不要在finally中返回数据！因为finally执行在return之前
            //return 111;
        }
    }
}

```

```
5
====Program was executed once===
5
```



复制案例：

```java
package com.itheima.p5_resource;

import java.io.*;

// 目标：掌握资源释放的方式
public class Test2 {
    public static void main(String[] args) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1. 创建一个字节输入流管道与源文件接通
            is= new FileInputStream("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2024-01-02 16.02.35.png");
            // 2. 创建一个字节输出流管道与目标文件接通
            os = new FileOutputStream("/Users/Hardy/Desktop/test1.png");
            System.out.println(10/0);
            // 3. 创建一个字节数组，负责转移字节数据。
            byte[] buffer = new byte[1024]; // 1KB
            // 4. 从字节输入流中读取字节数据，写出去到字节输出流中。读多少写出去多少
            int len; //记住每次读取了多少个字节。
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }

            System.out.println("Copied successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源的操作
            try {
                if(os != null){os.close();} //因为如果os/is流还没有内容，就会为null。在关闭流时候会出现空指针异常
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            if(is != null){is.close();}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

```



### try-with-resource

Try-catch-finally：代码臃肿、不优雅

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2016.59.42.png?raw=true" style="zoom:33%;" /> 



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2017.17.18.png?raw=true)

```java
package com.itheima.p5_resource;

import java.io.*;

// 目标：掌握资源释放的方式
public class Test3 {
    public static void main(String[] args) {

        try(
                // 1. 创建一个字节输入流管道与源文件接通
                InputStream is= new FileInputStream("/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2024-01-02 16.02.35.png");
        // 2. 创建一个字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream("/Users/Hardy/Desktop/test1.png");

                // 注意：这里只能放置资源对象。（流对象）
                // int age = 21; 普通变量不能放这里
                // 什么是资源呢？资源都是会实现AutoCloseable接口。资源都会有一个close方法，并且资源放到这里后
                // 用完之后，会被自动调用其close方法完成资源的释放操作（系统层面：资源会占用底层系统资源）
                ) {

            System.out.println(10/0);
            // 3. 创建一个字节数组，负责转移字节数据。
            byte[] buffer = new byte[1024]; // 1KB
            // 4. 从字节输入流中读取字节数据，写出去到字节输出流中。读多少写出去多少
            int len; //记住每次读取了多少个字节。
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }

            System.out.println("Copied successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

```



# D17. IO流（二）

## IO流-字符流

### 文件字符输入流-读字符数据进来

字节流：适合复制文件等，不适合读写文本文件（一个字节一个字节读会编码错误）

字符流：适合读写文本文件内容

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2017.24.53.png?raw=true" style="zoom:33%;" /> 



FileReader(文件字符输入流)

- 作用：以内存为基准，可以把文件中的数据以字符的形式读入到内存中去。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2017.27.05.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p1_char_stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

//目标：掌握文件字符输入流的每次读取一个字符
public class FileReaderTest1 {
    public static void main(String[] args) {
        try (
                // 1. 创建一个文件字符输入流管道与与源文件接通
                Reader fr = new FileReader("io-app2/src/itheima01.txt");
                ){
            // 2. 读取文本文件的内容了。
//            int c; // 记住每次读取的字符编号。
//            while ((c = fr.read()) != -1){
//                System.out.print((char)c);
//            }
            // 每次读取一个字符的形式，性能肯定是比较差的。

            // 3. 每次读取多个字符
            char[] buffer = new char[3];
            int len; //记住每次读取了多少个字符。
            while ((len = fr.read(buffer)) != -1){
                // 读取多少倒出多少
                System.out.print(new String(buffer, 0, len));
            }
            // 性能是比较不错的！系统调用次数减少了，cuz一次读取了3个
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

```

```
dsafasddssfs
大噶地方撒发的
```



### 文件字符输出流-写字符数据出去

FileWriter(文件字符输出流)

- 作用：以内存为基准，把内存中的数据以字符的形式写出到文件中去。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2018.02.50.png?raw=true" style="zoom:33%;" /> 

```java
package com.itheima.p1_char_stream;

import java.io.FileWriter;
import java.io.Writer;

//目标：掌握文件字符输出流：写字符数据出去
public class FileWriterTest2 {
    public static void main(String[] args) {
        try (
                // 0、创建一个文件字符输出流管道与目标文件接通。
                // 覆盖管道
                // Writer fw = new FileWriter("io-app2/src/itheima02out.txt");
                // 追加数据的管道
                Writer fw = new FileWriter("io-app2/src/itheima02out.txt", true);
        ){
            // 1、public void write(int c):写一个字符出去
            fw.write('a');
            fw.write(97);
            //fw.write('磊'); // 写一个字符出去
            fw.write("\r\n"); // 换行

            // 2、public void write(String c)写一个字符串出去
            fw.write("我爱你中国abc");
            fw.write("\r\n");

            // 3、public void write(String c ,int pos ,int len):写字符串的一部分出去
            fw.write("我爱你中国abc", 0, 5);
            fw.write("\r\n");

            // 4、public void write(char[] buffer):写一个字符数组出去
            char[] buffer = {'黑', '马', 'a', 'b', 'c'};
            fw.write(buffer);
            fw.write("\r\n");

            // 5、public void write(char[] buffer ,int pos ,int len):写字符数组的一部分出去
            fw.write(buffer, 0, 2);
            fw.write("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

````
aa
我爱你中国abc
我爱你中国
黑马abc
黑马
aa
我爱你中国abc
我爱你中国
黑马abc
黑马

````



- 注意事项：<font color= "blue">字符输出流写出数据后，必须刷新流，或者关闭流，</font>写出去的数据才能生效。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2018.25.34.png?raw=true" style="zoom:33%;" /> 





```java
package com.itheima.p1_char_stream;
import java.io.FileWriter;
import java.io.Writer;

/**
 * 目标：搞清楚字符输出流使用时的注意事项。
 */
public class FileWriterTest3 {
    public static void main(String[] args) throws Exception {
        Writer fw = new FileWriter("io-app2/src/itheima03out.txt");

        // 写字符数据出去
        fw.write('a');
        fw.write('b');
        fw.write('c');
        fw.write('d');
        fw.write("\r\n");

        fw.write("我爱你中国");
        fw.write("\r\n");
        fw.write("我爱你中国");

//        fw.flush(); // 刷新流。
//        fw.write("张三");
//        fw.flush();

        fw.close(); // 关闭流，关闭流包含刷新操作！但关闭后就不能用这个流了。
        // fw.write("张三");
    }
}

```

```
abcd
我爱你中国
我爱你中国
```



## IO流-缓冲流

对原始流进行包装，以提高原始流读写数据的性能。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2018.30.09.png?raw=true)



### 字节缓冲流

字节缓冲流作用

- 提高字节流读写数据的性能
- 原理：字节缓冲输入流自带了8KB缓冲池；字节缓冲输出流也自带了8KB缓冲池。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2018.36.29.png?raw=true" style="zoom:33%;" /> 

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2018.38.14.png?raw=true" style="zoom:33%;" /> 



```java
package com.itheima.p2_buffered_stream;
import java.io.*;
/**
 * 目标：掌握字节缓冲流的作用。
 */
public class BufferedInputStreamTest1 {
    public static void main(String[] args) {
        try (
                InputStream is = new FileInputStream("io-app2/src/itheima01.txt");
                // 1、定义一个字节缓冲输入流包装原始的字节输入流
                InputStream bis = new BufferedInputStream(is);

                OutputStream os = new FileOutputStream("io-app2/src/itheima01_bak.txt");
                // 2、定义一个字节缓冲输出流包装原始的字节输出流
                OutputStream bos = new BufferedOutputStream(os);
        ){

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
            System.out.println("复制完成！！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
复制完成！！
```



### 字符缓冲流

BufferedReader(字符缓冲输入流)

- 作用：自带8K(8192)的字符缓冲池，可以提高字符输入流读取字符数据的性能。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2018.54.36.png?raw=true" style="zoom:33%;" /> 



字符缓冲输入流介绍代码：

````java
package com.itheima.p2_buffered_stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * 目标：掌握字符缓冲输入流的用法。
 */
public class BufferedReaderTest2 {
    public static void main(String[] args)  {
        try (
                Reader fr = new FileReader("io-app2/src/itheima01.txt");
                // 创建一个字符缓冲输入流包装原始的字符输入流
                BufferedReader br = new BufferedReader(fr);
        ){
//            char[] buffer = new char[3];
//            int len;
//            while ((len = br.read(buffer)) != -1){
//                System.out.print(new String(buffer, 0, len));
//            }
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());

            String line; // 记住每次读取的一行数据
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

````

```
dsafasddssfs
大噶地方撒发的
```



BufferedWriter(字符缓冲输出流)

- 作用：自带8K的字符缓冲池，可以提高字符输出流写字符数据的性能

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2021.45.39.png?raw=true" style="zoom:33%;" /> 

 

BufferedWriter代码：

````java
package com.itheima.p2_buffered_stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

/**
 * 目标：掌握字符缓冲输出流的用法。
 */
public class BufferedWriterTest3 {
    public static void main(String[] args) {
        try (
                Writer fw = new FileWriter("io-app2/src/itheima02out.txt", true);
                // 创建一个字符缓冲输出流管道包装原始的字符输出流
                BufferedWriter bw = new BufferedWriter(fw);
        ){

            bw.write('a');
            bw.write(97);
            bw.write('磊');
            bw.newLine();

            bw.write("我爱你中国abc");
            bw.newLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

````



### 原始流、缓冲流的性能分析

测试案例：

- 分别使用原始的字节流，以及字节缓冲流复制一个很大的视频。

测试步骤：

1. 使用低级的字节流按照一个一个字节的形式复制文件。
2. 使用低级的字节流按照字节数组的形式复制文件。
3. 使用高级的缓冲字节流按照一个一个字节的形式复制文件。
4. 使用高级的缓冲字节流按照字节数组的形式复制文件。

```java
package com.itheima.p2_buffered_stream;

import java.io.*;

/**
 目标：观察原始流和缓冲流的性能。
 */
public class TimeTest4 {
    // 复制的视频路径
    private static final String SRC_FILE = "D:\\resource\\线程池.avi";
    // 复制到哪个目的地
    private static final String DEST_FILE = "D:\\";

    public static void main(String[] args) {
        // copy01(); // 低级字节流一个一个字节的赋值，慢的简直让人无法忍受，直接淘汰！
        copy02();// 低级的字节流流按照一个一个字节数组的形式复制，速度较慢！
        // copy03(); // 缓冲流按照一个一个字节的形式复制，速度较慢,直接淘汰！
        copy04(); // 缓冲流按照一个一个字节数组的形式复制，速度极快，推荐使用！
    }

    private static void copy01() {
        long startTime = System.currentTimeMillis();
        try (
                InputStream is = new FileInputStream(SRC_FILE);
                OutputStream os = new FileOutputStream(DEST_FILE + "1.avi");
        ){

            int b;
            while ((b = is.read()) != -1){
                os.write(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("低级字节流一个一个字节复制耗时：" + (endTime - startTime) / 1000.0 + "s");
    }

    private static void copy02() {
        long startTime = System.currentTimeMillis();
        try (
                InputStream is = new FileInputStream(SRC_FILE);
                OutputStream os = new FileOutputStream(DEST_FILE + "2.avi");
        ){
            byte[] buffer = new byte[1024*64];
            int len;
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("低级字节流使用字节数组复制耗时：" + (endTime - startTime) / 1000.0 + "s");
    }

    private static void copy03() {
        long startTime = System.currentTimeMillis();
        try (
                InputStream is = new FileInputStream(SRC_FILE);
                BufferedInputStream bis = new BufferedInputStream(is);
                OutputStream os = new FileOutputStream(DEST_FILE + "3.avi");
                BufferedOutputStream bos = new BufferedOutputStream(os);
        ){

            int b;
            while ((b = bis.read()) != -1){
                bos.write(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("缓冲流一个一个字节复制耗时：" + (endTime - startTime) / 1000.0 + "s");
    }

    private static void copy04() {
        long startTime = System.currentTimeMillis();
        try (
                InputStream is = new FileInputStream(SRC_FILE);
                BufferedInputStream bis = new BufferedInputStream(is, 64 * 1024);
                OutputStream os = new FileOutputStream(DEST_FILE + "4.avi");
                BufferedOutputStream bos = new BufferedOutputStream(os, 64 * 1024);
        ){
            byte[] buffer = new byte[1024 * 64]; // 32KB
            int len;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("缓冲流使用字节数组复制耗时：" + (endTime - startTime) / 1000.0 + "s");
    }
}

```



## IO流-转换流

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2022.56.33.png?raw=true)

### 引出问题：不同编码读取时会乱码

如果代码编码和被读取的文本文件的编码是一致的，使用字符流读取文本文件时不会出现乱码！

如果代码编码和被读取的文本文件的编码是不一致的，使用字符流读取文本文件时会出现乱码！



```java
package com.itheima.p3_transform_stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * 目标：掌握不同编码读取乱码的问题。
 */
public class Test1 {
    public static void main(String[] args) {
        try (
                // 1、创建一个文件字符输入流与源文件接通
                // 代码编码：UTF-8  文件的编码：UTF-8
                // Reader fr = new FileReader("io-app2/src/itheima04.txt");

                //         1 床    前     明  月光c
                //  GBK    o [oo] [oo]  [oo] ...
                //  UTF-8  1 ?????
                // 代码编码：UTF-8 文件的编码：GBK
                Reader fr = new FileReader("io-app2/src/itheima06.txt");
                // 2、把文件字符输入流包装成缓冲字符输入流
                BufferedReader br = new BufferedReader(fr);
        ){

            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
乱码
```



### 字符输入转换流

InputStreamReader(字符输入转换流)

- 解决不同编码时，字符流读取文本内容乱码的问题。
- 解决思路：先获取文件的原始字节流，再将文件按真实的字符集编码转成字符输入流，这样字符输入流中的字符就不乱码了。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2022.31.21.png?raw=true)

```java
package com.itheima.p3_transform_stream;


import java.io.*;

/**
 * 目标：掌握字符输入转换流的作用。
 */
public class InputStreamReaderTest2 {
    public static void main(String[] args) {
        try (
                // 1、得到文件的原始字节流（GBK的字节流形式）
                InputStream is = new FileInputStream("io-app2/src/itheima06.txt");
                // 2、把原始的字节输入流按照指定的字符集编码转换成字符输入流
                Reader isr = new InputStreamReader(is, "GBK");
                // 3、把字符输入流包装成缓冲字符输入流
                BufferedReader br = new BufferedReader(isr);
        ){
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
dsafasddssfs
大噶地方撒发的
```



### 字符输出转换流

需要控制写出去的字符使用什么字符集编码，该咋整？

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2022.41.28.png?raw=true" style="zoom:33%;" /> 



OutputStreamWriter字符输出转换流

- 作用：可以控制写出去的字符使用什么字符集编码。
- 解决思路：获取字节输出流，再按照指定的字符集编码将其转换成字符输出流，以后写出去的字符就会用该字符集编码了。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2022.44.47.png?raw=true)

```java
package com.itheima.p3_transform_stream;


import java.io.*;

/**
 * 目标：掌握字符输入转换流的作用。
 */
public class InputStreamReaderTest2 {
    public static void main(String[] args) {
        try (
                // 1、得到文件的原始字节流（GBK的字节流形式）
                InputStream is = new FileInputStream("io-app2/src/itheima01.txt");
                // 2、把原始的字节输入流按照指定的字符集编码转换成字符输入流
                Reader isr = new InputStreamReader(is, "GBK");
                // 3、把字符输入流包装成缓冲字符输入流
                BufferedReader br = new BufferedReader(isr);
        ){
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```



## IO流-打印流

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-02%2023.14.31.png?raw=true)

PrintStream/ PrintWriter(打印流)

- 作用：打印流可以实现更方便、更高效的打印数据出去，能实现打印啥出去就是啥出去。



PrintStream和PrintWriter的区别

- 打印数据的功能上是一摸一样的：都是使用方便，性能高效（核心优势）
- PrintStream继承自字节输出流OutputStream，因此支持写字节数据的方法。
- PrintWriter继承自字符输出流Writer，因此支持写字符数据出去。



PrintStream提供的打印数据的方案

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2014.40.58.png?raw=true"/> 



PrintWriter提供的打印数据的方案

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2015.42.30.png?raw=true)

```java
package com.itheima.p4_printstream;

import java.io.*;
import java.nio.charset.Charset;

//目标：掌握打印流: PrintStream/PrintWriter的用法
public class printTest1 {
    public static void main(String[] args) {
        try (
                // 1. 创建一个打印流管道
                // PrintStream ps = new PrintStream("io-app2/src/itheima02out.txt", Charset.forName("UTF-8"));

                // PrintWriter是高级流，不能追加数据。 如果想追加数据，里面额外写个低级流
                PrintWriter ps = new PrintWriter(new FileOutputStream("io-app2/src/itheima02out.txt",true));
        ){
            ps.println(97);
            ps.println('a');
            ps.println("我爱你中国");
            ps.println(true);

            ps.write(97); // 'a'

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

```



打印流的一种应用：输出语句的重定向。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2015.55.07.png?raw=true" style="zoom:33%;" /> 



- 可以把输出语句的打印位置改到某个文件中去。（不显示在控制台了，因为项目没有控制台）

```java
package com.itheima.p4_printstream;

import java.io.FileNotFoundException;
import java.io.PrintStream;

//目的：了解输出语句的重定向。
public class PrintTest2 {
    public static void main(String[] args) {
        System.out.println("老骥伏枥");

        try (
                PrintStream ps = new PrintStream("io-app2/src/itheima09.txt");
        ){
            //把系统默认的打印流对象改成自己设置的打印流
            System.setOut(ps);
            System.out.println("志在千里");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

```



## IO流-数据流

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2016.05.37.png?raw=true)



### DataOutputStream(数据输出流)

- 允许把数据和其类型一并写出去。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2016.07.14.png?raw=true)



```java
package com.itheima.p5_data_stream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * 目标：数据输出流。
 */
public class DataOutputStreamTest1 {
    public static void main(String[] args) {
        try (
                // 1、创建一个数据输出流包装低级的字节输出流
                DataOutputStream dos =
                        new DataOutputStream(new FileOutputStream("io-app2/src/itheima10out.txt"));
        ){
            dos.writeInt(97);
            dos.writeDouble(99.5);
            dos.writeBoolean(true);
            dos.writeUTF("黑马程序员666！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
   a@X�      黑马程序员666！
```



### DataInputStream(数据输入流)

- 用于读取数据输出流写出去的数据。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2016.14.25.png?raw=true)



```java
package com.itheima.p5_data_stream;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 目标：使用数据输入流读取特定类型的数据。
 */
public class DataInputStreamTest2 {
    public static void main(String[] args) {
        try (
                DataInputStream dis =
                        new DataInputStream(new FileInputStream("io-app2/src/itheima10out.txt"));
        ){
            //读取顺序要跟写入顺序一摸一样。e.g.先写的int，就要先读int
            int i = dis.readInt();
            System.out.println(i);

            double d = dis.readDouble();
            System.out.println(d);

            boolean b = dis.readBoolean();
            System.out.println(b);

            String rs = dis.readUTF();
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
97
99.5
true
黑马程序员666！
```



## IO流-序列化流

对象序列化：把java对象写入到文件中去。

对象反序列化：把文件里的java对象读出来。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2016.28.52.png?raw=true)



### ObjectOutputStream(对象字节输出流)

- 可以把java对象进行序列化：把java对象存入到文件中去。
- 注意：对象如果要参与序列化，必须实现序列化接口（java.io.Serializable）

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2016.31.14.png?raw=true)

 ```java
 package com.itheima.p6_object_stream;
 
 import com.itheima.p6_object_stream.User;
 
 import java.io.FileOutputStream;
 import java.io.ObjectOutputStream;
 import java.util.ArrayList;
 
 /**
  * 目标：掌握对象字节输出流的使用：序列化对象。
  */
 public class Test1ObjectOutputStream {
     public static void main(String[] args) {
         try (
                 // 2、创建一个对象字节输出流包装原始的字节 输出流。
                 ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream("io-app2/src/itheima11out.txt"));
         ){
             // 1、创建一个Java对象。
             User u = new User("admin", "张三", 32, "666888xyz");
 
             // 3、序列化对象到文件中去
             oos.writeObject(u);
             System.out.println("序列化对象成功！！");
 
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 }
 
 ```

```java
package com.itheima.p6_object_stream;

import java.io.Serializable;

// 注意：对象如果需要序列化，必须实现序列化接口。
public class User implements Serializable {
    private String loginName;
    private String userName;
    private int age;
    // transient 这个成员变量将不参与序列化。
    private transient String passWord;

    public User() {
    }

    public User(String loginName, String userName, int age, String passWord) {
        this.loginName = loginName;
        this.userName = userName;
        this.age = age;
        this.passWord = passWord;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}

```



```
序列化对象成功！！
�� sr !com.itheima.p6_object_stream.User�u���%� I ageL 	loginNamet Ljava/lang/String;L userNameq ~ xp    t admint 张三
```



### ObjectInputStream（对象字节输入流）

- 可以把java对象进行反序列化：把存储在文件中的java对象读入到内存中来。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2016.43.49.png?raw=true)

```java
package com.itheima.p6_object_stream;

import com.itheima.p6_object_stream.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 目标：掌握对象字节输入流的使用：反序列化对象。
 */
public class Test2ObjectInputStream {
    public static void main(String[] args) {
        try (
                // 1、创建一个对象字节输入流管道，包装 低级的字节输入流与源文件接通
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("io-app2/src/itheima11out.txt"));
        ){
            User u = (User) ois.readObject();
            System.out.println(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
User{loginName='admin', userName='张三', age=32, passWord='null'}
```



如果要一次序列化多个对象，咋整？

用一个ArrayList集合存储多个对象，然后直接对集合进行序列化即可。

注意：ArrayList集合已经实现了序列化接口！



## IO框架

什么是框架？

- 解决某类问题，编写的一套类、接口等，可以理解成一个半成品，大多框架都是第三方研发的。
- 好处：在框架的基础上开发，可以得到优秀的软件架构，并能提高开发效率。
- 框架的形式：一般是把类、接口等编译成class形式，再压缩成一个.jar结尾的文件发行出去。



什么是IO框架？

- 封装了java提供的对文件、数据进行操作的代码，对外提供了更简单的方式对文件进行操作，对数据进行读写等。



Commons-io

- Commons-io是apache开源基金组织提供的一组有关IO操作的小框架，目的是提高IO流的开发效率。



步骤：导入commons-io-2.11.0.jar 框架到项目中去。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2017.30.14.png?raw=true" style="zoom:33%;" /> 



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2016.59.32.png?raw=true)

```java
package com.itheima.p7_commons_io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//目标：使用CommonsIO框架进行IO相关的操作
public class CommonsIOTest1 {
    public static void main(String[] args) throws IOException {
        FileUtils.copyFile(new File("io-app2/src/itheima01.txt"), new File("io-app2/src/a.txt"));
        FileUtils.copyDirectory(new File("/Users/Hardy/Desktop/JAVA/抖音网友建议"), new File("/Users/Hardy/Desktop/JAVA/抖音网友建议2"));
        FileUtils.deleteDirectory(new File("/Users/Hardy/Desktop/JAVA/抖音网友建议2"));

        // Java提供的原生的一行代码搞定很多事情。(官方弥补)
        // Files.copy(Path.of("io-app2/src/itheima01.txt"), Path.of("io-app2/src/b.txt"));
        System.out.println(Files.readString(Path.of("io-app2/src/itheima01.txt")));
    }
}

```

```
dsafasddssfs
大噶地方撒发的
```



# D18. 特殊文本文件、日志技术

特殊文件

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2020.56.59.png?raw=true" style="zoom:33%;" /> 





为什么要用这些特殊文件？

存储多个用户的：用户名、密码

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2021.00.06.png?raw=true)





![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2021.01.14.png?raw=true)



日志技术

- 把程序运行的信息记录到文件中，方便程序员定位bug，并了解程序的执行情况等。



## 特殊文件：Properties属性文件

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2021.06.05.png?raw=true)



Properties

- 是一个Map集合（键值对集合），但是我们一般不会当集合使用。
- 核心作用：Properties是用来代表属性文件的，通过Properties可以读写属性文件里的内容。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2021.14.06.png?raw=true" style="zoom:33%;" /> 



使用Properties读取属性文件里的键值对数据

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2021.23.20.png?raw=true)

```java
package com.itheima.p1_properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.Set;

//目标：掌握使用Properties类读取属性文件中的键值对信息
public class PropertiesTest1 {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个Properties的对象出来（键值对集合，空容器）
        Properties properties = new Properties();
        System.out.println(properties);

        // 2. 开始加载属性文件中的键值对数据到properties对象中去
        properties.load(new FileReader("properties-xml-log-app/src/users.properties"));
        System.out.println(properties);

        // 3. 根据键取值
        System.out.println(properties.getProperty("赵敏"));
        System.out.println(properties.getProperty("张无忌"));

        // 4. 遍历全部的键和值
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            String value = properties.getProperty(key);
            System.out.println(key+"->"+value);
        }

        properties.forEach((k,v)->{
            System.out.println(k+"->"+v);
        });
    }
}

```

```
{}
{admin=123456, 周芷若=wuji, 赵敏=wuji, 张无忌=minmin}
wuji
minmin
admin->123456
周芷若->wuji
赵敏->wuji
张无忌->minmin
admin->123456
周芷若->wuji
赵敏->wuji
张无忌->minmin
```



使用Properties把键值对数据写出到属性文件里去

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.10.02.png?raw=true)

```java
package com.itheima.p1_properties;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

//目标：掌握把键值对数据存入到属性文件中去
public class PropertiesTest2 {
    public static void main(String[] args) throws Exception {
        // 1. 创建Properties对象出来，先用它存储一些键值对数据
        Properties properties = new Properties();
        properties.setProperty("张无忌", "minmin");
        properties.setProperty("殷素素", "cuishan");
        properties.setProperty("张翠山", "susu");

        // 2. 把properties对象中的键值对数据存入到属性文件中去
        properties.store(new FileWriter("properties-xml-log-app/src/users2.properties"),
                "i saved many users.");
    }
}

```

```
#i saved many users.
#Wed Jan 03 22:16:35 CST 2024
殷素素=cuishan
张翠山=susu
张无忌=minmin

```



案例练习：

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.26.16.png?raw=true)



## 特殊文件：XML文件

### 概述

XML（全称Extensible Markup Language，可扩展标记语言）

- 本质是一种数据的格式，可以用来存储复杂的数据结构，和数据关系。



XML特点

- XML中的“<标签名>”称为一个标签或一个元素，一般是成对出现的。
- XML中的标签名可以自己定义（可拓展），但必须要正确的嵌套。
- XML中只能有一个根标签。
- XML中的标签可以有属性。
- 如果一个文件中放置的是XML格式的数据，这个文件就是XML文件，后缀一般要写成.xml。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.33.50.png?raw=true" style="zoom:33%;" /> 



XML的创建

- 就是创建一个XML类型的文件，要求文件的后缀必须使用xml，如hello_world.xml

IEDA创建XML文件

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.35.40.png?raw=true)

XML的语法规则

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.42.32.png?raw=true" style="zoom:33%;" />  



XML的作用和应用场景

- 本质是一种数据格式，可以存储复杂的数据结构，和数据关系。
- 应用场景：经常用来作为系统的配置文件；或者作为一种特殊的数据结构，在网络中传输。（配置文件：用户名密码等信息）

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.50.41.png?raw=true)



### 读取XML文件中的数据

解析XML文件

- 使用程序读取XML文件中的数据。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.55.18.png?raw=true)





步骤：使用Dom4J解析出XML文件

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2022.56.44.png?raw=true" style="zoom:50%;" /> 



DOM4J解析XML文件的思想：文档对象模型

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2023.07.50.png?raw=true)



Dom4j解析XML - 得到Document对象

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-03%2023.08.51.png?raw=true" style="zoom:33%;" /> 



Element提供的方法



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2010.21.50.png?raw=true)



```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!-- 注释：以上抬头声明必须放在第一行，必须有 -->
<!-- 根标签只能有一个 -->
<users>
    <user id="1" desc="the first user">
        <name>张无忌</name>
        <sex>男</sex>
        <地址>光明顶</地址>
        <password>minmin</password>
        <!-- 特殊符号要用特殊字符或者数据区CD -->
        <data> 3 &lt; 2 &amp;&amp; 5 > 4 </data>
        <data1>
            <![CDATA[
                3 < 2 && 5 > 4
            ]]>
        </data1>
    </user>
    <people>many people</people>
    <user id="2">
        <name>敏敏</name>
        <sex>女</sex>
        <地址>光明顶</地址>
        <password>wuji</password>
    </user>
</users>
```



```java
package com.itheima.p2_xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

//目标：掌握使用Dom4j框架解析XML文件。
public class Dom4JTest1 {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个Dom4J框架提供的解析器对象
        SAXReader saxReader = new SAXReader();

        // 2. 使用saxReader对象把需要解析的XML文 件读成一个Document对象。
        Document document = saxReader.read("properties-xml-log-app/src/helloworld.xml");

        // 3. 从文档对象中解析XML文件的全部数据了。
        Element root = document.getRootElement();
        System.out.println(root.getName());

        // 4. 获取根元素下的全部一级子元素
        //List<Element> elements = root.elements();
        List<Element> elements = root.elements("user");
        for (Element element : elements) {
            System.out.println(element.getName());
        }

        // 5. 获取当前元素下的某个子元素
        Element people = root.element("people");
        System.out.println(people.getText());

        // 如果下面有很多子元素user，默认获取第一个
        Element user = root.element("user");
        System.out.println(user.elementText("name"));

        // 6. 获取元素的属性信息呢？
        System.out.println(user.attributeValue("id"));
        Attribute id = user.attribute("id");
        System.out.println(id.getName());
        System.out.println(id.getValue());

        List<Attribute> attributes = user.attributes();
        for (Attribute attribute : attributes) {
            System.out.println(attribute.getName()+"="+attribute.getValue());
        }

        // 7. 如何获取全部的文本内容。获取当前元素下的子元素文本值
        System.out.println(user.elementText("name"));
        System.out.println(user.elementText("地址"));
        System.out.println(user.elementText("password"));

        Element data = user.element("data");
        System.out.println(data.getText());
        System.out.println(data.getTextTrim()); // 去除文本去除前后空格


    }
}

```

```
users
user
user
many people
张无忌
1
id
1
id=1
desc=the first user
张无忌
光明顶
minmin
 3 < 2 && 5 > 4 
3 < 2 && 5 > 4
```



如何使用程序把数据写出到XML文件中去？

- 不建议用dom4j做

- 推荐直接把程序里的数据拼接XML格式，然后用IO流写出去。

```java
package com.itheima.p2_xml;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * 目标：如何使用程序把数据写出到 XML文件中去。
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <book>
 *     <name>从入门到跑路</name>
 *     <author>dlei</author>
 *     <price>999.9</price>
 * </book>
 */
public class Dom4JTest2 {
    public static void main(String[] args) {
        // 1、使用一个StringBuilder对象来拼接XML格式的数据。
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
        sb.append("<book>\r\n");
        sb.append("\t<name>").append("从入门到跑路").append("</name>\r\n");
        sb.append("\t<author>").append("dlei").append("</author>\r\n");
        sb.append("\t<price>").append(999.99).append("</price>\r\n");
        sb.append("</book>");

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter("properties-xml-log-app/src/book.xml"));
        ){
            bw.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

```

````xml
<?xml version="1.0" encoding="UTF-8" ?>
<book>
	<name>从入门到跑路</name>
	<author>dlei</author>
	<price>999.99</price>
</book>
````





### 约束XML文件的编写(了解)

什么是约束XML文件的书写？

- 就是限制XML文件只能按照某种格式进行书写。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2015.47.40.png?raw=true" style="zoom:33%;" /> 



约束文档

- 专门用来限制XML书写格式的文档，比如：限制标签、属性应该怎么写。

约束文档的分类

- DTD文档
- Schema文档

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2015.52.52.png?raw=true)



步骤：XML文档约束- DTD的使用（了解）

需求：利用DTD约束文档，约束一个XML文件的编写。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2015.59.10.png?raw=true" style="zoom:33%;" /> 

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2016.01.08.png?raw=true)



步骤：XML文档约束-schema的使用（了解）

- 可以约束XML文件的编写和数据类型

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2016.03.00.png?raw=true" style="zoom:33%;" /> 

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2016.07.47.png?raw=true)



## 日志技术

### 概述

什么是日志？

希望系统能记住某些数据是被谁操作的，比如被谁删除了？

想分析用户浏览系统的具体情况，以便挖掘用户的具体喜好？

当系统在开发中或者上线后出现了bug，崩溃了，该通过什么去分析、定位bug？



日志

- 好比生活中的日记，可以记录你生活中的点点滴滴。
- 程序中的日志，通常就是一个文件，里面记录的是程序运行过程中的各种信息。



目前记录日志的方案

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2016.14.53.png?raw=true" style="zoom:33%;" /> 





输出语句的弊端

- 日志会展示在控制台（关了就没了、项目上线了就没控制台了）。
- 不能更方便的将日志记录到其他的位置（文件，数据库）。
- 想取消日志，需要修改源代码带可以完成。



日志技术

- 可以将系统执行的信息，方便的记录到指定的位置（控制台、文件中、数据库中）。
- 可以随时以开关的形式控制日志的启停，无需侵入到源代码中去进行修改。



### 日志技术的体系、logback日志框架的概述

日志技术的体系结构

- 日志框架：牛人或者第三方公司已经做好的实现代码，后来者直接可以拿去使用。
- 日志接口：设计日志框架的一套标准，日志框架需要实现这些接口。
- 注意1：因为对Commons Logging接口不满意，有人就搞了SLF4j；因为对Log4j的性能不满意，有人就搞了Logback。
- 注意2：Logback是基于slf4j的日志规范实现的框架。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2016.25.17.png?raw=true)



Logback日志框架官网：https://logback.qos.ch/index.html

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2016.30.03.png?raw=true)



### Logback快速入门

注意：对Logback日志框架的控制，都是通过其核心配置文件logback.xml来实现的。

步骤：

需求

- 使用Logback日志框架，记录系统的运行信息。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2017.42.26.png?raw=true)

```java
package com.itheima.p3_log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 目标：掌握LogBack日志框架的使用。
 */
public class LogBackTest {
    // 创建一个Logger日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger("LogBackTest");

    public static void main(String[] args) {
        //while (true) {
        try {
            LOGGER.info("chu法方法开始执行~~~");
            chu(10, 0);
            LOGGER.info("chu法方法执行成功~~~");
        } catch (Exception e) {
            LOGGER.error("chu法方法执行失败了，出现了bug~~~");
        }
        //}
    }

    public static void chu(int a, int b){
        LOGGER.debug("参数a:" + a);
        LOGGER.debug("参数b:" + b);
        int c = a / b;
        LOGGER.info("结果是：" + c);
    }
}

```



```xml
`<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--
        CONSOLE ：表示当前的日志信息是可以输出到控制台的。
    -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--输出流对象 默认 System.out 改为 System.err-->
        <target>System.out</target>
        <encoder>
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度
                %msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%-5level]  %c [%thread] : %msg%n</pattern>
        </encoder>
    </appender>

    <!-- File是输出的方向通向文件的 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
        <!--日志输出路径-->
        <file>D:/log/itheima-data.log</file>
        <!--指定日志文件拆分和压缩规则-->
        <rollingPolicy
                class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--通过指定压缩文件名称，来确定分割文件方式-->
            <fileNamePattern>D:/log/itheima-data-%i-%d{yyyy-MM-dd}-.log.gz</fileNamePattern>
            <!--文件拆分大小-->
            <maxFileSize>1MB</maxFileSize>
        </rollingPolicy>
    </appender>

    <!--
        1、控制日志的输出情况：如，开启日志，取消日志
    -->
    <root level="debug">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE" />
    </root>
</configuration>
```



```log

2024-01-04 18:00:22.299 [main] INFO  LogBackTest - chu法方法开始执行~~~
2024-01-04 18:00:22.305 [main] DEBUG LogBackTest - 参数a:10
2024-01-04 18:00:22.305 [main] DEBUG LogBackTest - 参数b:0
2024-01-04 18:00:22.306 [main] ERROR LogBackTest - chu法方法执行失败了，出现了bug~~~
2024-01-04 18:02:31.901 [main] INFO  LogBackTest - chu法方法开始执行~~~
2024-01-04 18:02:31.908 [main] DEBUG LogBackTest - 参数a:10
2024-01-04 18:02:31.909 [main] DEBUG LogBackTest - 参数b:0
2024-01-04 18:02:31.909 [main] ERROR LogBackTest - chu法方法执行失败了，出现了bug~~~

```



核心配置文件logback.xml

- 对Logback日志框架进行控制的。



日志的输出位置、输出格式的设置

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2018.08.27.png?raw=true)



### Logback设置日志级别

什么是日志级别？

- 日志级别指的是日志信息的类型，日志都会分级别，常见的日志级别如下（优先级依次升高）：

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2018.25.37.png?raw=true)



# D19. 多线程

什么是线程？

- 线程(thread)是一个程序内部的一条执行流程。
- 程序中如果只有一条执行流程，那这个程序就是单线程的程序。



多线程是什么？

- 多线程是指从软硬件上实现的多条执行流程的技术（多条线程由CPU负责调度执行）。



多线程用在哪里，有什么好处？

再例如：消息通信（同时收发消息）、淘宝（同时多个用户购买）、京东系统

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2022.07.10.png?raw=true)



## 一、多线程的创建

### 方式一：继承Thread类



如何在程序中创建出多条线程？

- Java是通过java.lang.Thread类的对象来代表线程的。



多线程的创建方式一：继承Thread类

1. 定义一个子类MyThread继承线程类java.lang.Thread, 重写run()方法
2. 创建MyThread类的对象
3. 调用线程对象的start()方法启动线程(启动后还是执行run方法的)



方式一优缺点：

- 优点：编码简单
- 缺点：线程类已经继承Thread，无法继承其他类，不利于功能的扩展。



多线程的注意事项

1. 启动线程必须是调用start方法，不是调用run方法。
2. 不要把主线程任务放在启动子线程之前。



```java
package com.itheima.p1_create_thread;
//目标：掌握线程的创建方式一：继承Thread类
public class ThreadTest1 {
    // main方法是由一条默认的主线程负责执行
    public static void main(String[] args) {
        // 3. 创建MyThread线程类的对象代表一个线程
        Thread t = new MyThread();
        // 4. 启动线程（自动执行run方法）
        t.start(); // main线程 t线程 不要写成t.run()

        for (int i = 1; i <= 5 ; i++) {
            System.out.println("主线程main输出：" + i);
        }
    }
}

```

````java
package com.itheima.p1_create_thread;
// 1. 让子类继承Thread线程类。
public class MyThread extends Thread{
    // 2. 必须重写Thread类的run方法

    @Override
    public void run() {
        // 描述线程的执行任务
        for (int i = 1; i <= 5 ; i++) {
            System.out.println("子线程输出：" + i);
        }
    }
}

````

```
子线程输出：1
主线程main输出：1
主线程main输出：2
主线程main输出：3
子线程输出：2
主线程main输出：4
子线程输出：3
主线程main输出：5
子线程输出：4
子线程输出：5
```



### 方式二：实现Runnable接口



步骤：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-04%2022.55.13.png?raw=true" style="zoom:33%;" /> 



```java
package com.itheima.p1_create_thread;
// 目标：掌握多线程的创建方式二：实现Runnable接口。
public class ThreadTest2 {
    public static void main(String[] args) {
        // 3. 创建任务对象。
        Runnable target = new MyRunnable();
        // 4. 把任务对象交给一个线程对象处理。
        // public Thread(Runnable target)
        new Thread(target).start();

        for (int i = 1; i <= 5 ; i++) {
            System.out.println("main thread output: "+i);
        }
    }
}

```

```java
package com.itheima.p1_create_thread;
// 1. 定义一个任务类，实现Runnable接口
public class MyRunnable implements Runnable{
    // 2. 重写runnable的run方法
    @Override
    public void run() {
        // 线程要执行的任务
        for (int i = 1; i <= 5 ; i++) {
            System.out.println("sub thread output: "+i);
        }
    }
}

```

```
sub thread output: 1
main thread output: 1
sub thread output: 2
main thread output: 2
main thread output: 3
main thread output: 4
main thread output: 5
sub thread output: 3
sub thread output: 4
sub thread output: 5
```



线程创建方式二的匿名内部类写法

1. 可以创建Runnable的匿名内部类对象。
2. 再交给Thread线程对象。
3. 再调用线程对象的start()启动线程。

```java
package com.itheima.p1_create_thread;
// 目标：掌握多线程创建方式二的匿名内部类写法
public class ThreadTest2_2 {
    public static void main(String[] args) {
        // 1. 直接创建Runnable接口的匿名内部类形式（任务对象）
        Runnable target = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=5 ; i++) {
                    System.out.println("sub thread 1 output"+i);
                }
            }
        };
        new Thread(target).start();

        // 简化形式1:
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=5 ; i++) {
                    System.out.println("sub thread 2 output"+i);
                }
            }
        }).start();

        // 简化形式1:
        new Thread(() -> {
                for (int i = 1; i <=5 ; i++) {
                    System.out.println("sub thread 3 output"+i);

            }
        }).start();

        for (int i = 1; i <=5 ; i++) {
            System.out.println("main thread 1 output"+i);
        }
    }
}

```

```
sub thread 2 output1
sub thread 3 output1
sub thread 1 output1
main thread 1 output1
sub thread 1 output2
sub thread 3 output2
sub thread 3 output3
sub thread 2 output2
sub thread 3 output4
sub thread 2 output3
sub thread 1 output3
main thread 1 output2
sub thread 1 output4
sub thread 2 output4
sub thread 3 output5
sub thread 2 output5
sub thread 1 output5
main thread 1 output3
main thread 1 output4
main thread 1 output5
```



### 方式三：实现Callable接口



前两种线程创建方式都存在的一个问题

- 假如线程执行完毕后有一些数据需要返回，他们重写的方法均不能直接返回结果。



怎么解决？

- JDK5.0提供了Callable接口和FutureTask类来实现（多线程的第三种创建方式）。

- 这种方式最大的优点：可以返回线程执行完毕后的结果。



多线程的第三种创建方式：利用Callable接口、FutureTask类实现。

1. 创建任务对象
   - 定义一个类实现Callable接口，重写call方法，封装要做的事情，和要返回的数据。
   - 把Callable类型的对象封装成FutureTask（线程任务对象）。
2. 把线程任务对象交给Thread对象。
3. 调用Thread对象的start方法启动线程。
4. 执行完毕后，通过FutureTask对象的get方法去获取线程任务执行的结果。



FutureTask的API

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-05%2021.46.11.png?raw=true)



```java
package com.itheima.p1_create_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 目标：掌握线程的创建方式三：实现Callable接口
public class ThreadTest3 {
    public static void main(String[] args) throws Exception {
        // 3. 创建一个Callable对象
        Callable<String> call = new MyCallable(100);

        // 4. 把Callable对象封装成一个FutureTask对象（任务对象）
        // 未来任务对象的作用？
        // （1）是一个任务对象，实现Runnable对象
        // （2）可以在线程执行完毕之后，用未来任务对象调用get方法获取线程执行完毕后的结果。
        FutureTask<String> f1 = new FutureTask<>(call);
        // 5. 把任务对象交给一个Thread对象
        new Thread(f1).start();

        Callable<String> call2 = new MyCallable(200);
        FutureTask<String> f2 = new FutureTask<>(call2);
        new Thread(f2).start();

        // 6. 获取线程执行完毕后返回的结果。
        // 注意：如果执行到这，假如上面的线程还没有执行完毕，这里的代码会暂停，等待上面线程执行完毕后才会获取结果。
        String rs = f1.get(); //因为return值除了string还可能是Exception，所以抛exception
        System.out.println(rs);

        String rs2 = f2.get();
        System.out.println(rs2);

    }
}

```

```java
package com.itheima.p1_create_thread;

import java.util.concurrent.Callable;

// 1. 让这个类实现Callable接口
public class MyCallable implements Callable<String> {
    private int n;

    public MyCallable(int n) {
        this.n = n;
    }

    // 2. 重写call方法
    @Override
    public String call() throws Exception {
        // 描述线程的任务，返回线程执行返回后的结果。
        // 需求：求1-n的和返回。
        int sum=0;
        for (int i = 0; i <= n; i++) {
            sum+=i;
        }
        return "This thread calculate the sum of 1-"+n+": "+sum;
    }
}

```

```
This thread calculate the sum of 1-100: 5050
This thread calculate the sum of 1-200: 20100
```



## 二、Thread的常用方法



Thread提供了很多与线程操作相关的方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-05%2021.49.50.png?raw=true)

Thread类还提供了：yield, interrupt, 守护线程, 线程优先级等线程的控制方法，在开发中很少使用，这些方法会后续需要用到的时候再讲解。



````java
package com.itheima.p2_thread_api;

/**
 * 目标：掌握Thread的常用方法。
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        Thread t1 = new MyThread("1号线程");
        // t1.setName("1号线程");
        t1.start();
        System.out.println(t1.getName()); // Thread-0

        Thread t2 = new MyThread("2号线程");
        // t2.setName("2号线程");
        t2.start();
        System.out.println(t2.getName()); // Thread-1

        // 主线程对象的名字
        // 哪个线程执行它，它就会得到哪个线程对象。
        Thread m = Thread.currentThread();
        m.setName("最牛的线程");
        System.out.println(m.getName()); // main

        for (int i = 1; i <= 5; i++) {
            System.out.println(m.getName() + "线程输出：" + i);
        }
    }
}

````

````java
package com.itheima.p2_thread_api;

public class MyThread extends Thread{
    public MyThread(String name){
        super(name); // 为当前线程设置名字了
    }
    @Override
    public void run() {
        // 哪个线程执行它，它就会得到哪个线程对象。
        Thread t = Thread.currentThread();
        for (int i = 1; i <= 3; i++) {
            System.out.println(t.getName() + "输出：" + i);
        }
    }
}

````

```
1号线程
2号线程
最牛的线程
1号线程输出：1
1号线程输出：2
最牛的线程线程输出：1
2号线程输出：1
最牛的线程线程输出：2
1号线程输出：3
最牛的线程线程输出：3
2号线程输出：2
最牛的线程线程输出：4
2号线程输出：3
最牛的线程线程输出：5
```





Sleep, join方法介绍：

```java
package com.itheima.p2_thread_api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 目标：掌握sleep方法,join方法的作用。
 */
public class ThreadTest2 {
    public static void main(String[] args) throws Exception {
        System.out.println(Runtime.getRuntime().availableProcessors());
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
            // 休眠5s
            if(i == 3){
                // 会让当前执行的线程暂停5秒，再继续执行
                // 项目经理让我加上这行代码，如果用户交钱了，我就注释掉！
                Thread.sleep(5000);
            }
        }

        // join方法作用：让当前调用这个方法的线程先执行完。
        Thread t1 = new MyThread("1号线程");
        t1.start();
        t1.join();

        Thread t2 = new MyThread("2号线程");
        t2.start();
        t2.join();

        Thread t3 = new MyThread("3号线程");
        t3.start();
        t3.join();
    }
}

```

```
1
2
3
4
5
1号线程输出：1
1号线程输出：2
1号线程输出：3
2号线程输出：1
2号线程输出：2
2号线程输出：3
3号线程输出：1
3号线程输出：2
3号线程输出：3
```



## 三、线程安全

### 什么是线程安全问题？

- 多个线程，同时操作同一个共享资源的时候，可能会出现业务安全问题。



取钱的线程安全问题

- 场景：小明和小红是一对夫妻，他们有一个共同的账户，余额是10万元，如果小明和小红同事来取钱，并且2人各自都在取钱10万元，可能会出现什么问题呢？

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-05%2022.24.16.png?raw=true)



### 用程序模拟线程安全问题

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-05%2022.31.03.png?raw=true" style="zoom:33%;" /> 



取钱案例代码：

```java
package com.itheima.p3_thread_safe;

// 目标：模拟线程安全问题
public class ThreadTest {
    public static void main(String[] args) {
        // 1. 创建一个账户对象，代表两个人的共享账户。
        Account acc = new Account("ICBC-110", 100000);
        // 2. 创建两个线程，分别代表小明 小红，再去同一账户对象中取钱10万。
        new DrawThread(acc, "小明").start(); //小明
        new DrawThread(acc, "小红").start(); //小红

    }
}

```

```java
package com.itheima.p3_thread_safe;

public class DrawThread extends Thread{

    // 保证两个线程对象处理的是同一个账户
    private Account acc;
    public DrawThread(Account acc, String name){
        super(name);
        this.acc = acc;
    }
    @Override
    public void run() {
        // 取钱（小明，小红）
        acc.drawMoney(100000);
    }
}

```

```java
package com.itheima.p3_thread_safe;

public class Account {
    private String cardId; // 卡号
    private double money; // 余额

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    // 小明 小红同时过来的
    public void drawMoney(double withdrawMoney) {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();

        // 1. 判断余额是否足够
        if (this.money >= withdrawMoney){
            System.out.println(name+" get money "+withdrawMoney+" successfully");
            this.money -= withdrawMoney;
            System.out.println(name+" has a balance of "+this.money);
        }else {
            System.out.println(name+" balance is not enough.");
        }
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


}

```

```
小红 get money 100000.0 successfully
小明 get money 100000.0 successfully
小明 has a balance of -100000.0
小红 has a balance of 0.0
```



## 四、线程同步

### 认识线程同步



线程同步

- 解决线程安全问题的方案。



线程同步的思想

- 让多个线程实现先后依次访问共享资源，这样就解决了安全问题。



线程同步的常见方案

- 加锁：每次只允许一个线程加锁，加锁后才能进入访问，访问完毕后自动解锁，然后其他线程才能再加锁进来。(厕所占坑原理)

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2009.30.38.png?raw=true" style="zoom:33%;" /> 



### 方式一：同步代码块



同步代码块

- 作用：把访问共享资源的核心代码给上锁，以此保证线程安全。
- 原理：每次只允许一个线程加锁后进入，执行完毕后自动解锁，其他线程才可以进来执行。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2009.36.40.png?raw=true" style="zoom:33%;" /> 



同步锁的注意事项

- 对于当前同时执行的线程来说，同步锁必须是同一把（同一个对象），否则会出bug。



锁对象随便选择一个唯一的对象好不好呢？

- 不好，会影响其他无关线程的执行。



锁对象的使用规范

- 建议使用共享资源作为锁对象，对于实例方法建议使用this作为锁对象。
- 对于静态方法建议使用字节码（类名.class）对象作为锁对象。



同步代码块介绍代码：

```java
package com.itheima.p4_synchronized_code;

// 目标：模拟线程安全问题
public class ThreadTest {
    public static void main(String[] args) {
        // 1. 创建一个账户对象，代表两个人的共享账户。
        Account acc = new Account("ICBC-110", 100000);
        // 2. 创建两个线程，分别代表小明 小红，再去同一账户对象中取钱10万。
        new DrawThread(acc, "小明").start(); //小明
        new DrawThread(acc, "小红").start(); //小红

        Account acc1 = new Account("ICBC-112", 100000);
        // 2. 创建两个线程，分别代表小明 小红，再去同一账户对象中取钱10万。
        new DrawThread(acc1, "小黑").start(); //小黑
        new DrawThread(acc1, "小白").start(); //小白
    }
}

```

````java
package com.itheima.p4_synchronized_code;

public class DrawThread extends Thread{

    // 保证两个线程对象处理的是同一个账户
    private Account acc;
    public DrawThread(Account acc, String name){
        super(name);
        this.acc = acc;
    }
    @Override
    public void run() {
        // 取钱（小明，小红）
        acc.drawMoney(100000);
    }
}

````

````java

package com.itheima.p4_synchronized_code;

public class Account {
    private String cardId; // 卡号
    private double money; // 余额

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }
    
    public static void test(){
        synchronized (Account.class){ //静态方法用类名.class作锁，锁住所有用当前类名访问方法的线程
        }
    }

    // 小明 小红同时过来的
    public void drawMoney(double withdrawMoney) {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();

        // 1. 判断余额是否足够
        // this正好代表共享资源！
        synchronized (this) {
            if (this.money >= withdrawMoney){
                System.out.println(name+" get money "+withdrawMoney+" successfully");
                this.money -= withdrawMoney;
                System.out.println(name+" has a balance of "+this.money);
            }else {
                System.out.println(name+" balance is not enough.");
            }
        }
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


}

````

```
小黑 get money 100000.0 successfully
小明 get money 100000.0 successfully
小明 has a balance of 0.0
小黑 has a balance of 0.0
小红 balance is not enough.
小白 balance is not enough.
```



### 方式二：同步方法

同步方法

- 作用：把访问共享资源的核心方法给上锁，以此保证线程安全。（把方法锁起来）
- 原理：每次只能一个线程进入，执行完毕后自动解锁，其他线程才可以进行执行。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2010.09.17.png?raw=true" style="zoom:33%;" /> 



同步方法底层原理

- 同步方法其实底层也是有隐式锁对象的，只是锁的范围是整个方法代码。
- 如果方法是实力方法：同步方法默认用this作为锁对象。
- 如果方法是静态方法：同步方法默认用类名.this作为锁的对象。



同步方法介绍代码：

```java
package com.itheima.p5_synchronized_method;

import com.itheima.p5_synchronized_method.Account;
import com.itheima.p5_synchronized_method.DrawThread;

// 目标：模拟线程安全问题
public class ThreadTest {
    public static void main(String[] args) {
        // 1. 创建一个账户对象，代表两个人的共享账户。
        Account acc = new Account("ICBC-110", 100000);
        // 2. 创建两个线程，分别代表小明 小红，再去同一账户对象中取钱10万。
        new DrawThread(acc, "小明").start(); //小明
        new DrawThread(acc, "小红").start(); //小红

    }
}

```

```java
package com.itheima.p5_synchronized_method;

import com.itheima.p5_synchronized_method.Account;

public class DrawThread extends Thread{

    // 保证两个线程对象处理的是同一个账户
    private Account acc;
    public DrawThread(Account acc, String name){
        super(name);
        this.acc = acc;
    }
    @Override
    public void run() {
        // 取钱（小明，小红）
        acc.drawMoney(100000);
    }
}

```

```java
package com.itheima.p5_synchronized_method;

public class Account {
    private String cardId; // 卡号
    private double money; // 余额

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    // 小明 小红同时过来的
    public synchronized void drawMoney(double withdrawMoney) {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();

        // 1. 判断余额是否足够
        if (this.money >= withdrawMoney){
            System.out.println(name+" get money "+withdrawMoney+" successfully");
            this.money -= withdrawMoney;
            System.out.println(name+" has a balance of "+this.money);
        }else {
            System.out.println(name+" balance is not enough.");
        }
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


}

```

```
小明 get money 100000.0 successfully
小明 has a balance of 0.0
小红 balance is not enough.
```



是同步代码块好还是同步方法好一点？

- 范围上：同步代码块锁的范围更小，同步方法锁的范围更大。
- 可读性：同步方法更好。



### 方式三：Lock锁



Lock锁

- Lock锁是JDK5开始提供的一个新的锁定操作，通过它可以创建出锁对象进行加锁和解锁，更灵活、更方便、更强大。
- Lock是接口，不能直接实例化，可以用它的实现类ReentrantLock来构建Lock锁对象。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2010.53.31.png?raw=true" style="zoom:33%;" /> 



```java
package com.itheima.p6_synchronized_lock;

import com.itheima.p6_synchronized_lock.Account;
import com.itheima.p6_synchronized_lock.DrawThread;

// 目标：模拟线程安全问题
public class ThreadTest {
    public static void main(String[] args) {
        // 1. 创建一个账户对象，代表两个人的共享账户。
        Account acc = new Account("ICBC-110", 100000);
        // 2. 创建两个线程，分别代表小明 小红，再去同一账户对象中取钱10万。
        new DrawThread(acc, "小明").start(); //小明
        new DrawThread(acc, "小红").start(); //小红

    }
}

```

````java
package com.itheima.p6_synchronized_lock;

import com.itheima.p6_synchronized_lock.Account;

public class DrawThread extends Thread{

    // 保证两个线程对象处理的是同一个账户
    private Account acc;
    public DrawThread(Account acc, String name){
        super(name);
        this.acc = acc;
    }
    @Override
    public void run() {
        // 取钱（小明，小红）
        acc.drawMoney(100000);
    }
}

````

```java
package com.itheima.p6_synchronized_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private String cardId; // 卡号
    private double money; // 余额

    //创建了一个锁对象（每个账户都有自己的锁对象）
    private final Lock lk = new ReentrantLock(); //加了final就不能对lk进行二次赋值=>专业

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    // 小明 小红同时过来的
    public void drawMoney(double withdrawMoney) {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();

        try { //加try catch finally是因为如果try程序出现bug，仍然可以解锁。不会因为执行不到解锁导致其他线程进不去。
            lk.lock(); // 加锁
            // 1. 判断余额是否足够
            if (this.money >= withdrawMoney){
                System.out.println(name+" get money "+withdrawMoney+" successfully");
                this.money -= withdrawMoney;
                System.out.println(name+" has a balance of "+this.money);
            }else {
                System.out.println(name+" balance is not enough.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lk.unlock(); // 解锁
        }
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


}

```

````
小明 get money 100000.0 successfully
小明 has a balance of 0.0
小红 balance is not enough.
````



## 五、线程通信[了解]



**什么是线程通信？**

- 当多个线程共同操作共享资源时，线程间通过某种方式互相告知自己的状态，以相互协调，并避免无效的资源争夺。



**线程通信的常见模型（生产者与消费者模型）**

- 生产者线程负责生成数据
- 消费者线程负责消费生产者生成的数据
- 注意：生产者生产完数据后应该让自己等待，通知其他消费者消费；消费者消费完数据之后应该让自己等待，同时通知生产者生成。



**线程通信模型**

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2011.05.44.png?raw=true" style="zoom:33%;" /> 



**Object类的等待和唤醒方法：**

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2011.21.11.png?raw=true" style="zoom:33%;" /> 



注意

- 上述方法应该使用当前同步锁对象进行调用。（cuz只有锁对象知道谁被锁，谁在等锁）



生产者消费者案例代码

```java
package com.itheima.p7_thread_communication;

/**
 * 目标：了解一下线程通信。
 */
public class ThreadTest {
    public static void main(String[] args) {
        //   需求：3个生产者线程，负责生产包子，每个线程每次只能生产1个包子放在桌子上
        //      2个消费者线程负责吃包子，每人每次只能从桌子上拿1个包子吃。
        Desk desk  = new Desk();

        // 创建3个生产者线程（3个厨师）
        new Thread(() -> {
            while (true) {
                desk.put();
            }
        }, "厨师1").start();

        new Thread(() -> {
            while (true) {
                desk.put();
            }
        }, "厨师2").start();

        new Thread(() -> {
            while (true) {
                desk.put();
            }
        }, "厨师3").start();

        // 创建2个消费者线程（2个吃货）
        new Thread(() -> {
            while (true) {
                desk.get();
            }
        }, "吃货1").start();

        new Thread(() -> {
            while (true) {
                desk.get();
            }
        }, "吃货2").start();
    }
}

```

```java
package com.itheima.p7_thread_communication;

import java.util.ArrayList;
import java.util.List;

public class Desk {
    private List<String> list = new ArrayList<>();

    // 放1个包子的方法
    // 厨师1 厨师2 厨师3
    public synchronized void put() {
        try {
            String name = Thread.currentThread().getName();
            // 判断是否有包子。
            if(list.size() == 0){
                list.add(name + "做的肉包子");
                System.out.println(name + "做了一个肉包子~~");
                Thread.sleep(2000);

                // 唤醒别人, 等待自己。先唤醒别人，再等待自己。cuz自己睡着了唤醒不了别人
                this.notifyAll();
                this.wait();
            }else {
                // 有包子了，不做了。
                // 唤醒别人, 等待自己。先唤醒别人，再等待自己。cuz自己睡着了唤醒不了别人
                this.notifyAll();
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 吃货1 吃货2
    public synchronized void get() {
        try {
            String name = Thread.currentThread().getName();
            if(list.size() == 1){
                // 有包子，吃了
                System.out.println(name  + "吃了：" + list.get(0));
                list.clear();
                Thread.sleep(1000);
                this.notifyAll();
                this.wait();
            }else {
                // 没有包子
                this.notifyAll();
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
厨师1做了一个肉包子~~
吃货2吃了：厨师1做的肉包子
厨师3做了一个肉包子~~
吃货1吃了：厨师3做的肉包子
厨师1做了一个肉包子~~
吃货1吃了：厨师1做的肉包子
厨师3做了一个肉包子~~
吃货1吃了：厨师3做的肉包子
```



## 六、线程池

### 6.1 认识线程池



什么是线程池？

- 线程池就是一个可以复用线程的技术。



不使用线程池的问题

- 假设：用户每次发起一个请求给后台，后台就创建一个新的线程来处理，下次新的任务过来肯定也会创建新的线程，如果用户量非常大，创建的线程也讲越来越多。然而，创建线程是开销很大的，并且请求过多时，会严重影响系统性能。



线程池的工作原理

如下图所示，线程池内部会有一个容器，存储几个核心线程，假设有3个核心线程，这3个核心线程可以处理3个任务。但是任务总有被执行完的时候，假设第1个线程的任务执行完了，那么第1个线程就空闲下来了，有新的任务时，空闲下来的第1个线程可以去执行其他任务。依此内推，这3个线程可以不断的复用，也可以执行很多个任务。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2011.57.31.png?raw=true" style="zoom:33%;" /> 



### 6.2 如何创建线程池？



谁代表线程池？

- JDK5中提供了代表线程池的接口：ExecutorService



如何得到线程池对象？

- 方式一：使用Executor Service的实现类ThreadPoolExecutor自创键一个线程池对象。

- 方式二：使用Executors（线程池的工具类）调用方法返回不同特点的线程池对象。



线程池的注意事项

1. 临时线程什么时候创建？
   - 新任务提交时，发现核心线程都在忙、任务队列满了、并且还可以创建临时线程，此时会创建临时线程。
2. 什么时候会开始拒绝新任务？
   - 核心线程和临时线程都在忙、任务队列也满了、新任务过来时才会开始拒绝任务。



ThreadPoolExecutor构造器

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2012.12.58.png?raw=true)



线程池创建代码：

```java
package com.itheima.p8_thread_pool;

import java.util.concurrent.*;

// 目标：掌握线程池的创建
public class ThreadPoolTest1 {
    public static void main(String[] args) {
        // 1. 通过ThreadPoolExecutor创建一个线程池对象。
//            public ThreadPoolExecutor(int corePoolSize,
//                                        int maximumPoolSize,
//                                        long keepAliveTime,
//                                        TimeUnit unit,
//                                        BlockingQueue<Runnable> workQueue,
//                                        ThreadFactory threadFactory,
//                                        RejectedExecutionHandler handler)
        ExecutorService pool =  new ThreadPoolExecutor(3, 5, 8, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}

```



### 6.3 线程池处理Runnable任务



ExecutorService的常用方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2015.58.45.png?raw=true" style="zoom:33%;" /> 



新任务拒绝策略

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2016.04.29.png?raw=true" style="zoom:33%;" /> 



线程池处理任务代码：

```java
package com.itheima.p8_thread_pool;

import com.itheima.p1_create_thread.MyCallable;

import java.util.concurrent.*;

// 目标：掌握线程池的创建
public class ThreadPoolTest1 {
    public static void main(String[] args) {
        // 1. 通过ThreadPoolExecutor创建一个线程池对象。
//            public ThreadPoolExecutor(int corePoolSize,
//                                        int maximumPoolSize,
//                                        long keepAliveTime,
//                                        TimeUnit unit,
//                                        BlockingQueue<Runnable> workQueue,
//                                        ThreadFactory threadFactory,
//                                        RejectedExecutionHandler handler)
        ExecutorService pool =  new ThreadPoolExecutor(3, 5, 8, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        Runnable target = new MyRunnable();
        pool.execute(target); // 线程池会自动创建一个新县城，自动处理这个任务，自动执行的！
        pool.execute(target); // 线程池会自动创建一个新县城，自动处理这个任务，自动执行的！
        pool.execute(target); // 线程池会自动创建一个新县城，自动处理这个任务，自动执行的！
        pool.execute(target); // 复用前面的核心线程
        pool.execute(target); // 复用前面的核心线程
        pool.execute(target); // 复用前面的核心线程
        pool.execute(target); // 复用前面的核心线程
        // 到了临时线程的创建时机了
        pool.execute(target);
        pool.execute(target);
        // 到了新任务的拒绝时机了
        pool.execute(target);

        // pool.shutdown();  // 等着线程池的任务全部执行完毕后，再关闭线程池
        // pool.shutdownNow(); // 立即关闭线程池！不管任务是否执行完毕。


    }
}

```

```java
package com.itheima.p8_thread_pool;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        // 任务是干啥的？
        System.out.println(Thread.currentThread().getName()+" => output666");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

```
pool-1-thread-3 => output666
pool-1-thread-1 => output666
pool-1-thread-2 => output666
pool-1-thread-4 => output666
main => output666
pool-1-thread-5 => output666
```



### 6.4 线程池处理Callable任务



ExecutorService的常用方法

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2016.08.47.png?raw=true" style="zoom:33%;" /> 



线程池处理Callable任务代码：

```java
package com.itheima.p8_thread_pool;

import java.util.concurrent.*;

/**
 * 目标：掌握线程池的创建。
 */
public class ThreadPoolTest2 {
    public static void main(String[] args) throws Exception {
        // 1、通过ThreadPoolExecutor创建一个线程池对象。
        ExecutorService pool = new ThreadPoolExecutor(3, 5, 8,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 2、使用线程处理Callable任务。
        Future<String> f1 = pool.submit(new MyCallable(100));
        Future<String> f2 = pool.submit(new MyCallable(200));
        Future<String> f3 = pool.submit(new MyCallable(300));
        Future<String> f4 = pool.submit(new MyCallable(400));

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
        System.out.println(f4.get());
    }
}

```

```java
package com.itheima.p8_thread_pool;

import java.util.concurrent.Callable;

/**
 * 1、让这个类实现Callable接口
 */
public class MyCallable implements Callable<String> {
    private int n;
    public MyCallable(int n) {
        this.n = n;
    }

    // 2、重写call方法
    @Override
    public String call() throws Exception {
        // 描述线程的任务，返回线程执行返回后的结果。
        // 需求：求1-n的和返回。
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return Thread.currentThread().getName() + "求出了1-" + n + "的和是：" + sum;
    }
}

```

````
pool-1-thread-1求出了1-100的和是：5050
pool-1-thread-2求出了1-200的和是：20100
pool-1-thread-3求出了1-300的和是：45150
pool-1-thread-2求出了1-400的和是：80200
````



### 6.5 Executors工具类实现线程池



Executors

- 是一个线程池的工具类，提供了很多静态方法用于返回不同特点的线程池对象。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.03.14.png?raw=true)



Executors使用可能存在的陷阱

- 大型并发系统环境中使用Executors，如果不注意可能会出现系统风险。(OOM: out of memory内存溢出)

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.19.39.png?raw=true)



## 七、并发、并行



进程

- 正常运行的程序（软件）就是一个独立的进程。

- 线程是属于进程，一个进程中包含多个线程。
- 进程中的多个线程其实并发和并行同时存在。



并发

- 进程中的线程由CPU负责调度执行，但是CPU同时处理线程的数量是优先的，为了保证全部线程都能执行到，CPU采用轮询机制为系统的每个线程服务，由于CPU切换的速度很快，给我们的感觉这些线程在同时执行，这就是并发。（简单记：并发就是多条线程交替执行）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.27.01.png?raw=true" style="zoom:33%;" />



并行

- 在同一时刻上，同时有多个线程被CPU调度执行。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.30.43.png?raw=true" style="zoom:33%;" />



多线程到底怎么执行的？

并发和并行同时执行。

e.g. 电脑有16个核心

并发：每16个线程16个线程地切换。

并行：同一时刻有16个线程。



## 八、线程的生命周期



人的生命周期（各种状态）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.34.55.png?raw=true" style="zoom:33%;" />



线程的生命周期

- 就是线程从生到死的过程中，经历的各种状态及状态切换。
- 理解线程这些状态有利于提升并发编程的理解能力。



Java线程的状态

- Java总共定义了6种状态
- 6种状态都定义在Thread类的内部枚举类种。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.38.40.png?raw=true" style="zoom:33%;" /> 



线程的6种状态互相转换

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.55.36.png?raw=true)



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2017.58.35.png?raw=true)



## 九、乐观锁



乐观锁机制（CAS算法compare and set比较和修改）

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2021.29.47.png?raw=true" style="zoom:33%;" /> 



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2021.46.08.png?raw=true)



乐观锁代码

```java
package com.itheima.p9_tz;

public class Test2 {
    public static void main(String[] args) {
        // 目标：拓展悲观锁，乐观锁原理
        // 悲观锁：一上来就加锁，没有安全感。每次只能一个线程进入访问完毕后，再解锁。=> 线程安全，性能较差！
        // 乐观锁：一开始不上锁，认为是没有问题的，等要出现线程安全问题的时候才开始控制。 => 线程安全，性能较好。

        // 需求：1个变量，100个线程，每个线程对其加100次。
        Runnable target = new MyRunnable2(); // 1个任务
        for (int i = 1; i <= 100 ; i++) {
            new Thread(target).start(); // 100个线程执行同一个任务
        }
    }
}

```

```java
package com.itheima.p9_tz;

import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable2 implements Runnable{
    // 整数修改的乐观锁：原子类实现的。
    private AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        // 100次
        for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+"count => "+ count.incrementAndGet());

        }

    }
}

```

````
...（输出1-10000，太多了没全copy）
Thread-53count => 9992
Thread-53count => 9993
Thread-53count => 9994
Thread-53count => 9995
Thread-53count => 9996
Thread-53count => 9997
Thread-53count => 9998
Thread-53count => 9999
Thread-53count => 10000
````



# D20. 网络通信

## 一、网络编程概述



什么是网络编程？

- 可以让设备中的程序与网络上其他设备中的应用程序进行数据交互（实现网络通信的）。



Java提供了哪些网络编程的解决方案？

- Java.net.*包下提供了网络编程的解决方案！



基本的通信架构

- 通信的基本架构主要有两种形式：一种是CS架构（Client 客户端/Server服务端）、一种是BS架构（Brower 浏览器/Server服务端）。



- **CS架构的特点：**CS架构需要用户在自己的电脑或者手机上安装客户端软件，然后由客户端软件通过网络连接服务器程序，由服务器把数据发给客户端，客户端就可以在页面上看到各种数据了。e.g. 微信, intelliJ

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.00.57.png?raw=true" style="zoom:33%;" />



**BS架构的特点：**BS架构不需要开发客户端软件，用户只需要通过浏览器输入网址就可以直接从服务器获取数据，并由服务器将数据返回给浏览器，用户在页面上就可以看到各种数据了。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.03.37.png?raw=true" style="zoom:33%;" />



## 二、网络通信三要素



网络通信的关键三要素

- 分别是IP地址、端口号、通信协议

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.07.32.png?raw=true" style="zoom:33%;" />



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.08.35.png?raw=true" style="zoom:33%;" />



### 2.1 IP地址



IP地址

- IP（Ineternet Protocol）：全称互联网协议地址，是分配给网络设备的唯一表示。
- IP地址分为：IPV4地址、IPV6地址

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.12.30.png?raw=true" style="zoom:33%;" /> 

IPv6

- IPv6：128位二进制数据来表示（16个字节），号称可以为地球上的每一粒沙子编一个IP地址。
- IPv6分成8段表示，每段每四位编码成一个十六进制位表示，数之间用冒号（：）分开。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.16.44.png?raw=true" style="zoom:33%;" /> 



IP域名

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.19.20.png?raw=true" style="zoom:33%;" /> 



公网IP，内网IP

- 公网IP：是可以连接互联网的IP地址；
- 内网IP：也叫局域网IP，只能组织机构内部使用。
- 192.168.开头的就是常见的局域网地址，范围即为192.168.0.0--192.168.255.255，专门为组织机构内部使用。



特殊IP地址

- 127.0.0.1、localhost：代表本机IP，只会寻找当前所在主机。



IP常用命令：

- Ipconfig:查看本机IP地址。
- ping IP地址：检查网络是否连通。



### 2.2 InetAddress

- 代表IP地址的类。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.27.28.png?raw=true)



InetAddress方法介绍代码

```java
package com.itheima.p1_ip;

import java.net.InetAddress;

/**
 * 目标：掌握InetAddress类的使用。
 */
public class InetAddressTest {
    public static void main(String[] args) throws Exception {
        // 1、获取本机IP地址对象的
        InetAddress ip1 = InetAddress.getLocalHost();
        System.out.println(ip1.getHostName());
        System.out.println(ip1.getHostAddress());

        // 2、获取指定IP或者域名的IP地址对象。
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostName());
        System.out.println(ip2.getHostAddress());

        // ping www.baidu.com
        System.out.println(ip2.isReachable(60000));
    }
}

```

```
appledeMacBook-Pro.local
172.23.44.117
www.baidu.com
103.235.46.40
false
```



### 2.3 端口号



端口

- 指的是计算机设备上运行的应用程序的标识，被规定为一个16位的二进制数据，范围（0~65535）。



分类

- 周知端口：0~1023，被预先定义的知名应用程序占用（如：HTTP占用80，FTP占用21）
- 注册端口：1024~49151，分配给用户经常或者某些应用程序
- 动态端口：49152~65536，之所以称为动态端口，是因为它一般不固定分配给某进程，而是动态分配的。

注意：我们自己开发的程序一般选择使用注册端口，且一个设备中不能出现两个程序的端口号一样，否则出错。



### 2.4 协议



通信协议

- 网络上通信的设备，事先规定的连接规则，以及传输数据的规则被称为网络通信协议。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2022.46.54.png?raw=true)



传输层的2个通信协议

- UDP（User Datagram Protocol）:用户数据报协议
- TCP（Transmission Control Protocol）：传输控制协议



UDP协议（通信效率高！语音通话、视频直播）

- 特点：无连接、不可靠通信。
- 不实现建立连接，数据按照包发，一包数据包含：自己的IP，程序端口，目的地IP，程序端口和数据（限制在64KB内）等。
- 发送方不管对方是否在线，数据在中间丢失也不管，如果接收方收到数据也不返回确认，所以不可靠。



TCP协议

- 特点：面向连接、可靠通信。
- TCP的最终目的：要保证在不可靠的信道上实现可靠的传输。
- TCP主要有三个步骤实现可靠传输：三次握手建立连接，传输数据进行确认，四次挥手断开连接。



TCP三次握手建立可靠连接

- 如下图所示：目的是确认通信双方，收发消息都是正常没问题的。
- 传输数据会进行确认，以保证数据传输的可靠性。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2023.00.20.png?raw=true" style="zoom:33%;" />



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2023.01.12.png?raw=true" style="zoom:33%;" />



TCP：四次挥手断开连接

- 目的：确保双方数据的收发都已经完成。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2023.05.48.png?raw=true" style="zoom:33%;" />



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-06%2023.06.50.png?raw=true" style="zoom:33%;" />



## 三、UDP通信-一发一收



UDP通信

- 特点：无连接、不可靠通信。
- 不事先建立连接；发送端每次把要发送的数据（限制在64KB内）、接收端IP、等信息封装成一个数据包，发出去就不管了。
- Java提供了一个类叫DatagramSocket来完成基于UDP协议的收发数据。



具体流程如下图所示：假设我们把DatagramSocket看做是街道两天的人，现在左边的人要扔一盘韭菜到右边，这里的韭菜就是数据，但是数据需要用一个盘子装起来，这里的盘子就是DatagramPacket数据包的意思。通信双方都需要有DatagramSocket(扔、接韭菜人)，还需要有DatagramPacket(装韭菜的盘子)

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2012.58.32.png?raw=true" style="zoom:33%;" />



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2013.01.57.png?raw=true)



案例：UDP通信实现：发送消息、接收消息

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2013.35.32.png?raw=true" style="zoom:33%;" /> 

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2013.35.54.png?raw=true" style="zoom:33%;" />



```java
package com.itheima.p2_udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 目标：完成UDP通信快速入门：实现1发1收。
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1、创建客户端对象（发韭菜出去的人）
        DatagramSocket socket = new DatagramSocket(7777);

        // 2、创建数据包对象封装要发出去的数据（创建一个韭菜盘子）
       /* public DatagramPacket(byte buf[], int length,
             InetAddress address, int port)
               参数一：封装要发出去的数据。
               参数二：发送出去的数据大小（字节个数）
               参数三：服务端的IP地址（找到服务端主机）
               参数四：服务端程序的端口。
             */
        byte[] bytes = "我是快乐的客户端，我爱你abc".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length
                , InetAddress.getLocalHost(),  6666);

        // 3、开始正式发送这个数据包的数据出去了
        socket.send(packet);

        System.out.println("客户端数据发送完毕~~~");
        socket.close(); // 释放资源！cuz会占用网卡资源
    }
}

```

```java
package com.itheima.p2_udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 目标：完成UDP通信快速入门-服务端开发
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("----服务端启动----");
        // 1、创建一个服务端对象（创建一个接韭菜的人） 注册端口
        DatagramSocket socket = new DatagramSocket(6666);

        // 2、创建一个数据包对象，用于接收数据的（创建一个韭菜盘子）
        byte[] buffer = new byte[1024 * 64]; // 64KB. 1KB不一定够，64KB是一包UDP数据的最大值，这个字节数组肯定够了
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // 3、开始正式使用数据包来接收客户端发来的数据
        socket.receive(packet);

        // 4、从字节数组中，把接收到的数据直接打印出来
        // 接收多少就倒出多少（cuz发来的数据不一定能装满64KB）
        // 获取本次数据包接收了多少数据。
        int len = packet.getLength();

        String rs = new String(buffer, 0 , len);
        System.out.println(rs);

        System.out.println(packet.getAddress().getHostAddress());
        System.out.println(packet.getPort());

        socket.close(); // 释放资源
    }
}

```

````服务端
----服务端启动----
我是快乐的客户端，我爱你abc
172.23.44.117
7777
````

````客户端
客户端数据发送完毕~~~
````



## 四、UDP通信-多发多收

刚才的案例，我们只能客户端发一次，服务端接收一次就结束了。下面我们想把这个代码改进一下，

需求：实现客户端不断的发数据，而服务端能不断的接收数据，客户端发送exit时客户端程序退出。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2014.11.10.png?raw=true" style="zoom:33%;" />

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2014.11.41.png?raw=true" style="zoom:33%;" />





4.1 客户端程序

```java
package com.itheima.p3_udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * 目标：完成UDP通信快速入门：实现客户端反复的发。
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1、创建客户端对象（发韭菜出去的人）
        DatagramSocket socket = new DatagramSocket();

        // 2、创建数据包对象封装要发出去的数据（创建一个韭菜盘子）
       /* public DatagramPacket(byte buf[], int length,
             InetAddress address, int port)
               参数一：封装要发出去的数据。
               参数二：发送出去的数据大小（字节个数）
               参数三：服务端的IP地址（找到服务端主机）
               参数四：服务端程序的端口。
             */
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请说：");
            String msg = sc.nextLine();

            // 一旦发现用户输入的exit命令，就退出客户端
            if("exit".equals(msg)){
                System.out.println("欢迎下次光临！退出成功！");
                socket.close(); // 释放资源
                break; // 跳出死循环
            }

            byte[] bytes = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length
                    , InetAddress.getLocalHost(),  6666);

            // 3、开始正式发送这个数据包的数据出去了
            socket.send(packet);
        }
    }
}

```



4.2 服务端程序

```java

package com.itheima.p3_udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 目标：完成UDP通信快速入门-服务端反复的收
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("----服务端启动----");
        // 1、创建一个服务端对象（创建一个接韭菜的人） 注册端口
        DatagramSocket socket = new DatagramSocket(6666);

        // 2、创建一个数据包对象，用于接收数据的（创建一个韭菜盘子）
        byte[] buffer = new byte[1024 * 64]; // 64KB.
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            // 3、开始正式使用数据包来接收客户端发来的数据
            socket.receive(packet);

            // 4、从字节数组中，把接收到的数据直接打印出来
            // 接收多少就倒出多少
            // 获取本次数据包接收了多少数据。
            int len = packet.getLength();

            String rs = new String(buffer, 0 , len);
            System.out.println(rs);

            System.out.println(packet.getAddress().getHostAddress());
            System.out.println(packet.getPort());
            System.out.println("--------------------------------------");
        }
    }
}

```

````Client1
请说：
我是张三
请说：
exit
欢迎下次光临！退出成功！
````

```Client2
请说：
我是李四
请说：
exit
欢迎下次光临！退出成功！
```

```Server
----服务端启动----
我是张三
172.23.44.117
56159
--------------------------------------
我是李四
172.23.44.117
49723
--------------------------------------
```



## 五、TCP通信-一发一收



TCP通信

- 特点：面向连接、可靠通信。
- 通信双方事先会采用“三次握手”方式建立可靠连接，实现端到端的通信；底层能保证数据成功传给客户端。
- Java提供了一个java.net.Socket类来完成TCP通信。



我们先讲一下Socket完成TCP通信的流程，再讲代码怎么编写就很好理解了。如下图所示

1. 当创建Socket对象时，就会在客户端和服务端创建一个数据通信的管道，在客户端和服务端两边都会有一个Socket对象来访问这个通信管道。
2. 现在假设客户端要发送一个“在一起”给服务端，客户端这边先需要通过Socket对象获取到一个字节输出流，通过字节输出流写数据到服务端
3. 然后服务端这边通过Socket对象可以获取字节输入流，通过字节输入流就可以读取客户端写过来的数据，并对数据进行处理。
4. 服务端处理完数据之后，假设需要把“没感觉”发给客户端端，那么服务端这边再通过Socket获取到一个字节输出流，将数据写给客户端
5. 客户端这边再获取输入流，通过字节输入流来读取服务端写过来的数据。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2014.20.37.png?raw=true)

 

5.1 TCP客户端

- 客户端程序就是通过java.net包下的Socket类来实现的。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2014.27.17.png?raw=true)



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2014.39.22.png?raw=true" style="zoom:33%;" /> 



```java
package com.itheima.p4_tcp1;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *  目标：完成TCP通信快速入门-客户端开发：实现1发1收。
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1、创建Socket对象，并同时请求与服务端程序的连接。
        Socket socket = new Socket("127.0.0.1", 8888);

        // 2、从socket通信管道中得到一个字节输出流，用来发数据给服务端程序。
        OutputStream os = socket.getOutputStream();

        // 3、把低级的字节输出流包装成数据输出流.由于原始的字节流不是很好用，这里根据我的经验，我原始的OutputStream包装为DataOutputStream是比较好用的。
        DataOutputStream dos = new DataOutputStream(os);

        // 4、开始写数据出去了
        dos.writeUTF("在一起，好吗？");
        dos.close(); //cuz dos包括了字节输出流os，所以只关dos就ok

        socket.close(); // 释放连接资源
    }
}

```



5.2 TCP服务端

- 服务端是通过java.net包下的ServerSocket类来实现的。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2014.44.16.png?raw=true" style="zoom:33%;" /> 



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2015.02.48.png?raw=true" style="zoom:33%;" /> 



```java
package com.itheima.p4_tcp1;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  目标：完成TCP通信快速入门-服务端开发：实现1发1收。
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动成功-------");
        // 1、创建ServerSocket的对象，同时为服务端注册端口。
        ServerSocket serverSocket = new ServerSocket(8888);

        // 2、使用serverSocket对象，调用一个accept方法，等待客户端的连接请求
        Socket socket = serverSocket.accept();

        // 3、从socket通信管道中得到一个字节输入流。
        InputStream is = socket.getInputStream();

        // 4、把原始的字节输入流包装成数据输入流
        DataInputStream dis = new DataInputStream(is);

        // 5、使用数据输入流读取客户端发送过来的消息
        String rs = dis.readUTF(); // 要和输出的writeUTF对应，通信要求高
        System.out.println(rs);
        // 其实我们也可以获取客户端的IP地址
        System.out.println(socket.getRemoteSocketAddress());

        dis.close();
        socket.close();
    }
}

```

````
-----服务端启动成功-------
在一起，好吗？
/127.0.0.1:51471
````



## 六、TCP通信-多发多收



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2015.26.20.png?raw=true" style="zoom:33%;" />



6.1 TCP客户端

下面我们把客户端代码改写一下，采用键盘录入的方式发消息，为了让客户端能够一直发，我们只需要将发送消息的代码套一层循环就可以了，当用户输入exit时，客户端退出循环并结束客户端。

```java
package com.itheima.p5_tcp2;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *  目标：完成TCP通信快速入门-客户端开发：实现客户端可以反复的发消息出去
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1、创建Socket对象，并同时请求与服务端程序的连接。
        Socket socket = new Socket("127.0.0.1", 8888);

        // 2、从socket通信管道中得到一个字节输出流，用来发数据给服务端程序。
        OutputStream os = socket.getOutputStream();

        // 3、把低级的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请说：");
            String msg = sc.nextLine();

            // 一旦用户输入了exit，就退出客户端程序
            if("exit".equals(msg)){
                System.out.println("欢迎您下次光临！退出成功！");
                dos.close();
                socket.close();
                break;
            }

            // 4、开始写数据出去了
            dos.writeUTF(msg);
            dos.flush(); //刷出去 cuz防止在内存中待着（close时候会自动刷）
        }
    }
}

```



6.2 TCP服务端

为了让服务端能够一直接收客户端发过来的消息，服务端代码也得改写一下。我们只需要将读取数据的代码加一个循环就可以了。

但是需要我们注意的时，如果客户端Socket退出之后，就表示连接客户端与服务端的数据通道被关闭了，这时服务端就会出现异常。服务端可以通过出异常来判断客户端下线了，所以可以用try...catch把读取客户端数据的代码套一起来，catch捕获到异常后，打印客户端下线。

```java
package com.itheima.p5_tcp2;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  目标：完成TCP通信快速入门-服务端开发：实现服务端反复发消息
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动成功-------");
        // 1、创建ServerSocket的对象，同时为服务端注册端口。
        ServerSocket serverSocket = new ServerSocket(8888);

        // 2、使用serverSocket对象，调用一个accept方法，等待客户端的连接请求
        Socket socket = serverSocket.accept();

        // 3、从socket通信管道中得到一个字节输入流。
        InputStream is = socket.getInputStream();

        // 4、把原始的字节输入流包装成数据输入流
        DataInputStream dis = new DataInputStream(is);

        while (true) {
            try {
                // 5、使用数据输入流读取客户端发送过来的消息
                String rs = dis.readUTF(); // 服务端在这里等客户端消息，一旦客户端端口离线，这里殉情报错
                System.out.println(rs);
            } catch (Exception e) {
                System.out.println(socket.getRemoteSocketAddress() + "离线了！");
                dis.close();
                socket.close();
                break;
            }
        }
    }
}
```



## 七、TCP通信-支持与多个客户端同时通信

上一个案例中我们写的服务端程序只能和一个客户端通信，如果有多个客户端连接服务端，此时服务端是不支持的。

为了让服务端能够支持多个客户端通信，就需要用到多线程技术。具体的实现思路如下图所示：每当有一个客户端连接服务端，在服务端这边就为Socket开启一条线程取执行读取数据的操作，来多少个客户端，就有多少条线程。按照这样的设计，服务端就可以支持多个客户端连接了。(e.g. 老板门口接待客人，把每波客人交给一个服务员)

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2015.40.52.png?raw=true" style="zoom:33%;" />



### 7.1 多线程改进

客户端

```java
package com.itheima.p6_tcp3;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *  目标：完成TCP通信快速入门-客户端开发：实现客户端可以反复的发消息出去
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1、创建Socket对象，并同时请求与服务端程序的连接。
        Socket socket = new Socket("127.0.0.1", 8888);
        // 2、从socket通信管道中得到一个字节输出流，用来发数据给服务端程序。
        OutputStream os = socket.getOutputStream();
        // 3、把低级的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请说：");
            String msg = sc.nextLine();

            // 一旦用户输入了exit，就退出客户端程序
            if("exit".equals(msg)){
                System.out.println("欢迎您下次光临！退出成功！");
                dos.close();
                socket.close();
                break;
            }
            // 4、开始写数据出去了
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}

```

服务端的主程序代码，如下：

```java
package com.itheima.p6_tcp3;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *  目标：完成TCP通信快速入门-服务端开发：要求实现与多个客户端同时通信。
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动成功-------");
        // 1、创建ServerSocket的对象，同时为服务端注册端口。
        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            // 2、使用serverSocket对象，调用一个accept方法，等待客户端的连接请求
            Socket socket = serverSocket.accept();

            System.out.println("有人上线了：" + socket.getRemoteSocketAddress());

            // 3、把这个客户端对应的socket通信管道，交给一个独立的线程负责处理。
            new ServerReaderThread(socket).start();
        }
    }
}


```



我们需要写一个服务端的读取数据的线程类，代码如下

```java
package com.itheima.p6_tcp3;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ServerReaderThread extends Thread{
    private Socket socket;
    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true){
                try {
                    String msg = dis.readUTF(); //对应的客户端如果离线，会出异常=>所以trycatch
                    System.out.println(msg);

                } catch (Exception e) {
                    System.out.println("有人下线了：" + socket.getRemoteSocketAddress());
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

```
-----服务端启动成功-------
有人上线了：/127.0.0.1:57924
有人上线了：/127.0.0.1:57933
111
2222
有人下线了：/127.0.0.1:57924
有人下线了：/127.0.0.1:57933
```

````
请说：
111
请说：
exit
欢迎您下次光临！退出成功！
````

````
请说：
2222
请说：
exit
欢迎您下次光临！退出成功！
````



## 八、TCP通信-综合案例

### 8.1 群聊



TCP通信-端口转发

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2016.12.35.png?raw=true" style="zoom:33%;" />

Client

```java
package com.itheima.p7_tcp4;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *  目标：完成TCP通信快速入门-客户端开发：实现客户端可以反复的发消息出去
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1、创建Socket对象，并同时请求与服务端程序的连接。
        Socket socket = new Socket("127.0.0.1", 8888);

        // 创建一个独立的线程，负责随时从socket中接收服务端发送过来的消息。
        new ClientReaderThread(socket).start();

        // 2、从socket通信管道中得到一个字节输出流，用来发数据给服务端程序。
        OutputStream os = socket.getOutputStream();
        // 3、把低级的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请说：");
            String msg = sc.nextLine();

            // 一旦用户输入了exit，就退出客户端程序
            if("exit".equals(msg)){
                System.out.println("欢迎您下次光临！退出成功！");
                dos.close();
                socket.close();
                break;
            }
            // 4、开始写数据出去了
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}

```



ClientReaderThread

```java
package com.itheima.p7_tcp4;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ClientReaderThread extends Thread{
    private Socket socket;
    public ClientReaderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true){
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                } catch (Exception e) {
                    System.out.println("自己下线了：" + socket.getRemoteSocketAddress());
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```



Server

```java
package com.itheima.p7_tcp4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *  目标：完成TCP通信快速入门-服务端开发：要求实现与多个客户端同时通信。
 */
public class Server {
    public static List<Socket> onLineSockets = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动成功-------");
        // 1、创建ServerSocket的对象，同时为服务端注册端口。
        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            // 2、使用serverSocket对象，调用一个accept方法，等待客户端的连接请求
            Socket socket = serverSocket.accept();
            onLineSockets.add(socket);
            System.out.println("有人上线了：" + socket.getRemoteSocketAddress());
            // 3、把这个客户端对应的socket通信管道，交给一个独立的线程负责处理。
            new ServerReaderThread(socket).start();
        }
    }
}

```



ServerReaderThread

```java
package com.itheima.p7_tcp4;

import java.io.*;
import java.net.Socket;

public class ServerReaderThread extends Thread{
    private Socket socket;
    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true){
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                    // 把这个消息分发给全部客户端进行接收。
                    sendMsgToAll(msg);
                } catch (Exception e) {
                    System.out.println("有人下线了：" + socket.getRemoteSocketAddress());
                    com.itheima.p7_tcp4.Server.onLineSockets.remove(socket);
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMsgToAll(String msg) throws IOException {
        // 发送给全部在线的socket管道接收。
        for (Socket onLineSocket : com.itheima.p7_tcp4.Server.onLineSockets) {
            OutputStream os = onLineSocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}

```



Output



Server

```
-----服务端启动成功-------
有人上线了：/127.0.0.1:62970
有人上线了：/127.0.0.1:62979
有人上线了：/127.0.0.1:62986
111
222
333
有人下线了：/127.0.0.1:62970
有人走了
```



Client1

```
请说：
111
请说：
111
222
333
exit
欢迎您下次光临！退出成功！
自己下线了：/127.0.0.1:8888
```



Client2

```
请说：
111
222
请说：
222
333
有人走了
请说：
有人走了
```



Client3

```
请说：
111
222
333
请说：
333
有人走了
```



### 8.2 BS架构



BS架构的基本原理

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2016.58.06.png?raw=true" style="zoom:33%;" />



在BS结构的程序中，浏览器和服务器通信是基于HTTP协议来完成的，浏览器给客户端发送数据需要按照HTTP协议规定好的数据格式发给服务端，服务端返回数据时也需要按照HTTP协议规定好的数据给是发给浏览器，只有这两双方才能完成一次数据交互。

- 服务端给客户端响应数据的数据格式（HTTP协议规定数据格式）如下图所示：左图是数据格式，右图是示例。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2017.01.12.png?raw=true" style="zoom:50%;" />





案例需求：

要求从浏览器中访问服务器，并立即让服务器响应一个很简单的网页给浏览器展示，网页内容就是“黑马程序员666”



#### 8.2.1 服务端程序

服务端的主程序

````java
package com.itheima.p8_tcp5;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *  目标：完成TCP通信快速入门-服务端开发：要求实现与多个客户端同时通信。
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动成功-------");
        // 1、创建ServerSocket的对象，同时为服务端注册端口。
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            // 2、使用serverSocket对象，调用一个accept方法，等待客户端的连接请求
            Socket socket = serverSocket.accept();

            System.out.println("有人上线了：" + socket.getRemoteSocketAddress());

            // 3、把这个客户端对应的socket通信管道，交给一个独立的线程负责处理。
            new ServerReaderThread(socket).start();
        }
    }
}

````



一个线程类，用于按照HTTP协议的格式返回数据

```java
package com.itheima.p8_tcp5;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ServerReaderThread extends Thread{
    private Socket socket;
    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        //  立即响应一个网页内容：“黑马程序员”给浏览器展示。
        try {
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os); // 用打印流容易换行
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=UTF-8");
            ps.println(); // 必须换行
            ps.println("<div style='color:red;font-size:120px;text-align:center'>黑马程序员666<div>");
            ps.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```



#### 8.2.2 服务端主程序用线程池改进

为了避免服务端创建太多的线程，可以把服务端用线程池改进，提高服务端的性能。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2017.13.18.png?raw=true" style="zoom:33%;" />





改写服务端的主程序，使用ThreadPoolExecutor创建一个线程池，每次接收到一个Socket就往线程池中提交任务就行。

````java
package com.itheima.p9_tcp6;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  目标：完成TCP通信快速入门-服务端开发：要求实现与多个客户端同时通信。
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动成功-------");
        // 1、创建ServerSocket的对象，同时为服务端注册端口。
        ServerSocket serverSocket = new ServerSocket(8080);

        // 创建出一个线程池，负责处理通信管道的任务。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(16 * 2, 16 * 2, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8) , Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        while (true) {
            // 2、使用serverSocket对象，调用一个accept方法，等待客户端的连接请求
            Socket socket = serverSocket.accept();

            // 3、把这个客户端对应的socket通信管道，交给一个独立的线程负责处理。
            pool.execute(new ServerReaderRunnable(socket));
        }
    }
}
````



写一个给浏览器响应数据的线程任务

```java
package com.itheima.p9_tcp6;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ServerReaderRunnable implements Runnable{
    private Socket socket;
    public ServerReaderRunnable(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        //  立即响应一个网页内容：“黑马程序员”给浏览器展示。
        try {
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=UTF-8");
            ps.println(); // 必须换行
            ps.println("<div style='color:red;font-size:120px;text-align:center'>黑马程序员666<div>");
            ps.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2017.27.45.png?raw=true)



# D21. Java高级

## 一、单元测试



### 1.1 单元测试快速入门



单元测试

- 就是针对最小的功能单元（方法），编写测试代码对其进行正确性测试。



咱们之前是怎么进行测试的呢？有啥问题？

比如说我们写了一个学生管理系统，有添加学生、修改学生、删除学生、查询学生等这些功能。要对这些功能这几个功能进行测试，我们是在main方法中编写代码来测试的。

但是在main方法中写测试代码有如下的几个问题，如下图所示：

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2017.42.23.png?raw=true)



Junit单元测试框架

- Junit是第三方公司开源出来的，用于对代码进行单元测试的工具（IDEA已经集成了junit框架）

相比于在main方法中测试有如下几个优点

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2017.45.50.png?raw=true)

案例

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2017.47.11.png?raw=true" style="zoom:33%;" />



先准备一个类，假设写了一个StringUtil工具类，代码如下

```java
package com.itheima.p1_junit;
/**
 * 字符串工具类
 */
public class StringUtil {
    public static void printNumber(String name){
        if(name == null){
            System.out.println(0);
            return; // 停掉方法
        }
        System.out.println("名字长度是：" + name.length());
    }

    /**
     * 求字符串的最大索引
     */
    public static int getMaxIndex(String data){
        if(data == null) {
            return -1;
        }
        return data.length();
    }


}
```



接下来，写一个测试类，测试StringUtil工具类中的方法能否正常使用。

```java
package com.itheima.p1_junit;

import org.junit.*;

/**
 * 测试类
 */
public class StringUtilTest {
    @Before
    public void test1(){
        System.out.println("---> test1 Before 执行了---------");
    }

    @BeforeClass
    public static void test11(){
        System.out.println("---> test11 BeforeClass 执行了---------");
    }

    @After
    public void test2(){
        System.out.println("---> test2 After 执行了---------");
    }

    @AfterClass
    public static void test22(){
        System.out.println("---> test22 AfterClass 执行了---------");
    }

    @Test // 测试方法
    public void testPrintNumber(){
        StringUtil.printNumber("admin");
        StringUtil.printNumber(null);
    }

    @Test // 测试方法
    public void testGetMaxIndex(){
        int index1 = StringUtil.getMaxIndex(null);
        System.out.println(index1);

        int index2 = StringUtil.getMaxIndex("admin");
        System.out.println(index2);

        // 断言机制assert：程序员可以通过预测业务方法的结果。
        Assert.assertEquals("方法内部有bug!", 4, index2);
    }
}

```



````
---> test11 BeforeClass 执行了---------
---> test1 Before 执行了---------
名字长度是：5
0
---> test2 After 执行了---------
---> test1 Before 执行了---------
-1
5
---> test2 After 执行了---------

java.lang.AssertionError: 方法内部有bug! 
Expected :4
Actual   :5
<Click to see difference>

---> test22 AfterClass 执行了---------
````



### 1.2 Junit框架的常用注解

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2018.37.05.png?raw=true" style="zoom:33%;" />

​	例子代码看上面1.1的



我们现在已经知道每一个注解的作用了，那他们有什么用呢？应用场景在哪里?

我们来看一个例子，假设我想在每个测试方法中使用Socket对象，并且用完之后，需要把Socket关闭。代码就可以按照下面的结构来设计

```java
public class StringUtilTest{
    private static Socket socket;
    @Before
    public void test1(){
        System.out.println("--> test1 Before 执行了");
    }
    @BeforeClass
    public static void test11(){
        System.out.println("--> test11 BeforeClass 执行了");
        //初始化Socket对象
        socket = new Socket();
    }
    @After
    public void test2(){
        System.out.println("--> test2 After 执行了");
    }
    @AfterCalss
    public static void test22(){
        System.out.println("--> test22 AfterCalss 执行了");
         //关闭Socket
        socket.close();
    }
}
```



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2020.52.44.png?raw=true" style="zoom:33%;" />



## 二、反射



反射（reflection）

- 反射就是：指的是加载类的字节码到内存，并以编程的方法解刨出类中的各个成分（成员变量、方法、构造器等）。





反射有啥用呢？其实反射是用来写框架用的，但是现阶段同学们对框架还没有太多感觉。为了方便理解，我给同学们看一个我们见过的例子：平时我们用IDEA开发程序时，用对象调用方法，IDEA会有代码提示，idea会将这个对象能调用的方法都给你列举出来，供你选择，如果下图所示

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2020.57.23.png?raw=true)



因为反射获取的是类的信息，那么反射的第一步首先获取到类才行。由于Java的设计原则是万物皆对象，获取到的类其实也是以对象的形式体现的，**叫字节码对象**，用Class类来表示。获取到字节码对象之后，再通过字节码对象就可以获取到类的组成成分了，这些组成成分其实也是对象，其中**每一个成员变量用Field类的对象来表示**、**每一个成员方法用Method类的对象来表示**，**每一个构造器用Constructor类的对象来表示**。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2021.00.29.png?raw=true)



### 2.1 获取类的字节码



反射的第一步：是将字节码加载到内存，我们需要获取到的字节码对象。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2021.03.27.png?raw=true)



获取Class对象的三种方式代码

```java
package com.itheima.p2_reflect;

/**
 * 目标：获取Class对象。
 */
public class Test1Class {
    public static void main(String[] args) throws Exception {
        Class c1 = Student.class;
        System.out.println(c1.getName()); // 全类名
        System.out.println(c1.getSimpleName()); // 简名：Student

        Class c2 = Class.forName("com.itheima.p2_reflect.Student");
        System.out.println(c1 == c2);

        Student s = new Student();
        Class c3 = s.getClass();
        System.out.println(c3 == c2);
    }
}

```



Student类

```java
package com.itheima.p2_reflect;

public class Student {
    private String name;
    private int age;
    private char sex;
    private double height;
    private String hobby;

    public Student() {
    }

    public Student(String name, int age, char sex, double height, String hobby) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.hobby = hobby;
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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}

```



```
com.itheima.p2_reflect.Student
Student
true
true
```



### 2.2 获取类的构造器



- Class提供了从类中获取构造器的方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2021.13.57.png?raw=true)



### 2.3 反射获取构造器的作用



获取到构造器后，有什么作用呢？

其实构造器的作用：**初始化对象并返回**。

这里我们需要用到如下的两个方法，注意：这两个方法时属于Constructor的，需要用Constructor对象来调用。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2021.32.04.png?raw=true)



获取构造器方法 + 作用介绍代码：

```java
package com.itheima.p2_reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 目标：掌握获取类的构造器，并对其进行操作。
 */
public class Test2Constructor {
    @Test
    public void testGetConstructors(){
        // 1、反射第一步：必须先得到这个类的Class对象
        Class c = Cat.class;
        // 2、获取类的全部构造器
        // Constructor[] constructors = c.getConstructors();
        Constructor[] constructors = c.getDeclaredConstructors();
        // 3、遍历数组中的每个构造器对象
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName() + "--->"
                    + constructor.getParameterCount());
        }
    }

    @Test
    public void testGetConstructor() throws Exception {
        // 1、反射第一步：必须先得到这个类的Class对象
        Class c = Cat.class;
        // 2、获取类的某个构造器：无参数构造器
        Constructor constructor1 = c.getDeclaredConstructor();
        System.out.println(constructor1.getName() + "--->"
                + constructor1.getParameterCount());
        constructor1.setAccessible(true); // 禁止检查访问权限
        Cat cat = (Cat) constructor1.newInstance();
        System.out.println(cat);

        AtomicInteger a;


        // 3、获取有参数构造器
        Constructor constructor2 =
                c.getDeclaredConstructor(String.class, int.class); // 一定要.class。有class才代表类型
        System.out.println(constructor2.getName() + "--->"
                + constructor2.getParameterCount());
        constructor2.setAccessible(true); // 禁止检查访问权限
        Cat cat2 = (Cat) constructor2.newInstance("叮当猫", 3);
        System.out.println(cat2);

    }
}
```



Cat类

````java
package com.itheima.p2_reflect;

public class Cat {
    public static int a;
    public static final String COUNTRY = "中国";
    private String name;
    private int age;

    public Cat(){
        System.out.println("无参数构造器执行了~~");
    }

    private Cat(String name, int age) {
        System.out.println("有参数构造器执行了~~");
        this.name = name;
        this.age = age;
    }

    private void run(){
        System.out.println("🐱跑的贼快~~");
    }

    public void eat(){
        System.out.println("🐱爱吃猫粮~");
    }

    private String eat(String name){
        return "🐱最爱吃:" + name;
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

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

````



````
com.itheima.p2_reflect.Cat--->0
com.itheima.p2_reflect.Cat--->2
com.itheima.p2_reflect.Cat--->0
无参数构造器执行了~~
Cat{name='null', age=0}
com.itheima.p2_reflect.Cat--->2
有参数构造器执行了~~
Cat{name='叮当猫', age=3}
````



### 2.4 反射获取成员变量&使用



- Class类中提供了获取成员变量的方法

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2021.41.23.png?raw=true)



- 获取到成员变量的作用：依然是赋值、取值。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2021.50.43.png?raw=true)



反射获取成员变量&使用代码

```java
package com.itheima.p2_reflect;

import com.itheima.p2_reflect.Cat;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 目标：掌握获取类的成员变量，并对其进行操作。
 */
public class Test3Field {
    @Test
    public void testGetFields() throws Exception {
        // 1、反射第一步：必须是先得到类的Class对象
        Class c = Cat.class;
        // 2、获取类的全部成员变量。
        Field[] fields = c.getDeclaredFields();
        // 3、遍历这个成员变量数组
        for (Field field : fields) {
            System.out.println(field.getName() +  "---> "+ field.getType());
        }
        // 4、定位某个成员变量
        Field fName = c.getDeclaredField("name");
        System.out.println(fName.getName() + "--->" + fName.getType());

        Field fAge = c.getDeclaredField("age");
        System.out.println(fAge.getName() + "--->" + fAge.getType());

        // 赋值
        Cat cat = new Cat();
        fName.setAccessible(true); // 禁止访问控制权限
        fName.set(cat, "卡菲猫");
        System.out.println(cat);

        // 取值
        String name = (String) fName.get(cat);
        System.out.println(name);
    }
}

```



```
a---> int
COUNTRY---> class java.lang.String
name---> class java.lang.String
age---> int
name--->class java.lang.String
age--->int
无参数构造器执行了~~
Cat{name='卡菲猫', age=0}
卡菲猫
```



### 2.5 反射获取成员方法



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2022.00.32.png?raw=true)



成员方法获取 & 使用代码

```java
package com.itheima.p2_reflect;

import com.itheima.p2_reflect.Cat;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 目标：掌握获取类的成员方法，并对其进行操作。
 */
public class Test4Method {
    @Test
    public void testGetMethods() throws Exception {
        //  1、反射第一步：先得到Class对象。
        Class c = Cat.class;
        // 2、获取类的全部成员方法。
        Method[] methods = c.getDeclaredMethods();
        // 3、遍历这个数组中的每个方法对象
        for (Method method : methods) {
            System.out.println(method.getName() + "--->"
                    + method.getParameterCount() + "---->"
                    + method.getReturnType());
        }
        //  4、获取某个方法对象
        Method run = c.getDeclaredMethod("run"); // 拿run方法，无参数的
        System.out.println(run.getName() + "--->"
                + run.getParameterCount() + "---->"
                + run.getReturnType());

        Method eat = c.getDeclaredMethod("eat", String.class);
        System.out.println(eat.getName() + "--->"
                + eat.getParameterCount() + "---->"
                + eat.getReturnType());

        Cat cat = new Cat();
        run.setAccessible(true); // 禁止检查访问权限
        Object rs = run.invoke(cat); // 调用无参数的run方法，用cat对象触发调用的。
        System.out.println(rs);

        eat.setAccessible(true); // 禁止检查访问权限
        String rs2 = (String) eat.invoke(cat, "鱼儿");
        System.out.println(rs2);
    }
}

```



```
getName--->0---->class java.lang.String
run--->0---->void
toString--->0---->class java.lang.String
setName--->1---->void
getAge--->0---->int
setAge--->1---->void
eat--->0---->void
eat--->1---->class java.lang.String
run--->0---->void
eat--->1---->class java.lang.String
无参数构造器执行了~~
🐱跑的贼快~~
null
🐱最爱吃:鱼儿
```



### 2.6 反射的应用



反射的作用

- 基本作用：可以得到一个类的全部成分然后操作。
- 可以破坏封装性。
- 最重要的用途：适合做Java的框架。基本上，主流的框架都会基于反射设计出一些通用的功能。



案例：使用反射做一个简易版的框架

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2022.19.14.png?raw=true)



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2022.19.52.png?raw=true" style="zoom:33%;" />



需求是让我们写一个框架，能够将任意一个对象的属性名和属性值写到文件中去。不管这个对象有多少个属性，也不管这个对象的属性名是否相同。

分析一下该怎么做

````
1.先写好两个类，一个Student类和Teacher类
2.写一个ObjectFrame类代表框本架
	在ObjectFrame类中定义一个saveObject(Object obj)方法，用于将任意对象存到文件中去
	参数：Object obj: 就表示要存入文件中的对象
	
3.编写方法内部的代码，往文件中存储对象的属性名和属性值
	1)参数obj对象中有哪些属性，属性名是什么实现值是什么，中有对象自己最清楚。
	2)接着就通过反射获取类的成员变量信息了（变量名、变量值）
	3)把变量名和变量值写到文件中去
````



创建一个测试类，在测试中类创建一个Student对象，创建一个Teacher对象，用ObjectFrame的方法把这两个对象所有的属性名和属性值写到文件中去。

```java
package com.itheima.p2_reflect;

import com.itheima.p2_reflect.Student;
import com.itheima.p2_reflect.Teacher;
import org.junit.Test;
/**
 * 目标：使用反射技术：设计一个保存对象的简易版框架。
 */
public class Test5Frame {
    @Test
    public void save() throws Exception {
        Student s1 = new Student("黑马吴彦祖", 45, '男', 185.3, "蓝球，冰球，阅读");
        Teacher t1 = new Teacher("播妞", 999.9);

        // 需求：把任意对象的字段名和其对应的值等信息，保存到文件中去。
        ObjectFrame.saveObject(s1);
        ObjectFrame.saveObject(t1);
    }
}

```



写一个ObjectFrame表示自己设计的框架，代码如下图所示

```java
package com.itheima.p2_reflect;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class ObjectFrame {
    // 目标：保存任意对象的字段和其数据到文件中去
    public static void saveObject(Object obj) throws Exception {
        PrintStream ps = new PrintStream(new FileOutputStream("/Users/Hardy/Desktop/JAVA/Code/javasepromax/junit-reflect-annotation-proxy-app/src/data.txt", true));
        // obj是任意对象，到底有多少个字段要保存。
        Class c = obj.getClass();
        String cName = c.getSimpleName();
        ps.println("---------------" + cName + "------------------------");
        // 2、从这个类中提取它的全部成员变量
        Field[] fields = c.getDeclaredFields();
        // 3、遍历每个成员变量。
        for (Field field : fields) {
            // 4、拿到成员变量的名字
            String name = field.getName();
            // 5、拿到这个成员变量在对象中的数据。
            field.setAccessible(true); // 禁止检查访问控制
            String value = field.get(obj) + "";
            ps.println(name + "=" + value);
        }
        ps.close();
    }
}

```



使用自己设计的框架，往文件中写入Student对象的信息和Teacher对象的信息。

先准备好Student类和Teacher类

```java
public class Student{
    private String name;
    private int age;
    private char sex;
    private double height;
    private String hobby;
}
```

```java
public class Teacher{
    private String name;
    private double salary;
}
```



data.txt

```
---------------Student------------------------
name=黑马吴彦祖
age=45
sex=男
height=185.3
hobby=蓝球，冰球，阅读
---------------Teacher------------------------
name=播妞
salary=999.9

```



## 三、注解

### 3.1 认识注解&定义注解



注解（Annotation）

- Java注解是代码中的特殊标记，比如@Override、@Test等，作用是：让其他程序根据注解信息决定怎么执行该程序。
- 注解不光可以用在方法上，还可以用在类上、变量上、构造器上等位置。

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2022.45.36.png?raw=true" style="zoom:33%;" />



自定义注解

- 就是自己定义注解

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2022.46.45.png?raw=true" style="zoom:33%;" /> 



特殊属性名：value

- 如果注解中只有一个value属性，使用注解时，value名称可以不写。



注解的原理

想要搞清楚注解本质是什么东西，我们可以把注解的字节码进行反编译，使用XJad工具进行反编译。经过对MyTest1注解字节码反编译我们会发现：

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2022.54.59.png?raw=true" style="zoom:33%;" /> 



注解介绍代码

定义好MyTest注解之后，我们可以使用MyTest注解在类上、方法上等位置做标记。注意使用注解时需要加@符号，如下

定义好MyTest2注解后，再将@MyTest2标记在类上，此时value属性名可以省略，代码如下

````java
package com.itheima.p3_annotation;

import com.itheima.p3_annotation.MyTest1;

@MyTest1(aaa="牛魔王", ccc={"HTML", "Java"})
// @MyTest2(value = "孙悟空")
//@MyTest2("孙悟空")
//@MyTest2(value = "孙悟空", age = 1000)
@MyTest2("孙悟空")
public class AnnotationTest1 {
    @MyTest1(aaa="铁扇公主", bbb=false, ccc={"Python", "前端", "Java"})
    public void test1(){

    }

    public static void main(String[] args) {

    }
}

````



MyTest1 Annotation

```java
package com.itheima.p3_annotation;

/**
 * 自定义注解
 */
public @interface MyTest1 {
    String aaa();
    boolean bbb() default true;
    String[] ccc();
}

```



MyTest2 Annotation

注意：注解的属性名如何是value的话，并且只有value没有默认值，使用注解时value名称可以省略。比如现在重新定义一个MyTest2注解

```java
package com.itheima.p3_annotation;

public @interface MyTest2 {
    String value(); // 特殊属性
    int age() default 23;
}

```



### 3.2 元注解



什么是元注解？

- 元注解是修饰注解的注解

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2023.02.28.png?raw=true" style="zoom:33%;" />



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2023.03.16.png?raw=true" style="zoom:33%;" />





```java
package com.itheima.p3_annotation;

/**
 * 目标：认识元注解，搞清楚元注解的作用。
 */
@MyTest3
public class AnnotationTest2 {

    // @MyTest3 用在变量上有错
    private String name;

    @MyTest3
    public void test(){

    }
}

```



MyTest3 Annotation

```java
package com.itheima.p3_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // 当前被修饰的注解只能用在类上，方法上。
@Retention(RetentionPolicy.RUNTIME) // 控制下面的注解一直保留到运行时
public @interface MyTest3 {
}

```



### 3.3 解析注解



什么是注解的解析？

- 就是判断类上、方法上、成员变量上是否存在注解，并把注解里的内容给解析出来。



如何解析注解？

- 指导思想：要解析谁上面的注解，就应该先拿到谁。
- 比如要解析类上面的注解，则应该先获取该类的Class对象，再通过Class对象解析其上面的注解。
- 比如要解析成员方法上的注解，则应该获取成员方法的Method对象，再通过Method对象解析其上面的注解。
- Class, Method, Field, Constructor，都实现了AnnotatedElement接口，它们都拥有解析注解的能力。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2023.34.32.png?raw=true)



解析注解案例

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-07%2023.35.25.png?raw=true" style="zoom:33%;" />



① 先定义一个MyTest4注解

```java
package com.itheima.p3_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest4 {
    String value();
    double aaa() default 100;
    String[] bbb();
}

```



② 定义有一个类Demo

```java
package com.itheima.p3_annotation;

import com.itheima.p3_annotation.MyTest3;
import com.itheima.p3_annotation.MyTest4;

@MyTest4(value = "蜘蛛精", aaa=99.5, bbb = {"至尊宝", "黑马"})
@MyTest3
public class Demo {
    @MyTest4(value = "孙悟空", aaa=199.9, bbb = {"紫霞", "牛夫人"})
    public void test1(){
    }
}

```



③ 写一个测试类AnnotationTest3解析Demo类上的MyTest4注解

```java
package com.itheima.p3_annotation;

import com.itheima.p3_annotation.Demo;
import com.itheima.p3_annotation.MyTest4;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 目标：掌握注解的解析。
 */
public class AnnotationTest3 {
    @Test
    public void parseClass(){
        // 1、先得到Class对象
        Class c = Demo.class;
        // 2、解析类上的注解
        // 判断类上是否包含了某个注解
        if(c.isAnnotationPresent(MyTest4.class)){
            MyTest4 myTest4 =
                    (MyTest4) c.getDeclaredAnnotation(MyTest4.class);
            System.out.println(myTest4.value());
            System.out.println(myTest4.aaa());
            System.out.println(Arrays.toString(myTest4.bbb()));
        }
    }

    @Test
    public void parseMethod() throws Exception {
        // 1、先得到Class对象
        Class c = Demo.class;
        Method m = c.getDeclaredMethod("test1");
        // 2、解析方法上的注解
        // 判断方法对象上是否包含了某个注解
        if(m.isAnnotationPresent(MyTest4.class)){
            MyTest4 myTest4 =
                    (MyTest4) m.getDeclaredAnnotation(MyTest4.class);
            System.out.println(myTest4.value());
            System.out.println(myTest4.aaa());
            System.out.println(Arrays.toString(myTest4.bbb()));
        }
    }
}

```



### 3.4 注解的应用场景



案例：模拟Junit框架

<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-08%2000.28.09.png?raw=true" style="zoom:33%;" />



第一步：先定义一个MyTest注解

```java
package com.itheima.p3_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 注解只能注解方法。
@Retention(RetentionPolicy.RUNTIME) // 让当前注解可以一直存活着。
public @interface MyTest {
}

```



第二步：写一个测试类AnnotationTest4，在类中定义几个被@MyTest注解标记的方法

```java
package com.itheima.p3_annotation;

import java.lang.reflect.Method;

/**
 * 目标：模拟Junit框架的设计。
 */
public class AnnotationTest4 {
    // @MyTest
    public void test1(){
        System.out.println("===test1====");
    }

    @MyTest
    public void test2(){
        System.out.println("===test2====");
    }

    @MyTest
    public void test3(){
        System.out.println("===test3====");
    }

    @MyTest
    public void test4(){
        System.out.println("===test4====");
    }

    public static void main(String[] args) throws Exception {
        AnnotationTest4 a = new AnnotationTest4();
        // 启动程序！
        // 1、得到Class对象
        Class c = AnnotationTest4.class;
        // 2、提取这个类中的全部成员方法
        Method[] methods = c.getDeclaredMethods();
        // 3、遍历这个数组中的每个方法，看方法上是否存在@MyTest注解，存在
        // 触发该方法执行。
        for (Method method : methods) {
            if(method.isAnnotationPresent(MyTest.class)){
                // 说明当前方法上是存在@MyTest，触发当前方法执行。
                method.invoke(a);
            }
        }
    }
}

```





## 四、动态代理



### 4.1 动态代理介绍、准备功能



假设现在有一个大明星叫杨超越，它有唱歌和跳舞的本领，作为大明星是要用唱歌和跳舞来赚钱的，但是每次做节目，唱歌的时候要准备话筒、收钱，再唱歌；跳舞的时候也要准备场地、收钱、再唱歌。杨超越越觉得我擅长的做的事情是唱歌，和跳舞，但是每次唱歌和跳舞之前或者之后都要做一些繁琐的事情，有点烦。于是杨超越就找个一个经济公司，请了一个代理人，代理杨超越处理这些事情，如果有人想请杨超越演出，直接找代理人就可以了。如下图所示

我们说杨超越的代理是中介公司派的，那中介公司怎么知道，要派一个有唱歌和跳舞功能的代理呢？

解决这个问题，Java使用的是接口，杨超越想找代理，在Java中需要杨超越实现了一个接口，接口中规定要唱歌和跳舞的方法。Java就可以通过这个接口为杨超越生成一个代理对象，只要接口中有的方法代理对象也会有。

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-08%2000.47.47.png?raw=true)



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-08%2001.32.06.png?raw=true)





我们就先把有唱歌和跳舞功能的接口，和实现接口的大明星类定义出来。

```java
package com.itheima.p4_proxy;

public interface Star {
    String sing(String name);
    void dance();
}

```



````java
package com.itheima.p4_proxy;

public class BigStar implements Star{
    private String name;

    public BigStar(String name) {
        this.name = name;
    }

    public String sing(String name){
        System.out.println(this.name + "正在唱：" + name);
        return "谢谢！谢谢！";
    }

    public void dance(){
        System.out.println(this.name  + "正在优美的跳舞~~");
    }
}

````



下面我们写一个为BigStar生成动态代理对象的工具类。这里需要用Java为开发者提供的一个生成代理对象的类叫Proxy类。

```java
package com.itheima.p4_proxy;

import com.itheima.p4_proxy.BigStar;
import com.itheima.p4_proxy.Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Star createProxy(BigStar bigStar){
       /* newProxyInstance(ClassLoader loader,
                Class<?>[] interfaces,
                InvocationHandler h)
                参数1：用于指定一个类加载器.加载生成的代理类
                参数2：指定生成的代理长什么样子，也就是有哪些方法
                参数3：用来指定生成的代理对象要干什么事情
                */
        // Star starProxy = ProxyUtil.createProxy(s);
        // starProxy.sing("好日子") starProxy.dance()
        Star starProxy = (Star) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                new Class[]{Star.class}, new InvocationHandler() {
                    @Override // 回调方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 代理对象要做的事情，会在这里写代码
                        if(method.getName().equals("sing")){
                            System.out.println("准备话筒，收钱20万");
                        }else if(method.getName().equals("dance")){
                            System.out.println("准备场地，收钱1000万");
                        }
                        return method.invoke(bigStar, args);
                    }
                });
        return starProxy;
    }
}

```



调用我们写好的ProxyUtil工具类，为BigStar对象生成代理对象

```java
package com.itheima.p4_proxy;

import com.itheima.p4_proxy.BigStar;
import com.itheima.p4_proxy.ProxyUtil;
import com.itheima.p4_proxy.Star;

public class Test {
    public static void main(String[] args) {
        BigStar s = new BigStar("杨超越");
        Star starProxy = ProxyUtil.createProxy(s);

        String rs = starProxy.sing("好日子");
        System.out.println(rs);

        starProxy.dance();
    }
}

```



运行测试类，结果如下

```
准备话筒，收钱20万
杨超越正在唱：好日子
谢谢！谢谢！
准备场地，收钱1000万
杨超越正在优美的跳舞~~
```



程序梳理

![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-08%2001.30.37.png?raw=true)



![](https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-08%2001.31.35.png?raw=true)



### 4.2 动态代理应用



<img src="https://github.com/HardyDLBC/JAVAnote/blob/main/JAVA%E5%85%A5%E9%97%A8%E9%BB%91%E9%A9%AC%E5%BE%90%E7%A3%8A/%E5%9B%BE%E7%89%87/%E6%88%AA%E5%B1%8F2024-01-08%2001.36.56.png?raw=true" style="zoom:33%;" />



现有如下代码

`````java
package com.itheima.p5_proxy2;
/**
 *  用户业务接口
 */
public interface UserService {
    // 登录功能
    void login(String loginName,String passWord) throws Exception;
    // 删除用户
    void deleteUsers() throws Exception;
    // 查询用户，返回数组的形式。
    String[] selectUsers() throws Exception;
}

`````

下面有一个UserService接口的实现类，下面每一个方法中都有计算方法运行时间的代码。先在UserService类中把计算耗时的代码删除，代码如下

```java
package com.itheima.p5_proxy2;

/**
 * 用户业务实现类（面向接口编程）
 */
public class UserServiceImpl implements UserService{
    @Override
    public void login(String loginName, String passWord) throws Exception {
        if("admin".equals(loginName) && "123456".equals(passWord)){
            System.out.println("您登录成功，欢迎光临本系统~");
        }else {
            System.out.println("您登录失败，用户名或密码错误~");
        }
        Thread.sleep(1000);
    }

    @Override
    public void deleteUsers() throws Exception{
        System.out.println("成功删除了1万个用户~");
        Thread.sleep(1500);
    }

    @Override
    public String[] selectUsers() throws Exception{

        System.out.println("查询出了3个用户");
        String[] names = {"张全蛋", "李二狗", "牛爱花"};
        Thread.sleep(500);

        return names;
    }
}

```

然后为UserService生成一个动态代理对象，在动态代理中调用目标方法，在调用目标方法之前和之后记录毫秒值，并计算方法运行的时间。代码如下

```java
package com.itheima.p5_proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static UserService createProxy(UserService userService){
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                new Class[]{UserService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        if(method.getName().equals("login") || method.getName().equals("deleteUsers")||
                               method.getName().equals("selectUsers")){
                            long startTime = System.currentTimeMillis();

                            Object rs = method.invoke(userService, args);

                            long endTime = System.currentTimeMillis();
                            System.out.println(method.getName() + "方法执行耗时：" + (endTime - startTime)/ 1000.0 + "s");
                            return rs;
                        }else {
                            Object rs = method.invoke(userService, args);
                            return rs;
                        }
                    }
                });
        return userServiceProxy;
    }
}

```

在测试类中为UserService创建代理对象

```java
package com.itheima.p5_proxy2;

import com.itheima.p5_proxy2.ProxyUtil;

import java.util.Arrays;

/**
 * 目标：使用动态代理解决实际问题，并掌握使用代理的好处。
 */
public class Test {
    public static void main(String[] args) throws Exception{
        // 1、创建用户业务对象。
        UserService userService = ProxyUtil.createProxy(new UserServiceImpl());

        // 2、调用用户业务的功能。
        userService.login("admin", "123456");
        System.out.println("----------------------------------------------------");

        userService.deleteUsers();
        System.out.println("----------------------------------------------------");

        String[] names = userService.selectUsers();
        System.out.println("查询到的用户是：" + Arrays.toString(names));
        System.out.println("----------------------------------------------------");

    }
}

```



```
您登录成功，欢迎光临本系统~
login方法执行耗时：1.006s
----------------------------------------------------
成功删除了1万个用户~
deleteUsers方法执行耗时：1.503s
----------------------------------------------------
查询出了3个用户
selectUsers方法执行耗时：0.506s
查询到的用户是：[张全蛋, 李二狗, 牛爱花]
----------------------------------------------------
```









# 快捷键



1. alt + Enter 自动修改解决bug
2. Ctrl + Alt + T 抓取异常
3. 增强for快捷输入 集合/数组名.for+回车

<font color= "blue">
