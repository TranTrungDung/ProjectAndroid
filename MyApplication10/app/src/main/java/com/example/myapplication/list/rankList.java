package com.example.myapplication.list;

public class rankList {
    private String name;
    private int id;
    private int sumprice;

    public rankList(String name,int id, int sumprice) {
        this.name = name;
        this.id = id;
        this.sumprice = sumprice;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumprice() {
        return sumprice;
    }

    public void setSumprice(int sumprice) {
        this.sumprice = sumprice;
    }
}
