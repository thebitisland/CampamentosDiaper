package com.thebitisland.campamentosdiaper.auxClasses;

public class ItemDetail {

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
		return name;
	}

	public CharSequence getDescr() {
		return descr;
	}
	
	public long getID(){
		return id;
	}

}
