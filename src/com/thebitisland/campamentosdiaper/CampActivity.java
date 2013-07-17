package com.thebitisland.campamentosdiaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CampActivity extends Activity {

	Context context;
	Button campList;
	Button mapButton;
	Button peopleList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camp);

		context = getApplicationContext();
		
		peopleList = (Button) findViewById(R.id.personal_button);
		peopleList.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent people = new Intent(context, PeopleActivity.class);
				startActivity(people);
			}
		});

		campList = (Button) findViewById(R.id.camps_button);
		campList.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent camps = new Intent(context, HomeActivity.class);
				startActivity(camps);
			}

		});

		mapButton = (Button) findViewById(R.id.map_button);
		mapButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				//base de datos, claro
				String place = "Santoña, Cantabria";
				String query = "geo:0,0?q=hospitals near "+ place;
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(query));
				startActivity(intent);
			}

		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
