package com.gitwatch.gitwatch.infrastructure.github.Services;

import com.gitwatch.gitwatch.core.Domain.ICommitService;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AsyncJsonTask;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.CommitFactory;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.UserFactory;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bziswn on 23.05.2017.
 */

public class CommitService implements ICommitService{

    @Override
    public List getByRepoAndBranche(String repoId, String branche) throws JSONException {
        String url = "https://api.github.com/repositories/" + repoId + "/commits?sha=" + branche + "&per_page=500;";
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return CommitFactory.getListFromJson(json);
    }

    @Override
    public List getByRepo(String repoId) throws JSONException {
        String url = "https://api.github.com/repositories/" + repoId + "?per_page=500;";
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return CommitFactory.getListFromJson(json);
    }

    @Override
    public Object getById(long id) throws JSONException {
        return null;
    }
}
