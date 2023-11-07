package com.example.backend.util;

import java.util.Random;

public class CommonUtil {
    static Random random = new Random();
    public static String randomize() { return  Long.toString(System.currentTimeMillis()) + random.nextInt() ; }
}
