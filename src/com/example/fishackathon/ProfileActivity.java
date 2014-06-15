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
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;
import android.os.Build;

public class ProfileActivity extends ActionBarActivity {
	//VideoView vid;
	// This initiates submit of all fields
	private 
	Button 
	menuButton;
	// Field holds first name
	private 
	EditText 
	fNameField;
	// Field holds last name
	private 
	EditText 
	lNameField;
	// Field holds users phone numbers
	private 
	EditText 
	phoneNumField;
	// Field holds users home address
	private 
	EditText 
	homeAddressField;
	//Button menuButton2;
	// Player for background sounds
	private 
	MediaPlayer 
	mp;
	private 
	SharedPreferences 
	sharedPref;
	private 
	SharedPreferences.Editor 
	editor;
	
	@Override 
	protected void onResume()
	{
		super.onResume();
		//vid.start();
	
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        // Connects the first name field to the XML
        fNameField = 
        (EditText)
        findViewById
        (R.id.editText1);
     // Connects the last name field to the XML
        lNameField = 
        (EditText)
        findViewById
        (R.id.editText2);
     // Connects the phone number field to the XML
        phoneNumField = 
        (EditText)
        findViewById
        (R.id.editText3);
     // Connects the address field to the XML
        homeAddressField = 
        (EditText)
        findViewById
        (R.id.editText5);
        
        // Initiates persistent storage for app
        sharedPref = 
        getApplicationContext()
        .getSharedPreferences
        ("fishPrefs", 0);
        // It is time to edit
        editor = sharedPref.edit();
        // Connects to button1 in the XML
        menuButton = 
        (Button) 
        findViewById
        (R.id.button1);
        menuButton
        .setOnClickListener
        (new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UpdatePrefs();
				
				// Returns to main menu after user enters registration info
				finish();
			}
		});
        
    }
    
    // This function takes all fields and stores them in persistent storage
    public void UpdatePrefs()
    {
    	
    	// Data from the first name field is being stored into persistent storage
    	editor.putString
    	("name", 
    	fNameField.getText()
    	.toString());
    	
    	// Data from the last name field is being stored into persistent storage
        editor.putString
        ("lName",
        lNameField.getText()
        .toString());
        
        // Data from the phone number field is being stored into persistent storage
        editor.putString
        ("phoneNum"
        , phoneNumField.getText()
        .toString());
        
       // Data from the address field is being stored into persistent storage
        editor.putString
        ("address"
        , homeAddressField.getText()
        .toString());
        
        // Push all fields data to persistent storage forever
        editor.commit();
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
