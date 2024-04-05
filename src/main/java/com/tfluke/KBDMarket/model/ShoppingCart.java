package com.tfluke.KBDMarket.model;

import com.tfluke.KBDMarket.model.keyboard.Keyboard;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class ShoppingCart {
//    collections of ids
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//
//    @ManyToMany(mappedBy = "shoppingCarts",fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
//    private Set<Switches> switches;
//
//    @ManyToMany(mappedBy = "shoppingCarts",fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
//    private Set<Keyboard> keyboards;
//
//    @ManyToMany(mappedBy = "shoppingCarts",fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
//    private Set<Keycaps> keycaps;
//
//    @ManyToMany(mappedBy = "shoppingCarts",fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
//    private Set<Deskmat> deskmats;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//    public Set<Switches> getSwitches() {
//        return switches;
//    }
//    public void setSwitches(Set<Switches> switches) {
//        this.switches = switches;
//    }
//
//    public Set<Keyboard> getKeyboards() {
//        return keyboards;
//    }
//
//    public void setKeyboards(Set<Keyboard> keyboards) {
//        this.keyboards = keyboards;
//    }
//
//    public Set<Keycaps> getKeycaps() {
//        return keycaps;
//    }
//
//    public void setKeycaps(Set<Keycaps> keycaps) {
//        this.keycaps = keycaps;
//    }
//
//    public Set<Deskmat> getDeskmats() {
//        return deskmats;
//    }
//
//    public void setDeskmats(Set<Deskmat> deskmats) {
//        this.deskmats = deskmats;
//    }
}
