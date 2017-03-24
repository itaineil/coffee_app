package com.example.coffeeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Cappuccino extends Activity implements OnClickListener {

	Button aboutCappuchino;
	Button buyCappuchino;
	Button viewPrice;
	Button callUs;
	String edtname;
	int customerIdR;
	int cappuAmount;
	int cappuMultiplied;
	Intent sendToEmail, chooser = null;
	Button sendViaSms;
	String sendto[] = { "inchiriseri@gmail.com", "coffeehaven@gmail.com" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cappuchino);

		aboutCappuchino = (Button) findViewById(R.id.btnAboutCappu);
		buyCappuchino = (Button) findViewById(R.id.btnBuyCappu);
		viewPrice = (Button) findViewById(R.id.btnViewPriceCappuccino);
		callUs = (Button) findViewById(R.id.btnCallAndOrder);
		sendViaSms = (Button) findViewById(R.id.btnSendViaSms);

		callUs.setOnClickListener(this);

		aboutCappuchino.setOnClickListener(this);
		buyCappuchino.setOnClickListener(this);
		viewPrice.setOnClickListener(this);
		sendViaSms.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.btnCallAndOrder:

			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:0771255849"));
			startActivity(callIntent);

			break;

		case R.id.btnAboutCappu:

			Intent intent = new Intent(Cappuccino.this, CappuccinoAbout.class);
			startActivity(intent);

			break;

		case R.id.btnSendViaSms:

			AlertDialog dialogSms = new AlertDialog.Builder(this).create();
			dialogSms.setTitle("Enter details to send order");
			dialogSms.setIcon(R.drawable.alert);

			final LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);

			final EditText phoneNumber = new EditText(this);
			phoneNumber.setHint("Enter Our Number (0771 255 849)");

			final EditText order = new EditText(this);
			order.setHint("Enter your order in full");

			layout.addView(phoneNumber);
			layout.addView(order);

			dialogSms.setView(layout);

			dialogSms.setButton("Send SMS",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

							String gotNumber = phoneNumber.getText().toString();
							String message = order.getText().toString();

							if (!gotNumber.equals("0771255849")
									&& message.length() > 0) {

								sendMySms(gotNumber, message);
							} else {

								Toast.makeText(
										getBaseContext(),
										"Please enter your phone number and message",
										Toast.LENGTH_LONG).show();
							}

						}

						private void sendMySms(String gotNumber, String message) {
							// TODO Auto-generated method stub

							Intent intent = new Intent(getApplicationContext(),
									Cappuccino.class);
							PendingIntent pi = PendingIntent.getActivity(
									getApplicationContext(), 0, intent, 0);
							SmsManager sms = SmsManager.getDefault();
							sms.sendTextMessage(gotNumber, null, message, pi,
									null);

							Toast.makeText(getApplicationContext(),
									"Message Sent", Toast.LENGTH_LONG).show();

						}

					});

			dialogSms.show();

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

		AlertDialog dialog = new AlertDialog.Builder(this,
				AlertDialog.THEME_HOLO_LIGHT).create();
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
									+ " " + cappuMultiplied + "dollars" + " "
									+ "\n\n");

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
						dialogs.setInverseBackgroundForced(true);

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
