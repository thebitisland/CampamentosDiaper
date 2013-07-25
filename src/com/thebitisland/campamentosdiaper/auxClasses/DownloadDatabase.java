package com.thebitisland.campamentosdiaper.auxClasses;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

public class DownloadDatabase extends AsyncTask<Void, Void, Void> {
	
	private final String DATABASE_URL = "http://camps.thebitisland.com/CampsDB.db";
	private final String database_folder = "/data/data/com.thebitisland.campamentosdiaper/databases/";
	

	@Override
	protected Void doInBackground(Void... arg0) {
		
		String filename = "CampsDB.db";
        
        try {

        URL input = new URL(DATABASE_URL);
        URLConnection conn = input.openConnection();
        int contentLength = conn.getContentLength();
        
        DataInputStream stream = new DataInputStream(input.openStream());
        byte[] buffer = new byte[contentLength];
        stream.readFully(buffer);
        stream.close();
        
        DataOutputStream output = new DataOutputStream(new FileOutputStream(database_folder + filename));
        output.write(buffer);
        output.flush();
        output.close();
        
        } catch(FileNotFoundException e) {
        } catch (IOException e) {
        }
		return null;
	}
	
	

}
