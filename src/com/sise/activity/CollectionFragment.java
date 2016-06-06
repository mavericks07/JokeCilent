package com.sise.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sise.bean.Joke;
import com.sise.dao.JokeDAO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class CollectionFragment extends Fragment {
	private ListView lv;
	private ArrayList<HashMap<String,Object>> jokeArrayList;
	private SimpleAdapter mySimpleAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_collection, null);
		lv = (ListView)view.findViewById(R.id.lv);
		setData();       
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setData();
	}



	public void setData(){
		JokeDAO jokeDAO = new JokeDAO(getActivity());
		jokeArrayList = jokeDAO.getAllJoke();
        mySimpleAdapter=new SimpleAdapter(getActivity(),   
                jokeArrayList,   
                R.layout.item, 
                new String[]{"title", "content"},
                new int[]{R.id.title, R.id.content});	
        lv.setAdapter(mySimpleAdapter);
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
				intent.putExtra("from", "local");
				intent.setClass(getActivity(), DetailActivity.class);
				startActivity(intent);			
			}
		});
	}
	
}
