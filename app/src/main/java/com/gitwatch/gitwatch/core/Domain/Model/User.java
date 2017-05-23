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

    public long id;
    public String name;
    public double score;
    public String bio;
    public int repositoryCount;

}
