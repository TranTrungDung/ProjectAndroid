package com.example.myapplication.list;

public class rankList {
    private String name;

    private int sumprice;

    public rankList(String name, int sumprice) {
        this.name = name;

        this.sumprice = sumprice;
    }

    public String getName() {
        return name;
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
