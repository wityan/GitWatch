package com.gitwatch.gitwatch.infrastructure.github.Services;

import com.gitwatch.gitwatch.core.Domain.IRepositoryService;
import com.gitwatch.gitwatch.core.Domain.Objects.GitHubToken;
import com.gitwatch.gitwatch.infrastructure.github.ModelFactories.RepositoryFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RepositoryService implements IRepositoryService {

    @Override
    public Object getById(long id) throws JSONException {
        String url = "https://api.github.com/repositories/" + id + "?authorization_request=" + GitHubToken.getToken();
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return RepositoryFactory.getObjectFromJson(json);

    }

    @Override
    public List getByKeywords(String name) throws JSONException {
        String url = "https://api.github.com/search/repositories?q=" + name + "&per_page=100&authorization_request=" + GitHubToken.getToken();
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject object = new JSONObject(json);
        JSONArray jsonArray = jsonArray = new JSONArray(object.getString("items"));
        return RepositoryFactory.getListFromJson(jsonArray.toString());
    }

    public List getByUser(String username) throws JSONException {
        String url = "https://api.github.com/users/" + username + "/repos?authorization_request=" + GitHubToken.getToken();
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return RepositoryFactory.getListFromJson(json);

    }
}
