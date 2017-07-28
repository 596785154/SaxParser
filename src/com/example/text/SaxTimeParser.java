package com.example.text;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Result;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.sax.SAXTransformerFactory;  
import javax.xml.transform.sax.TransformerHandler;  
import javax.xml.transform.stream.StreamResult;  
  
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.AttributesImpl;  
import org.xml.sax.helpers.DefaultHandler;  
 
public class SaxTimeParser implements TimeParser {  
      
    @Override  
    public List<time> parse(InputStream is) throws Exception {  
        SAXParserFactory factory = SAXParserFactory.newInstance();  //ȡ��SAXParserFactoryʵ��  
        SAXParser parser = factory.newSAXParser();                  //��factory��ȡSAXParserʵ��  
        MyHandler handler = new MyHandler();                        //ʵ�����Զ���Handler  
        parser.parse(is, handler);                                  //�����Զ���Handler�������������  
        return handler.getTimes();  
    }  
  
    @Override  
    public String serialize(List<time> times) throws Exception {  
        SAXTransformerFactory factory = (SAXTransformerFactory) TransformerFactory.newInstance();//ȡ��SAXTransformerFactoryʵ��  
        TransformerHandler handler = factory.newTransformerHandler();           //��factory��ȡTransformerHandlerʵ��  
        Transformer transformer = handler.getTransformer();                     //��handler��ȡTransformerʵ��  
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");            // ����������õı��뷽ʽ  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");                // �Ƿ��Զ���Ӷ���Ŀհ�  
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");   // �Ƿ����XML����  
          
        StringWriter writer = new StringWriter();  
        Result result = new StreamResult(writer);  
        handler.setResult(result);  
          
        String uri = "";    //���������ռ��URI ��URI��ֵʱ ����Ϊ���ַ���  
        String localName = "";  //�����ռ�ı�������(������ǰ׺) ��û�н��������ռ䴦��ʱ ����Ϊ���ַ���  
          
        handler.startDocument();  
        handler.startElement(uri, localName, "times", null);  
          
        AttributesImpl attrs = new AttributesImpl();    //������Ԫ�ص�������Ϣ  
        char[] ch = null;  
        for (time time : times) {  
            attrs.clear();  //��������б�  
            attrs.addAttribute(uri, localName, "hour", "int",  String.valueOf(time.getHour()));//���һ����Ϊid������(typeӰ�첻��,������Ϊstring)  
            handler.startElement(uri, localName, "time", attrs);    //��ʼһ��bookԪ�� ���������趨��id����  
              
            handler.startElement(uri, localName, "minute", null); //��ʼһ��nameԪ�� û������  
            ch = String.valueOf(time.getminute()).toCharArray();  
            handler.characters(ch, 0, ch.length);   //����nameԪ�ص��ı��ڵ�  
            handler.endElement(uri, localName, "minute");  
              
            handler.startElement(uri, localName, "second", null);//��ʼһ��priceԪ�� û������  
            ch = String.valueOf(time.getSecond()).toCharArray();  
            handler.characters(ch, 0, ch.length);   //����priceԪ�ص��ı��ڵ�  
            handler.endElement(uri, localName, "second");  
            
            handler.startElement(uri, localName, "mesc", null);//��ʼһ��priceԪ�� û������  
            ch = String.valueOf(time.getMesc()).toCharArray();  
            handler.characters(ch, 0, ch.length);   //����priceԪ�ص��ı��ڵ�  
            handler.endElement(uri, localName, "mesc");  
              
            handler.endElement(uri, localName, "time");  
        }  
        handler.endElement(uri, localName, "times");  
        handler.endDocument();  
          
        return writer.toString();  
    }  
      
    //��Ҫ��дDefaultHandler�ķ���  
    private class MyHandler extends DefaultHandler {  
  
        private List<time> times;  
        private time time;  
        private StringBuilder builder;  
          
        //���ؽ�����õ���Book���󼯺�  
        public List<time> getTimes() {  
            return times;  
        }  
          
        @Override  
        public void startDocument() throws SAXException {  
            super.startDocument();  
            times = new ArrayList<time>();  
            builder = new StringBuilder();  
        }  
  
        @Override  
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
            super.startElement(uri, localName, qName, attributes);  
            if (localName.equals("time")) {  
                time = new time();  
            }  
            builder.setLength(0);   //���ַ���������Ϊ0 �Ա����¿�ʼ��ȡԪ���ڵ��ַ��ڵ�  
        }  
          
        @Override  
        public void characters(char[] ch, int start, int length) throws SAXException {  
            super.characters(ch, start, length);  
            builder.append(ch, start, length);  //����ȡ���ַ�����׷�ӵ�builder��  
        }  
          
        @Override  
        public void endElement(String uri, String localName, String qName) throws SAXException {  
            super.endElement(uri, localName, qName);  
            if (localName.equals("hour")) {  
            	time.setHour(Integer.parseInt(builder.toString()));
            } else if (localName.equals("minute")) {  
               time.setMinute(Integer.parseInt(builder.toString()))  ;
            } else if (localName.equals("second")) {  
            	time.setMinute(Integer.parseInt(builder.toString()))  ; 
            }else if (localName.equals("mesc")) {  
            	time.setMinute(Integer.parseInt(builder.toString()))  ; 
            } else if (localName.equals("time")) {  
                times.add(time);  
            }  
        }  
    }  
}  

