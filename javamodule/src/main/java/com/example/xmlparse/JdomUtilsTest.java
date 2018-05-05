package com.example.xmlparse;

import com.example.xmlparse.entity.Book;
import com.example.xmlparse.utils.JdomUtils;

import org.jdom.Element;
import org.jdom.JDOMException;

import java.io.IOException;

/**
 * Desc jdom解析测试
 * Created by Michael on 2018/4/24.
 */

public class JdomUtilsTest {

    static String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<responseData>\n" +
            "    <returnCode>00001</returnCode>\n" +
            "    <returnMessage>\n" +
            "        <Book>\n" +
            "            <counts>20</counts>\n" +
            "            <downUrl>http://localhost:8080/aaa.xls</downUrl>\n" +
            "            <startTime>2012-09-09 00:11:11</startTime>\n" +
            "            <endTime>2012-09-09 00:11:12</endTime>\n" +
            "            <success>5</success>\n" +
            "            <failure>15</failure>\n" +
            "        </Book>\n" +
            "    </returnMessage>\n" +
            "</responseData>";

    public static void main(String args[]) {

//        Map<String, String> xmlToMap = JdomUtilsx.xmlToMap(str);
//        xmlToMap.get("responseData");

//        Document document = new Document();
//        Element root = new Element("root");// 加根元素
//        document.addContent(root);
//        Comment comment = new Comment("This is my comments");// 给结点加上注释
//        root.addContent(comment);
//        Element e = new Element("hello");// 加子元素
//        e.setAttribute("sohu", "www.sohu.com");// 加属性
//        root.addContent(e);
//        Element e2 = new Element("world");// 加子元素2
//        Attribute attribute = new Attribute("testAttr", "attr Value");
//        e2.setAttribute(attribute);// set方法会返回元素本身（方法链method chain style）
//        root.addContent(e2);
//        e2.addContent(new Element("subElement").setAttribute("a", "aValue")
//                .setAttribute("x", "xValue").setAttribute("y", "yValue")
//                .setText("textContent"));
//        // 格式化
//        Format format = Format.getPrettyFormat();
//        // Format.getRawFormat()方法，通常用于XML数据的网络传输，因为这种格式会去掉所有不必要的空白，因此能够减少数据量
//        // 可以自己设定一些format的属性
//        format.setIndent("    ");// 把缩进设为四个空格（默认为两个空格）
//        // 输出
//        XMLOutputter out = new XMLOutputter(format);
//        try {
//            out.output(document, new FileOutputStream("javamodule/raw/jdom.xml"));// 文件写出路径
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }


        System.out.println("===============================javaBean 转 xml=================================");
        Book book = new Book();
        book.setCounts("20");
        book.setDownUrl("http://localhost:8080/aaa.xls");
        book.setStartTime("2012-09-09 00:11:11");
        book.setEndTime("2012-09-09 00:11:12");
        book.setFailure("15");
        book.setSuccess("5");
        String xml = null;
        try {
            xml = entityToXml(book);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(xml);

        System.out.println("===============================xml 转 javaBean=================================");
        Element responseDataElement = null;
        try {
            responseDataElement = JdomUtils.string2Root(xml, "utf-8");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element returnMessageElement =  responseDataElement.getChild("returnMessage");
        Element bookElement =  returnMessageElement.getChild("Book");
        String  counts =  bookElement.getChildTextTrim("counts");
        String  downUrl =  bookElement.getChildTextTrim("downUrl");
        String  startTime =  bookElement.getChildTextTrim("startTime");
        String  endTime =  bookElement.getChildTextTrim("endTime");
        String  success =  bookElement.getChildTextTrim("success");
        String  failure =  bookElement.getChildTextTrim("failure");
        Book bookNew = new Book();
        bookNew.setCounts(counts);
        bookNew.setDownUrl(downUrl);
        bookNew.setStartTime(startTime);
        bookNew.setEndTime(endTime);
        bookNew.setSuccess(success);
        bookNew.setFailure(failure);
        System.out.println("Counts="+bookNew.getCounts());
        System.out.println("DownUrl="+bookNew.getDownUrl());
        System.out.println("StartTime="+bookNew.getStartTime());
        System.out.println("EndTime="+bookNew.getEndTime());
        System.out.println("Success="+bookNew.getSuccess());
        System.out.println("Failure="+bookNew.getFailure());


    }

    public static String entityToXml(Book book) throws JDOMException, IOException{
        Element responseData = JdomUtils.createRoot("responseData");
        JdomUtils.addChild(responseData, "returnCode","00001");
        Element returnMessage =JdomUtils.addChild(responseData, "returnMessage");
        Element bookElement = JdomUtils.addChild(returnMessage, "Book");
        JdomUtils.addChild(bookElement, "counts",book.getCounts());
        JdomUtils.addChild(bookElement, "downUrl",book.getDownUrl());
        JdomUtils.addChild(bookElement, "startTime",book.getStartTime());
        JdomUtils.addChild(bookElement, "endTime",book.getEndTime());
        JdomUtils.addChild(bookElement, "success",book.getSuccess());
        JdomUtils.addChild(bookElement, "failure",book.getFailure());
        String xml = JdomUtils.root2String(responseData, "utf-8");
        return xml;
    }

}
