package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;

public interface IUserService extends IGitHubService{
    List getByKeywords(String name) throws JSONException;
}
