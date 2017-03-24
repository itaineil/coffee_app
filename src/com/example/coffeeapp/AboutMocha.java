package com.example.coffeeapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AboutMocha extends Activity{

	TextView tvMochaTitle,tvMochaAbout;
	public static Coffee mocha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee);
		
		tvMochaTitle = (TextView)findViewById(R.id.tvCoffeeName);
		tvMochaAbout = (TextView)findViewById(R.id.tvCoffeDetails);
	
		mocha = new Coffee();
		mocha.setTitle("Mochaccino");
		mocha.setDetails("This is a variant of a caffe latte. It is made up of one third espresso and two thirds steamed milk, but a portion of chocolate is added. This is typically in the form of a chocolate syrup. Mochas can contain dark or milk chocolate");
		
		
		tvMochaTitle.setText(mocha.getTitle());
		tvMochaAbout.setText(mocha.getDetails());
		
		
	}

}
