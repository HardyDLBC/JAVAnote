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
