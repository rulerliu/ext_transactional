package com.transaction.service;

import com.transaction.annotation.ExtTransaction;
import com.transaction.entity.User;
import com.transaction.exception.ResourceException;
import com.transaction.mapper.UserMapper;
import com.transaction.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类说明
 *
 * @author wangyu
 * @version 1.0
 * @date $2018/11/15$ $10:54$
 */
@Service
public class UserServiceImpl {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TransactionUtils transactionUtils;


    public Integer addUser(User user) {//会插入，不回滚

        Integer integer  = userMapper.addUser(user);
        int i = integer / 0;
        return i;
    }

    public String addUser1(User user) {//会插入，不回滚
        try {
            Integer integer  = userMapper.addUser(user);
            int i = integer / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Transactional
    public Integer addUser2(User user) {//不会插入，进异常通知

        Integer integer  = userMapper.addUser(user);
        int i = integer / 0;
        return i;
    }

    @Transactional
    public String addUser3(User user) {//方法执行完，提交事务
        try {
            Integer integer  = userMapper.addUser(user);
            int i = integer / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Transactional
    public String addUser31(User user) {//不会插入数据，进异常通知
        try {
            Integer integer  = userMapper.addUser(user);
            int i = integer / 0;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
        return "success";
    }

    @Transactional
    public String addUser32(User user) {//不会插入数据，进异常通知
        try {
            userMapper.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 1 / 0;
        return "success";
    }

    @Transactional
    public String addUser33(User user) {//方法执行完插入数据
        try {
            userMapper.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Transactional
    public String addUser34(User user) {//不会提交事务，进异常通知
        try {
            userMapper.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceException(e.getMessage());
        }

        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceException(e.getMessage());
        }
        return "success";
    }

    @ExtTransaction
    public Integer addUser4(User user) {//不会插入，进异常通知

        Integer integer  = userMapper.addUser(user);
        int i = integer / 0;
        return i;
    }

    @ExtTransaction
    public String addUser5(User user) {//方法执行完提交，不会进异常通知
        try {
            Integer integer  = userMapper.addUser(user);
            int i = integer / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
