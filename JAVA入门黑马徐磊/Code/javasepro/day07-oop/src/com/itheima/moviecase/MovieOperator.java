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
