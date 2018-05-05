package com.example.xmlparse.utils;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Desc
 * Created by Michael on 2018/4/24.
 */

public class JdomUtils {

    public JdomUtils() {
    }

    public static Element createRoot(String rootName) {
        Element root = new Element(rootName);
        return root;
    }

    public static Element addChild(Element parent, String childName) {
        Element child = new Element(childName);
        parent.addContent(child);
        return child;
    }

    public static void addChild(Element parent, Element childName) {
        parent.addContent(childName);
    }

    public static Element addChild(Element parent, String childName,
                                   String childValue) {
        Element child = new Element(childName);
        child.setText(childValue != null ? childValue : "");
        parent.addContent(child);
        return child;
    }

    public static String root2StringWithoutXMLDeclaration(Element root,
                                                          String encoding) throws JDOMException, IOException {
        return root2StringWithoutXMLDeclaration(root, encoding, false);
    }

    public static String root2StringWithoutXMLDeclaration(Element root,
                                                          String encoding, boolean formated) throws JDOMException,
            IOException {
        XMLOutputter outputter = new XMLOutputter();
        Format newFormat = Format.getPrettyFormat();
        newFormat.setEncoding(encoding);
        newFormat.setIndent("    ");
        newFormat.setExpandEmptyElements(true);
        outputter.setFormat(newFormat);
        if (formated) {
            String msg = outputter.outputString(root);
            root = string2Root(msg, encoding);
        }
        return outputter.outputString(root);
    }

    public static String root2String(Element root, String encoding)
            throws JDOMException, IOException

    {
        return root2String(root, encoding, false);
    }

    public static String root2String(Element root, String encoding,
                                     boolean formated) throws JDOMException, IOException

    {
        XMLOutputter outputter;
        Document document;
        outputter = new XMLOutputter();
        Format newFormat = Format.getPrettyFormat();
        newFormat.setEncoding(encoding);
        newFormat.setIndent("    ");
        newFormat.setTextMode(org.jdom.output.Format.TextMode.NORMALIZE);
        outputter.setFormat(newFormat);
        if (formated) {
            String msg = outputter.outputString(root);
            root = string2Root(msg, encoding);
        }
        document = null;
        if (root.getParent() != null)
            root.detach();
        document = new Document(root);
        return outputter.outputString(document);

    }

    public static Element string2Root(String content, String encoding)
            throws JDOMException, IOException

    {
        Document document = string2Document(content, encoding);
        return document.getRootElement();
    }

    private static Document string2Document(String content, String encoding)
            throws JDOMException, IOException

    {
        int startPoint = content.indexOf("<?xml");
        if (-1 != startPoint)
            content = content.substring(startPoint);
        if (-1 == content.indexOf("<?xml"))
            content = (new StringBuilder("<?xml version=\"1.0\" encoding = \"")).append(encoding).append("\"?>").append(content).toString();
        ByteArrayInputStream bais;
        SAXBuilder builder;
        bais = new ByteArrayInputStream(content.getBytes(encoding));
        builder = new SAXBuilder();
        return builder.build(bais);
    }
}
