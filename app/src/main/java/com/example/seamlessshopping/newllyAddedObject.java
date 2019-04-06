package com.example.seamlessshopping;

public class newllyAddedObject {
    private String name;
    private Integer quantity;
    private Integer id;
    private String price;
    private String imageurl;
    private String idmarket;
    private String marketfoodname;

    public newllyAddedObject(Integer productId, String name, Integer quantity, String imageurl, String price, String marketName, Integer marketID) {
    }

    public String getMarketfoodname() {
        return marketfoodname;
    }

    public void setMarketfoodname(String marketfoodname) {
        this.marketfoodname = marketfoodname;
    }



    public String getIdmarket() {
        return idmarket;
    }

    public void setIdmarket(String idmarket) {
        this.idmarket = idmarket;
    }

    public newllyAddedObject(String name, int quantity, String price, String imageurl, String idmarket,String marketfoodname) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imageurl = imageurl;
        this.idmarket=idmarket;
        this.marketfoodname=marketfoodname;
    }
    public newllyAddedObject(){
        this.name=name;}





    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
