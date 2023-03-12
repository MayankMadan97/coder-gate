package com.github.codergate.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String INSTALLATION_CREATED = "created";
    public static final String INSTALLATION_ACTION = "action";
    public static final String INSTALLATION_DELETED = "deleted";
    public static final String INSTALLATION_REPOSITORY_REMOVED = "removed";
    public static final String INSTALLATION_REPOSITORY_ADDED = "added";
    public static final String PUSH_EVENT = "push";
    public static final String PULL_REQUEST_EVENT = "pullRequest";

}
