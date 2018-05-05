package com.michael.dataparseimplmode.xmlparse.parser;

import java.io.InputStream;
import java.util.List;

public interface IParser<T> {
    /** 
     * 解析输入流 得到Book对象集合 
     * @param is 
     * @return 
     * @throws Exception 
     */
    List<T> parse(InputStream is) throws Exception;
      
    /** 
     * 序列化Book对象集合 得到XML形式的字符串 
     * @param obj
     * @return 
     * @throws Exception 
     */
    String serialize(List<T> obj) throws Exception;
}
