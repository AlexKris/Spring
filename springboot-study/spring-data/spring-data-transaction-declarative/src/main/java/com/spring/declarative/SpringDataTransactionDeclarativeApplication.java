package com.spring.declarative;

import com.spring.declarative.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@Slf4j
public class SpringDataTransactionDeclarativeApplication implements CommandLineRunner {
    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataTransactionDeclarativeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("AAA {}",
                jdbcTemplate.queryForObject("select count(*) from foo where bar = 'AAA'", Long.class));
        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                    jdbcTemplate.queryForObject("select count(*) from foo where bar = 'BBB'", Long.class));
        }

        try {
            /*
            由于只是调用了有事务的方法，此方法本身没有事务，事务没有被回滚，没有启动真正的事务，只是使用了数据库的隐式事务
             */
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                    jdbcTemplate.queryForObject("select count(*) from foo where bar = 'BBB'", Long.class));
        }

        try {
            fooService.invokeSupInsertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                    jdbcTemplate.queryForObject("select count(*) from foo where bar = 'BBB'", Long.class));
        }

        fooService.invokeInsertThenRollbackWithPropagation();
        log.info("CCC {}",
                jdbcTemplate.queryForObject("select count(*) from foo where bar = 'CCC'", Long.class));
        log.info("DDD {}",
                jdbcTemplate.queryForObject("select count(*) from foo where bar = 'DDD'", Long.class));
    }
}
