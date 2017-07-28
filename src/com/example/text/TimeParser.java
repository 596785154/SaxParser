package com.example.text;

import java.io.InputStream;  
import java.util.List;   
public interface TimeParser {  
    /** 
     * 解析输入流 得到Book对象集合 
     * @param is 
     * @return 
     * @throws Exception 
     */  
    public List<time> parse(InputStream is) throws Exception;  
      
    /** 
     * 序列化Book对象集合 得到XML形式的字符串 
     * @param books 
     * @return 
     * @throws Exception 
     */  
    public String serialize(List<time> times) throws Exception;  
}  