package com.example.demo.utils;

import java.util.Random;

/**
 * Created by cc on 2018/7/20.
 */
public class StringUtil {

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }


    public static String makeFileName(){
        return "" + new Random().nextInt(1000)+ System.currentTimeMillis();
    }

    public static String toString(Object obj) {
        return obj == null?"":obj.toString();
    }

    public static boolean isEqual(Object v1,String v2){
        if(v1 == v2){
            return true;
        }else if (v1 == null || v2 == null){
            return false;
        }else{
            return v1.equals(v2);
        }
    }
}
