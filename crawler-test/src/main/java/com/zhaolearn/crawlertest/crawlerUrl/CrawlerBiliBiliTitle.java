package com.zhaolearn.crawlertest.crawlerUrl;

import org.jsoup.Jsoup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CrawlerBiliBiliTitle {
    private final static Logger logger = LoggerFactory.getLogger(CrawlerBiliBiliTitle.class);

    public static void getBiliBiliTitle(String url) {
        Document doc = null;
        try {
            logger.info("1111111111");
            doc = Jsoup.connect(url).get();
//            logger.error("----"+doc.outerHtml());
            String regex = "\"part\":\"(.*?)\",\"duration\":";
            List<String> list = new ArrayList<String>();
            List<String> extvounoLists = new ArrayList<String>();
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(doc.outerHtml());
            while (m.find()) {
                int i = 1;
                list.add(m.group(i));
                i++;
            }
            for (String str : list) {
                System.out.println("-----"+str);
                String[] strs = str.split("\\.");
                String strss = strs[strs.length-1];
                extvounoLists.add(strs[strs.length-1]);
            }
int pN=0;
            for (String str : extvounoLists) {
                pN++;
                System.out.println(str+"（视频P"+pN+")");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
