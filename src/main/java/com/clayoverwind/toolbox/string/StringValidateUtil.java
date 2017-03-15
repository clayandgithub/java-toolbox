package com.clayoverwind.toolbox.string;

import java.io.UnsupportedEncodingException;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class StringValidateUtil {

    public static boolean isEmpty(String s, boolean trim) {
        if (s == null) {
            return true;
        }
        if (trim) {
            s = s.trim();
        }

        return s.isEmpty();
    }

    public static void testCharset(String s) {
        try {
            String temp = new String(s.getBytes(), "GBK");
            System.out.println("****** getBytes() -> GBK ******/n"+temp);
            temp = new String(s.getBytes("GBK"), "UTF-8");
            System.out.println("****** GBK -> UTF-8 *******/n"+temp);
            temp = new String(s.getBytes("GBK"), "ISO-8859-1");
            System.out.println("****** GBK -> ISO-8859-1 *******/n"+temp);
            temp = new String(s.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("****** ISO-8859-1 -> UTF-8 *******/n"+temp);
            temp = new String(s.getBytes("ISO-8859-1"), "GBK");
            System.out.println("****** ISO-8859-1 -> GBK *******/n"+temp);
            temp = new String(s.getBytes("UTF-8"), "GBK");
            System.out.println("****** UTF-8 -> GBK *******/n"+temp);
            temp = new String(s.getBytes("UTF-8"), "ISO-8859-1");
            System.out.println("****** UTF-8 -> ISO-8859-1 *******/n"+temp);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
