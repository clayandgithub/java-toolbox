package com.clayoverwind.toolbox.swing;

import com.sun.glass.ui.Size;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.Enumeration;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class FontUtil {
    private static AffineTransform sAffineTransform = new AffineTransform();

    private static FontRenderContext sFontRenderContext = new FontRenderContext(sAffineTransform, true,
            true);

    public static void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public static Size getStringSize(String str, Font font) {
        if (str == null || str.length() == 0 || font == null) {
            return new Size(0, 0);
        }
        return new Size((int) font.getStringBounds(str, sFontRenderContext).getWidth(), (int) font.getStringBounds(str, sFontRenderContext).getHeight());
    }
}
