package com.example.htmlparse;

import java.util.Map;

/**
 * Desc
 * Created by Michael on 2018/4/27.
 */

public class HTMLParseUtilsTest {

    private static String url = "http://caipiao.taobao.com/lottery/order/lottery_dlt.htm?type=1";
    private static String url1 = "https://www.baidu.com/";

    public static void main(String[] str) {

        HTMLParseUtils util=new HTMLParseUtils();

        System.out.println("=============================获得网页<body></body>标签中的内容============================");
        String bodyGetStr = util.bodyGet(url1, "utf-8");
        System.out.println(bodyGetStr);


        System.out.println("=============================获得网页中的超链接============================");
        Map<String, String> linkMap = util.linkGet(url1, "utf-8");
        for (String s : linkMap.keySet()) {
            System.out.println(s + " = " + linkMap.get(s));
        }

    }

}
