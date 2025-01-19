package com.example.MVC_START.repositories;

import com.example.MVC_START.configuration.ProductMapper;
import com.example.MVC_START.modelDTO.OfferProduct;
import com.example.MVC_START.modelDTO.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

import static com.example.MVC_START.modelDTO.OfferProduct.*;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final OfferProduct offerText = new OfferProduct();


    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getRandomTransactionAmount(UUID user) {
        Integer result = jdbcTemplate.queryForObject(
                "SELECT amount FROM transactions t WHERE t.user_id = ? LIMIT 1",
                Integer.class, user);
        return result != null ? result : 0;
    }

    public Collection<Product> getTransactionAmount(UUID user) {
        Collection<Product> products = getInvest500(user);
        if (!products.isEmpty()) {
            return products;
        }
        products = getTopSaving(user);
        return !products.isEmpty() ? products : getSimpleLoan(user);

    }

    private Collection<Product> getInvest500(UUID user) {
        String sql = "SELECT p.id, p.name, ? AS SENTENCE_TEXT FROM transactions t " +
                "JOIN products p ON t.type = 'DEPOSIT' AND p.type IN ('SAVING') " +
                "WHERE t.user_id = ? GROUP BY p.name HAVING SUM(t.amount) > ?";
        return jdbcTemplate.query(sql, new ProductMapper(), TEXT_INVEST_500, user, 1000);


    }

    private Collection<Product> getTopSaving(UUID user) {
        String sql = "WITH TransactionSums AS ( " +
                "SELECT " +
                "    SUM(CASE WHEN t.TYPE = 'DEPOSIT' THEN t.AMOUNT ELSE 0 END) AS total_deposit, " +
                "    SUM(CASE WHEN t.TYPE = 'WITHDRAW' THEN t.AMOUNT ELSE 0 END) AS total_withdraw " +
                "FROM TRANSACTIONS t " +
                "WHERE t.USER_ID = ? " +
                ") " +
                "SELECT p.ID, p.NAME, ? AS SENTENCE_TEXT " +
                "FROM PRODUCTS p " +
                ", TransactionSums ts " +
                "WHERE p.TYPE = 'DEBIT' " +
                "AND ((ts.total_deposit >= 50000) OR (p.TYPE = 'SAVING' AND ts.total_deposit >= 50000)) " +
                "AND (ts.total_deposit > ts.total_withdraw) " +
                "GROUP BY p.NAME;";

        return jdbcTemplate.query(sql, new ProductMapper(), user, TEXT_TOP_SAVING);
    }

    private Collection<Product> getSimpleLoan(UUID user) {
        String sql = "WITH TransactionSums AS ( " +
                "SELECT " +
                "    SUM(CASE WHEN t.TYPE = 'DEPOSIT' THEN t.AMOUNT ELSE 0 END) AS total_deposit, " +
                "    SUM(CASE WHEN t.TYPE = 'WITHDRAW' THEN t.AMOUNT ELSE 0 END) AS total_withdraw " +
                "FROM TRANSACTIONS t " +
                "WHERE t.USER_ID = ? " +
                ") " +
                "SELECT p.ID, p.NAME, ? AS SENTENCE_TEXT " +
                "FROM PRODUCTS p " +
                ", TransactionSums ts " +
                "WHERE p.TYPE != 'CREDIT' " +
                "AND (p.TYPE = 'DEBIT' AND ts.total_deposit > ts.total_withdraw) " +
                "AND (p.TYPE = 'DEBIT' AND ts.total_withdraw > 100000) " +
                "GROUP BY p.NAME;";

        return jdbcTemplate.query(sql, new ProductMapper(), user, TEXT_SIMPLE_LOAN);
    }

}