package com.book.service;


import com.book.mapper.ReaderMapper;
import com.book.pojo.po.Reader;
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
    private ReaderMapper readerMapper;


    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public Map<String, Object> login(String account, String password){
        Map<String,Object> map=new HashMap<>();
        // 读取账号
        Reader reader=readerMapper.selectWholeByAccount(account);
        if (Objects.isNull(reader)) {
            map.put("status","failed");
            return map;
        }
        boolean success = MD5Util.compare(reader.getPassword(), password + reader.getSalt());
        if (success) {
            reader.setPassword("");
            reader.setSalt("");
            // 生成token
            map.put("loginUser",reader);
            if(reader.getCondi()==0){
                map.put("condi",0);
            }else if(reader.getCondi()==1){
                map.put("condi",1);
            }else{
                map.put("condi",2);
            }
            map.put("token", JwtUtil.buildTokenByUser(reader));
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
        List<Reader> list = readerMapper.selectByAccount(account);
        if (!list.isEmpty()) {
            map.put("message", "account is exist");
            map.put("status", "failed");
            return map;
        }
        Reader reader = new Reader(account,password,name,sex, DateTimeUtil.getDate(),condi);
        reader.setSalt(RandomUtil.generateRandomNumber(9));
        reader.setPassword(MD5Util.MD5(reader.getPassword() + reader.getSalt()));
        /*插入用户*/
        try {
            readerMapper.insert(reader);
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
        Reader reader=readerMapper.selectWholeByAccount(account);
        if(reader==null){
            //不存在该用户
            map.put("message", "不存在该用户");
            map.put("status","failed");
        }
        //获取id，根据id修改权限
        try {
            //修改成功
            readerMapper.update(reader.getRid(),condi);
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
        List<Reader> list=readerMapper.selectByAccount(account);
        PageInfo<Reader> pageInfo=new PageInfo<>(list);
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
        if(readerMapper.selectByAccount(account).size()!=0){
            /*2.1.重复，返回失败*/
            map.put("status","no");
        }else{
            //默认密码，可更改密码生成逻辑
            String password="123456";
            //当前注册时间
            String time=DateTimeUtil.getDate();
            Reader reader=new Reader(account,password,name,sex,time,condi);
            readerMapper.insert(reader);
            //返回状态码
            map.put("status","ok");
        }

        //返回map
        return map;
    }
}
