package com.alerts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SetOneService extends Service {

	DBAdapter db3 = new DBAdapter(this);
	
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		
		db3.open();
		db3.setone();
		db3.close();
		
		
		return Service.START_STICKY;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void onCreate() {
	// TODO: Actions to perform when service is created.
		
			
		
	}
	
	

}
