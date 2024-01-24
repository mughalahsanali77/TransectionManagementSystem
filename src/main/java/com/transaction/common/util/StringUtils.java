package com.transaction.common.util;

public class StringUtils {

    public static boolean isEmptyOrNull(String str){
        return (str.trim().isEmpty()|| str.trim()==null);
    }
}
