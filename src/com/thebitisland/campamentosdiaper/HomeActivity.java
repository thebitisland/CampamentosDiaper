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

public class HomeActivity extends Activity{

	private ExpandableListAdapter mAdapter;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
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
		List<Category> cats = new ArrayList<Category>();
		cats.add(new Category("Campamento 1","Blalbabla", 1));
		cats.add(new Category("Campamento 2","tirururuia", 2));
		cats.add(new Category("Campamento 3","yooooooo", 3));
		
		List<List<ItemDetail>> items = new ArrayList<List<ItemDetail>>();
		List<ItemDetail> Campamento1 = new ArrayList<ItemDetail>();
		Campamento1.add(new ItemDetail("Personal","Director, monitores...", 1));
		Campamento1.add(new ItemDetail("Actividades","Lista de las actividades y su material", 2));
		Campamento1.add(new ItemDetail("Mapa","pues eso", 3));
		items.add(Campamento1);
		
		List<ItemDetail> Campamento2 = new ArrayList<ItemDetail>();
		Campamento2.add(new ItemDetail("Personal","Director, monitores...", 1));
		Campamento2.add(new ItemDetail("Actividades","Lista de las actividades y su material", 2));
		Campamento2.add(new ItemDetail("Mapa","pues eso", 3));
		items.add(Campamento2);
		
		List<ItemDetail> Campamento3 = new ArrayList<ItemDetail>();
		Campamento3.add(new ItemDetail("Personal","Director, monitores...", 1));
		Campamento3.add(new ItemDetail("Actividades","Lista de las actividades y su material", 2));
		Campamento3.add(new ItemDetail("Mapa","pues eso", 3));
		items.add(Campamento3);
		
		ExpandableListView exList = (ExpandableListView) findViewById(R.id.expandable_list);  
		mAdapter = new CustomExpandableListAdapter(context, cats, items);
		exList.setIndicatorBounds(0, 20);
		exList.setAdapter(mAdapter);
	}

}
