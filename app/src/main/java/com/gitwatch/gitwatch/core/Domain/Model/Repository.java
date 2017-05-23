package com.gitwatch.gitwatch.core.Domain.Model;

/**
 * Created by bziswn on 23.05.2017.
 */

public class Repository {
    private long id;
    private String name;
    private String owner;
    private String description;

    public Repository(long id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public Repository(long id, String name, String owner, String description) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }
}
