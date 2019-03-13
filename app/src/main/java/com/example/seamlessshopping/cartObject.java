package com.example.seamlessshopping;

public class cartObject {
    private String cartImage;
    private String pnameCart;
    private String priceCart;
    private Integer quantityCart;
    public cartObject(){}
    public cartObject(String cartImage,String pnameCart, String priceCart , Integer quantityCart){
        this.cartImage=cartImage;
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
