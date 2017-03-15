package com.clayoverwind.toolbox.io;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * @author wangweiwei
 *
 */
public final class PropertiesUtil {
    public static Properties loadPropertyFile(final File propFile) throws IOException {
        if (!propFile.exists()) {
            throw new FileNotFoundException(propFile.toString());
        }
        final Properties fileProps = new Properties();
        final FileInputStream inStream = new FileInputStream(propFile);
        try {
            fileProps.load(inStream);
        } finally {
            inStream.close();
        }
        return fileProps;
    }

    public static Properties loadPropertyByResourceName(final String resourceName) throws IOException {
        Properties retProperties = null;
        final URL pathURL = PropertiesUtil.class.getClassLoader().getResource(resourceName);
        if(pathURL != null) {
            final String path = pathURL.getPath();
            final File propFile = new File(path);
            if (propFile.exists()) {
                retProperties = PropertiesUtil.loadPropertyFile(propFile);
            }
        }
        return retProperties;
    }

    public static String getClassPath(ClassLoader classLoader) {
        return classLoader.getResource("").getPath();
    }

    public static String getValueInPropertiesFile(String propsFileName, String key) {
        InputStream inStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propsFileName);
        Properties props = new Properties();
        try {
            props.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String proValue = props.getProperty(key);
        String classPath = getClassPath(PropertiesUtil.class.getClassLoader());
        return proValue.replace("classpath:", classPath);
    }
}