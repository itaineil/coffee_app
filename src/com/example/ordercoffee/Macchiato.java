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

public class Macchiato extends Activity implements OnClickListener {

	Button aboutMark, buyMark, viewMacchiatoPrice;
	String customerName;
	String [] sendTo = { "inchiriseri@gmail.com", "coffeehaven@gmail.com" };
	int customerIds, MacciatoAmount, MacciatoMultiplied;
	Intent sendMacciato, chooser = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.markiato);

		aboutMark = (Button) findViewById(R.id.btnAboutMark);
		buyMark = (Button) findViewById(R.id.btnBuyMark);
		viewMacchiatoPrice = (Button) findViewById(R.id.btnViewMarkiatoPrice);

		aboutMark.setOnClickListener(this);
		buyMark.setOnClickListener(this);
		viewMacchiatoPrice.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.btnAboutMark:

			Intent intent = new Intent(Macchiato.this, AboutMacchiato.class);
			startActivity(intent);

			break;

		case R.id.btnBuyMark:

			buyMacchiato();

			break;

		case R.id.btnViewMarkiatoPrice:

			AlertDialog dialog = new AlertDialog.Builder(Macchiato.this)
					.create();
			dialog.setIcon(R.drawable.alert);
			dialog.setTitle("Notice");
			dialog.setMessage("Drink:    Macchiato" + "\n" + "\n"
					+ "Cost:    $3");
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

	private void buyMacchiato() {
		// TODO Auto-generated method stub
		AlertDialog maindialog = new AlertDialog.Builder(Macchiato.this).create();
		maindialog.setTitle("Enter details to order");
		maindialog.setIcon(R.drawable.alert);
	
		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		final EditText name = new EditText(this);
		name.setHint("Enter your name");
		
		final EditText customerId = new EditText(this);
		customerId.setHint("Enter your customer id");
		
		final EditText orderCup = new EditText(this);
		orderCup.setHint("Enter how many Macciatos you want($3 a cup)");
		
		layout.addView(name);
		layout.addView(customerId);
		layout.addView(orderCup);
		
		maindialog.setView(layout);
		
		
		maindialog.setButton("View and Send Order", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				try{
					
					customerName = name.getText().toString();
					customerIds = Integer.parseInt(customerId.getText().toString());
					MacciatoAmount = Integer.parseInt(orderCup.getText().toString());
					
					MacciatoMultiplied = MacciatoAmount*3;
					
					StringBuffer buffer = new StringBuffer();
					buffer.append("Name is" + " " + customerName + "\n\n");
					buffer.append("Id is" + " " + customerIds + "\n\n");
					buffer.append("Total amount for Macciato is" + " " + MacciatoMultiplied + "dollars" + " " +  "\n\n");
					
					sendData("Thank you" + " " + customerName,buffer.toString());
					
					
				}catch(Exception e){
				
					AlertDialog errorDialog = new AlertDialog.Builder(Macchiato.this).create();
					errorDialog.setIcon(R.drawable.error);
					errorDialog.setTitle("Error");
					errorDialog.setMessage("please dont leave blank fields and"
							+ "\n\n" + "fill in the correct data");
					errorDialog.setButton("DISMISS", new DialogInterface.OnClickListener() {
						
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
		
		AlertDialog finalDialog = new AlertDialog.Builder(Macchiato.this).create();
		finalDialog.setTitle(name);
		finalDialog.setIcon(R.drawable.alert);
		finalDialog.setMessage(details);
		finalDialog.setButton("Confirm and Send", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				sendMacciato = new Intent(Intent.ACTION_SEND);
				sendMacciato.setData(Uri.parse("mailto:"));
				sendMacciato.putExtra(Intent.EXTRA_EMAIL,sendTo);
				sendMacciato.putExtra(Intent.EXTRA_SUBJECT, "Order for Macciato");
				sendMacciato.putExtra(Intent.EXTRA_TEXT, "Order from" + " " + customerName+ "\n\n" +
										"Id is" + " " + customerIds + "\n\n"+
										"Total for Macciato is" + " " + MacciatoMultiplied);
				sendMacciato.setType("message/rfc822");
				chooser = sendMacciato.createChooser(sendMacciato,"Send order via Email");
				startActivity(chooser);
				
			}
		});
		
		finalDialog.show();
	}
}