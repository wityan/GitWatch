package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bziswn on 23.05.2017.
 */

public interface IGitHubService {
    Object getById(long id) throws JSONException;
}