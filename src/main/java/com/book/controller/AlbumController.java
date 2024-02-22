package com.book.controller;

import com.book.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 图书管理Controller
 */
@RestController
@RequestMapping("/book")
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * 获取书目集合
     * @param title
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getAllAlbums",method = RequestMethod.POST)
    public Map<String,Object> getAllAlbums(@RequestParam("title")String title, @RequestParam("currentPage")int currentPage){
        return albumService.getAllAlbums(title, currentPage);
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
    @RequestMapping(value = "/addAlbum",method = RequestMethod.POST)
    public Map<String,Object> addAlbum(@RequestParam("title")String title,@RequestParam("author")String author,@RequestParam("publisher")String publisher,@RequestParam("publishtime")String publishtime,@RequestParam("descri")String descri){
        return albumService.addAlbum(title, author, publisher, publishtime, descri);
    }


    /**
     * 添加图书编号
     * @param aid
     * @param number
     * @return
     */
    @RequestMapping(value = "/addSubAlbum",method = RequestMethod.POST)
    public Map<String,Object> addSubAlbim(@RequestParam("aid")int aid,@RequestParam("number")String number){
        return albumService.addSubAlbim(aid, number);
    }

    /**
     * 借阅图书
     * @param aid
     * @param rid
     * @param raccount
     * @return
     */
    @RequestMapping(value = "/borrow",method = RequestMethod.POST)
    public Map<String,Object> borrow(@RequestParam("aid")int aid,@RequestParam("rid")int rid,@RequestParam("raccount")String raccount){
        return albumService.borrow(aid, rid, raccount);
    }

}
