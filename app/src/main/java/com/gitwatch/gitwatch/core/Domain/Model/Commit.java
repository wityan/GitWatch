package com.gitwatch.gitwatch.core.Domain.Model;

import java.util.Date;

/**
 * Created by bziswn on 23.05.2017.
 */

public class Commit {
    private String id;
    private String committer;
    private String message;
    private String date;

    public Commit(String id, String committer, String message, String date) {
        this.id = id;
        this.committer = committer;
        this.message = message;
        this.date = date;
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

    public String getDate() {
        return date;
    }
}
