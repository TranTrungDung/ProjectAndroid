package com.example.myapplication.list;

import android.icu.text.TimeZoneFormat;

import java.io.Serializable;

public class baihoc implements Serializable {
    private int id_pr;
    private String price;
    private String name;
    private String details;
    private int image;
    private String size;
    private int amount;

    public int getAmount() { return amount; }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAmount(int amount) { this.amount = amount; }

    public int getId_pr() {
        return id_pr;
    }

    public void setId_pr(int id_pr) {
        this.id_pr = id_pr;
    }

    public String getPrice() { return price; }

    public void setPrice(String price) {
        this.price = price;
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

    public int  getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public baihoc(int id_pr, String price,  String name, String details,  int image,int amount,String size) {
        this.id_pr = id_pr;
        this.price = price;
        this.name = name;
        this.details = details;
        this.image = image;
        this.amount = amount;
        this.size = size;
    }
}