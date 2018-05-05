package com.example.xmlparse;

import com.example.xmlparse.utils.MyXmlUtil;

import java.util.HashMap;

/**
 * Desc
 * Created by Michael on 2018/4/21.
 */

public class MyXmlUtilsTest {
    public static void main(String[] args) {
        String returnStr =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><content_sid>B03RYWO6G3C12A933S970D8CPRK4OS</content_sid><music><sms_num>1065843601</sms_num><sms>Q01PX1M9ZURMTTZTelUydWZKdXl2aEczZkJMUkxwYWs3SDdJRzEtMnlKcTh6NHJCVWFUYXJLNjByVmZnWkVOYmNCLXJzUDBYUzAxRVZCZjl3a0FlLTlhUXE0dGtVSG5PMm0tU0RDMkwyVFBuamQtSmc9QEkxLjBAMTAwMQ==</sms>  </music></response>";
        HashMap<?, ?> m = MyXmlUtil.UnPackageXml(returnStr);
        System.out.println(m.get("music.sms_num"));
        System.out.println(m.get("music.sms"));
    }
}
