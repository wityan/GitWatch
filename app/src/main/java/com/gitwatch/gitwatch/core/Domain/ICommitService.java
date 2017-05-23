package com.gitwatch.gitwatch.core.Domain;

import org.json.JSONException;

import java.util.List;

/**
 * Created by bziswn on 23.05.2017.
 */

public interface ICommitService extends IGitHubService{
    List getByRepoAndBranche(String repoId, String branche) throws JSONException;
    List getByRepo(String repoId) throws JSONException;
}
