package com.example.MVC_START.modelDTO;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private UUID user_id;
    private String type;

    public Product(UUID user_id, String type) {
        this.user_id = user_id;
        this.type = type;
    }
    public Product() {

    }

    public String getName() {
        return type;
    }

    public void setName(String type) {
        this.type = type;
    }

    public UUID getId() {
        return user_id;
    }

    public void setId(UUID id) {
        this.user_id = id;
    }


    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;

        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getName());
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + user_id +
                ", name='" + type + '\'' +
                '}';
    }
}