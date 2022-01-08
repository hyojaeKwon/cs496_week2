package com.example.cs496_week2;

public class IdeaItem {
    private String idea_name;
    private String person_name;
    private int count;

    public IdeaItem(String idea_name, String person_name, int count) {
        this.idea_name = idea_name;
        this.person_name = person_name;
        this.count = count;
    }

    public String getIdeaName() {
        return idea_name;
    }

    public void setIdeaName(String idea_name) {
        this.idea_name = idea_name;
    }

    public String getPersonName() {
        return person_name;
    }

    public void setPersonName(String person_name) {
        this.person_name = person_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
