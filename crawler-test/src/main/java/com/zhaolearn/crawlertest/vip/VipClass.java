package com.zhaolearn.crawlertest.vip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class VipClass {
    /**
     * https://so.iqiyi.com/so/q_%E7%88%B1%E4%B8%8A%E5%8C%97%E6%96%97%E6%98%9F%E7%94%B7%E5%8F%8B?source=input&sr=1052548317589
     * 如爱奇艺的集数页面
     */
    private String movieUrl;

    private String interfaceVip;
}
