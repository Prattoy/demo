package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            jdbcTemplate.execute("SELECT 1 FROM DUAL");
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            System.err.println("Database connection failed. Error: " + e.getMessage());
        }
    }

    public List<Map<String, Object>> executeQuery(String sql) {
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            System.err.println("Query execution failed. Error: " + e.getMessage());
            return null;
        }
    }
}
