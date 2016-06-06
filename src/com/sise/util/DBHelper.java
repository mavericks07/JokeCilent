package com.sise.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private final static String DB_NAME ="test.db";//数据库名  
    private final static int VERSION = 1;//版本号  
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
    public DBHelper(Context context){
    	this(context, DB_NAME, null, VERSION);
    }
    //版本变更时  
    public DBHelper(Context cxt,int version) {  
        this(cxt,DB_NAME,null,version);  
    }     
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table joke(id varchar(30) primary key, title varchar(20), content varchar(2000))";
        db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
