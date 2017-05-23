package com.gitwatch.gitwatch.infrastructure.github.Services;

import com.gitwatch.gitwatch.core.Domain.ICommitService;

import org.json.JSONException;

import java.util.List;

/**
 * Created by bziswn on 23.05.2017.
 */

public class CommitService implements ICommitService{

    @Override
    public List getByBranche(String branche) {
        return null;
    }

    @Override
    public int getCommitCount(String brancheId) {
        return 0;
    }

    @Override
    public Object getById(long id) throws JSONException {
        return null;
    }
}
