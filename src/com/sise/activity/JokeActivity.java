package com.sise.activity;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class JokeActivity extends Activity {
	private ProgressBar bar;
	private TextView wrong;
	private ListView lv;
	private Button change;
	private String type;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_joke);
		init();
		get_joke(type);
		change.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				refresh();				
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>)lv.getItemAtPosition(position);
				String id = map.get("id");
				String title = map.get("title");
				String  content = map.get("content");
				Intent intent = new Intent();
				intent.putExtra("id", id);
				intent.putExtra("title", title);
				intent.putExtra("content", content);
				intent.putExtra("from", "net");
				intent.setClass(JokeActivity.this, DetailActivity.class);
				startActivity(intent);			
			}
		});
	}
	public void init(){
		bar = (ProgressBar)findViewById(R.id.bar);
		wrong = (TextView)findViewById(R.id.wrong);
		lv = (ListView)findViewById(R.id.lv);
		change = (Button)findViewById(R.id.change);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        //new Thread(mRunnable).start();
        
	}
	private void get_joke(String type){
        String url = "http://mavericks0.imwork.net:24572/get_joke";
        RequestQueue mQueue = Volley.newRequestQueue(JokeActivity.this);
        Map<String, String> typeMap = new HashMap<String, String>();
        typeMap.put("type", type);
        JSONObject typeJsonObject = new JSONObject(typeMap);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, typeJsonObject, 
        		new Response.Listener<JSONObject>() {
	            ArrayList<HashMap<String,Object>> jokeArrayList=new ArrayList<HashMap<String,Object>>();	            
					@Override
					public void onResponse(JSONObject resp) {
						try {
							JokeActivity.this.bar.setVisibility(View.GONE);
							//Toast.makeText(JokeActivity.this, String.valueOf(resp.getJSONArray("test").length()), Toast.LENGTH_SHORT).show();
							JSONArray jokeJsonArray = resp.getJSONArray("test");
							for(int i=0; i<jokeJsonArray.length(); i++){
								HashMap<String, Object> joke = new HashMap<String, Object>();
								JSONObject jokeJsonObject = (JSONObject) jokeJsonArray.get(i);
								joke.put("title", jokeJsonObject.get("title"));
								joke.put("content", jokeJsonObject.get("content"));
								joke.put("id", jokeJsonObject.getJSONObject("_id").get("$oid"));
								jokeArrayList.add(joke);								
							}
					        SimpleAdapter mySimpleAdapter=new SimpleAdapter(JokeActivity.this,   
					                jokeArrayList,//数据源   
					                R.layout.item,//ListView内部数据展示形式的布局文件listitem.xml   
					                new String[]{"title", "content"},//HashMap中的两个key值 itemTitle和itemContent   
					                new int[]{R.id.title, R.id.content});/*布局文件listitem.xml中组件的id    
					                                                            布局文件的各组件分别映射到HashMap的各元素上，完成适配*/
					        lv.setAdapter(mySimpleAdapter);
						} catch (JSONException e) {
							
							e.printStackTrace();
						}						
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						JokeActivity.this.bar.setVisibility(View.GONE);
						JokeActivity.this.wrong.setVisibility(View.VISIBLE);
						Toast.makeText(JokeActivity.this, "出错啦", Toast.LENGTH_SHORT).show();
						
					}
				});
        mQueue.add(jsonObjectRequest);	
	}
	private void refresh() { 
		finish(); 
		Intent intent = new Intent(); 
		intent.putExtra("type", type);
		intent.setClass(this, JokeActivity.class);
		startActivity(intent); 
		
	}
//	private Runnable mRunnable = new Runnable() {
//		
//		@Override
//		public void run() {
//			try {
//				Thread.sleep(2000);
//				handler.sendMessage(handler.obtainMessage());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//	};
//    Handler handler = new Handler(){  
//        public void handleMessage(Message msg){  
//            super.handleMessage(msg);  
//            JokeActivity.this.bar.setVisibility(View.GONE);
//        }  
//    };
}
