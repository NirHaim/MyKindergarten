package com.example.mykindergarten;

/**
 * Store information about a Child.
 */
public class Child {
	private String fName="";
	private String lName="";
	private int presence=0;

	
	public Child(String fName, String lName, int presence)
	{
		super();
		this.fName = fName;
		this.lName = lName;
		this.presence = presence;
	}
	
	public String getFName() {
		return fName;
	}
	public String getLName() {
		return lName;
	}
	public int getPresence() {
		return presence;
	}
}
