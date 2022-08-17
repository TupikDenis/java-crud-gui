package com.example.lab11.dto;

public class Town {
    private long id;
    private String townName;

    public Town(){}

    public Town(long id, String townName){
        this.id = id;
        this.townName = townName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @Override
    public String toString() {
        return this.id + " " + this.townName;
    }
}
