package com.tfluke.KBDMarket.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Switches extends Product{
//    @ManyToMany
//    @JoinTable(
//            name = "cart_switches",
//            joinColumns = @JoinColumn(name = "switches_id"),
//            inverseJoinColumns = @JoinColumn(name = "cart_id"))
//    private Set<ShoppingCart> shoppingCarts;

    private String type;
//    in millions of clicks
    private Integer durability;
    private Boolean lubed;
//    x+-y gf
    private String operationForce;
//    ex:Pre-travel: 2.0±0.2 mm
    private String preTravel;
//    ex:Total Travel: 3.6±0.2 mm
    private String totalTravel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDurability() {
        return durability;
    }

    public void setDurability(Integer durability) {
        this.durability = durability;
    }

    public Boolean getLubed() {
        return lubed;
    }

    public void setLubed(Boolean lubed) {
        this.lubed = lubed;
    }

    public String getOperationForce() {
        return operationForce;
    }

    public void setOperationForce(String operationForce) {
        this.operationForce = operationForce;
    }

    public String getPreTravel() {
        return preTravel;
    }

    public void setPreTravel(String preTravel) {
        this.preTravel = preTravel;
    }

    public String getTotalTravel() {
        return totalTravel;
    }

    public void setTotalTravel(String totalTravel) {
        this.totalTravel = totalTravel;
    }

    public Switches() {
    }

//    public Set<ShoppingCart> getShoppingCarts() {
//        return shoppingCarts;
//    }
//
//    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
//        this.shoppingCarts = shoppingCarts;
//    }
}
