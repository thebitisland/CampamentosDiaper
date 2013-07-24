package com.thebitisland.campamentosdiaper;

import java.util.ArrayList;
import java.util.List;

import com.thebitisland.campamentosdiaper.auxClasses.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class PeopleActivity extends Activity{

	private ExpandableListAdapter mAdapter;
	Context context;
	DBManager database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people);
		
		context = getApplicationContext();
		database = new DBManager(this);
		generateExpandableListView();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void generateExpandableListView(){
		List<Person> cats = new ArrayList<Person>();
		List<List<PersonOptions>> items = new ArrayList<List<PersonOptions>>();
		database.open();
		String[][] users = database.getAllUsers();
		for(int i=0;i<users.length;i++) {
		cats.add(new Person(users[i][2]+" "+users[i][3],users[i][6],"Monitor", R.drawable.photo, 1));
		List<PersonOptions> persona = new ArrayList<PersonOptions>();
		persona.add(new PersonOptions(Integer.parseInt(users[i][0])));
		items.add(persona);
		}
		
		/*
		List<PersonOptions> persona2 = new ArrayList<PersonOptions>();
		persona2.add(new PersonOptions(1));
		items.add(persona2);
		
		List<PersonOptions> persona3 = new ArrayList<PersonOptions>();
		persona3.add(new PersonOptions(1));
		items.add(persona3);
		*/
		ExpandableListView exList = (ExpandableListView) findViewById(R.id.people_list);  
		mAdapter = new PeopleExpandableListAdapter(context, cats, items);
		exList.setIndicatorBounds(0, 20);
		exList.setAdapter(mAdapter);
	}

}
