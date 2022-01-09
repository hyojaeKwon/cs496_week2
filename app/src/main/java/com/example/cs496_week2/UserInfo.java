package com.example.cs496_week2;

import java.util.HashSet;

public class UserInfo {
    private String Uid;
    private String Uname;
    private String Usay;
    private String github;
    private HashSet<String> language;

    public UserInfo(String Uid, String Uname, String Usay, String github, HashSet<String> language) {
        this.Uid = Uid;
        this.Uname = Uname;
        this.Usay = Usay;
        this.github = github;
        this.language = language;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    public String getUsay() {
        return Usay;
    }

    public void setUsay(String Usay) {
        this.Usay = Usay;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public HashSet<String> getLanguage() {
        return language;
    }

    public void setLanguage(HashSet<String> language) {
        this.language = language;
    }
}
