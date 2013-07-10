package com.thebitisland.campamentosdiaper.auxClasses;

/*import java.util.ArrayList;
import java.util.List;*/

public class Category  {

	private long id;
    private String name;
    private String descr;

    //private List<ItemDetail> itemList = new ArrayList<ItemDetail>();

    public Category(String name, String descr, long id){
    	this.descr=descr;
    	this.name=name;
    	this.id=id;
    }
    
	public CharSequence getName() {
		return name;
	}

	public CharSequence getDescr() {
		return descr;
	}
	
	public long getID(){
		return id;
	}


}
