package com.example.MVC_START.modelDTO;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product_dynamic_rule")

public class ProductDynamicRules {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private UUID productId;
        private String text;

        public ProductDynamicRules(Long id, String name, UUID productId, String text) {
            this.id = id;
            this.name = name;
            this.productId = productId;
            this.text = text;
        }

        public ProductDynamicRules() {

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProductDynamicRules that = (ProductDynamicRules) o;
            return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(productId, that.productId) && Objects.equals(text, that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, productId, text);
        }

        @Override
        public String toString() {
            return "ProductRule{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", idProduct=" + productId +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

