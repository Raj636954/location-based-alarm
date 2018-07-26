package com.alerts;



import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter{
	
		public static final String ID = "_id";
	    public static final String LOCATION_NAME = "location_name";
	    public static final String CATEGORY_ID = "category_id";
	    public static final String NOTE_FLAG= "location_id";
	    public static final String LOCATION_LAT = "location_lat";
	    public static final String LOCATION_LNG = "location_lng";
	    public static final String CATEGORY_NAME = "category_name";
	    public static final String NOTE = "note";
	    public static final String RADIUS = "proximity_radius";
		private static final String TAG = "DBAdapter";
		public static final String MONTH = "month";
		public static final String DATE = "date";
		public static final String YEAR = "year";
		 
		 
		private static final NumberFormat nf = new DecimalFormat("##.########");
	      
		final static String DATABASE_NAME = "database.db3";
		//final static String DATABASE_TABLE = "location";
		private static final int DATABASE_VERSION = 1;

	    private static final String CREATE_TABLE_LOCATION ="create table location " +
	    		"( _id integer primary key autoincrement,location_name " +
	    		"text,category_id integer, location_lat integer,location_lng integer);";
	    
	    private static final String CREATE_TABLE_CATEGORY ="create table category ( _id integer primary key autoincrement,category_name " +
	    		"text);";

	    private static final String CREATE_TABLE_ALARM ="create table alarm " +
		"( _id integer primary key autoincrement,note " +
		"text,location_id integer, location_lat integer,location_lng integer, location_name text, proximity_radius integer, month integer, date integer, year integer);";

	   
	    public static final String FINALQUERY ="SELECT * FROM (SELECT * FROM(SELECT * FROM "+
	    "(SELECT * FROM alarm WHERE location_id= 0) "+
"WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm WHERE location_id= 0)))WHERE "+
"month=(SELECT MIN(month) FROM (SELECT * FROM (SELECT * FROM alarm WHERE location_id= "+
"0) WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm WHERE location_id= 0))))) WHERE "+
"date=(SELECT MIN(date) FROM (SELECT * FROM(SELECT * FROM (SELECT * FROM alarm WHERE "+
"location_id= 0) WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm WHERE "+
"location_id= 0)))WHERE month=(SELECT MIN(month) FROM (SELECT * FROM (SELECT * FROM "+
"alarm WHERE location_id= 0) WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm "+
"WHERE location_id= 0))))))";
	    
	  //  public static final String FINALQUERYY="SELECT * FROM (SELECT * FROM(SELECT * FROM (SELECT * FROM alarm WHERE location_id= 0) WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm WHERE location_id= 0)))WHERE month=(SELECT MIN(MONTH) FROM (SELECT * FROM (SELECT * FROM alarm WHERE location_id= 0) WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm WHERE location_id= 0))))) WHERE date=(SELECT MIN(date) FROM (SELECT * FROM(SELECT * FROM (SELECT * FROM alarm WHERE location_id= 0) WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm WHERE location_id= 0)))WHERE month=(SELECT MIN(MONTH) FROM (SELECT * FROM (SELECT * FROM alarm WHERE location_id= 0) WHERE year=(SELECT MIN(year) FROM (SELECT * FROM alarm WHERE location_id= 0))))))";
	    
	    public static int onevalueid;
	    
	    public static String SETONE;
	    
	    public static final String FIN ="SELECT * FROM alarm";
	    
	    
	    private final Context context;  
	
	    private DatabaseHelper DBHelper;
	    private SQLiteDatabase db3;
	    
	   
	    
	    

	    //Constructor
	    public DBAdapter(Context ctx) 
	    {
	        this.context = ctx;
	        DBHelper = new DatabaseHelper(context);
	    }
	    
	

		//---opens the database---
	    public DBAdapter open() throws SQLException 
	    {
	        
	    	db3 = DBHelper.getWritableDatabase();
	        return this;
	    }

	    //---closes the database---    
	    public void close() 
	    {
	        DBHelper.close();
	    }
	    
	    //---insert a location into the database---
	    public long insertLocation(String name, String category, String latitude, String longitude) 
	    {   
	    	String lat= nf.format(Double.parseDouble(latitude));
	    	String lon= nf.format(Double.parseDouble(longitude));
	    	
	    	System.out.println("minalllllee"+latitude+"eeeeeeee"+longitude);
	        
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(LOCATION_NAME, name);
	        initialValues.put(CATEGORY_ID, category);
	        initialValues.put(LOCATION_LAT, lat);
	        initialValues.put(LOCATION_LNG, lon);
	        
	        return db3.insert("location", null, initialValues);
	    }

	    //---insert a category into the database---
	    public long insertCategory(String name) 
	    {
	        ContentValues initialValues = new ContentValues();
	       
	        initialValues.put(CATEGORY_NAME, name);
	       
	        return db3.insert("category", null, initialValues);
	    }

	    //---retrieves all the categories---
	    public Cursor getCategories() 
	    {
	        return db3.query("category", new String[] {ID,CATEGORY_NAME},null,null,null,null,null);
	    }
	    
	    //TODO remove this---retrieves all the locations---
	    public Cursor getLocation() 
	    {
	        return db3.query("location", new String[] {ID,LOCATION_NAME},null,null,null,null,null);
	    }
	    
	    //---retrieves all the locations---
	    public Cursor getLocations()
	    {	
	    	return db3.query("location", new String[] {ID,LOCATION_NAME,LOCATION_LAT,LOCATION_LNG},null,null,null,null,null);
	    }
	    
	    public long saveAlarm(String note, String location,double latitude, double longitude, int radius, int month, int date, int year)
	    {
	    	 ContentValues initialValues = new ContentValues();
	    	 
	    	 System.out.println("SAVE valuessssssss"+latitude+"longgggggggg"+longitude);
	    	 
	    	 //String lat= nf.format(Double.parseDouble(latitude));
	    	// String lon= nf.format(Double.parseDouble(longitude));
	    	 
	    	 System.out.println("FUCK valuessssssss"+latitude+"longgggggggg"+longitude);
	    	 
		        initialValues.put(LOCATION_NAME, location);
		        initialValues.put(RADIUS, radius);
		        initialValues.put(LOCATION_LAT, latitude);
		        initialValues.put(LOCATION_LNG, longitude);
		        initialValues.put(NOTE, note);
		        initialValues.put(MONTH,month);
		        initialValues.put(DATE,date);
		        initialValues.put(YEAR,year);
		        
		       //it's the flag for beeping alarm 0-alive 1-done
		        
		       initialValues.put(NOTE_FLAG,0); 
		        
		        System.out.println("dbbbbbbbbbbbbb"+month+date+year);
		        
		        
		        return db3.insert("alarm", null, initialValues);
	    }
	    
	    
	    

	  
	    //returns lat for alert evocation
	    public double getlatalert(String note)
	    {
	    String[] result_columns = new String[] {LOCATION_NAME, LOCATION_LAT, LOCATION_LNG, NOTE};
	    String where = NOTE + "=" + note;
	    Cursor myResult = db3.query("alarm", result_columns, where,
	     null, null, null,null);
	    String lat = myResult.getString(1);
	    return(Double.parseDouble(lat)); 
	    }
	    
	    
	    
	  //returns lng for alert evocation
	    public double getlngalert(String note)
	    {
	    String[] result_columns = new String[] {LOCATION_NAME, LOCATION_LAT, LOCATION_LNG, NOTE};
	    String where = NOTE + "=" + note;
	    Cursor myResult = db3.query("alarm", result_columns, where,
	     null, null, null,null);
	    String lng = myResult.getString(2);
	    return(Double.parseDouble(lng)); 
	    }
	    
	    
	    
	    
	    
	    //for getting id for setting it to one P.T.O
	    public void saveonevalue(int rowid)
	    {
	    	onevalueid=rowid;
	    	
	    	
	    }
	    
	    //for getting id value for row 1
	    public void setone()
	    {
	    		    	
	    	System.out.println("one value id"+onevalueid);
	    	SETONE="UPDATE alarm SET location_id = 1 WHERE _id = "+ 
		    onevalueid +"";
	    	db3.execSQL(SETONE);
	    	System.out.println("SETONE QUERY EXEC"+	SETONE);
	    }
	    
	    
	    
	    
	    
	    //for returning alarm table contents
	    
	    public String[] retAlarm(){
	    	
	    	
	    	String result[]=new String[7];
	    	Log.i("Return", "Alarm");
	    	//String[] result_columns = new String[] {NOTE , LOCATION_LAT, LOCATION_LNG, MONTH, DATE, YEAR};
	    	// String where = NOTE_FLAG + "=" + 0;
	    	
	    	//Cursor myResult = db3.query("alarm", result_columns, null,
		 	//null, null, null,null);
		 	
		 //	 myResult.moveToFirst();
	    	 
	    	//Cursor myResult=db3.rawQuery(FIN, result_columns);
	    	 Cursor resultcsr=db3.rawQuery(FINALQUERY,null);
	    	 Log.i("*****","@@@@@@");
	    	 resultcsr.moveToFirst();
	    	 result[0]=resultcsr.getString(1); //note
	    	 result[1]=String.valueOf(resultcsr.getDouble(3)); //lat
	    	 result[2]=String.valueOf(resultcsr.getDouble(4));; //long
	    	 result[3]=resultcsr.getString(7); //month
	    	 result[4]=resultcsr.getString(8); //date
	    	 result[5]=resultcsr.getString(9); //year
	    	 result[6]=resultcsr.getString(0);
	    	 System.out.println("the lat retrieved value"+result[1]);
	    	 
	    	 return (result);
        	
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    //SHORT PATH FILES
	    
	    public String[] distcalc(int reqval,String lat0,String lon0)
	    {   
	 	   Log.i("fff","hhh");
	 	  
	 	   
	 	   
	 	   double lat1 = Double.parseDouble(lat0);
	 	   double lon1 = Double.parseDouble(lon0);
	 	   String req_name,req_lat,req_long,result[]=new String[4];
	 	   
	 	   String[] result_columns = new String[] {LOCATION_NAME,CATEGORY_ID , LOCATION_LAT, LOCATION_LNG};
	 	   
	 	   //Cursor allRows = db3.query(true, "location", result_columns,
	 			   //null, null, null, null, null, null);
	 	   
	 	   String where = CATEGORY_ID + "=" + reqval;
	 	    Cursor myResult = db3.query("location", result_columns, where,
	 	   null, null, null,null);
	 	   
	 	    myResult.moveToFirst();
	 	    Log.i("fuck","uuu");
	 	       String lat2 = myResult.getString(2);
	 	    
	 	       String lon2 = myResult.getString(3);
	 	       
	 	       //req_id=myResult.getString(1);
	 	       double short_dist = distance(lat1,lon1,Double.parseDouble(lat2),Double.parseDouble(lon2));
	 	      //double short_dist = distance(lat1,lon1,lat2,lon2);
	 	     
	 	       double shortest_dist=short_dist;
	 	       Log.i("w1","done"); 	    
	 	       
	 	   do
	 	   { 
	 		   
	 		   Log.i("w2","done");
	 		   myResult.moveToNext();
	 		   lat2 = myResult.getString(2);
	 		   Log.i("w3","done");
	 		   lon2 = myResult.getString(3);
	 		   Log.i("w4","done");
	 		   short_dist = distance(lat1,lon1,Double.parseDouble(lat2),Double.parseDouble(lon2));
	 		   Log.i("w5","done");
	 	        
	 	       if(short_dist<=shortest_dist)
	 	       {
	 	    	 shortest_dist=short_dist; 
	 	    	 Log.i("w6","done");
	 	    	 result[0]=myResult.getString(0);
	 	    	 Log.i("w7","done");
	 	    	 result[1]=String.valueOf(shortest_dist);
	 	    	 result[2]=myResult.getString(2);
	 	    	 result[3]=myResult.getString(3);
	 	    	
	 	    	 Log.i("done","yes");
	 	       }
	 	         
	 	   } while(!myResult.isLast());   
	 	   
	 	   Log.i("******","&&&&&");
	 	 System.out.print(result);
	 	 
	 	
	 	 return(result);  
	    }
	    //to find relative distance
	    
	    public double distance(double lat1, double lon1, double lat2, double lon2) 
	    {
	    	 Log.i("fuck","uuu22222");
	 	   double theta = lon1 - lon2;
	 	   double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	 	   dist = Math.acos(dist);
	 	   dist = rad2deg(dist);
	 	   
	 	   //double dist_mile = dist * 60 * 1.1515;
	 	   
	 	   double dist_km = dist * 1.609344;
	 	   
	 	   return(dist_km);
	 	   } 
	 	   
	    //called functions
	 	 private double deg2rad(double deg)
	 	 
	 	 {
	 	   return (deg * Math.PI / 180.0);
	 	 }

	 	 private double rad2deg(double rad)
	 	 {
	 	   return (rad * 180.0 / Math.PI);
	 	 }
	    
	    
	    
	    
	    
	/*    //---deletes a particular title---
	    public boolean deleteTitle(long rowId) 
	    {
	        return db3.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	    }

	    //---retrieves all the titles---
	    public Cursor getAllTitles() 
	    {
	        return db3.query(DATABASE_TABLE, new String[] {
	        		KEY_ROWID, 
	        		KEY_ISBN,
	        		KEY_TITLE,
	                KEY_PUBLISHER}, 
	                null, 
	                null, 
	                null, 
	                null, 
	                null);
	    }

	    //---retrieves a particular title---
	    public Cursor getTitle(long rowId) throws SQLException 
	    {
	        Cursor mCursor =
	                db3.query(true, DATABASE_TABLE, new String[] {
	                		KEY_ROWID,
	                		KEY_ISBN, 
	                		KEY_TITLE,
	                		KEY_PUBLISHER
	                		}, 
	                		KEY_ROWID + "=" + rowId, 
	                		null,
	                		null, 
	                		null, 
	                		null, 
	                		null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;
	    }

	    //---updates a title---
	    public boolean updateTitle(long rowId, String isbn, 
	    String title, String publisher) 
	    {
	        ContentValues args = new ContentValues();
	        args.put(KEY_ISBN, isbn);
	        args.put(KEY_TITLE, title);
	        args.put(KEY_PUBLISHER, publisher);
	        return db3.update(DATABASE_TABLE, args, 
	                         KEY_ROWID + "=" + rowId, null) > 0;
	    }
	*/        
	    private static class DatabaseHelper extends SQLiteOpenHelper 
	    {
	        DatabaseHelper(Context context) 
	        {
	            super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        }

	        @SuppressWarnings("null")
			@Override
	        public void onCreate(SQLiteDatabase db3) 
	        { 	long id;
	        	id=0;
	        	
	            db3.execSQL(CREATE_TABLE_CATEGORY);
	            db3.execSQL(CREATE_TABLE_LOCATION);
	            Log.i("tupacccc","emineeeeeem");
	            db3.execSQL(CREATE_TABLE_ALARM);
	            
	            //Log.i("tupacccc","emineeeeeem");
	            
	            ContentValues initialValues = new ContentValues();
	 	       
	            //inserting default categories
		        initialValues.put(CATEGORY_NAME, "Medical");
		        id=db3.insert("category", null, initialValues);    
		        initialValues.put(CATEGORY_NAME, "Store");
		        id=db3.insert("category", null, initialValues);  
		        initialValues.put(CATEGORY_NAME, "Study");
		        id=db3.insert("category", null, initialValues);  
		        initialValues.put(CATEGORY_NAME, "Work");
		        id=db3.insert("category", null, initialValues);  
	            
	        }

	        @Override
	        public void onUpgrade(SQLiteDatabase db3, int oldVersion, 
	                              int newVersion) 
	        {
	            Log.w(TAG, "Upgrading database from version " + oldVersion 
	                  + " to "
	                  + newVersion + ", which will destroy all old data");
	            db3.execSQL("DROP TABLE IF EXISTS titles");
	            onCreate(db3);
	        }
	    }   
	    
	    
	}
