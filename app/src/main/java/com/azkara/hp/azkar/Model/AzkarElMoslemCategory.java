package com.azkara.hp.azkar.Model;

public class AzkarElMoslemCategory {
    public int id ;
    public String title;

    public AzkarElMoslemCategory() {
    }

    public AzkarElMoslemCategory(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
