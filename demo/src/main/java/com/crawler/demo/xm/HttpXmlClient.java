package com.crawler.demo.xm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


@Slf4j
public class HttpXmlClient {

    public static void testNameFenShu(String xs,String name) throws UnsupportedEncodingException {
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        String url = "https://www.xingming.com/dafen/";

        headers.put("authority","www.xingming.com");
        headers.put("cache-control","max-age=0");
        headers.put("upgrade-insecure-requests","1");
        headers.put("origin","https://www.xingming.com");
        headers.put("content-type","application/x-www-form-urlencoded");
        headers.put("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36");
        headers.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.put("sec-fetch-site","same-origin");
        headers.put("sec-fetch-mode","navigate");
        headers.put("sec-fetch-user","?1");
        headers.put("sec-fetch-dest","document");
        headers.put("referer","https://www.xingming.com/dafen/");
        headers.put("accept-language","zh-CN,zh;q=0.9");
        headers.put("cookie","__cfduid=df638bf12949ad47581e53154644f52d71610626799; ASPSESSIONIDQEATRQTR=OGLGKNACKACELOKIPEALEEJM; Hm_lvt_58c2934af7b1ea7638bdac03a6b435e5=1610626802,1610626916,1610888500; ming="+java.net.URLEncoder.encode(name,"utf-8")+"; xing="+java.net.URLEncoder.encode(xs,"utf-8")+"; ASPSESSIONIDSGDQQSSQ=KJFAMHKBNPNPHFIBNCOEKPCD; Hm_lpvt_58c2934af7b1ea7638bdac03a6b435e5=1610888537");


        bodys.put("xs",xs);
        bodys.put("mz",name);
        bodys.put("action","test");



        String xml = HttpXmlClient.post(url, headers, bodys);

        //匹配分数，并输出
        String regex = "<b><font color=ff0000 size=5>(.*?)</font></b>";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(xml);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        list.stream().forEach(e->{
//            System.out.println("-------------------------------------------");
            if(Double.valueOf(e)>=90){
                System.out.println(name);
            }
        });
    }




    public static String post(String url,Map<String, String> headers, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

//        log.info("create httppost:" + url);
        HttpPost post = postForm(url,headers, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

//        log.info("create httppost:" + url);
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);

        httpclient.getConnectionManager().shutdown();

        return body;
    }


    private static String invoke(DefaultHttpClient httpclient,
                                 HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
//        log.info("get response from http server..");
        HttpEntity entity = response.getEntity();

//        log.info("response status: " + response.getStatusLine());
        String charset = EntityUtils.getContentCharSet(entity);
//        log.info(charset);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
//            log.info(body);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpclient,
                                            HttpUriRequest httpost) {
//        log.info("execute post...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> headers, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        //设置header
        if (null != headers) {
            Set<String> keyHeadersSet = headers.keySet();
            for (String key : keyHeadersSet) {
                httpost.setHeader(key, headers.get(key));
            }
        }
        //设置body
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
//            log.info("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpost;
    }
}  