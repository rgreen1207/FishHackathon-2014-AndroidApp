package com.example.fishackathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.fishackathon.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;
import android.os.Build;

public class VesselActivity extends ActionBarActivity {
	// Shows two options for boat propulsion 
	private Spinner spinner1;
	private Button button1;
	private EditText gearField;
	private SharedPreferences sharedPref;
	
	@Override 
	protected void onResume()
	{
		super.onResume();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vessel);
        
        sharedPref = getApplicationContext().getSharedPreferences("fishPrefs", 0);
        
        gearField = (EditText)findViewById(R.id.editText2);
        
        button1 = (Button)findViewById(R.id.button1);
        
        button1.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				try 
				{
					SendData();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        // Connects spinner declaration with XML
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        
        // Content in the spinner
        List<String> list = new ArrayList<String>();
        // Here are the possible motors
        list.add("Motorized");
        list.add("Paddle");

        
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item,list);
        // The content is being inserted into drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
        spinner1.setAdapter(dataAdapter);
        // Listening for the user to click spinner 
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    
    protected void SendData() throws IOException
    {
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    	StrictMode.setThreadPolicy(policy);
    	
    	try {
			try {
				Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String sUrl = "https://hosting.otterlabs.org/db/index.php?db=gree1924&token=9627a24907d0cc1442f99f3ec6ee058a#PMAURL:db=gree1924&table=ExistingRegistration&target=sql.php&token=9627a24907d0cc1442f99f3ec6ee058a";
    	
    	String server = "198.189.160.18:33";
    	String db = "gree1924";
    	String user = "gree1924";
    	String pass = "11e46c7092551b4";
    	

    	
    	
    	
    	Connection conn = null;
    	String connUrl = null;
    	
    	connUrl="jdbc:jtds:sqlserver://" + server + ";" + "databaseName=" + db + ";user=" + user + ";password=" + pass + ";";
        try 
        {
			conn=DriverManager.getConnection(connUrl);
		} 
        catch (SQLException e) 
		{
			System.out.println("Did not connect successfully");
			e.printStackTrace();
		}
    	
        int rs;
        Statement statement;
		try 
		{
			statement = conn.createStatement();
			rs=statement.executeUpdate("INSERT INTO ExistingRegistration VALUES('Bob')");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
        
//    	try 
//    	{
//			DriverManager.getConnection(sUrl,"gree1924", "11e46c7092551b4");
//			System.out.println("Connected successfully");
//		} catch (SQLException e)
//		{
//			System.out.println("Did not connect successfully");
//			e.printStackTrace();
//		}
    	
//    	String username = "gree1924";
//    	String password = "11e46c7092551b4";
//    	URL url;
//    	String ownersName = sharedPref.getString("name", " ")+" "+sharedPref.getString("lName", " ");
//        String phoneNum = sharedPref.getString("phoneNum", " ");
//        String address = sharedPref.getString("address", " ");
//        
//
//		url = new URL("http://hosting.otterlabs.org/greenryanr/FishHackathon/add.php");
//
//    	String data  = URLEncoder.encode("username", "UTF-8") 
//    	+ "=" + URLEncoder.encode(username, "UTF-8");
//    	
//    	data += "&" + URLEncoder.encode("password", "UTF-8") 
//    	+ "=" + URLEncoder.encode(password, "UTF-8");
//    	
//    	data += "&" + URLEncoder.encode("name", "UTF-8") 
//    	+ "=" + URLEncoder.encode(ownersName, "UTF-8");
//    	
//    	data += "&" + URLEncoder.encode("phone", "UTF-8") 
//    	+ "=" + URLEncoder.encode(phoneNum, "UTF-8");
//    	
//    	data += "&" + URLEncoder.encode("address", "UTF-8") 
//    	+ "=" + URLEncoder.encode(address, "UTF-8");
//    	
//    	data += "&" + URLEncoder.encode("gear", "UTF-8") 
//    	+ "=" + URLEncoder.encode(gearField.getEditableText().toString(), "UTF-8");
//    	
//    	URLConnection conn = url.openConnection(); 
//    	
//    	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
//    	
//    	//StreamDump sD;
//    	
//    	wr.write(data); 
    	 //BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
