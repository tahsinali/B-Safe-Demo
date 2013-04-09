package com.example.b_safe;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.net.Uri;
import android.content.Intent;

//import java.util.Random;

public class MainActivity extends Activity {

	private ImageButton imageButton; 
	private String messageText;
	private String phoneNumber="4342270420";
	private double latitude;
	private double longitude;
	
	GPSTracker gps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageButton = (ImageButton) findViewById(R.id.imageButton1);
		
		imageButton.setOnClickListener(new View.OnClickListener() {
			  public void onClick(View arg0) {			  			  
			
				  gps = new GPSTracker(MainActivity.this);

					// check if GPS enabled		
			        if(gps.canGetLocation()){
			        	
			        	latitude = gps.getLatitude();
			        	longitude = gps.getLongitude();
			        	
			        	messageText="I need immediate help!" + "." + "\nMy location is - \nLat: " + latitude + "\nLong: " + longitude;
			        }else{
			        	// can't get location
			        	// GPS or Network is not enabled
			        	// Ask user to enable GPS/network in settings
			        	gps.showSettingsAlert();
			        }
					
				    sendSMS();
				    sendCALL();
				 }
	        });
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendSMS() {
		SmsManager smsManager = SmsManager.getDefault();
       smsManager.sendTextMessage(phoneNumber, null, messageText, null, null);
        
        Toast.makeText(getApplicationContext(), "Message Sent!" + "\n" + messageText, Toast.LENGTH_LONG).show();
    }
	public void sendCALL() {
        
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:4343264413" ));
		startActivity(intent);
		
        Toast.makeText(getApplicationContext(), "Call Sent!", Toast.LENGTH_LONG).show();
    }
/*	public void genCODE() {
		Random r = new Random();
		int i1=r.nextInt(9999-1000) + 1000;
	}*/
}
