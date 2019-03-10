package com.example.seamlessshopping;

public class paymentHistoryObject {
    private String recieptNumber;
    private String dateOfPurchase;
    private String marketname;
    private String totalPrice ;
    public paymentHistoryObject(){}
    public paymentHistoryObject(String dateOfPurchase , String totalPrice, String recieptNumber,String marketname)
    {
        this.dateOfPurchase=dateOfPurchase;
        this.totalPrice=totalPrice;
        this.recieptNumber=recieptNumber;
        this.marketname=marketname;
    }

    public void setMarketname(String marketname) {
        this.marketname = marketname;
    }

    public String getMarketname() {
        return marketname;
    }

    public void setRecieptNumber(String recieptNumber) {
        this.recieptNumber = recieptNumber;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getRecieptNumber() {
        return recieptNumber;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
