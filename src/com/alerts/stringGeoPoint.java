package com.alerts;

import java.util.Date;

import com.google.android.maps.GeoPoint;

import android.location.Location;

public class stringGeoPoint {

	public String location_name;
	public GeoPoint location_geoPoint;
	
	
	public stringGeoPoint(String locName, GeoPoint geoPoint) {
		// TODO Auto-generated constructor stub
		location_name=locName;
		location_geoPoint=geoPoint;
	}


	
}
