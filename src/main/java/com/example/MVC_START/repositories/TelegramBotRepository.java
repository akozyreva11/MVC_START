package com.example.MVC_START.repositories;

import com.example.MVC_START.configuration.UserMapper;
import com.example.MVC_START.modelDTO.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class TelegramBotRepository {

    private final JdbcTemplate jdbcTemplate;

    public TelegramBotRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<User> getUser(String username) {
        String sql = "SELECT u.id, u.username, u.frist_name, u.last_name FROM users u WHERE u.username = ?";
        return jdbcTemplate.query(sql, new UserMapper(), username);
    }
}