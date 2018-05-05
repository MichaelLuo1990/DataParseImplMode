package com.michael.dataparseimplmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.michael.dataparseimplmode.xmlparse.XmlParseActivity;


/**
 * Desc 数据解析测试（json、xml、HTML）
 * json
 * json-lib（java后台），jsonp（web前端相关）、Json-smart（号称解析速度最快 but 使用sample实例少，不易查找开发引用中出现的问题）
 * Jackson(第三方开源)，Gson（Google），Fastjson（Alibaba）  org.json （Android自带，解析效率/速度慢，淘汰）    moshi（square，实际开发使用较少）
 * xml
 * SAX、DOM、PULL方式
 * 第三方开源
 * XStream、jdom、dom4j
 * html
 * htmlparser-年代久远、文档较少
 * jsoup-使用方便（recommend）
 * Created by Michael on 2018/4/17.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv_main);
        String[] array = {
                "JacksonUtils-第三方开源（com.fasterxml.jackson.core）",
                "GsonUtils-gson解析（google）",
                "FastJsonUtils-FastJson解析（Alibaba）",
                "MoshiJsonTest-不常用，简单了解/测试（square)",
                "Xml解析（SAX、DOM、PULL方式）测试",
                "Xml解析拓展-XStream、jdom、dom4j(第三方开源jar包实现)",
                "Html数据抓取解析-htmlparser",
                "Html数据抓取解析-jsoup"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 4) {
                    Intent intent = new Intent(MainActivity.this, XmlParseActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "javaModule中实现/测试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
