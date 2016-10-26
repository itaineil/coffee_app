package com.example.ordercoffee;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutMacchiato extends Activity{

	TextView tvMarkTitle,tvAboutMark;
	private static Coffee markiato;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee);
		
		tvMarkTitle = (TextView)findViewById(R.id.tvCoffeeName);
		tvAboutMark = (TextView)findViewById(R.id.tvCoffeDetails);
		
		
		markiato = new Coffee();
		markiato.setTitle("Macchiato");
		markiato.setDetails("Macchiato is an Espresso with a dash of formed milk. The milk is foamed directly into the espresso cup which is then put under the coffee outlet. It sometimes comes with cocoa which is sprinkled over the drink");
		
		tvMarkTitle.setText(markiato.getTitle());
		tvAboutMark.setText(markiato.getDetails());
		
		
		
	}

}
