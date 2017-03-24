package com.example.coffeeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity{

	TextView aboutPerson,aboutDetails;
	public static AboutMaker about;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		aboutPerson = (TextView)findViewById(R.id.tvAbout);
		aboutDetails = (TextView)findViewById(R.id.tvAboutDetails);
		
		
		about = new AboutMaker();
		
		about.setPerson("About");
		about.setDetails( "By Itai Neil Chiriseri " + "\n\n" + "email: inchiriseri@gmail.com" + "\n\n"+ "cell: 0775 770 137");
		
		aboutPerson.setText(about.getPerson());
		aboutDetails.setText(about.getDetails());
	
		
		
	}

}
