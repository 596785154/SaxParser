package com.example.text;

import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.util.List;  
  
import android.app.Activity;  
import android.content.Context;  
import android.os.Bundle;  
import android.util.Log;  
import android.view.View;  
import android.widget.Button;  
  
public class MainActivity extends Activity {  
      
    private static final String TAG = "XML";  
      
    private TimeParser parser;  
    private List<time> times;  
      
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
          
        Button readBtn = (Button) findViewById(R.id.read);
        Button writeBtn = (Button) findViewById(R.id.write);  
          
        readBtn.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                try {  
                    InputStream is = getAssets().open("times.xml");  
                    parser = new SaxTimeParser();  //创建SaxBookParser实例  
                    times = parser.parse(is);  //解析输入流  
                    for (time time: times) {  
                        Log.i(TAG, time.toString());  
                    }  
                } catch (Exception e) {  
                    Log.e(TAG,e.getMessage());  
                }  
            }  
        });  
        writeBtn.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                try {  
                    String xml = parser.serialize(times);  //序列化  
                    FileOutputStream fos = openFileOutput("times.xml", Context.MODE_PRIVATE);  
                    fos.write(xml.getBytes("UTF-8"));  
                } catch (Exception e) {  
                    Log.e(TAG, e.getMessage());  
                }  
            }  
        });  
    }  
}  