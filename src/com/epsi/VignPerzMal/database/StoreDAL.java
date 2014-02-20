package com.epsi.VignPerzMal.database;

import java.util.AbstractList;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.epsi.VignPerzMal.models.Store;
import com.epsi.VignPerzMal.parser.StoreTags;

public class StoreDAL extends DAL<Store> {

	public StoreDAL(Context context) {
		super(context);
		
		columns = new String[] {StoreTags.ID, StoreTags.CODEMAG, StoreTags.ADDRESS, StoreTags.ZIPCODE, StoreTags.CITY, StoreTags.PHONE, StoreTags.SCHEDULE, StoreTags.FAX, StoreTags.LATITUDE, StoreTags.LONGITUDE };
	}

	@Override
	protected ContentValues convertInContentValues(Store entity) {
		ContentValues values = new ContentValues();
		values.put(StoreTags.ADDRESS, entity.getAddress());
		values.put(StoreTags.CITY, entity.getCity());
		values.put(StoreTags.CODEMAG, entity.getCodeMag());
		values.put(StoreTags.FAX, entity.getFax());
		values.put(StoreTags.LATITUDE, entity.getLatitude());
		values.put(StoreTags.LONGITUDE, entity.getLongitude());
		values.put(StoreTags.LABEL, entity.getName());
		values.put(StoreTags.PHONE, entity.getPhone());
		values.put(StoreTags.SCHEDULE, entity.getSchedule());
		values.put(StoreTags.ZIPCODE, entity.getZipCode());

		return values;
	}

	@Override
	protected Store extractEntity(Cursor cursor) {
		int id = cursor.getInt(0);
		String codeMag = cursor.getString(1);
		String name = cursor.getString(2);
		String address = cursor.getString(3);
		String zipCode = cursor.getString(4);
		String city = cursor.getString(5);
		String phone = cursor.getString(6);
		String schedule = cursor.getString(7);
		String fax = cursor.getString(8);
		double latitude = cursor.getDouble(9);
		double longitude = cursor.getDouble(10);
		
		Store store = new Store(id, codeMag, name, address, zipCode, city, phone, schedule, fax, latitude, longitude);
		return store;
	}
	
	@Override
	public Store get(int id) {
		String whereClause = StoreTags.ID +"=" + id;
		
		Store store = null;
		Cursor cursor = db.query(StoreDatabaseHandler.TABLE_NAME, columns, whereClause, null, null, null, null);
		
		if(cursor.moveToFirst())
			store = extractEntity(cursor);
		
		return store;
	}

	@Override
	public AbstractList<Store> get() {
		AbstractList<Store> stores = new ArrayList<Store>();
		Cursor cursor = db.query(StoreDatabaseHandler.TABLE_NAME, columns, null, null, null, null, null);

		if(cursor.moveToFirst()) {
			do {
				Store store = extractEntity(cursor);
				stores.add(store);
			} while(cursor.moveToNext());
		}

		return stores;
	}
	
	@Override
	public int count() {
		Cursor cursor = db.query(StoreDatabaseHandler.TABLE_NAME, new String[] { StoreTags.ID }, null, null, null, null, null);
		return cursor.getCount();
	}

	@Override
	public long insert(Store entity) {
		ContentValues values = convertInContentValues(entity);
		return db.insert(StoreDatabaseHandler.TABLE_NAME, null, values);
	}

	@Override
	public int update(Store entity) {
		String whereClause = StoreTags.ID +"=" + entity.getId();
		
		ContentValues values = convertInContentValues(entity);	
		return db.update(StoreDatabaseHandler.TABLE_NAME, values, whereClause, null);
	}

	@Override
	public int delete(Store entity) {
		String whereClause = StoreTags.ID +"=" + entity.getId();
		return db.delete(StoreDatabaseHandler.TABLE_NAME, whereClause, null);
	}

	@Override
	public void clear() {
		db.execSQL("DELETE * FROM " + StoreDatabaseHandler.TABLE_NAME);
	}
}
