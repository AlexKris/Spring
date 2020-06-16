package com.spring.errorhandle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class SpringDataErrorhandleApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'a')");
        jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'b')");
    }

}
