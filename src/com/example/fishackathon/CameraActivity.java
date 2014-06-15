package com.example.fishackathon;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.fishackathon.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.os.Build;
import android.provider.MediaStore;

public class CameraActivity extends ActionBarActivity {
	private static final 
	String TAG = "Camera";
	  private static final int 
	  CAPTURE_IMAGE_ACTIVITY_REQ = 0;
	  
	  //General variable initializations
	  private Uri 
	  fileUri 
	  = null;
	  private ImageView 
	  photoImage = null;
	  private 
	  Button 
	  button1;
	  private 
	  File 
	  imageFile;
	  private 
	  LocationManager 
	  locationManager;
	  private 
	  String 
	  provider;
	  private 
	  Location 
	  location;
	  private 
	  Criteria 
	  criteria;
	  
	  // Local variables for latitude and longitude of user
	  private int 
	  lng;
	  private int 
	  lat;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) 
	  {
	    super.onCreate
	    (savedInstanceState);
	    // Calls the layout in the xml
	    setContentView
	    (R.layout.activity_call_camera);
	    // Calls the image view settings in xml
	    photoImage = 
	    (ImageView) 
	    findViewById
	    (R.id.photo_image);
	    // The image view should not be seen
	   photoImage.setVisibility
	   (View.INVISIBLE);
	   // Calls the button settings in xml
	    button1 = 
	    (Button) 
	    findViewById
	    (R.id.button1);
	    // Button initializes the camera interface on screen
	    Button callCameraButton = 
	    (Button) findViewById
	    (R.id.button_callcamera);
	    
	    // Setting up location services for user's location
	    locationManager = (LocationManager) 
	    getSystemService(Context.LOCATION_SERVICE);
	    criteria = new Criteria();
	    // Further setup information
	    provider = locationManager
	    .getBestProvider(criteria, false);
	    location = locationManager
	    .getLastKnownLocation(provider);
	    // Sets up button click listner
	    button1.setOnClickListener(new View.OnClickListener() 
	    {	
			@Override
			public void onClick(View v) 
			{
				// Starts up the intent to send an email to report fisheries
				Intent i = new Intent
				(Intent.ACTION_SEND);
				 // Adding a person's email address
	            i.putExtra
	            (Intent.EXTRA_EMAIL, new String[]
	            {"Alexhauser23@yahoo.com"});
	            // Adding a subject to report fisheries
	            i.putExtra
	            (Intent.EXTRA_SUBJECT
	            ,"On The Job");
	            // Adding the latitude and longitude into the email message
	            i.putExtra(Intent.EXTRA_TEXT
	            	,"<"+lat+","+lng+">");
	            // Attaches the evidence of the illegal fisheries
	            i.putExtra(Intent.EXTRA_STREAM
	            	, Uri.fromFile(imageFile));

	            i.setType("image/png");
	            startActivity(i);
			}
		});
	    // Camera function begins
	    callCameraButton.setOnClickListener(new View.OnClickListener() 
	    {
	      public void onClick(View view) 
	      {
	    	 // Starts the image capture in XML
	        Intent i = new Intent
	        (MediaStore.ACTION_IMAGE_CAPTURE);
	        // Gets photo from camera
	        File file = getOutputPhotoFile();
	        // Inserting photo file into the URI from the Camera
	        fileUri = Uri
	        .fromFile
	        (getOutputPhotoFile());
	        // Adding extra details from the above function
	        i.putExtra
	        (MediaStore.EXTRA_OUTPUT, fileUri);
	        // Let the photo taking begin
	        startActivityForResult
	        (i, CAPTURE_IMAGE_ACTIVITY_REQ );
	      }
	    });
	  }
	  
	  // Function fetches photo file from camera
	  private File getOutputPhotoFile() 
	  {
		  // Setting up directory using External Storage
		  File directory = new File
				  (Environment.getExternalStoragePublicDirectory(
		                Environment.DIRECTORY_PICTURES), getPackageName());
		  // Checks if the directory exist in the external storage
		  if (!directory.exists()) 
		  {
			  //Did we make the directory successfully?
		    if (!directory.mkdirs()) 
		    {
		      //We did not make the directory successfully
		      Log.e(TAG, "Failed to create storage directory.");
		      return null;
		    }
		  }
		  
		  String timeStamp = new SimpleDateFormat
		("yyyMMdd_HHmmss", Locale.UK).format(new Date());
		  //Return the photo as a new file with the proper nomenclature and timestamp.
		  return new File(directory.getPath() + File.separator + "IMG_"  
		                    + timeStamp + ".jpg");
		}
	  
	  //This event will fire after the user takes a picture in the process of submitting a report
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	  {
		  if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQ) {
		    if (resultCode == RESULT_OK) 
		    {
		      Uri photoUri = null;
		      
		      //make the imageView that will contain and show the new photo visible
		      photoImage.setVisibility
		      (View.VISIBLE);
		      
		      
		      provider = locationManager
		    		  .getBestProvider
		    		  (criteria, false);
		      //retrieve the current location from the LocationManager
			  location = locationManager
					  .getLastKnownLocation
					  (provider);
		      
			  //If we received a valid location from the previous result
		      if (location != null) 
		      {
		    	 //Assign longitude and latitude from the location variable's data
		         lat = (int) 
		        		 (location.getLatitude());
		         lng = (int) 
		        		 (location.getLongitude());
		         System.out.println
		         ("Lat: " + String.valueOf(lat) + " , Lng: "+ String.valueOf(lng));
		      } 
		      else 
		      {
		    	 //We did not receive a valid location
		         System.out.println
		         ("Location not available");
		      }
		      
		      if (data == null) 
		      {
		    	//Photo capture succeeded, retrieve from fileUri
		        Toast.makeText
		        (this, "Image saved successfully", 
		                       Toast.LENGTH_LONG).show();
		        photoUri = fileUri;
		      } 
		      else 
		      {
		    	//Photo capture failed, retrieve by calling the showPhoto(Uri photoUri) method
		        photoUri = data.getData();
		        Toast.makeText
		        (this, "Image saved successfully in: " + data.getData(), 
		                       Toast.LENGTH_LONG).show();
		      }
		       showPhoto(photoUri);
		    } 
		    else if (resultCode == RESULT_CANCELED) 
		    {
		      //Photo was cancelled
		      Toast.makeText
		      (this, "Cancelled", Toast.LENGTH_SHORT).show();
		    } 
		    else 
		    {
		      //Photo capture failed
		      Toast.makeText
		      (this, "Callout for image capture failed!", 
		                     Toast.LENGTH_LONG).show();
		    }
		  }
		}
	  
	  //Display the photo on the 'photoImage' ImageView.
	  private void showPhoto(Uri photoUri) 
	  {
		  String filePath = photoUri.getEncodedPath(); 
		  imageFile = new File(filePath);
		  if(imageFile.exists())
		  {
		     Bitmap bitmap = 
		    BitmapFactory.decodeFile
		    (imageFile.getAbsolutePath());
		     BitmapDrawable drawable = 
		    new BitmapDrawable
		    (this.getResources(), bitmap);
		     photoImage.setScaleType
		     (ImageView.ScaleType.FIT_CENTER);
		     photoImage
		     .setImageDrawable
		     (drawable);
		  }       
	  }
	  
}
