package com.example.rj.openeyesvideo.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rj on 2018/1/23.
 */

public class TextUtil {

    public static ArrayList<String> getListContent(String content){
        ArrayList<String> list=new ArrayList<>();
        int stringType=0;
        String stringEnglish="";
        String stringNum="";
        content+="";
        int len=content.length();

        for(int i=0;i<len;i++){
            char c=content.charAt(i);
            char c_1=' ';
            if(i>=1){
                 c_1=content.charAt(i-1);
            }
            if(isEnglish(c) || isEnglish(c_1) && (c+"").equals("'")){
                stringEnglish+=c;
                stringType=1;
            }else if(isNum(c) || isNum(c_1) && (c+"").equals(".")){
                stringNum+=c;
                stringType=2;
            }else {
                if(!stringEnglish.equals("") && !stringNum.equals("") && stringType==1){
                    list.add(stringNum+stringEnglish);
                }else if(!stringEnglish.equals("") && !stringNum.equals("") && stringType==2){
                    list.add(stringEnglish+stringNum);
                }else if(!stringEnglish.equals("")&& stringNum.equals("")){
                    list.add(stringEnglish);
                }else if(!stringNum.equals("")&&stringEnglish.equals("")){
                    list.add(stringNum);
                }
                list.add(c+"");
                stringType=0;
                stringEnglish="";
                stringNum="";
            }
        }
        return list;
    }

    public static boolean isEnglish(char c){
        Pattern p=Pattern.compile("^[a-zA-Z]*$");
        Matcher m=p.matcher(c+"");
        if(m.matches()){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isNum(char c){
        Pattern pattern=Pattern.compile("^[0-9]*$");
        Matcher matcher=pattern.matcher(c+"");
        if(matcher.matches()){
            return true;
        }else {
            return false;
        }
    }

}
