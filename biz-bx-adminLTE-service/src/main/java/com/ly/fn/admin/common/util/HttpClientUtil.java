package com.ly.fn.admin.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.http.impl.client.HttpClients.createDefault;

/**
 * 类名称：HttpClientUtil
 * 类描述：
 * 创建人：张波波【zbb08364】
 * 创建时间：2018/3/6.13:45
 * 修改备注：
 *
 * @version 1.0.0
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 连接池
     */
    private static PoolingHttpClientConnectionManager connectionManager;
    /**
     * 请求配置
     */
    private static RequestConfig requestConfig;
    /**
     * 超时时间
     */
    private static final int MAX_TIMEOUT = 600000;//600秒,10分钟

    static {
        connectionManager = new PoolingHttpClientConnectionManager();
        //设置连接池大小
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultMaxPerRoute(connectionManager.getMaxTotal());
        //设置请求参数配置
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        //设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        //设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        //设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        //在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    private HttpClientUtil() {
    }

    public static String doGet(String strUrl) {
        String httpStr = null;
        CloseableHttpClient httpclient = createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpget = new HttpGet();
            URL url = new URL(strUrl);
            URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);
            httpget.setURI(uri);
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                httpStr = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            LoggerUtils.info(logger, "ERROR", "HttpClientUtils", e.toString());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                }
            }
        }
        return httpStr;
    }

    public static String doPost(String apiUrl, Map<String, Object> params) {
        CloseableHttpClient httpClient = createDefault();
        String httpStr = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;
        try {
            //URL url = new URL(apiUrl);
            //URI(String scheme, String authority, String path, String query, String fragment)
            //URI(String scheme, String userInfo, String host, int port, String path, String query, String fragment)
            //URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            httpPost = new HttpPost(apiUrl);
            httpPost.setConfig(requestConfig);
            if (params != null && params.size() > 0) {
                List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    pairList.add(pair);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LoggerUtils.info(logger, "ERROR", "doGet", e.toString());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (Exception e) {
                }
            }
        }
        return httpStr;
    }

    public static String doPostJson(String url, String jsonParam) throws IOException {
        String entityStr = null;
        // 创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpGet
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            // 处理参数
            StringEntity stringEntity = new StringEntity(jsonParam);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            // 执行请求
            response = httpClient.execute(httpPost);
            // 处理返回
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entityStr = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            LoggerUtils.info(logger, "ERROR", "doGet", e.toString());
        } finally {
            // 关闭response
            if (response != null) {
                response.close();
            }
            // 关闭httpClient
            httpClient.close();
        }
        return entityStr;
    }

    public static byte[] doPostJsonStream(String url, String jsonParam) throws IOException {
        byte[] result = null;
        // 创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpGet
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            // 处理参数
            StringEntity stringEntity = new StringEntity(jsonParam);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            // 执行请求
            response = httpClient.execute(httpPost);
            // 处理返回
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                byte[] buffer=new byte[1024];
                int ch;
                InputStream inputStream = entity.getContent();
                while ((ch = inputStream.read(buffer)) != -1) {
                    bytestream.write(buffer,0,ch);
                }
                byte[] data = bytestream.toByteArray();
                bytestream.close();
                result = data;
            }
        } catch (Exception e) {
            LoggerUtils.info(logger, "ERROR", "doGet", e.toString());

        } finally {
            // 关闭response
            if (response != null) {
                response.close();
            }
            // 关闭httpClient
            httpClient.close();
        }
        return result;
    }
}
