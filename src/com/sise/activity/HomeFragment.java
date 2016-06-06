package com.sise.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment{
    private Button bt1, bt2, bt3, bt4, bt5, bt6;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		bt1 = (Button)view.findViewById(R.id.bt1);
		bt2 = (Button)view.findViewById(R.id.bt2);
		bt3 = (Button)view.findViewById(R.id.bt3);
		bt4 = (Button)view.findViewById(R.id.bt4);
		bt5 = (Button)view.findViewById(R.id.bt5);
		bt6 = (Button)view.findViewById(R.id.bt6);
		bt1.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", "冷笑话");
				intent.setClass(getActivity(), JokeActivity.class);
				startActivity(intent);
			}
		});
		bt2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", "社会");
				intent.setClass(getActivity(), JokeActivity.class);
				startActivity(intent);
			}
		});		
		bt3.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", "夫妻");
				intent.setClass(getActivity(), JokeActivity.class);
				startActivity(intent);
			}
		});	
		bt4.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", "儿童");
				intent.setClass(getActivity(), JokeActivity.class);
				startActivity(intent);
			}
		});	
		bt5.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", "校园");
				intent.setClass(getActivity(), JokeActivity.class);
				startActivity(intent);
			}
		});	
		bt6.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("type", "职场");
				intent.setClass(getActivity(), JokeActivity.class);
				startActivity(intent);
			}
		});	
		
		return view;
	}
    
	
}
