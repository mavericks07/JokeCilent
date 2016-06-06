package com.sise.activity;

import com.sise.bean.Joke;
import com.sise.dao.JokeDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
	TextView title, content;
	Button collect;
	Button del;
	Joke joke;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		init();
		//收藏
		collect.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				JokeDAO jokeDAO = new JokeDAO(DetailActivity.this);
				if(jokeDAO.isExist(joke.getId())){
					Toast.makeText(DetailActivity.this, "你已经收藏过了~~", Toast.LENGTH_SHORT).show();
				}else {
					jokeDAO.addJoke(joke);
					Toast.makeText(DetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();						
				}
			
			}
		});
		//删除
		del.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				JokeDAO jokeDAO = new JokeDAO(DetailActivity.this);
				jokeDAO.del(joke.getId());
				Toast.makeText(DetailActivity.this, "已经删除了~~", Toast.LENGTH_SHORT).show();
				DetailActivity.this.finish();
			}
		});

	}
	
	private void init(){
		title = (TextView)findViewById(R.id.title);
		content = (TextView)findViewById(R.id.content);
		collect = (Button)findViewById(R.id.collect);
		del = (Button)findViewById(R.id.del);
		Intent intent = getIntent();
		String id = intent.getStringExtra("id");
		String titleString = intent.getStringExtra("title");
		String contentString = intent.getStringExtra("content");
		String from = intent.getStringExtra("from");
		if(from.equals("net")){
			collect.setVisibility(View.VISIBLE);
		}
		else {
			del.setVisibility(View.VISIBLE);
		}
		joke = new Joke(id, titleString, contentString);
		title.setText(titleString);
		content.setText(contentString);
	}
}
