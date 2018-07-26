package com.alerts;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class myLocation extends MapActivity {
    /** Called when the activity is first created. */
	
	private MapController mapController;
	private MapView myMapView; 
	//static final private int onClickMarkLocation = 1;	
	DBAdapter db3 = new DBAdapter(this);

	Double geoLat;
	Double geoLng;
	String TAG="GPS";
	Cursor curLocations;
	
    locationOverlay myLocationOverlay;
	
/*	 public void myClickHandler(View dialogView) {
	        // Do stuff

		
		 final EditText x= (EditText)findViewById(R.id.edtLocationName);
        	
        	
        	long id;
        	db3.open();
        	//String strTextValue = x.getText().toString();
        	int x1 =geoLat.intValue();
           // id =db3.insertLocation(Loc_Name.getText().toString(), "new category", 33, 33 );
            id =db3.insertLocation("new category2", "new category3",12,12 );
            Toast.makeText(getApplicationContext(), "Location saved", Toast.LENGTH_SHORT).show();
            //Log.i(TAG,"saved " + Loc_Name.getText().toString());
            
            db3.close();
	    } */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_location);
        Intent sourceIntent = getIntent();
        
        myMapView = (MapView)findViewById(R.id.myMapView);
   
		// Get the Map View’s controller 
		
		mapController = myMapView.getController();

		// Configure the map display options
		myMapView.setSatellite(true);
		myMapView.setStreetView(true);
		myMapView.displayZoomControls(true);
		// Zoom in
		mapController.setZoom(19);
		myMapView.setBuiltInZoomControls(true);
		
		
		db3.open();
		curLocations = db3.getLocations();
		
	
		myLocationOverlay = new locationOverlay(curLocations);
		db3.close();
		
		List<Overlay> overlays1 = myMapView.getOverlays();
		// TODO overlays1.add(myLocationOverlay);
		
		
			LocationManager locationManager;
			String context = Context.LOCATION_SERVICE;
			locationManager = (LocationManager)getSystemService(context);
			
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		
		String provider = locationManager.getBestProvider(criteria, true);
		
		List<Overlay> overlays = myMapView.getOverlays();
		//blue pointer
		MyLocationOverlay myLocationOverlay =
		new MyLocationOverlay(this, myMapView);
		overlays.add(myLocationOverlay);
		
		myLocationOverlay.enableCompass();
		myLocationOverlay.enableMyLocation();
		
		//listen for location changes every two seconds but fire only when it
		//detects movement of more than 10 meters
		
	/*	Location location =locationManager.getLastKnownLocation(provider);

		if(location!=null){
		geoLat = location.getLatitude()*1E6;
		geoLng = location.getLongitude()*1E6; 
		} */
		
		locationManager.requestLocationUpdates(provider, 2000, 10,locationListener);
		
		Button btnMark = (Button)findViewById(R.id.btnMark);
		
		btnMark.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				
				//createDatabase();
			
				//Log.i("GPS","created"+geoLat.intValue()+" "+geoLng.intValue());
				if(geoLat!=null){
					Intent saveLocation = new Intent(myLocation.this,saveLocation.class);
					saveLocation.putExtra("latitude", geoLat.intValue());
					saveLocation.putExtra("longitude", geoLng.intValue());
					Log.i("GPS","called");
					startActivity(saveLocation);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "GPS not ready", Toast.LENGTH_LONG).show();
		               
				}
				
		/*		AlertDialog.Builder alertbox = new AlertDialog.Builder(getBaseContext());
				  alertbox.setMessage("This is the alertbox!");
				  alertbox.show(); */
				
			/*	Dialog viewDialog = new Dialog(markLocation.this); 
				viewDialog.getWindow().setFlags( 
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND, 
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND); 
				viewDialog.setTitle("Add Location"); 
				
				
				
				LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
				View dialogView = li.inflate(R.layout.mark_location_get_data, null); 
				viewDialog.setContentView(dialogView); 
				viewDialog.show(); */
				
				/*Button btnSave =(Button)findViewById(R.id.btnSave);
				
				
			   
				btnSave.setOnClickListener(new View.OnClickListener() {
		                @Override
		                    public void onClick(View v) {  */
		           /*     	EditText Loc_Name= (EditText)findViewById(R.id.edtLocationName);
				            	
				            	
				            	long id;
				            	db3.open();
				            	
				            	//int x =geoLat.intValue();
				                //id =db3.insertLocation(Loc_Name.getText().toString(), "new category", 33, 33 );
				  a              id =db3.insertLocation("new category2", "new category3",12,12 );
					            Toast.makeText(getApplicationContext(), "Location saved", Toast.LENGTH_SHORT).show();
				                //Log.i(TAG,"saved " + Loc_Name.getText().toString());
				                
				                db3.close();*/
				
		                    }
		                });
				/*
				LayoutInflater li = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
				View v = li.inflate(R.layout.mark_location_get_data, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(markLocation.this);
				builder.setView(v);
			    builder.setTitle("Add location");
			   // builder.setIcon(R.drawable.icon);
			   // builder.setMessage("test");
			  
			    
			    builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					
		            // do something when the button is clicked
		            public void onClick(DialogInterface arg0, int arg1) {
		            	
		            	EditText Loc_Name= (EditText)findViewById(R.id.edtLocationName);
		            	
		            	
		            	long id;
		            	db3.open();
		            	
		            	//int x =geoLat.intValue();
		                id =db3.insertLocation(Loc_Name.getText().toString(), "new category", 33, 33 );
		                id =db3.insertLocation("new category2", "new category3",12,12 );
			            Toast.makeText(getApplicationContext(), "Location saved", Toast.LENGTH_SHORT).show();
		                Log.i(TAG,"saved " + Loc_Name.getText().toString());
		                
		                db3.close();
		                
		            }
		        });

		        // set a negative/no button and create a listener
			    builder.setNegativeButton("Add alert", new DialogInterface.OnClickListener() {

		            // do something when the button is clicked
		            public void onClick(DialogInterface arg0, int arg1) {
		                Toast.makeText(getApplicationContext(), "'No' button clicked", Toast.LENGTH_SHORT).show();
		            }
		        });
				
				//showDialog(onClickMarkLocation);
			    builder.show(); */
		//		}
		//		});
		  		
	}
    
 /*
    //DIALOG
	@Override
	public Dialog onCreateDialog(int id) {
		switch(id) {
			case (onClickMarkLocation) :
			LayoutInflater li = LayoutInflater.from(this);
			View getData = li.inflate(R.layout.mark_location_get_data, null);
			AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
			dialog1.setTitle("Add Location");
			dialog1.setView(getData);
			
			dialog1.setPositiveButton("Save", new DialogInterface.OnClickListener() {
				
	            // do something when the button is clicked
	            public void onClick(DialogInterface arg0, int arg1) {
	            	
	            	EditText Loc_Name= (EditText)findViewById(R.id.edtLocationName);
	            	
	            	
	            	long id;
	            	db3.open();
	            	
	            	int x =geoLat.intValue();
	                //id =db3.insertLocation(Loc_Name.toString(), "new category", geoLat.intValue(), geoLng.intValue() );
	                //id =db3.insertLocation("new category2", "new category3",12,12 );
		            Toast.makeText(getApplicationContext(), "Location saved", Toast.LENGTH_SHORT).show();
	                Log.i(TAG,"saved "+Loc_Name.toString());
	                
	                db3.close();
	                
	            }
	        });

	        // set a negative/no button and create a listener
			dialog1.setNegativeButton("Add alert", new DialogInterface.OnClickListener() {

	            // do something when the button is clicked
	            public void onClick(DialogInterface arg0, int arg1) {
	                Toast.makeText(getApplicationContext(), "'No' button clicked", Toast.LENGTH_SHORT).show();
	            }
	        });
			
			return dialog1.create();
		}
	return null;
	}
	*/
	
	
    private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) 
		{
			updateWithNewLocation(location);
			
		}
		public void onProviderDisabled(String provider)
		{
			updateWithNewLocation(null);
		}
		
		public void onProviderEnabled(String provider){ }
		public void onStatusChanged(String provider, int status,
		Bundle extras){ }
		};	

		private void updateWithNewLocation(Location location) {
        	// TODO Auto-generated method stub
			
        	 		if (location != null) {
        			
        		// Update my location marker
        		//	positionOverlay.setLocation(location);
        			
        			// Update the map location.//projection
        			geoLat = location.getLatitude()*1E6;
        			geoLng = location.getLongitude()*1E6;
        			GeoPoint point = new GeoPoint(geoLat.intValue(),geoLng.intValue());
        			
        			Log.i("main overlay",geoLat.intValue()+" "+geoLng.intValue());
        			mapController.animateTo(point);
        			//Log.i(TAG, "new category"+ " " +geoLat.intValue());
	            	
        			Log.i("GPS","updated")	;
        	
        		} else 
        		{
        			Log.i("GPS","null")	;
        		}
		}

		
		
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onResume() {
		curLocations.requery();
	super.onResume();
	}
	@Override
	public void onPause() {
		curLocations.deactivate();
	super.onPause();
	}
	@Override
	public void onDestroy() {
		curLocations.close();
	super.onDestroy();
	}
	
	
}