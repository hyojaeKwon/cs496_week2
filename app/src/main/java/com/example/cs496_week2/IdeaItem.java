package com.example.cs496_week2;

public class IdeaItem {
    private int Iid;
    private String Ititle;
    private String Idescription;
    private String IauthorId;

    public IdeaItem(int Iid, String Ititle, String Idescription, String IauthorId) {
        this.Iid = Iid;
        this.Ititle = Ititle;
        this.Idescription = Idescription;
        this.IauthorId = IauthorId;
    }

    public int getIid() {
        return Iid;
    }

    public void setIid(int Iid) {
        this.Iid = Iid;
    }

    public String getItitle() {
        return Ititle;
    }

    public void setItitle(String Ititle) {
        this.Ititle = Ititle;
    }

    public String getIdescription() {
        return Idescription;
    }

    public void setIdescription(String Idescription) {
        this.Idescription = Idescription;
    }

    public String getIauthorId() {
        return IauthorId;
    }

    public void setIauthorId(String IauthorId) {
        this.IauthorId = IauthorId;
    }
}
