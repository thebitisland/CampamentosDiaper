package com.thebitisland.campamentosdiaper.auxClasses;

import android.graphics.Bitmap;

public class CampActivity {
	
	private int id;
	private String name;
	private String file_url;
	private Bitmap picture;
	
	public CampActivity(int id, String name, String file_url, Bitmap picture) {
		this.id = id;
		this.name = name;
		this.file_url = file_url;
		this.picture = picture;
	}
	
	public long getID(){
		return id;
	}
	
	public CharSequence getName() {
		return name;
	}

	public CharSequence getFileURL() {
		return file_url;
	}
	
	public Bitmap getPicture(){
		return picture;
	}

}
