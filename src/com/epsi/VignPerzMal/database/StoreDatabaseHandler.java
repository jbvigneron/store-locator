package com.epsi.VignPerzMal.database;

import com.epsi.VignPerzMal.parser.StoreTags;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class StoreDatabaseHandler extends SQLiteOpenHelper {
	
	private static int DB_VERSION = 1;
	private static String DB_NAME = "StoreLocator.db";
	public static String TABLE_NAME = "table_stores";
 
	private String scriptCreateDb = "CREATE TABLE " 
	+ TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ StoreTags.CODEMAG + " TEXT NOT NULL, "
	+ StoreTags.LABEL + " TEXT NOT NULL, " 
	+ StoreTags.ADDRESS + " TEXT NOT NULL, "
	+ StoreTags.ZIPCODE + " TEXT NOT NULL, "
	+ StoreTags.CITY + " TEXT NOT NULL, "
	+ StoreTags.PHONE + " TEXT, "
	+ StoreTags.SCHEDULE + " TEXT, "
	+ StoreTags.FAX + " TEXT, "
	+ StoreTags.LATITUDE + " REAL NOT NULL, "
	+ StoreTags.LONGITUDE + " REAL NOT NULL);";
 
	public StoreDatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(scriptCreateDb);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE_NAME + ";");
		onCreate(db);
	}
}