package com.sise.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sise.bean.Joke;
import com.sise.util.DBHelper;

public class JokeDAO {
	private SQLiteDatabase db;
	DBHelper helper = null;
	public JokeDAO(Context context){
		helper = new DBHelper(context);
	}
	public JokeDAO(Context cxt, int version) {  
	        helper = new DBHelper(cxt, version);  
	}
	//检查笑话是否存在
	public Boolean isExist(String id){
		db = helper.getWritableDatabase();
		String sql = "select title from joke where id = ?";
		Cursor cursor = db.rawQuery(sql, new String[]{id});
		if(cursor.moveToNext()){
			return true;
		}
		return false;
	}	
	//增加一条笑话
	public void addJoke(Joke joke){
		String sql = "insert into joke (id, title, content) values (?,?,?)";
		db = helper.getWritableDatabase();
		db.execSQL(sql, new Object[]{joke.getId(), joke.getTitle(), joke.getContent()});
	}
    //查询所有笑话
	public ArrayList<HashMap<String,Object>> getAllJoke(){
		ArrayList<HashMap<String,Object>> jokeArrayList=new ArrayList<HashMap<String,Object>>();
		//List<Joke> jokes = new ArrayList<Joke>();
		db = helper.getWritableDatabase();
		String sql = "select * from joke";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			HashMap<String, Object> joke = new HashMap<String, Object>();
			joke.put("id", cursor.getString(cursor.getColumnIndex("id")));
			joke.put("title", cursor.getString(cursor.getColumnIndex("title")));
			joke.put("content", cursor.getString(cursor.getColumnIndex("content")));
			jokeArrayList.add(joke);
		}
		return jokeArrayList;
	}
	//删除
	public void del(String id){
		db = helper.getWritableDatabase();
		String sql = "delete from joke where id = ?";
		db.execSQL(sql, new Object[]{id});
	}
}
