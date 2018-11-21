package com.transaction.aop;

/**
 * Created by Administrator on 2018/11/15 0015.
 */

import com.transaction.annotation.ExtTransaction;
import com.transaction.utils.TransactionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

/**
 *
 * 方法说明 环绕
 * @version 1.0
 * @date 2018/11/15 14:12
 * @return java.lang.Object
 * @exception
 */

// 定义切面可以让事务注解可以生效
@Aspect
@Component
public class AopExtTransaction {
    @Autowired
    private TransactionUtils transactionUtils;

    //@Pointcut("execution(public * com.transaction.service.*.*(..))")
    @Pointcut("@annotation(com.transaction.annotation.ExtTransaction)")
    public void rlAop() {
    }

    // 使用异常通知 接受回滚事务
    @AfterThrowing("rlAop()")
    public void afterThrowing(JoinPoint point) {
        System.out.println("afterThrowing");
        // 获取当前事务状态
        TransactionStatus transaction = transactionUtils.gteTransactionStatus();
        if (transaction != null)
            transactionUtils.rollback(transaction);
    }

    // 事务底层原理 采用那些通知？环绕和异常即可 注意单例线程安全问题
    // 可以使用环绕通知对方法进行开启事务和提交事务 异常通知当程序抛出异常的情况下，进行回滚
    @Around("rlAop()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around");
        // 1.判断方法上面是否有加上事务注解
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        ExtTransaction extTransaction = methodSignature.getMethod().getDeclaredAnnotation(ExtTransaction.class);
        if (extTransaction == null) {// 方法上面没有加上注解执行原有方法
            return pjp.proceed();
        }
        // 2.如果方法上加上事务直接的 开启或者是提交事务 proceed();执行原有方法
        TransactionStatus transactionStatus = transactionUtils.begin();
        Object proceed = pjp.proceed();// 执行原有方法
        transactionUtils.commit(transactionStatus);
        return proceed;
    }
}
