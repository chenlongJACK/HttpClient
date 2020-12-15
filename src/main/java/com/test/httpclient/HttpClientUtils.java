package com.test.httpclient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author chenlong
 * @version 1.0
 * @description HttpClient工具类，发送http请求
 * @date
 */
public class HttpClientUtils {

    private static RequestConfig requestConfig=null;
    //设置请求连接时间与数据传输时间限制
    static {
        requestConfig = RequestConfig.custom().setConnectTimeout(1000).setSocketTimeout(3000).build();
    }
    /**
     * @Description post请求
     * @param url
     * @param params
     */
    public static void postRequest(String url,String params,Map<String,String> head){
        /**创建httpClient对象*/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**创建post请求对象*/
        HttpPost httpPost=new HttpPost(url);
        //设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        //设置参数，编码
        HttpEntity httpEntity=new StringEntity(params,Consts.UTF_8);
        httpPost.setEntity(httpEntity);
        //设置请求头信息
        if(head!=null&&!head.isEmpty()){
            Set<Map.Entry<String, String>> entries = head.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            System.out.println(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
