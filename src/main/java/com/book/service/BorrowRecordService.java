package com.book.service;

import com.book.constants.RoleConstant;
import com.book.mapper.BorrowrecordMapper;
import com.book.mapper.SubalbumMapper;
import com.book.pojo.po.Borrowrecord;
import com.book.pojo.po.Subalbum;
import com.book.pojo.po.User;
import com.book.util.CurrentUserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李嘉劲
 * 借阅记录相关service
 */
@Service
public class BorrowRecordService {
    @Autowired
    private BorrowrecordMapper borrowrecordMapper;
    @Autowired
    private SubalbumMapper subalbumMapper;

    /**
     *获取全部借阅记录
     * @param currentPage
     * @return
     */
    public Map<String,Object> getBorrowRecords(int currentPage){
        User user = CurrentUserInfo.get();
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        // 如果account为空可以获取所有记录，所以判断用户是否为管理员，如果为管理员的话将账号设置为空
        String account = user.getAccount();
        if (user.getCondi() == RoleConstant.MANAGER) {
            account = "";
        }
        List<Borrowrecord> list=borrowrecordMapper.selectAllInfoByRaccount(account);
        PageInfo<Borrowrecord> pageInfo=new PageInfo<>(list);
        map.put("pageInfo",pageInfo);
        map.put("borrowrecords",list);
        return map;
    }

    public Map<String,Object> reback(int bid, int sid){
        Map<String,Object> map=new HashMap<>();
        if(bid!=0&&sid!=0){
            borrowrecordMapper.delete(borrowrecordMapper.selectByPrimaryKey(bid));
            Subalbum subalbum=subalbumMapper.selectByPrimaryKey(sid);
            subalbum.setCondi(0);
            subalbumMapper.updateByPrimaryKey(subalbum);
            map.put("status","yes");
        }else{
            map.put("status","no");
        }
        return map;
    }
}
