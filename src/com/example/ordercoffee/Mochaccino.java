package com.example.ordercoffee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Mochaccino extends Activity implements OnClickListener {

	Button aboutMocha, buyMoccha, viewMochaPrice;
	String customerName;
	String[] sendTo = { "inchiriseri@gmail.com", "coffeehaven@gmail.com" };
	int customerIds, MoccaccinoAmount, MoccaccinoMultiplied;
	Intent sendMoccaccino, chooser = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mocchacino);

		aboutMocha = (Button) findViewById(R.id.btnAboutMocha);
		buyMoccha = (Button) findViewById(R.id.btnBuyMocha);
		viewMochaPrice = (Button) findViewById(R.id.btnViewMochaPrice);

		aboutMocha.setOnClickListener(this);
		buyMoccha.setOnClickListener(this);
		viewMochaPrice.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnAboutMocha:

			Intent intent = new Intent(Mochaccino.this, AboutMocha.class);
			startActivity(intent);

			break;

		case R.id.btnBuyMocha:

			buyMoccaccino();

			break;

		case R.id.btnViewMochaPrice:

			AlertDialog dialog = new AlertDialog.Builder(Mochaccino.this).create();
			dialog.setIcon(R.drawable.alert);
			dialog.setTitle("Notice");
			dialog.setMessage("Drink:    Mochaccino" + "\n" + "\n"
					+ "Cost:    $4");
			dialog.setButton("DISMISS", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});

			dialog.show();

			break;
		}
	}

	private void buyMoccaccino() {
		// TODO Auto-generated method stub
		AlertDialog maindialog = new AlertDialog.Builder(Mochaccino.this)
				.create();
		maindialog.setIcon(R.drawable.alert);
		maindialog.setTitle("Enter details to send order");
		

		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		final EditText name = new EditText(this);
		name.setHint("Enter your name");

		final EditText customerId = new EditText(this);
		customerId.setHint("Enter your customer Id");

		final EditText orderCup = new EditText(this);
		orderCup.setHint("Enter how many Moccaccinos you want($4)");

		layout.addView(name);
		layout.addView(customerId);
		layout.addView(orderCup);

		maindialog.setView(layout);

		maindialog.setButton("View and Send Order",
				new DialogInterface.OnClickListener() {

			
				
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						try{
						
						customerName = name.getText().toString();
						customerIds = Integer.parseInt(customerId.getText().toString());
						MoccaccinoAmount = Integer.parseInt(orderCup.getText().toString());
						
						
						MoccaccinoMultiplied = MoccaccinoAmount*4;
						
						StringBuffer buffer = new StringBuffer();
						buffer.append("Name is" + " " + customerName+ "\n\n");
						buffer.append("Id id" + " " + customerIds + "\n\n");
						buffer.append("Total amount for Moccaccino is"+ " " + MoccaccinoMultiplied + " " + "dollars" + "\n\n");
						
						sendData("Thank you" + " " + customerName ,buffer.toString());
						}catch(Exception e){
							
							AlertDialog errorDialog = new AlertDialog.Builder(Mochaccino.this).create();
							errorDialog.setIcon(R.drawable.error);
							errorDialog.setTitle("Error");
							errorDialog.setMessage("please dont leave blank fields and"
									+ "\n\n" + "fill in the correct data");
							errorDialog.setButton("DISMISS",new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
								}
							});
							
							errorDialog.show();
						}
						
					}
				});

		maindialog.show();

	}

	protected void sendData(String name, String details) {
		// TODO Auto-generated method stub
		
		AlertDialog finalDialog = new AlertDialog.Builder(Mochaccino.this).create();
		finalDialog.setTitle(name);
		finalDialog.setIcon(R.drawable.alert);
		finalDialog.setMessage(details);
		finalDialog.setButton("Confirm and Send", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				
				sendMoccaccino = new Intent(Intent.ACTION_SEND);
				sendMoccaccino.setData(Uri.parse("mailto:"));
				sendMoccaccino.putExtra(Intent.EXTRA_EMAIL, sendTo);
				sendMoccaccino.putExtra(Intent.EXTRA_SUBJECT, "Order for Moccaccino");
				sendMoccaccino.putExtra(Intent.EXTRA_TEXT, "Order from" + " "
				+ customerName + " " + "\n\n"  + "Id is" + " " + customerIds + "\n\n"+
						"Total for Moccaccino in dollars is" + " " + 	MoccaccinoMultiplied );
						
				sendMoccaccino.setType("message/frc822");
				chooser = sendMoccaccino.createChooser(sendMoccaccino, "Send Order via Email");
				startActivity(chooser);
			}
		});
		
		finalDialog.show();
	}

}
