package com.epsi.VignPerzMal.database;

import java.util.AbstractList;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storesparser.StoreConstants;

public class StoreDAL extends DAL<Store> {

	public StoreDAL(Context context) {
		super(context);

		columns = new String[]
		{
				StoreConstants.ID,
				StoreConstants.CODEMAG,
				StoreConstants.NAME,
				StoreConstants.ADDRESS,
				StoreConstants.ZIPCODE,
				StoreConstants.CITY,
				StoreConstants.PHONE,
				StoreConstants.SCHEDULE,
				StoreConstants.FAX,
				StoreConstants.LATITUDE,
				StoreConstants.LONGITUDE
		};
	}

	@Override
	protected ContentValues convertInContentValues(Store entity) {

		ContentValues values = new ContentValues();
		values.put(StoreConstants.ADDRESS, entity.getAddress());
		values.put(StoreConstants.CITY, entity.getCity());
		values.put(StoreConstants.CODEMAG, entity.getCodeMag());
		values.put(StoreConstants.FAX, entity.getFax());
		values.put(StoreConstants.LATITUDE, entity.getLatitude());
		values.put(StoreConstants.LONGITUDE, entity.getLongitude());
		values.put(StoreConstants.NAME, entity.getName());
		values.put(StoreConstants.PHONE, entity.getPhone());
		values.put(StoreConstants.SCHEDULE, entity.getSchedule());
		values.put(StoreConstants.ZIPCODE, entity.getZipCode());

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

		db = helper.getReadableDatabase();

		Store store = null;

		String whereClause = StoreConstants.ID +"=" + id;
		Cursor cursor = db.query(StoreDatabaseHandler.TABLE_NAME, columns, whereClause, null, null, null, null);

		if(cursor.moveToFirst())
			store = extractEntity(cursor);

		cursor.close();
		db.close();

		return store;
	}

	@Override
	public AbstractList<Store> get() {

		db = helper.getReadableDatabase();

		AbstractList<Store> stores = null;

		Cursor cursor = db.query(StoreDatabaseHandler.TABLE_NAME, columns, null, null, null, null, null);

		if(cursor.moveToFirst()) {
			stores = new ArrayList<Store>();

			do {
				Store store = extractEntity(cursor);
				stores.add(store);
			} while(cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return stores;
	}

	@Override
	public AbstractList<Store> search(String zipCode) {

		db = helper.getReadableDatabase();

		AbstractList<Store> stores = null;

		String whereClause = StoreConstants.ZIPCODE +"=" + zipCode;
		Cursor cursor = db.query(StoreDatabaseHandler.TABLE_NAME, columns, whereClause, null, null, null, null);

		if(cursor.moveToFirst()) {
			stores = new ArrayList<Store>();

			do {
				Store store = extractEntity(cursor);
				stores.add(store);
			} while(cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return stores;
	}

	@Override
	public int count() {

		db = helper.getReadableDatabase();

		Cursor cursor = db.query(StoreDatabaseHandler.TABLE_NAME, new String[] { StoreConstants.ID }, null, null, null, null, null);
		int count = cursor.getCount();

		cursor.close();
		db.close();

		return count;
	}

	@Override
	public long insert(Store entity) {

		ContentValues values = convertInContentValues(entity);

		db = helper.getWritableDatabase();
		long insertedId = db.insert(StoreDatabaseHandler.TABLE_NAME, null, values);

		db.close();

		return insertedId;
	}

	@Override
	public AbstractList<Long> insert(AbstractList<Store> entities) {

		AbstractList<ContentValues> valuesList = new ArrayList<ContentValues>();

		for(Store entity : entities) {
			ContentValues values = convertInContentValues(entity);
			valuesList.add(values);
		}

		db = helper.getWritableDatabase();

		AbstractList<Long> insertedIds = new ArrayList<Long>();

		for(ContentValues values : valuesList) {
			long insertedId = db.insert(StoreDatabaseHandler.TABLE_NAME, null, values);
			insertedIds.add(insertedId);
		}

		db.close();

		return insertedIds;
	}

	@Override
	public int update(Store entity) {

		ContentValues values = convertInContentValues(entity);

		db = helper.getWritableDatabase();
		String whereClause = StoreConstants.ID +"=" + entity.getId();
		int nbRowsAffected = db.update(StoreDatabaseHandler.TABLE_NAME, values, whereClause, null);

		db.close();

		return nbRowsAffected;
	}

	@Override
	public int delete(Store entity) {

		String whereClause = StoreConstants.ID +"=" + entity.getId();

		db = helper.getWritableDatabase();
		int nbRowsAffected = db.delete(StoreDatabaseHandler.TABLE_NAME, whereClause, null);

		db.close();

		return nbRowsAffected;
	}

	@Override
	public void clear() {

		db = helper.getWritableDatabase();
		db.execSQL("DELETE FROM " + StoreDatabaseHandler.TABLE_NAME);
		db.close();
	}
}
