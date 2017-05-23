package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;

/**
 * Created by bziswn on 23.05.2017.
 */

public interface IUserServiceInterface {
    List getByName(String name) throws JSONException;
}
