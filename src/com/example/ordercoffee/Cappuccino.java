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

public class Cappuccino extends Activity implements OnClickListener {

	Button aboutCappuchino;
	Button buyCappuchino;
	Button viewPrice;
	String edtname;
	int customerIdR;
	int cappuAmount;
	int cappuMultiplied;
	Intent sendToEmail, chooser = null;
	String sendto[] = { "inchiriseri@gmail.com", "coffeehaven@gmail.com" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cappuchino);

		aboutCappuchino = (Button) findViewById(R.id.btnAboutCappu);
		buyCappuchino = (Button) findViewById(R.id.btnBuyCappu);
		viewPrice = (Button) findViewById(R.id.btnViewPriceCappuccino);

		aboutCappuchino.setOnClickListener(this);
		buyCappuchino.setOnClickListener(this);
		viewPrice.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.btnAboutCappu:

			Intent intent = new Intent(Cappuccino.this, CappuccinoAbout.class);
			startActivity(intent);

			break;

		case R.id.btnBuyCappu:

			sendCappuccino();

			break;

		case R.id.btnViewPriceCappuccino:

			AlertDialog dialog = new AlertDialog.Builder(Cappuccino.this)
					.create();
			dialog.setIcon(R.drawable.alert);
			dialog.setTitle("Notice");
			dialog.setMessage("Drink:    Cappuccino" + "\n" + "\n"
					+ "Cost:    $1");
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

	private void sendCappuccino() {
		// TODO Auto-generated method stub

		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Enter details to send order");
		dialog.setIcon(R.drawable.alert);

		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		final EditText name = new EditText(this);
		name.setHint("Enter your name");
		

		final EditText customerId = new EditText(this);
		customerId.setHint("Enter your customer id");

		final EditText orderCup = new EditText(this);
		orderCup.setHint("Enter how many cappuccinos you want($1 per cup)");

		layout.addView(name);
		layout.addView(customerId);
		layout.addView(orderCup);

		dialog.setView(layout);

		dialog.setButton("View and send Order",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						try {
							edtname = name.getText().toString();
							customerIdR = Integer.parseInt(customerId.getText()
									.toString());
							cappuAmount = Integer.parseInt(orderCup.getText()
									.toString());

							cappuMultiplied = cappuAmount * 1;

							StringBuffer buffer = new StringBuffer();
							buffer.append("Name is" + " " + edtname + "\n\n");
							buffer.append("Id is" + " " + customerIdR + "\n\n");
							buffer.append("Total amount for cappuccino is"
									+ " " + cappuMultiplied + "dollars"+ " " + "\n\n");

							sendData("Thank you" + " " + edtname,
									buffer.toString());

						} catch (Exception e) {

							AlertDialog dia = new AlertDialog.Builder(
									Cappuccino.this).create();
							dia.setIcon(R.drawable.error);
							dia.setTitle("Error");
							dia.setMessage("please dont leave blank fields and"
									+ "\n\n" + "fill in the correct data");
							dia.setButton("DISMISS",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									});
							dia.show();
						}

					}

					private void sendData(String name, String details) {
						// TODO Auto-generated method stub

						AlertDialog dialogs = new AlertDialog.Builder(
								Cappuccino.this).create();
						dialogs.setIcon(R.drawable.alert);
						dialogs.setTitle(name);
						dialogs.setMessage(details);
						dialogs.setButton("Confirm and Send Order",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

										sendToEmail = new Intent(
												Intent.ACTION_SEND);
										sendToEmail.setData(Uri
												.parse("mailto:"));
										sendToEmail.putExtra(
												Intent.EXTRA_EMAIL, sendto);
										sendToEmail.putExtra(
												Intent.EXTRA_SUBJECT,
												"Order for Cappuccino");
										sendToEmail
												.putExtra(
														Intent.EXTRA_TEXT,
														"Order from"
																+ " "
																+ edtname
																+ "\n\n"

																+ "Id is"
																+ " "
																+ customerIdR
																+ "\n\n"

																+ "Total for cappuccino in dollars is"
																+ " "
																+ cappuMultiplied);
										sendToEmail.setType("message/rfc822");
										chooser = sendToEmail.createChooser(
												sendToEmail,
												"Send Order via Email");
										startActivity(chooser);
									}
								});
						dialogs.show();
					}
				});

		dialog.show();

	}

}
