package com.thebitisland.campamentosdiaper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.thebitisland.campamentosdiaper.auxClasses.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class GamesActivity extends Activity{

	private ExpandableListAdapter mAdapter;
	static Context context;
	DBManager database;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_games);
		
		context = getApplicationContext();
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		database = new DBManager(this);
		try {
			generateExpandableListView();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Why do we even need a menu here?
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}*/
	
	public void generateExpandableListView() throws InterruptedException, ExecutionException{
		List<ListActivity> acts = new ArrayList<ListActivity>();
		List<List<ActivityOptions>> items = new ArrayList<List<ActivityOptions>>();
		database.open();
		String[][] activities = database.getCampActivities(1);
		for(int i=0;i<activities.length;i++) {
			
			Bitmap photo = getBitMapFromURL(activities[i][3]);
			
		acts.add(new ListActivity(Integer.parseInt(activities[i][0]), activities[i][1],
				activities[i][2], photo));
		Log.d("Activity", activities[i][0]+" "+activities[i][1]+" "+activities[i][2]+" "+activities[i][3]);
		List<ActivityOptions> activity = new ArrayList<ActivityOptions>();
		activity.add(new ActivityOptions(Integer.parseInt(activities[i][0])));
		items.add(activity);
		}
		

		//Bitmap bmp = getBitMap(String name);

		//Bitmap bPNGcompress = codec(bmp, Bitmap.CompressFormat.PNG, 0);

		//int h = bmp.getHeight();
		
		//Log.d("altura",""+h);
		/*
		List<PersonOptions> persona2 = new ArrayList<PersonOptions>();
		persona2.add(new PersonOptions(1));
		items.add(persona2);
		
		List<PersonOptions> persona3 = new ArrayList<PersonOptions>();
		persona3.add(new PersonOptions(1));
		items.add(persona3);
		*/
		ExpandableListView exList = (ExpandableListView) findViewById(R.id.games_list);  
		mAdapter = new ActivityExpandableListAdapter(this, acts, items);
		exList.setIndicatorBounds(0, 20);
		exList.setAdapter(mAdapter);
		database.close();
	}
	
	/*Generate PNG bitmap from bitmap */
	private static Bitmap codec(Bitmap src, Bitmap.CompressFormat format,
			int quality) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		src.compress(format, quality, os);
 
		byte[] array = os.toByteArray();
		return BitmapFactory.decodeByteArray(array, 0, array.length);
	}
	
	/*Generate bitmap from string given from DB */
	private static Bitmap getBitMapFromURL(String fileURL) throws InterruptedException, ExecutionException{
		
		DownloadImage imageTask = new DownloadImage();
		imageTask.execute(fileURL);
		Bitmap bitmap = imageTask.get();
		
		if(bitmap == null )
			return BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.photo);
		
		return bitmap;
	}
	
	
 
}

