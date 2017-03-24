package com.example.coffeeapp;

public class Coffee {
	
	public String title;
	public String details;
	
	
	public Coffee(){
		
		
	}

	
	public Coffee(String title,String details){
		
		this.title = title;
		this.details = details;
		
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
