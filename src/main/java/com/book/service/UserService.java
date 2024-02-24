package com.book.service;


import com.book.mapper.UserMapper;
import com.book.pojo.po.User;
import com.book.util.DateTimeUtil;
import com.book.util.JwtUtil;
import com.book.util.MD5Util;
import com.book.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author 李嘉劲
 * 用户相关service
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public Map<String, Object> login(String account, String password){
        Map<String,Object> map=new HashMap<>();
        // 读取账号
        User user= userMapper.selectWholeByAccount(account);
        if (Objects.isNull(user)) {
            map.put("status","failed");
            return map;
        }
        boolean success = MD5Util.compare(user.getPassword(), password + user.getSalt());
        if (success) {
            user.setPassword("");
            user.setSalt("");
            // 生成token
            map.put("loginUser",user);
            if(user.getCondi()==0){
                map.put("condi",0);
            }else if(user.getCondi()==1){
                map.put("condi",1);
            }else{
                map.put("condi",2);
            }
            map.put("token", JwtUtil.buildTokenByUser(user));
            map.put("status","ok");
            return map;
        }
        map.put("status","failed");
        return map;
    }

    /**
     * 用户注册
     * @param account
     * @param name
     * @param password
     * @param sex
     * @param condi
     */
    public Map<String, Object> register(String account, String name, String password, String sex, int condi){
        Map<String,Object> map=new HashMap<>();
        List<User> list = userMapper.selectByAccount(account);
        if (!list.isEmpty()) {
            map.put("message", "account is exist");
            map.put("status", "failed");
            return map;
        }
        User user = new User(account,password,name,sex, DateTimeUtil.getDate(),condi);
        user.setSalt(RandomUtil.generateRandomNumber(9));
        user.setPassword(MD5Util.MD5(user.getPassword() + user.getSalt()));
        /*插入用户*/
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", "ok");
        return map;
    }

    /**
     *权限修改
     * @param account
     * @param condi
     * @return
     */
    public Map<String,Object> updateRole(String account, int condi){
        Map<String,Object> map=new HashMap<>();
        User user= userMapper.selectWholeByAccount(account);
        if(user==null){
            //不存在该用户
            map.put("message", "不存在该用户");
            map.put("status","failed");
        }
        //获取id，根据id修改权限
        try {
            //修改成功
            userMapper.update(user.getRid(),condi);
            map.put("message", "修改成功");
            map.put("status","ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 展示所有用户
     * @param account
     * @param currentPage
     * @return
     */
    public Map<String,Object> getAllReaders(String account,int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<User> list= userMapper.selectByAccount(account);
        PageInfo<User> pageInfo=new PageInfo<>(list);
        map.put("readers",list);
        map.put("pageInfo",pageInfo);
        return map;
    }

    /**
     * 添加用户业务
     * @param account
     * @param name
     * @param sex
     * @param condi
     * @return
     */
    public Map<String,Object> addUser( String account, String name, String sex, int condi){
        /*1.声明返回结果*/
        Map<String,Object> map=new HashMap<>();
        /*2.判断是否有重复*/
        if(userMapper.selectByAccount(account).size()!=0){
            /*2.1.重复，返回失败*/
            map.put("status","no");
        }else{
            //默认密码，可更改密码生成逻辑
            String password="123456";
            //当前注册时间
            String time=DateTimeUtil.getDate();
            User user=new User(account,password,name,sex,time,condi);
            userMapper.insert(user);
            //返回状态码
            map.put("status","ok");
        }

        //返回map
        return map;
    }
}
