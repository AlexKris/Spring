package com.spring.springboot.transactiondeclarative.service;

import com.spring.springboot.transactiondeclarative.exception.RollbackException;

public interface FooService {
    void insertRecord();

    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollback() throws RollbackException;

    void invokeSupInsertThenRollback() throws RollbackException;

    void insertThenRollbackWithPropagation() throws RollbackException;

    void invokeInsertThenRollbackWithPropagation() throws Exception;
}
