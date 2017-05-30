package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IGitHubService {
    Object getById(long id) throws JSONException;
}
