package com.tfluke.KBDMarket.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private Float price;
    private String name;
    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 1000)
    private String description;

    @ElementCollection
    private Set<String> imageLinks;

    public Set<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(Set<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product() {
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String description) {
        this.name = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer stock) {
        this.quantity = stock;
    }

    public Product(Float price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(price, product.price)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(quantity, product.quantity)) return false;
        if (!Objects.equals(description, product.description)) return false;
        return Objects.equals(imageLinks, product.imageLinks);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageLinks != null ? imageLinks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id + System.lineSeparator() +
                "\n price=" + price +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", imageLinks=" + imageLinks +
                '}';
    }
}
