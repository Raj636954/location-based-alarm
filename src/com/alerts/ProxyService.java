package com.alerts;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.alerts.ProximityIntentReceiver;


import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProxyService extends Service {
	
	
	
	private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
	
	private static final long POINT_RADIUS = 1000; // in Meters
	private static final long PROX_ALERT_EXPIRATION = -1; 
	
	private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
	private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
	
	private static final String PROX_ALERT_INTENT = "com.proxyAlert.ProximityIntentReceiver";
	
	private static final NumberFormat nf = new DecimalFormat("##.########");
	
	public LocationManager locationManager;
	
	
	@Override
	public IBinder onBind(Intent intent) {
	// TODO: Replace with service binding implementation.
	return null;
	}	
	
	
	@Override
	public void onCreate() {
	// TODO: Actions to perform when service is created.
		
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	    locationManager.requestLocationUpdates(
	            		LocationManager.GPS_PROVIDER, 
	            		MINIMUM_TIME_BETWEEN_UPDATE, 
	            		MINIMUM_DISTANCECHANGE_FOR_UPDATE,
	            		new MyLocationListener()
	    );
		
	}
	
	
	 public void saveProximityAlertPoint(double latitude, double longitude) {
	    	float lat=(float) latitude;
	    	float lon = (float) longitude;
	    		
	    		saveCoordinatesInPreferences(lat,lon);
	    	addProximityAlert(latitude, longitude);
		}
	 
	 public void addProximityAlert(double latitude, double longitude) {
			
	        Intent intent = new Intent(PROX_ALERT_INTENT);
	        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
	        
	        locationManager.addProximityAlert(
	    		latitude, // the latitude of the central point of the alert region
	    		longitude, // the longitude of the central point of the alert region
	    		POINT_RADIUS, // the radius of the central point of the alert region, in meters
	    		PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration 
	    		proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
	       );
	        
	       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);  
	       registerReceiver(new ProximityIntentReceiver(), filter);
	       
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public void saveCoordinatesInPreferences(float latitude, float longitude) {
	    	SharedPreferences prefs = this.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE);
	    	SharedPreferences.Editor prefsEditor = prefs.edit();
	    	prefsEditor.putFloat(POINT_LATITUDE_KEY, latitude);
	    	prefsEditor.putFloat(POINT_LONGITUDE_KEY, longitude);
	    	prefsEditor.commit();
	    }
	    
	   /* private Location retrievelocationFromPreferences() {
	    	SharedPreferences prefs = this.getSharedPreferences(getClass().getSimpleName(), Context.MODE_PRIVATE);
	    	Location location = new Location("POINT_LOCATION");
	    	location.setLatitude(prefs.getFloat(POINT_LATITUDE_KEY, 0));
	    	location.setLongitude(prefs.getFloat(POINT_LONGITUDE_KEY, 0));
	    	return location;
	    }*/
	
	
	
	
	
	
	
	
	
	public class MyLocationListener implements LocationListener {
    	public void onLocationChanged(Location location) {
    				
    	}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
	
	public int onStartCommand(Intent intent, int flags, int startId){
		
		
		Log.i("FFFFUUUU","CCCCCCKKKK");
		
		
		  super.onStart(intent, startId);
		  double lat, lon;
		  lat = intent.getExtras().getDouble("latitude"); 
		  lon = intent.getExtras().getDouble("longitude");
		  //Toast.makeText(this, extras, Toast.LENGTH_LONG).show(); 
		  
		  System.out.println("After Fuck"+lat+"lon"+lon);
		
		saveProximityAlertPoint(lat,lon);
		
		
		return Service.START_STICKY;
		
		
	}
	
	
	
	
	
	 
	
	
	
	
}
	
	
	
		
		
	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

