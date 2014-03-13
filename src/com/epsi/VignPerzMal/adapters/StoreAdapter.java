package com.epsi.VignPerzMal.adapters;

import java.util.List;

import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storelocator.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StoreAdapter extends ArrayAdapter<Store> {

	private final Context context;
	private final List<Store> stores;
	
	public StoreAdapter(Context context, List<Store> stores) {
		
		super(context, R.layout.list_stores, stores);
		this.context = context;
		this.stores = stores;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_stores, parent, false);
		
		TextView tvListName = (TextView) rowView.findViewById(R.id.tvListName);
		tvListName.setText(stores.get(position).getName());
		
		TextView tvListAddress = (TextView) rowView.findViewById(R.id.tvListAddress);
		tvListAddress.setText(stores.get(position).getAddress());
		
		TextView tvListPhone = (TextView) rowView.findViewById(R.id.tvListPhone);
		tvListPhone.setText(stores.get(position).getPhone());
 
		return rowView;
	}
}