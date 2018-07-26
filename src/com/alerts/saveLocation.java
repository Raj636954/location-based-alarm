package com.alerts;

import java.text.DecimalFormat;
import java.text.NumberFormat;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class saveLocation extends Activity {
	
	
	
	 private LocationManager locationManager;
	 private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
		private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
		
		String lat;
		String lng;
		private static final NumberFormat nf = new DecimalFormat("##.########");
		String latfin1,lonfin1;
		
		
		public class MyLocationListener implements LocationListener {
	    	public void onLocationChanged(Location location) {

	    	}
	    	public void onStatusChanged(String s, int i, Bundle b) {			
	    	}
	    	public void onProviderDisabled(String s) {
	    	}
	    	public void onProviderEnabled(String s) {			
	    	}
	    }
		
		
		
		
		public void btnAddAlertClick(View dialogView)
		{
			
			Intent saveLocation = new Intent(saveLocation.this,NewAlert.class);
			startActivity(saveLocation);
			
		}
		
		
		
		
		
	 	public void btnSaveClick(View dialogView) {
	        // Do stuff
	 	final EditText x= (EditText)findViewById(R.id.edtLocationName);
	 	final Spinner spin= (Spinner)findViewById(R.id.spnCategory);
     	
	 	DBAdapter db3 = new DBAdapter(this);
     	long id;
     	
     	
     	Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
     	
     	latfin1=nf.format(location.getLatitude());
     	lonfin1=nf.format(location.getLongitude());
     	
     	
     	//System.out.println("ggggooooooooooooooo"+latfin1+"aaaaaa"+lonfin1);
     	
     	db3.open();
     	
     	
     	
     	
     	
     	String strTextValue = x.getText().toString();
     	Cursor mCursor = (Cursor) spin.getSelectedItem();
     	Log.d("!!", mCursor.getString(0));
     	
     	//col 0 -- category_id
       id =db3.insertLocation(strTextValue, mCursor.getString(0),latfin1,lonfin1);
     	//id =db3.insertLocation(strTextValue, mCursor.getString(0),nf.format(lat),nf.format(lng));
     	//id =db3.insertLocation(strTextValue, mCursor.getString(0),Double.parseDouble(lat),lonfin1=Double.parseDouble(lng));
     	
     	Log.i("log","strTextValue " +strTextValue);
         
         db3.close();
         
		//Intent saveLocation = new Intent(saveLocation.this,myLocation.class);
		//startActivity(saveLocation);
		Toast.makeText(getApplicationContext(), "Location \""+strTextValue+ "\" saved " , Toast.LENGTH_LONG).show();


	    }
	
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	   
	        setContentView(R.layout.mark_location_get_data);
	        DBAdapter db3 = new DBAdapter(this);
	       // Intent sourceIntent = getIntent();		
			//lat = String.valueOf(sourceIntent.getIntExtra("latitude", 0));
			//lng = String.valueOf(sourceIntent.getIntExtra("longitude", 0));
	        
	        
	        //to get current location
	        
	        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	        locationManager.requestLocationUpdates(
	                		LocationManager.GPS_PROVIDER, 
	                		MINIMUM_TIME_BETWEEN_UPDATE, 
	                		MINIMUM_DISTANCECHANGE_FOR_UPDATE,
	                		new MyLocationListener()
	        );
	        
	        
	        
	        
	        
	       
			
			System.out.println("latitude"+lat);
			System.out.println("longitude"+lng);
			
			Cursor cursorCategory;
			Spinner spnCategory1 = (Spinner) findViewById(R.id.spnCategory);
			db3.open();
			cursorCategory= db3.getCategories();
			
			Log.i("log"," db3.getCategories() ");
			startManagingCursor(cursorCategory);
			/*Create an array to specify the fields we want to display in the list (only the 'colourName' column in this case) */

			String[] from = new String[]{DBAdapter.CATEGORY_NAME}; 
			 Log.i("log"," String[]{db3.CATEGORY_NAME  ");
			/* and an array of the fields we want to bind those fields to (in this case just the textView 'tvDBViewRow' from our new db_view_row.xml layout above) */
			int[] to = new int[]{R.id.DBView};
			 Log.i("log"," R.id.edtLocationName  ");
			/* Now create a simple cursor adapter.. */ 
			SimpleCursorAdapter categoryAdapter = new SimpleCursorAdapter(saveLocation.this, R.layout.db_text, cursorCategory, from, to);
			
			categoryAdapter.setDropDownViewResource(R.layout.db_text);
		 	  Log.i("log"," SimpleCursorAdapter(this,  ");
			/* and assign it to our Spinner widget */ 
			spnCategory1.setAdapter(categoryAdapter);
			
			db3.close();
	   }
	
}
