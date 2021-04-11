package com.example.myapplication.list;

import java.util.Date;

public class ListMyOrder {
     private String date;
    private int id;
    private int price;

    public ListMyOrder(String date, int id, int price) {
        this.date = date;
        this.id = id;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
