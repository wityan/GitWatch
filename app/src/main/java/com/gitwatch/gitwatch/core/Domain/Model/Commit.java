package com.gitwatch.gitwatch.core.Domain.Model;

import java.util.Date;

/**
 * Created by bziswn on 23.05.2017.
 */

public class Commit {
    private String committer;
    private String message;
    private Date date;

    public Commit(String committer, String message, Date date) {
        this.committer = committer;
        this.message = message;
        this.date = date;
    }

    public String getCommitter() {
        return committer;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
