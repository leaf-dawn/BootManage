package com.book;

import com.book.pojo.po.Booktype;
import com.book.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookSysServerApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void RedisTest(){
        List<Booktype> list=new ArrayList<>();
        list.add(new Booktype(1,"2","3"));
        list.add(new Booktype(1,"2","3"));
        list.add(new Booktype(1,"2","3"));

        /*将list存入集合*/
        redisUtil.setValue("list",list);

        System.out.println(redisUtil.getValue("list"));
    }

}
