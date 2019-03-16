package com.example.seamlessshopping;

public class cartObject {
    private String cartImage;
    private String pnameCart;
    private String priceCart;
    private Integer quantityCart;
    private String marketName;
    private Integer transactionId;
    private Integer marketId;
    private Integer productId;

    public cartObject(){}

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public cartObject(String cartImage, String pnameCart, String priceCart , Integer quantityCart,Integer transactionId,Integer productId,Integer marketId, String marketName){
        this.cartImage=cartImage; this.transactionId=transactionId;
        this.productId=productId; this.marketId=marketId; this.marketName=marketName;
        this.pnameCart=pnameCart;
        this.priceCart=priceCart;
        this.quantityCart=quantityCart;
    }

    public String getCartImage() {
        return cartImage;
    }

    public void setCartImage(String cartImage) {
        this.cartImage = cartImage;
    }

    public String getPnameCart() {
        return pnameCart;
    }

    public void setPnameCart(String pnameCart) {
        this.pnameCart = pnameCart;
    }

    public String getPriceCart() {
        return priceCart;
    }

    public void setPriceCart(String priceCart) {
        this.priceCart = priceCart;
    }

    public Integer getQuantityCart() {
        return quantityCart;
    }

    public void setQuantityCart(Integer quantityCart) {
        this.quantityCart = quantityCart;
    }
}
