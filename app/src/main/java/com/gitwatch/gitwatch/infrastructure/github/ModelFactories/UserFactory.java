package com.gitwatch.gitwatch.infrastructure.github.ModelFactories;

import com.gitwatch.gitwatch.core.Domain.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserFactory{

    public static User getObjectFromJson(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        return new User(obj.getLong("id"), obj.getString("login"), obj.getString("bio"));
    }

    public static List<User> getListFromJson(String json) throws JSONException {
        List<User> users = new ArrayList<User>();
        JSONObject object = new JSONObject(json);
        JSONArray jsonArray = jsonArray = new JSONArray(object.getString("items"));
        for (int i = 0, size = jsonArray.length(); i < size; i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            users.add(new User(obj.getLong("id"), obj.getString("login"), obj.getDouble("score")));
        }
        return users;
    }
}
