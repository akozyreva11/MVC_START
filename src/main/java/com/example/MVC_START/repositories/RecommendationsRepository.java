package com.example.MVC_START.repositories;

import com.example.MVC_START.modelDTO.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;
    String invest;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getRandomTransactionAmount(UUID user) {
        Integer result = jdbcTemplate.queryForObject(
                "SELECT amount FROM transactions t WHERE t.user_id = ? LIMIT 1",
                Integer.class,
                user);
        return result != null ? result : 0;
    }

//    public Collection<Product> getTransactionCheck(UUID idUser) {
//        if (invest = null)
//    }

    public List<Product> getInvest500(UUID user_id) {
        return jdbcTemplate.query(
                "SELECT DISTINCT t.user_id, p.type FROM public.TRANSACTIONS t INNER JOIN public.PRODUCTS p ON p.id=t.PRODUCT_ID  where  p.TYPE in('DEPOSIT','SAVING') and  (p.TYPE ='SAVING'and T.AMOUNT>=1000) AND P.TYPE !='INVEST' AND t.USER_ID='cd515076-5d8a-44be-930e-8d4fcb79f42d'",
                new BeanPropertyRowMapper<>(Product.class))
                ;

    }

}
//    private List<Product> topSaving (UUID user) {
//        return jdbcTemplate.query(
//                "SELECT DISTINCT p.*, t.* FROM TRANSACTIONS t INNER JOIN " +
//                        "PRODUCTS p ON p.id=t.PRODUCT_ID  where  " +
//                        "p.TYPE in('DEPOSIT','SAVING') and  (p.TYPE ='SAVING'" +
//                        "and T.AMOUNT>=1000) AND P.TYPE !='INVEST' AND t.USER_ID=?",
//                new BeanPropertyRowMapper<>(Product.class));
//    }




