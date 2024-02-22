package com.book.util;

import java.util.Random;

public class RandomUtil {
    /**
     * 生成指定位数的随机数字字符串。
     * @param n 指定随机数字的位数。
     * @return 返回生成的随机数字字符串。
     */
    public static String generateRandomNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("位数必须是正整数");
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // 生成第一位数字，确保不为0
        sb.append(random.nextInt(9) + 1);

        // 生成其余位数的数字
        for (int i = 1; i < n; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}