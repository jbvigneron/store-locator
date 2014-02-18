package com.epsi.VignPerzMal.storelocator;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.epsi.VignPerzMal.storelocator.R;

public class SingleContactActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_LIBELLE = "Libelle";
	private static final String TAG_ADRESSE = "Adresse1";
	private static final String TAG_PHONE = "Telephone";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String libelle = in.getStringExtra(TAG_LIBELLE);
        String adresse = in.getStringExtra(TAG_ADRESSE);
        String phone = in.getStringExtra(TAG_PHONE);
        
        // Displaying all values on the screen
        TextView lblLibelle = (TextView) findViewById(R.id.libelle);
        TextView lblAdresse = (TextView) findViewById(R.id.adresse);
        TextView lblPhone = (TextView) findViewById(R.id.phone);
        
        lblLibelle.setText(libelle);
        lblAdresse.setText(adresse);
        lblPhone.setText(phone);
    }
}
