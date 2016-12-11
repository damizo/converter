package com.app.utils;

import java.util.UUID;

/**
 * Created by Damian on 2016-11-20.
 */
public class CryptoUtils {

    public static String generateRandomToken(){
        return UUID.randomUUID().toString();
    }
}
