package com.example.seamlessshopping;
public class categoriesObject {
    private String imageurl;
    private String idmarket;
  public categoriesObject(){}

    public categoriesObject(String imageurl) {
        this.imageurl = imageurl;

    }

    public String getIdmarket() {
        return idmarket;
    }

    public void setIdmarket(String idmarket) {
        this.idmarket = idmarket;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }


}