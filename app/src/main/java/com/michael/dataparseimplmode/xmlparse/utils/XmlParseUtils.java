package com.michael.dataparseimplmode.xmlparse.utils;

import android.util.Xml;

import com.michael.dataparseimplmode.xmlparse.model.Book;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc
 * Created by Michael on 2018/4/21.
 */

public class XmlParseUtils<T> {

    public List<T> parse(InputStream is, T type) throws Exception {
        List<T> books = null;
        Book book = null;

        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    books = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("book")) {
                        book = new Book();
                    } else if (parser.getName().equals("id")) {
                        eventType = parser.next();
                        book.setId(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("name")) {
                        eventType = parser.next();
                        book.setName(parser.getText());
                    } else if (parser.getName().equals("price")) {
                        eventType = parser.next();
                        book.setPrice(Float.parseFloat(parser.getText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("book")) {
                        books.add(type);
                        book = null;      // good
                    }
                    break;
            }
            eventType = parser.next();
        }
        return books;
    }
}
