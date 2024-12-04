package com.example.MVC_START.modelDTO;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
public class Recommendation {
    private UUID user_id;

    private List<Product> recommendations;

    public Recommendation(UUID user_id, List<Product> recommendations) {
        this.user_id = user_id;
        this.recommendations = recommendations;
    }

    public Recommendation() {
    }

    public List<Product> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Product> recommendations) {
        this.recommendations = recommendations;
    }

    public UUID getId() {
        return user_id;
    }

    public void setId(UUID id) {
        this.user_id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recommendation that = (Recommendation) o;
        return user_id == that.user_id && Objects.equals(recommendations, that.recommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, recommendations);
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "user_id=" + user_id +
                ", recommendations=" + recommendations +
                '}';
    }
}