package com.alerts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import com.alerts.DBAdapter;


import android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class shortPath extends Activity {
    /** Called when the activity is first created. */
	
	
	DBAdapter db3 = new DBAdapter(this);
	
	 
	
	public void btnFindClick (View dialogView){
		String temp[]=null;
		Log.i("line","34");
		
		
		 Intent sourceIntent = getIntent();		
			String currentlat = String.valueOf(sourceIntent.getIntExtra("latitude", 0));
			String currentlng = String.valueOf(sourceIntent.getIntExtra("longitude", 0));
		
		
	System.out.println("latitude"+currentlat);
	System.out.println("longitude"+currentlng);
	
		
		
		db3.open();
		    //ListView myListView = (ListView)findViewById(R.id.locListView);
	       
	        final Spinner spin= (Spinner)findViewById(R.id.spinCat);
	        ListView myListView = (ListView)findViewById(R.id.locListView);
	        final ArrayList<String> locValues = new ArrayList<String>();
	        final ArrayAdapter<String> aa;
	        aa = new ArrayAdapter<String>(this,
	    	        android.R.layout.simple_list_item_1,
	    	        locValues);
	    	        // Bind the array adapter to the listview
	        
	        myListView.setAdapter(aa);
	        
	        //Cursor mCursor = (Cursor) spin.getSelectedItem();
	        
	        //System.out.println("SpinnerValue"+ spin.getSelectedItemPosition());
	        temp=db3.distcalc(spin.getSelectedItemPosition()+1,currentlat,currentlng);
	        
	        Log.i("hell","yeah");
	        
	        locValues.add(0, "Name:"+temp[0]);
	        
	        locValues.add(1, "Dist:"+temp[1]);
	        locValues.add(2, "LAT: "+temp[2]);
	        locValues.add(2, "LON: "+temp[3]);
	        
	        
	        aa.notifyDataSetChanged();
	       
	    	       
		}
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	Log.i("saaaaaaa1","b4");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shorty);
        Log.i("saaaaaaa2","b4");
        
        /*final Spinner spin= (Spinner)findViewById(R.id.spinCat);
        Cursor mCursor = (Cursor) spin.getSelectedItem();
       */
       // Button btnFind = (Button)findViewById(R.id.findBtn);
      /*c
        
       */ 
         db3.open();

		 Cursor cursorLocation;
		
		 Spinner spnCategory1 = (Spinner) findViewById(R.id.spinCat);
			
			
			
			//to populate category spinner
		
		 cursorLocation= db3.getCategories();
			
		Log.i("log"," db3.getLocations() ");
			startManagingCursor(cursorLocation);
			/*Create an array to specify the fields we want to display in the list (only the 'colourName' column in this case) */

			String[] from = new String[]{DBAdapter.CATEGORY_NAME}; 
			/* and an array of the fields we want to bind those fields to (in this case just the textView 'tvDBViewRow' from our new db_view_row.xml layout above) */
			int[] to = new int[]{R.id.DBView};
			 Log.i("log"," R.id.edtLocationName  ");
			/* Now create a simple cursor adapter.. */ 
			SimpleCursorAdapter locationAdapter = new SimpleCursorAdapter(shortPath.this, R.layout.shorty, cursorLocation, from, to);
			
			locationAdapter.setDropDownViewResource(R.layout.db_text);
		 	  Log.i("log"," SimpleCursorAdapter(this,  ");
			/* and assign it to our Spinner widget */ 
			spnCategory1.setAdapter(locationAdapter);
			
			db3.close();

        
        
         	long id;
     	
     	
     /*	id =db3.insertLocation("Hello",3,100,76 );
     	id =db3.insertLocation("Hello2",2,120,66 );
     	id =db3.insertLocation("Hello3",4,160,36 );
     	id =db3.insertLocation("Hello3tr",4,160,36 );*/
     	
     	Log.i("W1","work");
     	
     	//str= db3.funcquery();
     	//str=db3.distcalc();
     	//TextView mytxt=(TextView) findViewById(R.id.txt);
     	
     	Log.i("w6","yes");
     	
     	//mytxt.setText(String.valueOf(str));    	
     	 Log.i("w6","yes");
     	
     	 
     
     	
     
     	
     	
    }
    }
