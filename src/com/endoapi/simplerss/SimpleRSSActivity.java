package com.endoapi.simplerss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.endoapi.simplerss.parser.model.Message;
import com.endoapi.simplerss.parser.rss.RssFeedParser;

public class SimpleRSSActivity extends Activity {
    /** Called when the activity is first created. */
   //https://daynatem@github.com/daynatem/SimpleAndroidRSS.git
    
    private RssFeedParser rss;
    private ProgressDialog progressDlg;
    private List<Map<String, String>>data;
    
    
    private final Handler progressHandler = new Handler(){

		@Override
		public void handleMessage(android.os.Message msg) {			
			super.handleMessage(msg);
			if(msg.obj!= null){
				List<HashMap<String,String>> data = (List<HashMap<String, String>>) msg.obj;
				setData(data);
				progressDlg.dismiss();
			}
		}
    	
    };
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        rss = new RssFeedParser("http://api.twitter.com/1/statuses/public_timeline.rss");
        
        setTitle("Twitter public timeline");
        
        ((Button)findViewById(R.id.reloadBTN)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				loadingData();
				
			}
		});
        
        
        ListView view = (ListView) findViewById(R.id.listRss);
        view.setOnItemClickListener(new OnItemClickListener() {
			
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			
				
				
				
			}
		});
        
    }
    
    
    public void loadingData(){
    	progressDlg = ProgressDialog.show(this, "", "Please wait", true);
    	
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				rss.parse();
				android.os.Message msg = progressHandler.obtainMessage();
				msg.obj = rss.getMessages();
				progressHandler.sendMessage(msg);
				
				
			}
		}).start();
    }
    
    public void setData (List<HashMap<String, String>> data){
    	
  
    	
    	SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),data,R.layout.main_item_two_line_row, 
    			new String[] {Message.USER,Message.TITLE}, new int[] {R.id.userTxt,R.id.titleTxt});
    	ListView view = (ListView) findViewById(R.id.listRss);
    	view.setAdapter(adapter);
    }
    
    
    
    
}