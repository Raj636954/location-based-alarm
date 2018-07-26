package com.alerts;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;


public class markLocation extends Activity{
    /** Called when the activity is first created. */
	
	
	public void btnMyLocations(View dialogView){
		Log.i("main","b4");
		Intent myLocation = new Intent(markLocation.this,myLocation.class);
		startActivity(myLocation);
	}
		
		public void btnAlert(View dialogView){
			Log.i("main","b4");
			Intent alert = new Intent(markLocation.this,alert.class);
			startActivity(alert);
		
		
	}
		public void btnShortPath(View dialogView){
			
			Log.i("short","b4");
			Intent shortPath = new Intent(markLocation.this,shortPath.class);
			Log.i("short2222","b4");
			startActivity(shortPath);
			
		}	
		
	
	
@Override
public void onCreate(Bundle savedInstanceState){

	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.main);
 
        
      
	}
    
 
	
   
	
}