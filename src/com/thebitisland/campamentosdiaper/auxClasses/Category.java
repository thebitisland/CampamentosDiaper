package com.thebitisland.campamentosdiaper.auxClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

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
		// TODO Auto-generated method stub
		return name;
	}

	public CharSequence getDescr() {
		// TODO Auto-generated method stub
		return descr;
	}


}
