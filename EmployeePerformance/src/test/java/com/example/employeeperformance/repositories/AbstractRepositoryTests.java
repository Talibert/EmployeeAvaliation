package com.example.employeeperformance.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractRepositoryTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void resetID() {
        jdbcTemplate.execute("ALTER TABLE changes_registry ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE employee_performance ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE employee ALTER COLUMN id RESTART WITH 1");
    }

    public abstract void limpaBancos();
}