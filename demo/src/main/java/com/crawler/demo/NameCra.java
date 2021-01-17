package com.crawler.demo;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class NameCra {

    public static void getName(String url) {
        Document doc = null;
//        log.info("111111111111111111111111");
        try {
            //因为bilibili的代码，并不是将视频列表的标题放在标签中，而是在头部有，所以创建好正则，一会从页面代码拿
//            String regex = "<span>[\\u4e00-\\u9fa5]</span>";
            String regex = "<span>(.*?)</span>";
            List<String> list = new ArrayList<String>();
            List<String> extvounoLists = new ArrayList<String>();
            Pattern pattern = Pattern.compile(regex);
            // 先拿到整个页面
            doc = Jsoup.connect(url).get();
            //再拿到页面代码
            Matcher m = pattern.matcher(doc.outerHtml());


//            //根据页面代码使用正则分隔出来
            while (m.find()) {
                int i = 1;
                list.add(m.group(i));
                i++;
            }
int j=0;
            for (int i = 0; i <list.size(); ) {
                String a1 = list.get(i++);
                String a2= list.get(i++);
                String a3 = list.get(i++);
                System.out.println(a1+" "+a2+" "+a3);
                j++;
                if(j%3==0){
                    System.out.println("");
                }
            }



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
