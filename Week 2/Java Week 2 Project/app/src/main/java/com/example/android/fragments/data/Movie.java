package com.scottwaite.android.fragments.data;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import android.os.Bundle;

public class Contact {

    public static final String MOVIE_NAME = "contactName";
    public static final String IMAGE_RESOURCE = "imageResource";
    public static final String PRICE = "price";
    public static final String INSTRUCTIONS = "instructions";

    private String contactName;
    private int imageResource;
    private String instructions;
    private double price;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //	Used when creating the data object
    public Contact(String id, int imageResource, double price, String instructions) {
        this.contactName = id;
        this.imageResource = imageResource;
        this.price = price;
        this.instructions = instructions;
    }

    public Contact(Bundle b) {
        if (b != null) {
            this.contactName = b.getString(MOVIE_NAME);
            this.imageResource = b.getInt(IMAGE_RESOURCE);
            this.price = b.getDouble(PRICE);
            this.instructions = b.getString(INSTRUCTIONS);
        }
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(MOVIE_NAME, this.contactName);
        b.putInt(IMAGE_RESOURCE, this.imageResource);
        b.putDouble(PRICE, this.price);
        b.putString(INSTRUCTIONS, this.instructions);
        return b;
    }

    @Override
    public String toString() {
        return contactName;
    }

}

