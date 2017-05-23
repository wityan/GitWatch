package com.gitwatch.gitwatch.infrastructure.github.Services;

import android.app.ProgressDialog;

import com.gitwatch.gitwatch.core.Domain.IGitHubService;
import com.gitwatch.gitwatch.core.Domain.IRepositoryService;
import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AsyncJsonTask;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.RepositoryFactory;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.UserFactory;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bziswn on 23.05.2017.
 */

public class RepositoryService implements IRepositoryService {

    @Override
    public Object getById(long id) throws JSONException {
        String url = "https://api.github.com/repositories/"+id;
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
        String url = "https://api.github.com/search/repositories?q=" + name;
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return RepositoryFactory.getListFromJson(json);
    }
}
