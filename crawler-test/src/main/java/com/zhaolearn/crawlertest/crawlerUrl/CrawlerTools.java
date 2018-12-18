package com.zhaolearn.crawlertest.crawlerUrl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CrawlerTools {
    @Autowired
    private URLEntityRepository urlEntityRepository;
    public static void crawlerTools(String firstURL) {
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        PrintWriter pw = null;
//        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
        String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";//url匹配规则
        Pattern p = Pattern.compile(regex);
        try {
            url = new URL(firstURL);//爬取的网址、这里爬取的是一个生物网站
            urlconn = url.openConnection();
            pw = new PrintWriter(new FileWriter("D:/SiteURL.txt"), true);//将爬取到的链接放到D盘的SiteURL文件中
            br = new BufferedReader(new InputStreamReader(
                    urlconn.getInputStream()));
            String buf = null;
            while ((buf = br.readLine()) != null) {
                Matcher buf_m = p.matcher(buf);
                while (buf_m.find()) {
                    pw.println(buf_m.group());
                }
            }
            System.out.println("爬取成功^_^");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }

    public static void spider(String firstURL) {
        Document document = null;
        try {
            document = Jsoup.connect(firstURL).timeout(3000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过Document的select方法获取属性结点集合
        Elements elements = document.select("img");
        for (Element element : elements) {
            System.out.println(element);
        }
    }

    public void crawlerAndDown(String firstURL,String path) {
        List<URLEntity> urlList=new ArrayList<>();
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;

        String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";//url匹配规则
        Pattern p = Pattern.compile(regex);
        try {
            url = new URL(firstURL);//爬取的网址、这里爬取的是一个生物网站
            urlconn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            String buf = null;
            while ((buf = br.readLine()) != null) {
                Matcher buf_m = p.matcher(buf);
                while (buf_m.find()) {
//                    System.out.println(buf_m.group());
                    String result= buf_m.group();
                    if(result.contains(".jpg")||result.contains(".png")){
                        String uuid=UUID.randomUUID().toString();
                        URLEntity haha=new URLEntity();
                        haha.setUrl(result);
                        haha.setUrl(uuid+".jpg");
                        DownURLPicture.downloadPicture(result,path+"/"+uuid+".jpg");
                        urlList.add(haha);
                    }
                }
            }
            System.out.println("爬取成功^_^");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlList.size()!=0){
                urlEntityRepository.saveAll(urlList);
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
