package com.example.cs496_week2;

import java.util.HashSet;

public class UserInfo {
    private String Uid;
    private String Uname;
    private String Usay;
    private String github;
    private HashSet<String> language;
    private int score;
    private int Utype;

    public UserInfo(String Uid, String Uname, String Usay, String github, HashSet<String> language, int score, int Utype) {
        this.Uid = Uid;
        this.Uname = Uname;
        this.Usay = Usay;
        this.github = github;
        this.language = language;
        this.score = score;
        this.Utype = Utype;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUtype() {
        return Utype;
    }

    public void setUtype(int Utype) {
        this.Utype = Utype;
    }
}
