package com.example.seamlessshopping;
public class categoriesObject {
    private String marketImage;
    private String idmarket;
    private String marketfoodname;
    private String categoryID;
    private String categoryName;
    private String categoryImage;

    public String getMarketImage() {
        return marketImage;
    }

    public void setMarketImage(String marketImage) {
        this.marketImage = marketImage;
    }

    public String getMarketfoodname() {
        return marketfoodname;
    }

    public void setMarketfoodname(String marketfoodname) {
        this.marketfoodname = marketfoodname;
    }



  public categoriesObject(){}



    public categoriesObject(String categoryID, String categoryName, String categoryImage) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }
    public categoriesObject(String marketfoodname,String marketImage) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getIdmarket() {
        return idmarket;
    }

    public void setIdmarket(String idmarket) {
        this.idmarket = idmarket;
    }




}