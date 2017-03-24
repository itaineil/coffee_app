package com.example.coffeeapp;

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
import android.widget.Toast;

public class OrderMultipleItems extends Activity implements OnClickListener {

	EditText name, id, cappuccino, espresso, macciato, moccaccino;
	Button viewOrder, Order;
	int multipliedCappuccino, multipliedEspresso, multipliedMacciato,
			multipliedMoccaccino;
	Button callUs;
	int gotId;
	int sum;
	String gotName;
	String[] to = { "inchiriseri@gmail.com", "coffeehaven@gmail.com" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ordermultiple);

		name = (EditText) findViewById(R.id.edtName);
		id = (EditText) findViewById(R.id.edtId);
		cappuccino = (EditText) findViewById(R.id.edtMacciato);
		espresso = (EditText) findViewById(R.id.edtEspresso);
		macciato = (EditText) findViewById(R.id.edtMacciato);
		moccaccino = (EditText) findViewById(R.id.edtMoccaccino);
		viewOrder = (Button) findViewById(R.id.btnViewOrder);
		Order = (Button) findViewById(R.id.btnConfirmOrder);
		callUs = (Button) findViewById(R.id.btnCallAndOrderMultiple);
		callUs.setOnClickListener(this);
		viewOrder.setOnClickListener(this);
		Order.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		
		case R.id.btnCallAndOrderMultiple:
			
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:0771255849"));
			startActivity(callIntent);
		break;
		
		case R.id.btnViewOrder:

			// gettting values from edit texts
			try {
				gotName = name.getText().toString();
				gotId = Integer.parseInt(id.getText().toString());
				int gotcappuchino = Integer.parseInt(cappuccino.getText()
						.toString());
				int gotEspresso = Integer.parseInt(espresso.getText()
						.toString());
				int gotMacciato = Integer.parseInt(macciato.getText()
						.toString());
				int gotMoccaccino = Integer.parseInt(moccaccino.getText()
						.toString());

				// multiply the values

				multipliedCappuccino = gotcappuchino * 1;
				multipliedEspresso = gotEspresso * 2;
				multipliedMacciato = gotMacciato * 3;
				multipliedMoccaccino = gotMoccaccino * 4;

				// sum of all

				sum = multipliedCappuccino + multipliedEspresso
						+ multipliedMacciato + multipliedMoccaccino;

				// displaying it in alert dialog box

				StringBuffer buffer = new StringBuffer();
				buffer.append("Name" + " " + " " + gotName + "\n\n");
				buffer.append("Id" + " " + " " + gotId + "\n\n");
				buffer.append("Total for Cappuccino in dollars" + " " + " "
						+ multipliedCappuccino + "\n\n");
				buffer.append("Total for Espresso in dollars" + " " + " "
						+ multipliedEspresso + "\n\n");
				buffer.append("Total for Macciato in dollars" + " " + " "
						+ multipliedMacciato + "\n\n");
				buffer.append("Total for Moccaccino in dollars" + " " + " "
						+ multipliedMoccaccino + "\n\n");
				buffer.append("Total for all items to purchase in dollars"
						+ " " + " " + sum + "\n\n");

				sendData("Thank you customer" + " " + gotName,
						buffer.toString());

			} catch (Exception e) {

				AlertDialog dig = new AlertDialog.Builder(
						OrderMultipleItems.this).create();
				dig.setIcon(R.drawable.error);
				dig.setTitle("Error");
				dig.setMessage("This page is only if you want to buy everything on the list."
						+ "\n\n" + "Please dont leave any empty fields");
				dig.setButton("DISMISS", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
				dig.show();
			}

			break;

		case R.id.btnConfirmOrder:

			try {
				Intent sendResult, chooser = null;

				sendResult = new Intent(Intent.ACTION_SEND);
				sendResult.setData(Uri.parse("mailto:"));
				sendResult.putExtra(Intent.EXTRA_EMAIL, to);
				sendResult.putExtra(Intent.EXTRA_SUBJECT, "Order from" + " "
						+ gotName);
				sendResult.putExtra(Intent.EXTRA_TEXT, "The order is" + "\n\n"
						+ "Cappuccino" + " " + multipliedCappuccino + " "
						+ "\n\n" + "Espresso" + " " + multipliedEspresso
						+ "\n\n" + "Macciatto" + " " + multipliedMacciato
						+ "\n\n" + "Moccaccino" + multipliedMoccaccino + "\n\n"
						+ "Grand total is" + " " + sum);

				sendResult.setType("message/rfc822");
				chooser = sendResult.createChooser(sendResult,
						"Send Coffee order via");
				startActivity(chooser);

			} catch (Exception e) {

				Toast.makeText(OrderMultipleItems.this, "Cannot be sent now",
						Toast.LENGTH_LONG).show();

			}

			break;
		}

	}

	private void sendData(String title, String buffer) {
		// TODO Auto-generated method stub

		AlertDialog dialog = new AlertDialog.Builder(OrderMultipleItems.this)
				.create();
		dialog.setTitle(title);
		dialog.setIcon(R.drawable.alert);
		dialog.setMessage(buffer);
		dialog.setButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});

		dialog.show();
	}

}
