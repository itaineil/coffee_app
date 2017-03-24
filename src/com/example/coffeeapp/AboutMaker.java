package com.example.coffeeapp;

public class AboutMaker {
	
	
	public String person;
	public String details;
	
	public AboutMaker(){
		
		
	}
	
	
	public AboutMaker(String person,String details){
		
		this.person = person;
		this.details = details;
	}


	public String getPerson() {
		return person;
	}


	public void setPerson(String person) {
		this.person = person;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}

}
