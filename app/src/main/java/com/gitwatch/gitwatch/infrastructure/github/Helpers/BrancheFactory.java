package com.gitwatch.gitwatch.infrastructure.github.Helpers;

import com.gitwatch.gitwatch.core.Domain.Model.Branch;
import com.gitwatch.gitwatch.core.Domain.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bziswn on 23.05.2017.
 */

public class BrancheFactory {
    public static Branch getObjectFromJson(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        return new Branch(obj.getString("name"));
    }

    public static List<Branch> getListFromJson(String json) throws JSONException {
        List<Branch> branches = new ArrayList<Branch>();
        JSONObject object = new JSONObject(json);
        JSONArray jsonArray = jsonArray = new JSONArray(object.getString("items"));
        for (int i = 0, size = jsonArray.length(); i < size; i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            branches.add(new Branch(obj.getString("name")));
        }
        return branches;
    }
}
