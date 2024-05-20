package com.tfluke.KBDMarket.model;

import jakarta.persistence.*;

@Entity
public class Keyboard extends Product {

    private String brand;
    private String model;
    private String layout;
    private String color;
    private Integer size;
    private Boolean led;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getLed() {
        return led;
    }

    public void setLed(Boolean led) {
        this.led = led;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }


    public String getLayout() {
        return layout;
    }

    public String getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "Keyboard\n{\n" +
                "    \"id\" : " + getId() +
                ",\n    \"brand\" : " + brand +
                ",\n    \"model\" : " + model +
                ",\n    \"led\" : " + led +
                ",\n    \"size\" : " + size +
                ",\n    \"layout\" : " + layout +
                ",\n    \"color\" : " + color +
                ",\n    \"price\" : " + getPrice() +
                ",\n    \"imagesGroupId\" : " + getImageLinks() +
                ",\n    \"description\" : " + getName() +
                "\n}";
    }

    public Keyboard() {
    }
}
