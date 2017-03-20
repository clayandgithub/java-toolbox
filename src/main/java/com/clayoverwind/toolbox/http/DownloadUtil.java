package com.clayoverwind.toolbox.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.clayoverwind.toolbox.io.IOUtil;
import org.apache.commons.io.FileUtils;

public class DownloadUtil {

    private DownloadUtil(){}
	
	public static String downloadImageByUrl(String imgUrl, String dstDir) {
		String localDstFileName = dstDir.concat(imgUrl.substring(imgUrl.lastIndexOf('/')));
		InputStream is = IOUtil.getInputStreamFromUrl(imgUrl);
		IOUtil.fromIputStreamToFile(is, localDstFileName);
		return localDstFileName;
	}

    public static boolean downloadFileByURL(final String url, final String absFilePath) {
        try {
            URL httpUrl = new URL(url);
            File f = new File(absFilePath);
            FileUtils.copyURLToFile(httpUrl, f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
