package com.clayoverwind.toolbox.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class StringFilterUtil {

    private StringFilterUtil(){}

    public static String removeSpecialChar(String str) {
        String regEx="[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
