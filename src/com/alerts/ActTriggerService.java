package com.alerts;

import java.util.Calendar;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.method.DateKeyListener;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.webkit.DateSorter;
import android.widget.DatePicker;

public class ActTriggerService extends Service {

	 public int mYear;
	    public int mMonth;
	    public int mDay; 
	    
	    DBAdapter db3 = new DBAdapter(this);
	    
	   public String alrmstrng[] = new String[7];
	    
	      
		
	
	public void dateupdater()
	{
		
		db3.open();
		final Calendar c = Calendar.getInstance();
	      mYear = c.get(Calendar.YEAR);
	      mMonth = c.get(Calendar.MONTH)+1;
	      mDay = c.get(Calendar.DAY_OF_MONTH); 
	      
	      Log.i("acttrigg","service");
	      
	      System.out.println("ndate"+mDay+"nmonth"+mMonth+"nyear"+mYear);
	      
	      alrmstrng=db3.retAlarm();
	      
	      System.out.println("nnndate"+Integer.parseInt(alrmstrng[4])+"nnnnmonth"+Integer.parseInt(alrmstrng[3])+"nnnnyear"+Integer.parseInt(alrmstrng[5]));
	      
	     System.out.println("retrieveddddddd"+alrmstrng[0]);
	      
	      if(mYear==Integer.parseInt(alrmstrng[5]) && mMonth==Integer.parseInt(alrmstrng[3]) && mDay==Integer.parseInt(alrmstrng[4]))
	      {
	    	  Intent dialogIntent = new Intent(getBaseContext(), ProxyService.class);
	  		Log.i("date comparison","sss");
	  		//dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  		dialogIntent.putExtra("latitude",Double.parseDouble(alrmstrng[1]));
	  	    dialogIntent.putExtra("longitude",Double.parseDouble(alrmstrng[2]));
	  		//dialogIntent.putExtra("note",Double.parseDouble(alrmstrng[0]));
	  	    
	  	  System.out.println("sent lat"+Double.parseDouble(alrmstrng[1])+"sent lon"+Double.parseDouble(alrmstrng[2]));
	  	    
	  	    
	  		Log.i("if ya smelllllllll","sss");
	  	    db3.saveonevalue(Integer.parseInt(alrmstrng[6]));
	  		getApplication().startService(dialogIntent);
	  		
	      }
	      
	      
	      else
	      {   
	    	  
	    	  
	    	  Log.i("elseeeeeeeeee","sss");
	    	  Handler handler = new Handler();
	    	  handler.postDelayed(new Runnable() {
	    	      @Override
	    	      public void run() {
	    	    	  dateupdater();   
	    	      }
	    	  }, 3600000);

	    	  
	      }
	      db3.close();
		
	}
	
	    
	    
	
	
	@Override
	public void onCreate() {
	// TODO: Actions to perform when service is created.
		
			
		
	}
	@Override
	public IBinder onBind(Intent intent) {
	// TODO: Replace with service binding implementation.
	return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	// TODO Launch a background thread to do processing
	    
		  
	      
	      
	       dateupdater();
	      
	      
	      //float latitude=(float) 37.423207;
	    	//float longitude = (float) -122.086092;
	      
	      
	      
	      /*Intent dialogIntent = new Intent(getBaseContext(), ProxyService.class);
	      Log.i("ssssssssss","oooooooo");
	      dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     dialogIntent.putExtra("latitude",latitude);
  	 dialogIntent.putExtra("longitude",longitude);
  		//dialogIntent.putExtra("note",Double.parseDouble(alrmstrng[0]));
	      getApplication().startService(dialogIntent);*/
	      
	      
		
	    /*  Launching an activity is not all. After launching another subactivity/activity you might want to send some data/parameter to that activity. This is done in a new way known as Bundle. To launch an activity we create an intent. Suppose our intent name is "intent". We can put data with the intent like this

	      intent.putExtra("keyName", "somevalue");

	      We can add multiple entries here. This is a key,value pair. So to receive this data from the receiving activity we have to write this code

	      Bundle extras = getIntent().getExtras();
	      if(extras !=null)
	      {
	      String value = extras.getString("keyName");
	      }

	      Read more: http://getablogger.blogspot.com/2008/01/android-pass-data-to-activity.html#ixzz1HqohC3y3*/

		

		
	return Service.START_STICKY;
	}
	
	
	
	
	
	
	
	
}
