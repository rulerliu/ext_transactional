package com.transaction.controller;

import com.transaction.entity.User;
import com.transaction.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明
 *
 * @author wangyu
 * @version 1.0
 * @date $2018/11/15$ $10:39$
 */
@RestController
public class TestController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("getUser")
    public String test(String name) {
        return name;
    }

    @GetMapping("/addUser")
    public Object addUser(User user) {
        return userService.addUser(user);
    }

    @GetMapping("/addUser1")
    public Object addUser1(User user) {
        return userService.addUser1(user);
    }

    @GetMapping("/addUser2")
    public Object addUser2(User user) {
        return userService.addUser2(user);
    }

    @GetMapping("/addUser3")
    public Object addUser3(User user) {
        return userService.addUser3(user);
    }

    @GetMapping("/addUser31")
    public Object addUser31(User user) {
        return userService.addUser31(user);
    }

    @GetMapping("/addUser32")
    public Object addUser32(User user) {
        return userService.addUser32(user);
    }

    @GetMapping("/addUser33")
    public Object addUser33(User user) {
        return userService.addUser33(user);
    }

    @GetMapping("/addUser34")
    public Object addUser34(User user) {
        return userService.addUser34(user);
    }

    @GetMapping("/addUser4")
    public Object addUser4(User user) {
        return userService.addUser4(user);
    }

    @GetMapping("/addUser5")
    public Object addUser5(User user) {
        return userService.addUser5(user);
    }
}
