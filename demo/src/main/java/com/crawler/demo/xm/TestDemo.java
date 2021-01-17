package com.crawler.demo.xm;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
public class TestDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {

//    "筱静","奕璇","念瑶","凝夏","知柠","春柔","天薇","雨璇","梦清"






        List<String> collect = Stream.of(
                "筱静","奕璇","念瑶","凝夏","知柠","春柔","天薇","雨璇","梦清"
        ).collect(Collectors.toList());
        collect.parallelStream().forEach(e->{
            try {
                HttpXmlClient.testNameFenShu("何",e);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        });
    }
}
