package com.michael.dataparseimplmode.xmlparse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.dataparseimplmode.R;
import com.michael.dataparseimplmode.xmlparse.parser.DomParser;
import com.michael.dataparseimplmode.xmlparse.parser.IParser;
import com.michael.dataparseimplmode.xmlparse.parser.PullParser;
import com.michael.dataparseimplmode.xmlparse.parser.SaxParser;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Desc xml解析测试（Sax、Pull、Dom实现方式）
 * Created by Michael on 2018/4/17.
 */
public class XmlParseActivity extends Activity {

    private List<Object> objects;

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parse);
    }

    /**
     * 读取xml文件，解析xml
     *
     * @param view
     */
    public void readParseXmlClick(View view) {
        TextView tvDom = (TextView) findViewById(R.id.tv_parse_xml_by_dom);
        String parseXmlByDom = parseXml("books.xml", new DomParser());
        tvDom.setText("dom方式读取解析结果：\n" + parseXmlByDom);

        TextView tvSax = (TextView) findViewById(R.id.tv_parse_xml_by_sax);
        String parseXmlBySax = parseXml("books.xml", new SaxParser());
        tvSax.setText("sax方式读取解析结果：\n" + parseXmlBySax);

        TextView tvPull = (TextView) findViewById(R.id.tv_parse_xml_by_pull);
        String parseXmlByPull = parseXml("books.xml", new PullParser());
        tvPull.setText("pull方式读取解析结果：\n" + parseXmlByPull);

    }

    /**
     * 解析xml
     *
     * @param srcFileName 解析元文件
     * @param parserType  解析方式
     * @return
     */
    public String parseXml(String srcFileName, IParser parserType) {
        InputStream is;
        String bks = "";
        try {
            is = getAssets().open(srcFileName);
            List<Object> objects = parserType.parse(is);
            setObjects(objects);
            for (Object obj : objects) {
                bks += obj.toString() + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
            bks += "读取解析异常：" + e.getMessage();
        }
        return bks;
    }

    /**
     * 序列化写入xml文件   文件写出路径： data/data/<your package>/files
     *
     * @param view
     */
    public void writeXmlClick(View view) {
        if (objects != null && objects.size() > 0) {
            TextView tvDom = (TextView) findViewById(R.id.tv_write_xml_by_dom);
            String isSuccess = writeXml(new DomParser(), getObjects(), "book.xml");
            tvDom.setText(isSuccess);

            TextView tvSax = (TextView) findViewById(R.id.tv_write_xml_by_sax);
            String isSuccess1 = writeXml(new SaxParser(), getObjects(), "book1.xml");
            tvSax.setText(isSuccess1);

            TextView tvPull = (TextView) findViewById(R.id.tv_write_xml_by_pull);
            String isSuccess2 = writeXml(new PullParser(), getObjects(), "book2.xml");
            tvPull.setText(isSuccess2);
        } else {
            Toast.makeText(this, "序列化对象为null，请先解析xml转ListBean", Toast.LENGTH_SHORT).show();
        }
    }

    public String writeXml(IParser iParser, List<Object> objects, String outputFileName) {
        String str;
        try {
            String xml = iParser.serialize(objects);
            FileOutputStream fos = openFileOutput(outputFileName, Context.MODE_PRIVATE);
            fos.write(xml.getBytes("UTF-8"));
            str = "写入成功，文件写出路径：data/data/<your package>/files/" + outputFileName;
        } catch (Exception e) {
            str = "写入异常：" + e.getMessage();
        }
        return str;
    }

}
