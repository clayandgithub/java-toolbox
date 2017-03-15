package com.clayoverwind.toolbox.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class StringTransformUtil {
    public static String gzipInputStreamToUTF8String(InputStream is) {
        GZIPInputStream gZipIs = null;
        InputStreamReader isr = null;
        StringBuilder sb = new StringBuilder();
        try {
            gZipIs = new GZIPInputStream(is);
            isr = new InputStreamReader(gZipIs, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator", "\n"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
                if (gZipIs != null) {
                    gZipIs.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sb.toString();
    }
}
