package com.alerts;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;




public class NewAlert extends Activity  {
	
	String y;
	public static final NumberFormat nf = new DecimalFormat("##.########");
	
	
	
	
	
	
	
	public void btnSaveClick(View dialogView) {
		
		Log.i("workinggggggg","11111");
		int month,date,year;
		
		final EditText x= (EditText)findViewById(R.id.edtNote);
	 	final Spinner loc= (Spinner)findViewById(R.id.spnLocation);
	 	final Spinner radius= (Spinner)findViewById(R.id.spnRadius);
	 	
	    y=x.getText().toString();
		
		final Intent MY_ACTION = null;
		
		
     	
	 	DBAdapter db3 = new DBAdapter(this);
     	long id;
     	
     	db3.open();
     	
     	String note = x.getText().toString();
     	
     	Cursor mloc = (Cursor) loc.getSelectedItem();
     	
     	
     	//date access
     	
     	
     	DatePicker mPickDate = (DatePicker) findViewById(R.id.datePicker);
        month=mPickDate.getMonth();
        date=mPickDate.getDayOfMonth();
        year=mPickDate.getYear();
        
        System.out.println("watchhhhhhhhhhh"+month+date+year);
     	
    
     	
     	//Cursor mradius = (Cursor) radius.getSelectedItem();
     	
     //	Log.i("!!!!!!!!!!!!", mloc.getDouble(2));
        System.out.println("fuckkkkkkkkkkkkkkkkk"+mloc.getDouble(2));
     	
     	//col 0 -- category_id
        id =db3.saveAlarm(note, mloc.getString(1),mloc.getDouble(2),mloc.getDouble(3),12,month+1,date,year );
       // Log.i("log","strTextValue " +strTextValue);
        
    
     	//id =db3.saveAlarm(note, mloc.getString(1),mloc.getString(2),mloc.getString(3),12,month+1,date,year );
        
         
         db3.close();
         
         //for starting service
         startService(new Intent(NewAlert.this, ActTriggerService.class));
         
         
         
	
		Toast.makeText(getApplicationContext(), "Alarm  saved " , Toast.LENGTH_LONG).show();
		
		
		Intent saveLocation = new Intent(NewAlert.this,markLocation.class);
		startActivity(saveLocation);
		
	//ALERT EVOCATION
		
		
		
		
		
	}
	
	private ArrayAdapter <CharSequence> radiusSpinner;
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		
         Log.i("jjjj","sss");
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.newalert);
		 Spinner spnRadius = (Spinner)findViewById(R.id.spnRadius);
		 
		 radiusSpinner = new ArrayAdapter(NewAlert.this, android.R.layout.simple_spinner_item);
		 radiusSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 spnRadius.setAdapter(radiusSpinner);
		 radiusSpinner.add("25");
		 radiusSpinner.add("50");
		 radiusSpinner.add("75");
		 radiusSpinner.add("100");
		 
		 DBAdapter db3 = new DBAdapter(this);
	 	
		 Cursor cursorLocation;
		
		 Spinner spnCategory1 = (Spinner) findViewById(R.id.spnLocation);
			db3.open();
		
		 cursorLocation= db3.getLocations();
			
		Log.i("log"," db3.getLocations() ");
			startManagingCursor(cursorLocation);
			/*Create an array to specify the fields we want to display in the list (only the 'colourName' column in this case) */

			String[] from = new String[]{DBAdapter.LOCATION_NAME}; 
			/* and an array of the fields we want to bind those fields to (in this case just the textView 'tvDBViewRow' from our new db_view_row.xml layout above) */
			int[] to = new int[]{R.id.DBView};
			 Log.i("log"," R.id.edtLocationName  ");
			/* Now create a simple cursor adapter.. */ 
			SimpleCursorAdapter locationAdapter = new SimpleCursorAdapter(NewAlert.this, R.layout.db_text, cursorLocation, from, to);
			
			locationAdapter.setDropDownViewResource(R.layout.db_text);
		 	  Log.i("log"," SimpleCursorAdapter(this,  ");
			/* and assign it to our Spinner widget */ 
			spnCategory1.setAdapter(locationAdapter);
			
			db3.close();

}
	public void bt(View dialogView){
		Log.i("main","b4");
		
	
	}
}
