package com.transaction.util;

public class Utils {
    public static String getPinCodeStarts(Integer pinCode){
        if (pinCode!=null){
            String stars="";
            for (int i=0;i<pinCode.toString().length();i++){
                stars+="*";
            }
            return stars;
        }
        return null;
    }
}
