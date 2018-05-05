package com.example.xmlparse;

import com.example.xmlparse.utils.Dom4JUtils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

/**
 * Desc
 * Created by Michael on 2018/4/27.
 */

public class Dom4JUtilsTest {

    private static String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<root>\n" +
            "    <!--This is my comments-->\n" +
            "    <hello sohu=\"www.sohu.com\" />\n" +
            "    <world testAttr=\"attr Value\">\n" +
            "        <subElement a=\"aValue\" x=\"xValue\" y=\"yValue\">textContent</subElement>\n" +
            "    </world>\n" +
            "</root>";

    public static void main(String args[]) {
        System.out.println("========================通过文件的路径获取xml的document对象============================");
        testGetXMLByFilePath();
        System.out.println("========================通过xml字符串获取document文档============================");
        testGetXMLByString();
    }

    /**
     * 测试通过xml字符串获取document文档
     */
    private static void testGetXMLByString() {
        try {
            Document document = Dom4JUtils.getXMLByString(xmlStr);
            System.out.println(document);
            System.out.println("获取root节点" + document.getRootElement());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试通过文件路径读取xml
     */
    private static void testGetXMLByFilePath() {
        Document document = Dom4JUtils.getXMLByFilePath("javamodule/raw/friends.xml");
        System.out.println("获取的document：     " + document);
        System.out.println("获取相关节点对象");
        // 查询我所有的小学的结点
        Element root = document.getRootElement();
        List<Element> elements = root.element("xiaoxue").elements();
        System.out.println(elements.get(0).getText());
        List<Element> studs = elements.get(1).elements();
        for(Element stu:studs){
            System.out.print("-->"+stu.element("name").getText()+" ");
            System.out.println(stu.element("gender").getText());
        }
        for(Iterator iterator = root.element("chuzhong").elementIterator(); iterator.hasNext();){
            Element element = (Element) iterator.next();
            if(element.getName().equals("oc")){
                System.out.println(element.getText());
            }else if(element.getName().equals("list")){
                for(Iterator iterator2 = element.elements("stud").iterator();iterator2.hasNext();){
                    Element stuElement = (Element) iterator2.next();
                    System.out.print("-->"+stuElement.element("name").getText());
                    System.out.println(stuElement.element("gender").getText());
                }
            }else if(element.getName().equals("anlian")){
                for(Iterator iterator2 = element.elementIterator();iterator2.hasNext();){
                    Element current = (Element) iterator2.next();
                    if(current.getName().equals("xihuan")){
                        for(Iterator iterator3 = current.element("list").elementIterator();iterator3.hasNext();){
                            Element sElement = (Element) iterator3.next();
                            System.out.print("-->"+sElement.element("name").getText());
                            System.out.println(sElement.element("key").getText());
                        }
                    }else if(current.getName().equals("feichangxihuan")){
                        Element element2 = current.element("stud");
                        System.out.print("-->"+element2.element("name").getText());
                        System.out.println(element2.element("des").getText());
                    }
                }
            }
        }
    }
}

