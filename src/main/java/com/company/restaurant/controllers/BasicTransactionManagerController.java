package com.company.restaurant.controllers;

import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by Yevhen on 07.06.2016.
 */
public class BasicTransactionManagerController {
    private PlatformTransactionManager txManager;

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }
}
