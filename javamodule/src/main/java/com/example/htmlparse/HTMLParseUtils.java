package com.example.htmlparse;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc
 * Created by Michael on 2018/4/27.
 */

public class HTMLParseUtils {

    WebHttpClient util = new WebHttpClient();

    /**
     * 获得网页中的超链接，将href和text保存在Map中：map(href,text)
     *
     * @param url
     * @param charset
     * @return
     */
    public Map<String, String> linkGet(String url, String charset) {
        String content = util.getWebContentByGet(url, charset);
        Map<String, String> linkMap = new HashMap<String, String>();
        try {
            //开始解析
            Parser parser = Parser.createParser(content, charset);
            // 过滤出<a></a>标签
            NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
            NodeList list = parser.extractAllNodesThatMatch(linkFilter);
            Node node = null;
            for (int i = 0; i < list.size(); i++) {
                node = list.elementAt(i);
                // 获得网页中的链接map(href,text)
                linkMap.put(((LinkTag) node).getLink(), this.processText(((LinkTag) node).getLinkText()));
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return linkMap;
    }

    private String processText(String content) {
        content = content.trim().replaceAll("&nbsp;", "");
//      content=content.replaceAll("<p>", "\n");
//      content=content.replaceAll("</TD>", "");
//      content=content.replaceAll("</div>", "");
//      content=content.replaceAll("</a>", "");
//      content=content.replaceAll("<a href=.*>", "");
        return content;
    }

    /**
     * 获得网页<body></body>标签中的内容
     *
     * @param url
     * @param charset
     * @return
     */
    public String bodyGet(String url, String charset) {
        String content = util.getWebContentByGet(url, charset);
        String body = "";
        try {
            Parser parser = Parser.createParser(content, charset);
            // 过滤<body></body>标签
            NodeFilter bodyFilter = new NodeClassFilter(BodyTag.class);
            NodeList list = parser.extractAllNodesThatMatch(bodyFilter);
            Node node = null;
            for (int i = 0; i < list.size(); i++) {
                node = list.elementAt(i);
                // 获得网页内容 保存在content中
                body = ((BodyTag) node).getBody();
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return body;
    }

}
