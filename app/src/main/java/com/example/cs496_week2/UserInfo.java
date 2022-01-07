package com.example.cs496_week2;

import java.util.HashSet;

public class UserInfo {
    private String name;
    private HashSet<String> language;
    private String description;
    private String gitAddr;

    public UserInfo(String name, HashSet<String> language, String description, String gitAddr) {
        this.name = name;
        this.language = language;
        this.description = description;
        this.gitAddr = gitAddr;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public HashSet<String> getLanguage() { return language; }

    public void setLanguage(HashSet<String> language) { this.language = language; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getGitAddr() { return gitAddr; }

    public void setGitAddr(String gitAddr) { this.gitAddr = gitAddr; }
}
