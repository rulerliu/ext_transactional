package com.transaction.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * 类说明
 *
 * @author wangyu
 * @version 1.0
 * @date $2018/11/15$ $11:12$
 */
@Component
@Scope("prototype")
public class TransactionUtils {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionStatus transactionStatus;

    /**
     * 方法说明
     *
     * @param
     * @return void
     * @throws
     * @author wangyu
     * @version 1.0
     * @date 2018/11/15 11:13
     */
    public TransactionStatus begin() {
        // 开启事务 ，设置默认事务隔离级别
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        //System.out.println("开启事务...");
        return transactionStatus;
    }//  事务

    public void commit(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.commit(transactionStatus);
    }//  提交事务

    /**
     * 方法说明 事务回滚
     *
     * @param
     * @return void
     * @throws
     * @author wangyu
     * @version 1.0
     * @date 2018/11/15 11:16
     */
    public void rollback(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.rollback(transactionStatus);
    }

    public TransactionStatus gteTransactionStatus() {
        return transactionStatus;
    }
}
