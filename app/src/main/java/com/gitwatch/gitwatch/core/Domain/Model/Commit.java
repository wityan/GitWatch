package com.gitwatch.gitwatch.core.Domain.Model;

public class Commit {
    private String id;
    private String committer;
    private String message;

    public Commit(String id, String committer, String message) {
        this.id = id;
        this.committer = committer;
        this.message = message;
    }

    public String getCommitter() {
        return committer;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

}
