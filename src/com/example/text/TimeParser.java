package com.example.text;

import java.io.InputStream;  
import java.util.List;   
public interface TimeParser {  
    /** 
     * ���������� �õ�Book���󼯺� 
     * @param is 
     * @return 
     * @throws Exception 
     */  
    public List<time> parse(InputStream is) throws Exception;  
      
    /** 
     * ���л�Book���󼯺� �õ�XML��ʽ���ַ��� 
     * @param books 
     * @return 
     * @throws Exception 
     */  
    public String serialize(List<time> times) throws Exception;  
}  