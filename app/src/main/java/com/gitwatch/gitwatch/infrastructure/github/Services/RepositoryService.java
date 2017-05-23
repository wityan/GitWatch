package com.gitwatch.gitwatch.infrastructure.github.Services;

import android.app.ProgressDialog;

import com.gitwatch.gitwatch.core.Domain.IGitHubService;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bziswn on 23.05.2017.
 */

public class RepositoryService implements IGitHubService {

    @Override
    public List getAll() throws ExecutionException, InterruptedException, JSONException {
        return null;
    }

    @Override
    public Object getById(long id) throws JSONException {
        return null;
    }
}
