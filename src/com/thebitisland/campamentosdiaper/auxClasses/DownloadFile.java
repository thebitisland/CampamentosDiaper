package com.thebitisland.campamentosdiaper.auxClasses;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.thebitisland.campamentosdiaper.R;

import android.os.Environment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

public class DownloadFile extends AsyncTask<String, Void, File> {
	
	private File storage_path;
	private ProgressDialog pDialog;
	private Context context;
	
	public DownloadFile(Context context) {
		this.context = context;
		pDialog = new ProgressDialog(context);
		pDialog.setIndeterminate(false);
		pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        pDialog.setMessage(context.getResources().getString(R.string.download_file));

	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		pDialog.show();
	}


	@Override
	protected File doInBackground(String... file) {
		
		publishProgress();
		storage_path = Environment.getExternalStorageDirectory();
		File path = new File(storage_path, "activityFile.pdf");
		URL input;
		try {
			input = new URL(file[0]);
			URLConnection conn = input.openConnection();
	        int contentLength = conn.getContentLength();
	        
	        DataInputStream stream = new DataInputStream(input.openStream());
	        byte[] buffer = new byte[contentLength];
	        stream.readFully(buffer);
	        stream.close();
	        
	        DataOutputStream output = new DataOutputStream(new FileOutputStream(path));
	        output.write(buffer);
	        output.flush();
	        output.close();
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
	@Override
	protected void onPostExecute(File result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pDialog.dismiss();
	}

}
