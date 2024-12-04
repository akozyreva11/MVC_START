package com.example.MVC_START.model;

import java.util.Objects;
import java.util.UUID;

    public class Product {
        private UUID id;
        private String name;
        private String text;


        public Product (UUID id, String name, String text) {
            this.id = id;
            this.name = name;
            this.text = text;
        }

        @Override
        public String toString() {
            return "{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, text);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

              public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof Product product)) return false;

            return getId().equals(product.getId()) && getName().equals(product.getName()) && getText().equals(product.getText());
        }

    }
