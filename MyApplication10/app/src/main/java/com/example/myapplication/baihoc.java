package com.example.myapplication;

import android.icu.text.TimeZoneFormat;

public class baihoc {
    private int id_pr;
    private String price;
    private String size;
    private String name;
    private String details;
    private int amount;
    private int image;




    public int getId_pr() {
        return id_pr;
    }

    public void setId_pr(int id_pr) {
        this.id_pr = id_pr;
    }

    public String getPrice() { return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int  getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public baihoc(int id_pr, String price, String size, String name, String details, int amount, int image) {
        this.id_pr = id_pr;
        this.price = price;
        this.size = size;
        this.name = name;
        this.details = details;
        this.amount = amount;
        this.image = image;
    }
}