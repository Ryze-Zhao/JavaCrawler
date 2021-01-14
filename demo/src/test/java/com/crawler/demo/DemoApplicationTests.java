package com.crawler.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		NameCra.getName("http://www.laohuangli.net/topic/baobaonamelist.html?StrName=%E4%BD%95&xi=%E6%9C%A8%E7%81%AB&StrSex=1&LYear=%E9%BC%A0");
	}

}
