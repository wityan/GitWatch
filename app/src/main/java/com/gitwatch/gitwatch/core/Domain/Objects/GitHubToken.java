package com.gitwatch.gitwatch.core.Domain.Objects;

public class GitHubToken {

    // The GitHubToken is used by the IGitHubServices. the GitHub API provides unlimitted access only if you are
    // Authenticated, so we have to authenticate with a Token. The current Token is generated by Nino Ziswiler.
    // If something goes wrong pleas Contact him on nino1999@bluewin.ch or generate and add your own GitHub Token here
    private final static String token = "NoTokenYouNoob!!";

    public static String getToken() {
        return token;
    }
}
