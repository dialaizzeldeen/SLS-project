package com.example.seamlessshopping;

public class marketObject {
    private String marketImage;
    private String idmarket;
    private String marketfoodname;
    private String categoryID;

    public marketObject(String marketImage, String idmarket, String marketfoodname, String categoryID) {
        this.marketImage = marketImage;
        this.idmarket = idmarket;
        this.marketfoodname = marketfoodname;
        this.categoryID = categoryID;
    }

    public marketObject() {
    }

    public String getMarketImage() {
        return marketImage;
    }

    public void setMarketImage(String marketImage) {
        this.marketImage = marketImage;
    }

    public String getIdmarket() {
        return idmarket;
    }

    public void setIdmarket(String idmarket) {
        this.idmarket = idmarket;
    }

    public String getMarketfoodname() {
        return marketfoodname;
    }

    public void setMarketfoodname(String marketfoodname) {
        this.marketfoodname = marketfoodname;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }


}
