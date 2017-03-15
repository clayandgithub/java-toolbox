package com.clayoverwind.toolbox.http;

import java.io.*;

import com.clayoverwind.toolbox.io.FileUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    private static final int WAIT_TIME_OUT = 20000;

    public static String httpGetResultStringEntity(final String url) {
        HttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpGet = new HttpGet(url);
        return httpRequestResultStringEntity(httpGet, httpClient, url);
    }

    public static boolean writeRequestEntityIntoFile(final String url, final String absFileName) {
        HttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpGet = new HttpGet(url);
        return writeRequestEntityIntoFile(httpGet, httpClient, url, absFileName);
    }

    public static InputStream getInputStreamByUrl(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpGet = new HttpGet(url);
        try {
            httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, WAIT_TIME_OUT);
            final HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            entity = decompressingEntityIfNecessary(entity);
            return entity.getContent();
        } catch (final ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpGet.abort();
        }
    }

    private static HttpEntity decompressingEntityIfNecessary(HttpEntity entity) {
        HttpEntity retEntity = entity;
        if (entity != null && entity.getContentEncoding() != null) {
            if ("gzip".equalsIgnoreCase(entity.getContentEncoding().getValue())) {
                retEntity = new GzipDecompressingEntity(entity);
            } else if ("deflate".equalsIgnoreCase(entity.getContentEncoding().getValue())) {
                retEntity = new DeflateDecompressingEntity(entity);
            }
        }
        return retEntity;
    }

    private static boolean writeRequestEntityIntoFile(final HttpRequestBase requestBase,
                                                      final HttpClient httpClient, final String url, final String absFileName) {
        try {
            requestBase.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, WAIT_TIME_OUT);

            final HttpResponse response = httpClient.execute(requestBase);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.println("response.getStatusLine().getStatusCode() != HttpStatus.SC_OK");
                return false;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null && entity.getContentEncoding() != null) {
                System.out.println("contentType : " + entity.getContentType());
                if ("gzip".equalsIgnoreCase(entity.getContentEncoding().getValue())) {
                    System.out.println("contentEncoding : gzip");
                    return FileUtil.readInputStreamAndWriteToFile(absFileName, new GzipDecompressingEntity(entity).getContent());
                } else if ("deflate".equalsIgnoreCase(entity.getContentEncoding().getValue())) {
                    System.out.println("contentEncoding : deflate");
                    return FileUtil.readInputStreamAndWriteToFile(absFileName, new DeflateDecompressingEntity(entity).getContent());
                }
            } else {
                System.out.println("entity == null || entity.getContentEncoding() == null");
            }
        } catch (final ClientProtocolException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            requestBase.abort();
        }
        return false;
    }

    private static String httpRequestResultStringEntity(final HttpRequestBase requestBase,
                                                        final HttpClient httpClient, final String url) {
        try {
            requestBase.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, WAIT_TIME_OUT);
            final HttpResponse response = httpClient.execute(requestBase);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            entity = decompressingEntityIfNecessary(entity);
            return EntityUtils.toString(entity, "UTF-8");
        } catch (final ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } finally {
            requestBase.abort();
        }
    }
}
