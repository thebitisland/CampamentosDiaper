package com.thebitisland.campamentosdiaper.auxClasses;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

	@Override
	protected Bitmap doInBackground(String... url) {
		
		
		URL fileURL;
		Bitmap activityPhoto = null;
		try {
			fileURL = new URL(url[0]);
        HttpURLConnection connection = (HttpURLConnection) fileURL.openConnection();
        connection.setDoInput(true);
        connection.connect();
        
        InputStream input = connection.getInputStream();
        activityPhoto = BitmapFactory.decodeStream(input);
        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activityPhoto;
	}

}
