package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;

public interface IBrancheService extends IGitHubService{
    List getByRepository(String repository) throws JSONException;
}
