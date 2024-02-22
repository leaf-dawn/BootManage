package com.book.controller;

import com.book.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @Author 李嘉劲
 */
@RestController
@CrossOrigin
@RequestMapping("/borrowrecord")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowrecordService;

    /**
     *获取借阅记录
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getBorrowRecords",method = RequestMethod.POST)
    public Map<String,Object> getBorrowRecords(@RequestParam("currentPage")int currentPage){
        return borrowrecordService.getBorrowRecords(currentPage);
    }

    @RequestMapping(value = "/reback",method = RequestMethod.POST)
    public Map<String,Object> reback(@RequestParam("bid")int bid,@RequestParam("sid")int sid){
        return borrowrecordService.reback(bid, sid);
    }
}
