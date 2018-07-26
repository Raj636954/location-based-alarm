package com.alerts;

//import com.paad.shortPath.ShortDb;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.location.LocationManager;
import android.view.View;

public class alertEvocation  extends NewAlert {
	
	DBAdapter db3 = new DBAdapter(this);
	
	
	private static final Intent MY_ACTION = null;
	Intent intent = new Intent(MY_ACTION);
	PendingIntent pendingIntent = PendingIntent.getBroadcast(this, -1, intent, 0);
	
	private static String TREASURE_PROXIMITY_ALERT = "com.paad.treasurealert";
	
	
	
	private void setProximityAlert() {
	String locService = Context.LOCATION_SERVICE;
	LocationManager locationManager;
	locationManager = (LocationManager)getSystemService(locService);
	//double lat = 73.147536;
	//double lng = 0.510638;
	NewAlert nalert = new NewAlert();
	String notefin;
	notefin=nalert.y;
    //  notefin= "sam";
	double lat = db3.getlatalert(notefin);
	double lng = db3.getlngalert(notefin);
	float radius = 100f; // meters
	long expiration = -1; // do not expire
	Intent intent = new Intent(TREASURE_PROXIMITY_ALERT);
	PendingIntent proximityIntent = PendingIntent.getBroadcast(this, -1,
			intent,
			0);
			locationManager.addProximityAlert(lat, lng, radius,
			expiration,
			proximityIntent);
			}
	
	public class ProximityIntentReceiver extends BroadcastReceiver {
		@Override
		public void onReceive (Context context, Intent intent) {
		String key = LocationManager.KEY_PROXIMITY_ENTERING;
		Boolean entering = intent.getBooleanExtra(key, false);
		 Notification notification = createNotification();
	        notification.setLatestEventInfo(context, 
	            "Proximity Alert!", "You are near your point of interest.", pendingIntent);
		// [ . . . perform proximity alert actions . . . ]
		}

		private Notification createNotification() {
			 Notification notification = new Notification();
			 
		        notification.flags |= Notification.FLAG_AUTO_CANCEL;
		        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		        
		        notification.defaults |= Notification.DEFAULT_VIBRATE;
		        notification.defaults |= Notification.DEFAULT_LIGHTS;
		        notification.defaults |= Notification.DEFAULT_SOUND;
		        
		        
		        
		        notification.ledARGB = Color.WHITE;
		        notification.ledOnMS = 1500;
		        notification.ledOffMS = 1500;
			// TODO Auto-generated method stub
			return null;
		}
		}

}
