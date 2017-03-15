package com.clayoverwind.toolbox.string;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class StringValidateUtil {

    private static final String RANDOM_STRING_BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static boolean isEmpty(String s, boolean trim) {
        if (s == null) {
            return true;
        }
        if (trim) {
            s = s.trim();
        }

        return s.isEmpty();
    }

    public static String writeValueAsJsonStr(final Object object) {
        String retStr = null;
        try {
            retStr = OBJECT_MAPPER.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return retStr;
    }

    public static String getRandomString(final int length) {
        final Random random = new Random();
        final StringBuilder retSb = new StringBuilder();
        final int baseLength = RANDOM_STRING_BASE.length();
        for (int i = 0; i < length; ++i) {
            char c = RANDOM_STRING_BASE.charAt(random.nextInt(baseLength));
            retSb.append(c);
        }
        return retSb.toString();
    }

}
