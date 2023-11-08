package com.jack.utils;

import java.util.Random;

/**
 * 通用工具类
 */
public class CommonUtils {

    /**
     * 获取随机数
     *
     * @param length
     * @return
     */
    public static String getRandomCode(int length) {
        String sources = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(sources.charAt(random.nextInt(9)));
        }
        return sb.toString();
    }

}
