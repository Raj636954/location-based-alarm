package com.alerts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class alert extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){

		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.alert);

}
	public void btnNewAlert(View dialogView){
		Log.i("main","b4");
		Intent NewAlert = new Intent(alert.this,NewAlert.class);
		Log.i("jj5424","sss");
		startActivity(NewAlert);
	
	
}

}