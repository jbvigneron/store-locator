package com.epsi.VignPerzMal.database;

import java.util.AbstractList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DAL<T> {
	
	protected static SQLiteOpenHelper helper;
	protected static SQLiteDatabase db;
	
	protected String[] columns;
	
	protected abstract ContentValues convertInContentValues(T entity);
	protected abstract T extractEntity(Cursor cursor);
	
	public abstract T get(int id);
	public abstract AbstractList<T> get();
	public abstract AbstractList<T> search(String zipCode);
	public abstract int count();
	public abstract long insert(T entity);
	public abstract AbstractList<Long> insert(AbstractList<T> entity);
	public abstract int update(T entity);
	public abstract int delete(T entity);
	public abstract void clear();
	
	protected DAL(Context context) {
		helper = new StoreDatabaseHandler(context);
	}
}