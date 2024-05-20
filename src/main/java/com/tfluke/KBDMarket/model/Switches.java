package com.tfluke.KBDMarket.model;


import jakarta.persistence.*;


@Entity
public class Switches extends Product {

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

    @Override
    public String toString() {
        return "Switches\n{\n" +
                "    \"id\" : " + getId() +
                ",\n    \"price\" : " + getPrice() +
                ",\n    \"imagesGroupId\" : " + getImageLinks() +
                ",\n    \"description\" : " + getName() +
                ",\n    \"type\" : " + type +
                ",\n    \"durability\" : " + durability +
                ",\n    \"lubed\" : " + lubed +
                ",\n    \"operationForce\" : " + operationForce +
                ",\n    \"preTravel\" : " + preTravel +
                ",\n    \"totalTravel\" : " + totalTravel +
                "\n}";
    }
}
