package com.tfluke.KBDMarket.model;

import jakarta.persistence.Entity;


@Entity
public class Deskmat extends Product {

    private Integer length;
    private Integer width;
    private Integer thickness;

    public Deskmat() {}

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }


}
