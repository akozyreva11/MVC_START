package com.example.MVC_START.modelDTO;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Objects;
@Entity
public class TotalRules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "rule_id")
    private Rules ruleId;
    private Long total;

    public TotalRules(Long id, Rules ruleId, Long total) {
        this.id = id;
        this.ruleId = ruleId;
        this.total = total;
    }

    public TotalRules() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rules getRuleId() {
        return ruleId;
    }

    public void setRuleId(Rules ruleId) {
        this.ruleId = ruleId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalRules totalRule = (TotalRules) o;
        return Objects.equals(id, totalRule.id) && Objects.equals(ruleId, totalRule.ruleId) && Objects.equals(total, totalRule.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ruleId, total);
    }
}