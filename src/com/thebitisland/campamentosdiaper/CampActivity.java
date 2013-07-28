package com.thebitisland.campamentosdiaper;

import com.thebitisland.campamentosdiaper.auxClasses.AuxMethods;
import com.thebitisland.campamentosdiaper.auxClasses.DownloadDatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CampActivity extends Activity {

	Context context;
	Button campList;
	Button mapButton;
	Button peopleList;
	SharedPreferences prefs;
	TextView dbversion;
	Boolean isConnected;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camp);

		context = getApplicationContext();
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		dbversion = (TextView) findViewById(R.id.dbversion);
		dbversion.setText("v."+String.valueOf(prefs.getInt("DBVersion", 0)/10));
		
		isConnected = AuxMethods.checkInternetConnection(context);
		
		if (isConnected) {
			DownloadDatabase thread = new DownloadDatabase(prefs, this);
			thread.execute();
		}

		peopleList = (Button) findViewById(R.id.personal_button);
		peopleList.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent people = new Intent(context, PeopleActivity.class);
				startActivity(people);
			}
		});

		/*
		 * campList = (Button) findViewById(R.id.camps_button);
		 * campList.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View arg0) { Intent camps = new Intent(context,
		 * HomeActivity.class); startActivity(camps); }
		 * 
		 * });
		 */

		mapButton = (Button) findViewById(R.id.map_button);
		mapButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// base de datos, claro
				String place = "Santoña, Cantabria";
				String query = "geo:0,0?q=hospitals near " + place;
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse(query));
				startActivity(intent);
			}

		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.home_settings_logoff:
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("loginOK", false);
			editor.commit();
			Intent login = new Intent(context, LoginActivity.class);
			startActivity(login);
			finish();
			return true;
		case R.id.home_settings_options:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
