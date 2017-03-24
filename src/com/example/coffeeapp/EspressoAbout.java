package com.example.coffeeapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EspressoAbout extends Activity{

	TextView espressoTitle,espressoAbout;
	public static Coffee espresso;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee);
		
		espressoTitle = (TextView)findViewById(R.id.tvCoffeeName);
		espressoAbout = (TextView)findViewById(R.id.tvCoffeDetails);
		
		
		espresso = new Coffee();
		espresso.setTitle("Espresso");
		espresso.setDetails("This is a black strong coffee. It is prepared by pushing with pressure hot water through fine ground coffee. It is thicker than regular drip coffee and served in small cups");
		
		espressoTitle.setText(espresso.getTitle());
		espressoAbout.setText(espresso.getDetails());
		
	}

}
