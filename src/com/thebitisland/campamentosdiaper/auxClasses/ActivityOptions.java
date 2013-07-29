package com.thebitisland.campamentosdiaper.auxClasses;

import android.widget.Button;

public class ActivityOptions {
	
	private long id;
	private Button downloadFile;
	private Button checklist;
	
	public ActivityOptions(long id) {
		this.id = id;
	}
	
	public long getID() {
		return id;
	}
	
	public Button getDownloadButton() {
		return downloadFile;
	}
	
	public Button getChecklistButton() {
		return checklist;
	}
	
	

}
