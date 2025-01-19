package com.example.MVC_START.repositories;

import com.example.MVC_START.configuration.ProductMapper;
import com.example.MVC_START.modelDTO.OfferProduct;
import com.example.MVC_START.modelDTO.Product;
import com.example.MVC_START.modelDTO.Rules;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static com.example.MVC_START.modelDTO.OfferProduct.*;

@Repository
public class DynamicRepository {

    private final JdbcTemplate jdbcTemplate;
    private final OfferProduct offerProduct = new OfferProduct();
    private final RecommendationRulesRepository recommendationRulesRepository;

    public DynamicRepository(JdbcTemplate jdbcTemplate, RecommendationRulesRepository recommendationRulesRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.recommendationRulesRepository = recommendationRulesRepository;
    }

    public Collection<Product> getTransactionAmount(UUID user) {
        Collection<Product> products = getInvest500Dynamic(user);
        if (!products.isEmpty()) {
            return products;
        }
        products = getTopSavingDynamic(user);
        return !products.isEmpty() ? products : getSimpleLoanDynamic(user);
    }

    private Collection<Product> getInvest500Dynamic(UUID user) {
        Optional<Rules> userOf = recommendationRulesRepository.findByQuery("USER_OF");
        Optional<Rules> transactionSumCompareDepositWithdraw = recommendationRulesRepository.findByQuery("TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW");
        String[] argumentsTransactionSumCompareDepositWithdraw = transactionSumCompareDepositWithdraw.get().getArguments().split(", ");
        String sql = "SELECT p.id, p.name, ? AS SENTENCE_TEXT FROM transactions t " +
                "JOIN products p ON t.type = ? AND p.type IN ('SAVING') " +
                "WHERE t.user_id = ? GROUP BY p.name HAVING SUM(t.amount) ? ?";
        return jdbcTemplate.query(sql,
                new ProductMapper(),
                TEXT_INVEST_500,
                userOf.get().getArguments(),
                user,
                argumentsTransactionSumCompareDepositWithdraw[0],
                argumentsTransactionSumCompareDepositWithdraw[1]);
    }

    private Collection<Product> getTopSavingDynamic(UUID user) {
        Optional<Rules> userOf = recommendationRulesRepository.findByQuery("USER_OF");
        Optional<Rules> transactionSumCompare = recommendationRulesRepository.findByQuery("TRANSACTION_SUM_COMPARE");
        String[] argumentsTransactionSumCompare = transactionSumCompare.get().getArguments().split(", ");
        String sql = "WITH TransactionSums AS ( " +
                "SELECT " +
                "    SUM(CASE WHEN t.TYPE = ? THEN t.AMOUNT ELSE 0 END) AS total_deposit, " +
                "    SUM(CASE WHEN t.TYPE = 'WITHDRAW' THEN t.AMOUNT ELSE 0 END) AS total_withdraw " +
                "FROM TRANSACTIONS t " +
                "WHERE t.USER_ID = ? " +
                ") " +
                "SELECT p.ID, p.NAME, ? AS SENTENCE_TEXT " +
                "FROM PRODUCTS p " +
                ", TransactionSums ts " +
                "WHERE p.TYPE = ? " +
                "AND ((ts.total_deposit ? ?) OR (p.TYPE = 'SAVING' AND ts.total_deposit ? ?)) " +
                "AND (ts.total_deposit > ts.total_withdraw) " +
                "GROUP BY p.NAME;";
        return jdbcTemplate.query(sql,
                new ProductMapper(),
                argumentsTransactionSumCompare[1],
                user,
                TEXT_TOP_SAVING,
                userOf.get().getArguments(),
                argumentsTransactionSumCompare[2],
                argumentsTransactionSumCompare[3],
                argumentsTransactionSumCompare[2],
                argumentsTransactionSumCompare[3]
        );
    }

    private Collection<Product> getSimpleLoanDynamic(UUID user) {
        Optional<Rules> userOf = recommendationRulesRepository.findByQuery("USER_OF");
        Optional<Rules> transactionSumCompareDepositWithdraw = recommendationRulesRepository.findByQuery("TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW");
        Optional<Rules> transactionSumCompare = recommendationRulesRepository.findByQuery("TRANSACTION_SUM_COMPARE");
        String[] argumentsTransactionSumCompareDepositWithdraw = transactionSumCompareDepositWithdraw.get().getArguments().split(", ");
        String[] argumentsTransactionSumCompare = transactionSumCompare.get().getArguments().split(", ");
        String sql = "WITH TransactionSums AS ( " +
                "SELECT " +
                "    SUM(CASE WHEN t.TYPE = 'DEPOSIT' THEN t.AMOUNT ELSE 0 END) AS total_deposit, " +
                "    SUM(CASE WHEN t.TYPE = 'WITHDRAW' THEN t.AMOUNT ELSE 0 END) AS total_withdraw " +
                "    SUM(CASE WHEN t.TYPE = ? THEN t.AMOUNT ELSE 0 END) AS total " +
                "FROM TRANSACTIONS t " +
                "WHERE t.USER_ID = ? " +
                ") " +
                "SELECT p.ID, p.NAME, ? AS SENTENCE_TEXT " +
                "FROM PRODUCTS p " +
                ", TransactionSums ts " +
                "WHERE p.TYPE = ? " +
                "AND (p.TYPE = ? AND ts.total_deposit ? ts.total_withdraw) " +
                "AND (p.TYPE = ? AND ts.total ? ?) " +
                "GROUP BY p.NAME;";
        return jdbcTemplate.query(sql,
                new ProductMapper(),
                argumentsTransactionSumCompare[1],
                user,
                TEXT_SIMPLE_LOAN,
                userOf.get().getArguments(),
                argumentsTransactionSumCompareDepositWithdraw[0],
                argumentsTransactionSumCompareDepositWithdraw[1],
                argumentsTransactionSumCompare[0],
                argumentsTransactionSumCompare[2],
                argumentsTransactionSumCompare[3]
        );
    }
}