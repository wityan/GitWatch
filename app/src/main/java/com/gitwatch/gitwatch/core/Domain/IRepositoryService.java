package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;

public interface IRepositoryService extends IGitHubService {
    List getByKeywords(String name) throws JSONException;
}
