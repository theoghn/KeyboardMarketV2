package com.tfluke.KBDMarket.model;

import jakarta.persistence.*;

@Entity
public class Images {
    @Id
    @SequenceGenerator(
            name = "images_id_sequence",
            sequenceName = "images_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "images_id_sequence"
    )
    private Integer id;
    private String link;


    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setImageLink(String link) {
        this.link = link;
    }


}
