package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;

/**
 * Created by bziswn on 23.05.2017.
 */

public interface IUserService extends IGitHubService{
    List getByKeywords(String name) throws JSONException;
}
