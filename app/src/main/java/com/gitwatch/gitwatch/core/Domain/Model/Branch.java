package com.gitwatch.gitwatch.core.Domain.Model;

/**
 * Created by bziswn on 23.05.2017.
 */

public class Branch {
    private String name;
    private int commitCount;

    public Branch(String name) {
        this.name = name;
    }

    public void setCommitCount(int commitCount) {
        this.commitCount = commitCount;
    }

    public String getName() {
        return name;
    }

    public int getCommitCount() {
        return commitCount;
    }
}
