package com.thebitisland.campamentosdiaper.auxClasses;

import android.graphics.Bitmap;
import android.widget.ImageView;

/*import java.util.ArrayList;
import java.util.List;*/

public class Person  {

	private long id;
    private String fullname;
    private String telephone;
    private String occupation;
    private Bitmap photo;

    //private List<ItemDetail> itemList = new ArrayList<ItemDetail>();

    public Person(String fullname, String telephone, String occupation, Bitmap photo, long id){
    	this.fullname=fullname;
    	this.telephone=telephone;
    	this.occupation=occupation;
    	this.photo=photo;
    	this.id=id;
    }
    
	public CharSequence getFullName() {
		return fullname;
	}

	public CharSequence getTelephone() {
		return telephone;
	}
	
	public CharSequence getOccupation() {
		return occupation;
	}
	
	public long getID(){
		return id;
	}
	
	public Bitmap getPhoto(){
		return photo;
	}


}
