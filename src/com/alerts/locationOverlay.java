package com.alerts;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class locationOverlay extends Overlay {
	private final int mRadius = 5;
	ArrayList<stringGeoPoint> arrayLocations;
	Cursor curLocations;
	
	public locationOverlay(Cursor cursor) {
	super();
	
	 curLocations = cursor;
	 refreshLocations();
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		Projection projection = mapView.getProjection();
		
	if (shadow == false) {
		
		
		for (stringGeoPoint strPoint : arrayLocations) {
			GeoPoint  gPoint=strPoint.location_geoPoint;
			Point point = new Point();
			projection.toPixels(gPoint, point);
			
					
			RectF oval = new RectF(point.x - mRadius, point.y - mRadius,
			point.x + mRadius, point.y + mRadius);
			// Setup the paint
			Paint paint = new Paint();
			paint.setARGB(250, 255, 255, 255);
			paint.setAntiAlias(true);
			paint.setFakeBoldText(true);
			Paint backPaint = new Paint();
			backPaint.setARGB(175, 50, 50, 50);
			backPaint.setAntiAlias(true);
			RectF backRect = new RectF(point.x + 2 + mRadius,
			point.y - 3*mRadius,
			point.x + 65, point.y + mRadius);
			// Draw the marker
			canvas.drawOval(oval, paint);
			canvas.drawRoundRect(backRect, 5, 5, backPaint);
			canvas.drawText(strPoint.location_name,
					point.x + 2*mRadius, point.y,
					paint);
			}
					
		}
			
	super.draw(canvas, mapView, shadow);
					
	}
	
	@Override
	public boolean onTap(GeoPoint point, MapView mapView) {
		return false;
	}
	
		
		private void refreshLocations() {
			
		if (curLocations.moveToFirst())
		do {
			Log.i("ad","(curLocations.moveToFirst())");
			Double lat = curLocations.getInt(curLocations.getColumnIndex(DBAdapter.LOCATION_LAT)) * 1E6;
			Log.i("ad","s.getColumnIndex(DBAdapter.LOCATION_LAT))");
			Double lng =curLocations.getInt(curLocations.getColumnIndex(DBAdapter.LOCATION_LNG))* 1E6;
			String locName = curLocations.getString(curLocations.getColumnIndex(DBAdapter.LOCATION_NAME));
			
			Log.i("ad","values "+"lat "+lat+" lng "+lng+" name "+locName);
			
			//curLocations.getInt(columnIndex)
			GeoPoint geoPoint = new GeoPoint(lng.intValue(),lat.intValue());
			
			Log.i("ad","values "+"glat "+geoPoint.getLatitudeE6()+" glng "+geoPoint.getLongitudeE6()+" name "+locName);
			stringGeoPoint str = new stringGeoPoint(locName, geoPoint);
			
			Log.i("ad","values "+"slat "+str.location_geoPoint.getLongitudeE6()+" slng "+str.location_geoPoint.getLatitudeE6()+" sname "+str.location_name);
			
			// TODO arrayLocations.add(str);
			
		} while(curLocations.moveToNext());
	}
	
	}
	


