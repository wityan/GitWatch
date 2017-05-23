package com.gitwatch.gitwatch.infrastructure.github.Services;

import com.gitwatch.gitwatch.core.Domain.IGitHubService;
import com.gitwatch.gitwatch.core.Domain.IUserServiceInterface;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AsyncJsonTask;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.UserFactory;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bziswn on 23.05.2017.
 */

public class UserService implements IGitHubService, IUserServiceInterface {

    @Override
    public List getAll() throws ExecutionException, InterruptedException, JSONException {
        String url = "https://api.github.com/users?per_page=100";
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return UserFactory.getListFromJson(json);
    }

    @Override
    public List getByName(String name) throws JSONException {
        String url = "https://api.github.com/search/users?q=" + name;
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return UserFactory.getListFromJson(json);
    }

    @Override
    public Object getById(long id) throws JSONException {
        String url = "https://api.github.com/user/"+id;
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return UserFactory.getObjectFromJson(json);
    }
}
