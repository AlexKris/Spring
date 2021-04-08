package com.spring.declarative.service;

import com.spring.declarative.exception.RollbackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into foo (bar) values ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("insert into foo (bar) values ('BBB')");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }

    @Override
    public void invokeSupInsertThenRollback() throws RollbackException {
        /*
        同一个类内部，不带事务方法调用带事务方法，使用此方法可使事务生效
         */
        fooService.insertThenRollback();
    }

    @Override
//    @Transactional(rollbackFor = RollbackException.class, propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = RollbackException.class, propagation = Propagation.NESTED)
    public void insertThenRollbackWithPropagation() throws RollbackException {
        jdbcTemplate.execute("insert into foo (bar) values ('DDD')");
        throw new RollbackException();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void invokeInsertThenRollbackWithPropagation() {
        jdbcTemplate.execute("insert into foo (bar) values ('CCC')");
        try {
            fooService.insertThenRollbackWithPropagation();
        } catch (RollbackException e) {
            log.error("RollbackException", e);
        }
//        throw new RuntimeException();
    }
}
