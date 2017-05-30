package com.gitwatch.gitwatch.infrastructure.github.Services;

import com.gitwatch.gitwatch.core.Domain.ICommitService;
import com.gitwatch.gitwatch.infrastructure.github.ModelFactories.CommitFactory;
import com.gitwatch.gitwatch.core.Domain.Objects.GitHubToken;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CommitService implements ICommitService{

    @Override
    public List getByRepoAndBranch(String repoId, String branch) throws JSONException {
        String url = "https://api.github.com/repositories/" + repoId + "/commits?sha=" + branch + "&per_page=500&authorization_request=" + GitHubToken.getToken();
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return CommitFactory.getListFromJson(json);
    }

    @Override
    public int getByAmountByRepoAndBranch(String repoId, String branch) throws JSONException {
        String url = "https://api.github.com/repositories/" + repoId + "/commits?sha=" + branch + "&per_page=500&authorization_request=" + GitHubToken.getToken();
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return CommitFactory.getLengthOfJsonArray(json);
    }

    @Override
    public List getByRepo(String repoId) throws JSONException {
        String url = "https://api.github.com/repositories/" + repoId + "?per_page=500&authorization_request=" + GitHubToken.getToken();
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return CommitFactory.getListFromJson(json);
    }

    // Special case of IGitHubService, because Branch dosent has an id.
    @Override
    public Object getById(long id) throws JSONException {
        return null;
    }
}
