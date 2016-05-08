package com.l1.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luopotaotao on 2016/5/8.
 */
public class FileUtil {
    private static String dir = System.getProperty("user.dir");
    public static void save(MultipartFile img, String fileName) {
        System.out.println(dir);
    }

    public static void remove(String s) {
    }
}
