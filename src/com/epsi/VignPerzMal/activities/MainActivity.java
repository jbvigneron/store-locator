package com.epsi.VignPerzMal.activities;

import com.epsi.VignPerzMal.storelocator.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tvZipCode;
	private ImageButton btnSearch;
	private ImageButton btnAroundMe;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		// Get controls
		tvZipCode = (TextView)findViewById(R.id.tvZipCode);
		btnSearch = (ImageButton)findViewById(R.id.btnSearch);
		btnAroundMe = (ImageButton)findViewById(R.id.btnAroundMe);
		
		// Create events
		btnSearch.setOnClickListener(this);
		btnAroundMe.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == btnSearch) {
			// TODO: Launch search by ZIP Code
		}
		else if(v == btnAroundMe) {
			// TODO: Launch search around the user
		}
	}
}