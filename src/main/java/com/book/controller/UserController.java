package com.book.controller;

import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @Author 李嘉劲
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    //TODO
    @Autowired
    private UserService userService;
    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestParam("account")String account, @RequestParam("password")String password){
        return userService.login(account, password);
    }

    /**
     * 用户注册
     * @param account
     * @param name
     * @param password
     * @param sex
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Map<String,Object> register(@RequestParam("account") String account, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("sex") String sex){
        return userService.register(account, name, password, sex, 0);
    }

    /**
     *权限修改
     * @param account
     * @param condi
     * @return
     */
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    public Map<String,Object> updatRrole(@RequestParam("account") String account,@RequestParam("condi") int condi){
        return userService.updateRole(account, condi);
    }

    /**
     * 展示所有用户
     * @param account
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getAllReaders",method = RequestMethod.POST)
    public Map<String,Object> getAllReaders(@RequestParam("account")String account,@RequestParam("currentPage")int currentPage){
        return userService.getAllReaders(account, currentPage);
    }

    /**
     * 添加用户业务
     * @param account
     * @param name
     * @param sex
     * @param condi
     * @return
     */
    @RequestMapping("/addReader")
    public Map<String,Object> addUser(@RequestParam("account") String account,@RequestParam("name") String name,@RequestParam("sex") String sex,@RequestParam("condi") int condi){
        return userService.addUser(account, name, sex, condi);
    }
}
