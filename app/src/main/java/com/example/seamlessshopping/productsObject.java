package com.example.seamlessshopping;
public class productsObject {


    private String name;

    private int quantity;
    private int id;
    private String price;
    private String imageurl;
    private String marketfoodname;
    public productsObject(int id,String name , int quantity,String imageurl,String price){
        //    int id ,this.id=id;
        this.name=name;this.quantity=quantity;this.imageurl=imageurl;this.price=price;this.id=id;
    }

    public productsObject(String name , int quantity,String imageurl,String price,String marketfoodname){
        //    int id ,this.id=id;
        this.name=name;this.quantity=quantity;this.imageurl=imageurl;this.price=price;
        this.marketfoodname = marketfoodname;}




    public void setMarketfoodname(String marketfoodname) {
        this.marketfoodname = marketfoodname;
    }

    public String getMarketfoodname() {
        return marketfoodname;
    }

    public productsObject(){
        this.name=name;}
    public  Integer getQuantity() {
        return quantity;
    }

    public  void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getID() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public  void setPrice(String price) {
        this.price = price;
    }

    public void setID(Integer id) {
        this.id= id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public  void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}