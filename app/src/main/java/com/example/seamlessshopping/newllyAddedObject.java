package com.example.seamlessshopping;

public class newllyAddedObject {
    private String name;
    private int quantity;
    private int id;
    private String price;
    private String imageurl;

    public newllyAddedObject(String name, int quantity, String price, String imageurl) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imageurl = imageurl;
    }
    public newllyAddedObject(){
        this.name=name;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
