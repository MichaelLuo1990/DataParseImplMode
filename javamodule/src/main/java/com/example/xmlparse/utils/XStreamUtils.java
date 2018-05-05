package com.example.xmlparse.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Map;

/**
 * Desc XStream 工具类
 * 相关引用libs
 * compile files('libs/xstream-1.4.8.jar')
 * compile files('libs/xmlpull_1_0_5.jar')
 * compile files('libs/xpp3_min-1.1.4c.jar')
 * Created by Michael on 2018/4/23.
 */

public class XStreamUtils {

    /**
     * XML转Bean对象
     * @param xml
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        XStream xstream = new XStream(new DomDriver());
        xstream.registerConverter(new DateConverter());
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(clazz);
        xstream.setClassLoader(clazz.getClassLoader());
        return (T) xstream.fromXML(xml);
    }

    /**
     * 对象(JavaBean、list、map)转XML
     * @param bean
     * @return
     */
    public static String beanToXml(Object bean) {
        XStream xstream = new XStream();
        xstream.registerConverter(new DateConverter());
        xstream.autodetectAnnotations(true);
        return xstream.toXML(bean);
    }

    /**
     * list map 转 xml
     * @param bean
     * @param maps
     * @param aliasArgs
     * @return
     */
    public static String listMapToXml(Object bean, Map<String, Class<?>> maps, String... aliasArgs) {
        XStream xStream = new XStream();
        xStream.registerConverter(new DateConverter());
        xStream.autodetectAnnotations(true);
        for (String alias:aliasArgs) {
            xStream.alias(alias, maps.get(alias));
        }
        return xStream.toXML(bean);
    }

    /**
     * Bean对象转XML(以json格式类型输出）
     * @param bean
     * @param maps 定义编译与相关联类对象
     * @return
     */
    public static String xmlToJson(Object bean, Map<String, Class<?>> maps, String... aliasArgs) {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());//设置Json解析器
        xStream.setMode(XStream.NO_REFERENCES);//设置reference模型,不引用
        for (String alias:aliasArgs) {
            xStream.alias(alias, maps.get(alias));
        }
        return xStream.toXML(bean);
    }
}
