package com.gitwatch.gitwatch.core.Domain.Model;

public class Repository {
    private long id;
    private String name;
    private String owner;
    private String description;

    // Constructor for list view
    public Repository(long id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    // Constructor for detail view
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
