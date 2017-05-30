package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;

public interface ICommitService extends IGitHubService{
    List getByRepoAndBranch(String repoId, String branche) throws JSONException;

    List getByRepo(String repoId) throws JSONException;

    int getByAmountByRepoAndBranch(String repoId, String branche) throws JSONException;
}
