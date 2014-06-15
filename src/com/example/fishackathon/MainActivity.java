package com.example.fishackathon;

import com.example.fishackathon.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.os.Build;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

// Entry point of the application
public class MainActivity extends ActionBarActivity {
	private Button 
	menuButton;
	private 
	Button 
	menuButton2;
	private 
	MediaPlayer 
	mp;
	private 
	SharedPreferences 
	sharedPref;
	
	@Override 
	protected void onResume()
	{
		super.onResume();
	}
	
    @Override
    protected void 
    onCreate
    (Bundle savedInstanceState) 
    {
        super.onCreate
        (savedInstanceState);
        setContentView
        (R.layout.activity_main);
        
        // App's persistent data storage
        sharedPref = getApplicationContext()
        		.getSharedPreferences
        		("fishPrefs", 0);
        
        // Plays ocean background ambience
        mp = MediaPlayer
        	.create(this,R.raw.oceanwaves );
        mp.start();
        
        // Determines if the application has already stored the user's name
        // If the user is new, it transitions to the profile page
        if(sharedPref.getString("name", null) == null)
        {
        	Toast.makeText
        	(getApplicationContext(), 
            "Welcome! Please set up your profile!",
            Toast.LENGTH_SHORT).show();
        	
        	startActivity
        	(new Intent("android.intent.action.PROFILE"));
        }
        
        //If the user already submitted their information, it displays the welcome message
        else
        {
        	Toast.makeText
        	(getApplicationContext(),"Welcome " +
        			sharedPref
        			.getString("name", null),
            		Toast.LENGTH_SHORT).show();
        	
        }
        // Register Boat form
        menuButton = 
        (Button) 
        findViewById
        (R.id.button1);
        // Detects when the user clicks the button
        menuButton
        .setOnClickListener
        (new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				// Opens corresponding java file
				startActivity
				(new Intent
				("android.intent.action.VESSEL"));
			}
		});
        
        // Button allows users to report illegal trawlers
        menuButton2 = 
        (Button) 
        findViewById
        (R.id.button2);
        // Detects when the user clicks the button
        menuButton2
        .setOnClickListener
        (new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				// Opens corresponding java file
				startActivity(new Intent
				("android.intent.action.MYCAMERA"));
			}
		});
		
    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/
 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

*/

}
