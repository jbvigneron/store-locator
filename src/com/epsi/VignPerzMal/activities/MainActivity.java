package com.epsi.VignPerzMal.activities;
 
import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.epsi.VignPerzMal.controllers.JSONParserController;
import com.epsi.VignPerzMal.storelocator.R;
import com.epsi.VignPerzMal.storelocator.SingleContactActivity;
 
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends ListActivity {
 
    private ProgressDialog pDialog;
 
    private static final String TAG_CODEMAG = "CodeMAG";
    private static final String TAG_LIBELLE = "Libelle";
    private static final String TAG_ADRESSE1 = "Adresse1";
    private static final String TAG_CP = "CodePostal";
    private static final String TAG_VILLE = "Ville";
    private static final String TAG_PHONE = "Telephone";
    private static final String TAG_HORAIRES = "Horaires";
    private static final String TAG_FAX = "Fax";
    private static final String TAG_LONGITUDE = "Latitude";
    private static final String TAG_LATITUDE = "Fax";
    private boolean loadStores;
 
    // contacts JSONArray
    JSONArray stores = null;
 
    // Hashmap for ListView
    ArrayList<HashMap<String, String>> storeList;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
        storeList = new ArrayList<HashMap<String, String>>();
 
        ListView lv = getListView();
 
        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String libelle = ((TextView) view.findViewById(R.id.libelle))
                        .getText().toString();
                String adresse = ((TextView) view.findViewById(R.id.adresse))
                        .getText().toString();
                String phone = ((TextView) view.findViewById(R.id.phone))
                        .getText().toString();
 
                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(), SingleContactActivity.class);
                in.putExtra(TAG_LIBELLE, libelle);
                in.putExtra(TAG_ADRESSE1, adresse);
                in.putExtra(TAG_PHONE, phone);
                startActivity(in);
 
            }
        });
 
        // Calling async task to get json
        if(!loadStores){
        	new GetStores().execute();
        }
        
    }
    
    public void onStart(){
    	super.onStart();
    	Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
    }
    
    public void onStop(){
    	super.onStop();
    	Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
    	//loadContacts = false;
    }
    
    public void onPause(){
    	super.onPause();
    	Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
    }
 
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetStores extends AsyncTask<Void, Void, Void> {
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            JSONParserController sh = new JSONParserController();
 
            
			// Making a request to url and getting response
            String jsonStr = sh.getJSONParser().readJSONUrl();
 
            Log.d("Response: ", "> " + jsonStr);
 
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                     
                    // Getting JSON Array node
                    
                    JSONObject sto =  jsonObj.getJSONObject("Magasins");
                   stores = sto.getJSONArray("Magasin");
                    
//                    String date = jsonObj.getString("Libelle");
//                    Log.d("Test: ", "> " + date);
                    // looping through All Stores
                    for (int i = 0; i < stores.length(); i++) {
                        JSONObject c = stores.getJSONObject(i);
                         
                        String codeMag = c.getString(TAG_CODEMAG);
                        String libelleMag = c.getString(TAG_LIBELLE);
                        String adresseMag = c.getString(TAG_ADRESSE1);
                        c.getString(TAG_CP);
                        
                        c.getString(TAG_VILLE);
                        c.getString(TAG_HORAIRES);
                        String phoneMag = c.getString(TAG_PHONE);
                        c.getString(TAG_FAX);
                        c.getString(TAG_LONGITUDE);
                        c.getString(TAG_LATITUDE);

                        
 
                        // tmp hashmap for single contact
                        HashMap<String, String> store = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        store.put(TAG_CODEMAG, codeMag);
                        store.put(TAG_LIBELLE, libelleMag);
                        store.put(TAG_ADRESSE1, adresseMag);
                        store.put(TAG_PHONE, phoneMag);
 
                        // adding contact to contact list
                        storeList.add(store);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
 
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, storeList,
                    R.layout.list_item, new String[] { TAG_LIBELLE, TAG_ADRESSE1,
                            TAG_PHONE }, new int[] { R.id.libelle,
                            R.id.adresse, R.id.phone });
 
            setListAdapter(adapter);
        }
 
    }
 
}