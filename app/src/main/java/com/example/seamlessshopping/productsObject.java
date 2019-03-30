package com.example.seamlessshopping;
public class productsObject {


    private  Integer productId;
    private String name;

    private int quantity;
    private int id;
    private String price;
    private String imageurl;
    private String marketfoodname;
    private Integer marketID;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMarketID() {
        return marketID;
    }

    public void setMarketID(Integer marketID) {
        this.marketID = marketID;
    }


    public productsObject(Integer productId,String name , Integer quantity,String imageurl,String price,String marketfoodname,Integer marketID){
        //    int id ,this.id=id;
        this.name=name;this.quantity=quantity;this.imageurl=imageurl;this.price=price; this.marketfoodname=marketfoodname;
        this.marketID=marketID;
        this.productId=productId;
    }

    public productsObject(String name , int quantity,String imageurl,String price,String marketfoodname){
        //    int id ,this.id=id;
        this.name=name;this.quantity=quantity;this.imageurl=imageurl;this.price=price;
        this.marketfoodname = marketfoodname;}

    public productsObject(Integer productId, String name, Integer quantity, String imageurl, String price) {
        this.name=name;this.quantity=quantity;this.imageurl=imageurl;this.price=price;
        this.productId = productId;
    }


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