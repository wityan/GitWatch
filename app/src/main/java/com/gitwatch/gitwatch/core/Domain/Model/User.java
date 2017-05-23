package com.gitwatch.gitwatch.core.Domain.Model;

public class User{

    public User(long id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public User(long id, String name, String bio, int repositoryCount) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.repositoryCount = repositoryCount;
    }

    private long id;
    private String name;
    private double score;
    private String bio;
    private int repositoryCount;

}
