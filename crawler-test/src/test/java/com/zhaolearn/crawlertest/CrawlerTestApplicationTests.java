package com.zhaolearn.crawlertest;

import com.zhaolearn.crawlertest.crawlerUrl.CrawlerBiliBiliTitle;
import com.zhaolearn.crawlertest.crawlerUrl.CrawlerTools;
import com.zhaolearn.crawlertest.crawlerUrl.DownURLPicture;
import com.zhaolearn.crawlertest.crawlerUrl.URLEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerTestApplicationTests {
    @Autowired
    private CrawlerTools crawlerTools;
    @Autowired
    private CrawlerBiliBiliTitle crawlerBiliBiliTitle;

    @Test
    public void crawlerAndDown() {
        crawlerTools.crawlerAndDown("https://www.bilibili.com/", "D:/Crawler");
    }

    @Test
    public void spider() {
        CrawlerTools.spider("https://www.bilibili.com/");
    }

    @Test
    public void testDown() {
        DownURLPicture.downloadPicture("https://i0.hdslb.com/bfs/sycp/creative_img/201901/93004fc1c8b8fedf261d38928604905e.jpg", "d:/pic.jpg");
    }

    @Test
    public void replace() {
        DownURLPicture.downloadPicture("https://i0.hdslb.com/bfs/sycp/creative_img/201901/93004fc1c8b8fedf261d38928604905e.jpg", "d:/pic.jpg");
    }
    @Test
    public void getBiliBiliTitle() {
        CrawlerBiliBiliTitle.getBiliBiliTitle("https://www.bilibili.com/video/av36650577/?p=94");
    }

}

