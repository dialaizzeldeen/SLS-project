package com.example.seamlessshopping;

public class ippage {
    public static final String ip= "192.168.1.11";
   // public static final String ip= "172.19.31.73";
   public static String regex;


    public static boolean isRegexValidate(String mail){
        boolean valid;
        regex="^[_A-Za-z0-9-]*@[A-Za-z]*(\\.[A-Za-z]{2,})$";
        if (mail.matches(regex)){ valid = true;}
        else valid = false;
        return  valid;


    }
}
