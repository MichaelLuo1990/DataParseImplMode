package com.example.htmlparse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Desc java jsoup库引用测试
 * Created by Michael on 2018/5/2.
 */

public class JsoupTest {

    public static void main(String args[]) {
        try {
            Document document = Jsoup.connect("http://www.jianshu.com/").timeout(10000).get();
            //读取document对象
//            System.out.println(document);
            //遍历节点取值
            System.out.println("==================================遍历标签取值===========================================");
            Elements noteList = document.select("ul.note-list");
            Elements li = noteList.select("li");
            for (Element element : li) {
                System.out.println("==============================================================================");
                System.out.println("作者姓名-------->" + element.select("div.name").text()); // 作者姓名
                System.out.println("作者首页链接-------->" + element.select("a.blue-link").attr("abs:href")); // 作者首页链接
                System.out.println("发表时间-------->" + element.select("span.time").attr("data-shared-at"));   // 发表时间
                System.out.println("主图-------->" + element.select("img").attr("src"));  // 主图
                System.out.println("头像-------->" + element.select("a.avatar").select("img").attr("src")); // 头像
                System.out.println("标题-------->" + element.select("a.title").text());    // 标题
                System.out.println("标题链接-------->" + element.select("a.title").attr("abs:href")); // 标题链接
                System.out.println("内容-------->" + element.select("p.abstract").text());       // 内容
                System.out.println("专题链接-------->" + element.select("a.collection-tag").attr("abs:href")); // 专题链接
            }
            System.out.println("==================================获取body标签===========================================");
            //获取body标签
            Element body = document.body();
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
