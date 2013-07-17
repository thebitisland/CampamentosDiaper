package com.thebitisland.campamentosdiaper.auxClasses;

import android.widget.Button;

public class PersonOptions {

    private long id;
    //private int imgId;
    private Button callPerson;
    private Button addContact;
    private Button sendSMS;
	
    public PersonOptions(long id){
    	this.id=id;
    }
    
    public Button getCallButton() {
		return callPerson;
	}

	public Button getAddContactButton() {
		return addContact;
	}
	
	public Button getSMSButton() {
		return sendSMS;
	}
	
	public long getID(){
		return id;
	}

}
