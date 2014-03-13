package com.epsi.VignPerzMal.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.epsi.VignPerzMal.storelocator.R;
import com.epsi.VignPerzMal.storesparser.StoreConstants;

public class DisplayStoreActivity  extends Activity implements OnClickListener {
	
	private TextView lblLabel;
	private TextView lblAddress;
	private TextView lblPhone;
	private ImageButton imgVoiceCall;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_single_contact);
        
        // Get all activity controls
        lblLabel = (TextView) findViewById(R.id.tvStoreName);
        lblAddress = (TextView) findViewById(R.id.tvAddress);
        lblPhone = (TextView) findViewById(R.id.tvPhoneNumber);
        imgVoiceCall = (ImageButton) findViewById(R.id.imgVoiceCall);
        
        // Create events
        imgVoiceCall.setOnClickListener(this);
        
        displayValues();
    }
	
	private void displayValues() {
        // getting intent data
        Intent in = getIntent();
        
        // Get values from precedent intents
        String label = in.getStringExtra(StoreConstants.LABEL);
        String address = in.getStringExtra(StoreConstants.ADDRESS);
        String phone = in.getStringExtra(StoreConstants.PHONE);

        // Set values
        lblLabel.setText(label);
        lblAddress.setText(address);
        lblPhone.setText(phone);
	}

	@Override
	public void onClick(View v) {
		// Call image
		if(v == imgVoiceCall) {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:" + lblPhone.getText()));
			startActivity(callIntent);
		}
	}
}
