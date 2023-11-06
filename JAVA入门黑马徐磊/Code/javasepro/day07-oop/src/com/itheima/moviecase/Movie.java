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
