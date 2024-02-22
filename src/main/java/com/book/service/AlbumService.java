package com.book.service;

import com.book.mapper.AlbumMapper;
import com.book.mapper.BorrowrecordMapper;
import com.book.mapper.SubalbumMapper;
import com.book.pojo.po.Album;
import com.book.pojo.po.Borrowrecord;
import com.book.pojo.po.Subalbum;
import com.book.util.DateTimeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李嘉劲
 * 图书相关service
 */
@Service
public class AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private SubalbumMapper subalbumMapper;

    @Autowired
    private BorrowrecordMapper borrowrecordMapper;

    /**
     * 获取书目集合
     * @param title
     * @param currentPage
     * @return
     */
    public Map<String,Object> getAllAlbums(String title, int currentPage){
        List<Album> list=new ArrayList<>();
        /*声明返回值对象*/
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        /*查询数据库*/
        list=albumMapper.selectByTitle(title);

        /*将list存入map返回*/
        PageInfo<Album> pageInfo=new PageInfo<>(list);
        map.put("pageInfo",pageInfo);
        map.put("albums",list);
        return map;
    }

    /**
     * 添加书籍
     * @param title
     * @param author
     * @param publisher
     * @param publishtime
     * @param descri
     * @return
     */
    public Map<String,Object> addAlbum(String title,String author,String publisher,String publishtime,String descri){
        Map<String,Object> map=new HashMap<>();
        albumMapper.insert(new Album(title,author,publisher,publishtime,0,descri, DateTimeUtil.getDate()));
        map.put("status","ok");
        return map;
    }


    /**
     * 添加图书编号
     * @param aid
     * @param number
     * @return
     */
    public Map<String,Object> addSubAlbim(int aid,String number){
        Map<String,Object> map=new HashMap<>();
        if(subalbumMapper.selectByNumber(number)!=null){
            map.put("status","no");
        }
        else{
            Album album=albumMapper.selectById(aid);
            album.setNum(album.getNum()+1);
            albumMapper.updateByPrimaryKey(album);
            subalbumMapper.insert(new Subalbum(aid,number,0,DateTimeUtil.getDate()));
            map.put("status","ok");
        }
        return map;
    }

    /**
     * 借阅图书
     * @param aid
     * @param rid
     * @param raccount
     * @return
     */
    public Map<String,Object> borrow(int aid,int rid,String raccount){
        Map<String,Object> map=new HashMap<>();
        if(rid!=0){
            Album album=albumMapper.selectById(aid);
            int count=0;
            //找到可借的那本书编号id
            int enableborrowSAid=0;
            for(int i=0,len=album.getSubalbums().size();i<len;i++){
                if(album.getSubalbums().get(i).getCondi()==0){
                    enableborrowSAid=album.getSubalbums().get(i).getSid();
                    count++;
                }
            }
            if(count!=0){
                map.put("status","ok");
                Borrowrecord borrowrecord=new Borrowrecord(rid,raccount,aid,enableborrowSAid,DateTimeUtil.getDate(),DateTimeUtil.getDateAfter15(),DateTimeUtil.getDateNumber());
                borrowrecordMapper.insert(borrowrecord);
                Subalbum subalbum=subalbumMapper.selectById(enableborrowSAid);
                subalbum.setCondi(1);
                subalbumMapper.updateByPrimaryKey(subalbum);
            }else{
                map.put("status","no");
            }
        }
        return map;
    }

}