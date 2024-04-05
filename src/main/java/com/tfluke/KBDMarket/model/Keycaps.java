package com.tfluke.KBDMarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Keycaps extends Product{
//    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
//    private ShoppingCart cart;
    private Integer numberOfKeys;
    private String material;
    private String profile;
    private String productionMethod;

    public Keycaps() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProductionMethod() {
        return productionMethod;
    }

    public void setProductionMethod(String productionMethod) {
        this.productionMethod = productionMethod;
    }
    public Integer getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(Integer number) {
        this.numberOfKeys = number;
    }

//    public ShoppingCart getCart() {
//        return cart;
//    }
//
//    public void setCart(ShoppingCart cart) {
//        this.cart = cart;
//    }
}
