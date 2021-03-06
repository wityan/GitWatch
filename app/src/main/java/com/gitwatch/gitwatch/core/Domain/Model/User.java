package com.gitwatch.gitwatch.core.Domain.Model;

public class User{

    // Constructor for list view
    public User(long id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Constructor for detail view
    public User(long id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    private long id;
    private String name;
    private double score;
    private String bio;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getBio() {
        return bio;
    }
}
