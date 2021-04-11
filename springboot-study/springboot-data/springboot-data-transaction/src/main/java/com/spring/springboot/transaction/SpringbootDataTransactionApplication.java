package com.spring.springboot.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootApplication
@Slf4j
public class SpringbootDataTransactionApplication implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataTransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 编程式事务
        log.info("count before transaction: {}", getCount());// 0
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("insert into foo (id, bar) values (1, 'aaa')");
                log.info("count in transaction: {}", getCount());// 1
                // 设置此事务只能回滚
                transactionStatus.setRollbackOnly();
            }
        });
        log.info("count after transaction: {}", getCount());// 0
    }

    private long getCount() {
        return (long) jdbcTemplate.queryForList("select count(*) as cnt from foo").get(0).get("cnt");
    }
}
