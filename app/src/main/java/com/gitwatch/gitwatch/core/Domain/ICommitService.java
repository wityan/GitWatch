package com.gitwatch.gitwatch.core.Domain;

import java.util.List;

/**
 * Created by bziswn on 23.05.2017.
 */

public interface ICommitService extends IGitHubService{
    List getByBranche(String branche);
    int getCommitCount(String brancheId);
}
