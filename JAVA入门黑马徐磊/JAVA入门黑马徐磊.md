# D7. 面向对象基础



## 03. 对象在计算机中的执行原理

### 多个对象在计算机中的执行原理

以案例计算成绩为例：

1. 先运行Test类，把Test.class放到方法区里
2. main方法放到栈内存中运行
3. 创建学生对象时用到Student类，把Student.class放到方法区里
4. 变量s1放到栈内存中，但new出来的变量要放到堆内存中。 s1变量里面记住的是学生对象的地址



<img src="/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-28 21.24.48.png" style="zoom:;" />

补充：引用类型的变量

- s1变量中存储的是对象的地址，因此s1称为引用类型的变量



## 04. 类和对象的一些注意事项

<img src="/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-28 22.00.52.png" style="height:500px;" />

- 注意点5的原理 

![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-28 22.03.20.png)

- 注意点6的原理(堆内存中没有变量指向的变量会被java自动删除)

![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-28 22.05.06.png)

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

![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-28 22.37.47.png)

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

![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-29 16.43.04.png)



e.g. <font color = 'blue'>左侧调用，右上存储，右下处理</font>

![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-29 17.13.17.png)



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



![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-30 22.13.44.png)



### 补充：如何定义类

![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-30 23.31.11.png)





# D8. 常用API



## 1. 包



### 什么是包？

- 包是分门别类管理不同程序的，类似于文件夹，建包有利于程序的管理和维护
- 建包的语法格式 (IDEA是自动导入的) 告诉Student类编译以后放到哪个包里面

![](/Users/Hardy/Desktop/JAVA/Note/JAVA入门黑马徐磊/图片/截屏2023-10-31 00.52.16.png)
