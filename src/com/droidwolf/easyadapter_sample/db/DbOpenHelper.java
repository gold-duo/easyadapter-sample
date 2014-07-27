package com.droidwolf.easyadapter_sample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
	final static String NAME = "test.db";
	final static int VERSION = 1;
	public DbOpenHelper(Context context) {
		this(context, NAME, null, VERSION);
	}
	public DbOpenHelper(Context context, String name, CursorFactory factory, 	int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		PersonDao.createTable(db);
		addPersons(db);
	}
	
	private void addPersons(SQLiteDatabase db){
		for(int i=0;i<300;i++){
			Person p= new Person();
			p.Name=i+"_P";
			PersonDao.insert0(db, p);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		PersonDao.dropTable(db);
	}

}
