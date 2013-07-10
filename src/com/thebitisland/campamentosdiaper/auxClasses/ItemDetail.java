package com.thebitisland.campamentosdiaper.auxClasses;

import java.io.Serializable;

public class ItemDetail implements Serializable {

    private long id;
    //private int imgId;
    private String name;
    private String descr;
	
    public ItemDetail(String name, String descr, long id){
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
