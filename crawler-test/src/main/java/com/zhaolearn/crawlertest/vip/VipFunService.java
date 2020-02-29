package com.zhaolearn.crawlertest.vip;

import com.zhaolearn.crawlertest.crawlerUrl.CrawlerTools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VipFunService {
    public static void main(String[] agrs) throws Exception {
        VipClass vipClass = new VipClass();
//        vipClass.setMovieUrl("https://so.iqiyi.com/so/q_%E7%88%B1%E4%B8%8A%E5%8C%97%E6%96%97%E6%98%9F%E7%94%B7%E5%8F%8B?source=input&sr=1052548317589");
        vipClass.setMovieUrl("https://so.iqiyi.com/so/q_%E7%88%B1%E4%B8%8A%E5%8C%97%E6%96%97%E6%98%9F%E7%94%B7%E5%8F%8B?source=input&sr=1052548317589");
//        vipClass.setInterfaceVip("http://api.nobij.top/jx/?url=");
        vipClass. setInterfaceVip("http://jx.aeidu.cn/index.php?url=");
        aiQiYiVip(vipClass);
    }

    /**
     * 拼接出爱奇艺的VIP连接。，只需在main中启动即可
     *
     * @param vipClass
     */
    public static void aiQiYiVip(VipClass vipClass) throws Exception {
        Pattern pattern = Pattern.compile("第(.*?)集");
        Pattern pattern1 = Pattern.compile("(.*?)www.iqiyi.com(.*?).html");
        URL url = new URL(vipClass.getMovieUrl());
        Document doc = Jsoup.parse(url, 30000);
        Elements elements = doc.select("a");
        //所有数据
        List<AHrefClass> hrefClassList = new ArrayList<>();
        //去重
        HashSet<String> titleSet = new HashSet<>();
        for (Element element : elements) {
            String title = element.attr("title");
            if (!titleSet.contains(title)) {
                String href = element.attr("href");
                Matcher matcher = pattern.matcher(title);
                Matcher matcher1 = pattern1.matcher(href);
                if (matcher.find() && matcher1.find()) {
                    AHrefClass aHrefClass = new AHrefClass();
                    aHrefClass.setHref(href);
                    aHrefClass.setTitle(title);
                    titleSet.add(title);
                    hrefClassList.add(aHrefClass);
                }
            }
        }
        hrefClassList.stream().forEach(e -> {
            System.out.println(e.getTitle() + "：" + vipClass.getInterfaceVip() + "https:" + e.getHref());
        });
    }


}
