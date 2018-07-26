package com.alerts;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.alerts.DBAdapter;



public class ProximityIntentReceiver extends BroadcastReceiver  {
	
	
	
	public static final int NOTIFICATION_ID = 1000;
	
			

	@Override
	public void onReceive(final Context context, Intent intent) {
		
	
			
		         

		
		Intent myIntent=new Intent(context,SetOneService.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startService(myIntent);  
        
        
        //stop acttriggerservice
        
        Intent myIntent2=new Intent(context,ActTriggerService.class);
    	context.stopService(myIntent2);
		
		
				
		Log.i("hellllll","yeahhhhhhhhhh");
		
		String key = LocationManager.KEY_PROXIMITY_ENTERING;

		Boolean entering = intent.getBooleanExtra(key, false);
		
		if (entering) {
			Log.d(getClass().getSimpleName(), "entering");
			Toast.makeText(context, "You have reached ur destination" , Toast.LENGTH_LONG).show();
		}
		else {
			
			Log.d(getClass().getSimpleName(), "exiting");
			
			}
		
		
		
		
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		    @Override
		    public void run() {
		    	Intent myIntent2=new Intent(context,SetOneService.class);
		    	context.stopService(myIntent2);
		    }
		}, 30000);

		
		
		
		
		NotificationManager notificationManager = 
			(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, null, 0);		
		
		Notification notification = createNotification();
		notification.setLatestEventInfo(context, "Proximity Alert!", "You are near your point of interest.", pendingIntent);
		
		notificationManager.notify(NOTIFICATION_ID, notification);
		
		
		Handler handler2 = new Handler();
		handler2.postDelayed(new Runnable() {
		    @Override
		    public void run() {
		    	Intent myIntent2=new Intent(context,ActTriggerService.class);
		    	context.startService(myIntent2);
		    }
		}, 10000);
		
		
		
		
	}
	


	public Notification createNotification() {
		Notification notification = new Notification();
		
		//notification.icon = R.drawable.ic_menu_notifications;
		notification.when = System.currentTimeMillis();
		
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		
		notification.ledARGB = Color.WHITE;
		notification.ledOnMS = 1500;
		notification.ledOffMS = 1500;
		
		return notification;
	}
	
}
