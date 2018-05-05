package com.example.htmlparse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Desc
 * Created by Michael on 2018/4/27.
 */

public class WebHttpClient {

    /**
     * 获得网页中的所有HTML内容
     * @param url
     * @param charset
     * @return
     */
    public String getWebContentByGet(String url,String charset){
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        StringBuilder sb = new StringBuilder();
        try {
            // 状态码
            int statusCode=client.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                //获得HTML文本
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        getMethod.getResponseBodyAsStream(), charset));
                String line = null;
                while ((line = bf.readLine()) != null) {
                    sb.append(line).append("\r\n");
                }
                bf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        return sb.toString();
    }
    /**
     * 获得网页中的所有HTML内容
     * @param url
     * @param mapData:传递的参数
     * @param charset
     * @return
     */
    public String getWebContentByPost(String url, Map<String,String> mapData, String charset){
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        StringBuilder sb = new StringBuilder();
        // 填入各个表单域的值
        NameValuePair[] data = new NameValuePair[mapData.size()];
        Set set = mapData.entrySet();
        Iterator iterator = set.iterator();
        int i=0;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            data[i]=new NameValuePair((String)entry.getKey(),(String)entry.getValue());
            i++;
        }
        /*
        NameValuePair[] data = {new NameValuePair("toPath","toPath"),
                                new NameValuePair("action","login"),
                                new NameValuePair("loginname","13761083826"),
                                new NameValuePair("password","111111")
                                };
        */
        // 将表单的值放入postMethod中
        postMethod.setRequestBody(data);
        try {
            int statusCode = client.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                //获得HTML文本
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        postMethod.getResponseBodyAsStream(), charset));
                String line = null;
                while ((line = bf.readLine()) != null) {
                    sb.append(line).append("\r\n");
                }
                bf.close();
            }
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            postMethod.releaseConnection();
        }
        return sb.toString();
    }

}
