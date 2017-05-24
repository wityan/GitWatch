package com.gitwatch.gitwatch.infrastructure.github.ModelFactories;

import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.core.Domain.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bziswn on 23.05.2017.
 */

public class RepositoryFactory {
    public static Repository getObjectFromJson(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        return new Repository(obj.getLong("id"), obj.getString("name"), obj.getJSONObject("owner").getString("login"), obj.getString("description"));
    }

    public static List<Repository> getListFromJson(String json) throws JSONException {
        List<Repository> repositories = new ArrayList<Repository>();
        JSONArray jsonArray = jsonArray = new JSONArray(json);
        for (int i = 0, size = jsonArray.length(); i < size; i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            repositories.add(new Repository(obj.getLong("id"), obj.getString("name"), obj.getJSONObject("owner").getString("login")));
        }
        return repositories;
    }
}
