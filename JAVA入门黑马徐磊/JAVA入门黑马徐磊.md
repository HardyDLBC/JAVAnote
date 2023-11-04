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

- 确保一个类只有一个对象。（类比成对类做结扎，只能有一个对象）

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



































# 快捷键

alt + Enter 自动修改解决bug



