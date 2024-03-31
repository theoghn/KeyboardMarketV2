package com.tfluke.KBDMarket.model;

import jakarta.persistence.Entity;

@Entity
public class Keycaps extends Product{
    private Integer number;
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
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
