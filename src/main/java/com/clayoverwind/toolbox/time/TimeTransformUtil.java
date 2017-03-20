package com.clayoverwind.toolbox.time;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class TimeTransformUtil {

    private TimeTransformUtil(){}

    public static String secondToString(long s) {
        long h = s / 3600;
        long m = s / 60 - h * 60;
        s = s - h * 3600 - m * 60;
        StringBuilder sb = new StringBuilder();
        if (h > 9) {
            sb.append(String.valueOf(h)).append(":");
        } else if (h > 0){
            sb.append("0").append(String.valueOf(h)).append(":");
        }
        if (m > 9) {
            sb.append(String.valueOf(m)).append(":");
        } else {
            sb.append("0").append(String.valueOf(m)).append(":");
        }
        if (s > 9) {
            sb.append(String.valueOf(s));
        } else {
            sb.append("0").append(String.valueOf(s));
        }
        return sb.toString();
    }
}
