package com.tfluke.KBDMarket;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Keyboard {
    @Id
    @SequenceGenerator(
            name = "keyboard_id_sequence",
            sequenceName = "keyboard_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "keyboard_id_sequence"
    )
    @Column(name = "kbd_id")
    private Integer id;
    private String layout;
    private String color;
    private Integer price;
    private String description;
    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_kbd_id",referencedColumnName = "kbd_id")
    This is a good way if you want to have objects one to many but i just want the links not anything else
    */
    @ElementCollection
    private List<String> imageLinks;

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imagesGroupId) {
        this.imageLinks = imagesGroupId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getLayout() {
        return layout;
    }

    public String getColor() {
        return color;
    }

    public Integer getPrice() {
        return price;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }



    public Keyboard(Integer id, String layout, String color, Integer price, List<String> imageLink, String description) {
        this.id = id;
        this.layout = layout;
        this.color = color;
        this.price = price;
        this.imageLinks = imageLink;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Keyboard\n{\n" +
                "    \"id\" : " + id +
                ",\n    \"layout\" : " + layout +
                ",\n    \"color\" : " + color +
                ",\n    \"price\" : " + price +
                ",\n    \"imagesGroupId\" : " + imageLinks +
                ",\n    \"description\" : " + description +
                "\n}";
    }

    public Keyboard() {
    }
}
