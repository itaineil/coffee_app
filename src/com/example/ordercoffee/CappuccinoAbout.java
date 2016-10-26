package com.example.ordercoffee;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CappuccinoAbout extends Activity {

	TextView cappuchinoName;
	TextView cappuchinoAbout;
	public static Coffee cappuchino;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee);

		cappuchinoName = (TextView) findViewById(R.id.tvCoffeeName);
		cappuchinoAbout = (TextView) findViewById(R.id.tvCoffeDetails);

		cappuchino = new Coffee();
		cappuchino.setTitle("Cappuccino");
		cappuchino
				.setDetails("Cappuchino is a coffee-based drink prepared with espresso, hot milk, and steamed milk foam. The total espresso and milk/foam makes up approximately 250 and 180 millilitres and its served in a porcelain cup for heat retention purposes");

		cappuchinoName.setText(cappuchino.getTitle());
		cappuchinoAbout.setText(cappuchino.getDetails());

	}

}
