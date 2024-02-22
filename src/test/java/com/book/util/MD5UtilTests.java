package com.book.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MD5UtilTests {
    @Test
    void MD5Test() {
        String password = "super" + "2020202";
        System.out.println(MD5Util.MD5(password));
    }
}
