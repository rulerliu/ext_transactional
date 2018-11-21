package com.transaction.mapper;

import com.transaction.entity.User;
import org.apache.ibatis.annotations.Insert;

/**
 * 类说明
 *
 * @author wangyu
 * @version 1.0
 * @date $2018/11/15$ $10:49$
 */
public interface UserMapper {

    @Insert("INSERT INTO user(`name`, `age`) VALUES (#{name}, #{age}) ")
    Integer addUser(User user);
}
