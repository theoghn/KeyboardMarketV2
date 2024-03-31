package com.tfluke.KBDMarket.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Keyboard extends Product {
//    @Id
//    @SequenceGenerator(
//            name = "keyboard_id_sequence",
//            sequenceName = "keyboard_id_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "keyboard_id_sequence"
//    )
//    @Column(name = "kbd_id")
//    private Integer id;
    private String brand;
    private String model;
    private String layout;
    private String color;
    private Integer size;
    private Boolean led;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_kbd_id",referencedColumnName = "kbd_id")
    This is a good way if you want to have objects one to many but I just want the links not anything else
    */


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

//    public void setImageLinks(List<String> imagesGroupId) {
//        this.imageLinks = imagesGroupId;
//    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) { this.brand = brand; }
    public String getBrand() { return brand; }

//    public List<String> getImageLinks() {
//        return imageLinks;
//    }

    public String getLayout() {
        return layout;
    }

    public String getColor() { return color; }


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
                ",\n    \"description\" : " + getDescription() +
                "\n}";
    }

    public Keyboard() {
    }
}
