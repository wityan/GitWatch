package com.gitwatch.gitwatch.infrastructure.github.ModelFactories;

import com.gitwatch.gitwatch.core.Domain.Model.Branch;
import com.gitwatch.gitwatch.core.Domain.Model.Commit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommitFactory {

    public static Commit getObjectFromJson(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONObject commiter = obj.getJSONObject("commit").getJSONObject("comitter");
        return new Commit(obj.getString("sha"), commiter.getString("name"), obj.getString("message"));
    }

    public static List<Commit> getListFromJson(String json) throws JSONException {
        List<Commit> commits = new ArrayList<Commit>();
        JSONArray jsonArray = jsonArray = new JSONArray(json);
        for (int i = 0, size = jsonArray.length(); i < size; i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            JSONObject commiter = obj.getJSONObject("commit").getJSONObject("committer");
            commits.add(new Commit(obj.getString("sha"), commiter.getString("name"), obj.getJSONObject("commit").getString("message")));
        }
        return commits;
    }

    public static int getLengthOfJsonArray(String json) throws JSONException {
        JSONArray jsonArray = jsonArray = new JSONArray(json);
        return jsonArray.length();
    }
}
