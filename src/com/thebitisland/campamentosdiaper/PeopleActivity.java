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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people);
		
		context = getApplicationContext();
		
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
		cats.add(new Person("Juan Luis Sanz","600600500","Monitor", R.drawable.photo, 1));
		cats.add(new Person("Álvaro Sánchez Pérez","600500600", "Monitor", R.drawable.photo, 2));
		cats.add(new Person("Jorge Lavín Montoro","600500500", "Infiltrado", R.drawable.photo, 3));
		
		List<List<PersonOptions>> items = new ArrayList<List<PersonOptions>>();
		List<PersonOptions> persona1 = new ArrayList<PersonOptions>();
		persona1.add(new PersonOptions(1));
		items.add(persona1);
		
		List<PersonOptions> persona2 = new ArrayList<PersonOptions>();
		persona2.add(new PersonOptions(1));
		items.add(persona2);
		
		List<PersonOptions> persona3 = new ArrayList<PersonOptions>();
		persona3.add(new PersonOptions(1));
		items.add(persona3);
		
		ExpandableListView exList = (ExpandableListView) findViewById(R.id.people_list);  
		mAdapter = new PeopleExpandableListAdapter(context, cats, items);
		exList.setIndicatorBounds(0, 20);
		exList.setAdapter(mAdapter);
	}

}
