package com.example.MVC_START.modelDTO;

import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product_dynamic_rule")

public class ProductRules {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private UUID productId;
        private String text;

        public ProductRules(Long id, String name, UUID productId, String text) {
            this.id = id;
            this.name = name;
            this.productId = productId;
            this.text = text;
        }

        public ProductRules() {

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
            ProductRules that = (ProductRules) o;
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

