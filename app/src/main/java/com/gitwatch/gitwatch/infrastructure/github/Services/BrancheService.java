package com.gitwatch.gitwatch.infrastructure.github.Services;

import com.gitwatch.gitwatch.core.Domain.IBrancheService;
import com.gitwatch.gitwatch.core.Domain.Model.Branch;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AsyncJsonTask;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.BrancheFactory;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.UserFactory;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bziswn on 23.05.2017.
 */

public class BrancheService implements IBrancheService {
    @Override
    public List getByRepository(String repoId) throws JSONException {
        String url = "https://api.github.com/repositories/" + repoId + "/branches?per_page=100";
        String json = "";
        try {
            json = new AsyncJsonTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        List<Branch> branches = BrancheFactory.getListFromJson(json);
        for (Branch branch: branches){
            branch.setCommitCount(new CommitService().getByRepoAndBranche(repoId, branch.getName()));
        }
        return branches;
    }

    @Override
    public Object getById(long id) throws JSONException {
        return null;
    }
}
