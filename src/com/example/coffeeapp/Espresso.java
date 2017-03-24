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

public class Espresso extends Activity implements OnClickListener {
	//
	Button aboutEspresso, buyEspresso, viewEspressoPrice;
	String customerName;
	String[] sendTo = { "inchiriseri@gmail.com", "coffeehaven@gmail.com" };
	int customerIds, espressoAmount, espressoMultiplied;
	Intent sendEspresso, chooser = null;
	Button callUs;
	Button sendViaSms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.espressos);

		aboutEspresso = (Button) findViewById(R.id.btnAboutEspresso);
		buyEspresso = (Button) findViewById(R.id.btnBuyEspresso);
		viewEspressoPrice = (Button) findViewById(R.id.btnViewEspressoPrice);
		callUs = (Button) findViewById(R.id.btnCallAndOrder);
		sendViaSms = (Button) findViewById(R.id.btnSendViaSms);
		aboutEspresso.setOnClickListener(this);
		buyEspresso.setOnClickListener(this);
		viewEspressoPrice.setOnClickListener(this);
		callUs.setOnClickListener(this);
		sendViaSms.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

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

		case R.id.btnCallAndOrder:

			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:0771255849"));
			startActivity(callIntent);

			break;

		case R.id.btnAboutEspresso:

			Intent intent = new Intent(Espresso.this, EspressoAbout.class);
			startActivity(intent);

			break;

		case R.id.btnBuyEspresso:

			espressoBuy();

			break;

		case R.id.btnViewEspressoPrice:

			AlertDialog dialog = new AlertDialog.Builder(Espresso.this)
					.create();
			dialog.setTitle("Notice");
			dialog.setIcon(R.drawable.alert);
			dialog.setMessage("Drink:    Espresso" + "\n" + "\n"
					+ "Cost:    $2");
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

	private void espressoBuy() {
		// TODO Auto-generated method stub

		AlertDialog mainDialog = new AlertDialog.Builder(Espresso.this)
				.create();
		mainDialog.setTitle("Enter details to order");
		mainDialog.setIcon(R.drawable.alert);

		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		final EditText name = new EditText(this);
		name.setHint("Enter your name");

		final EditText customerId = new EditText(this);
		customerId.setHint("Enter your customer id");

		final EditText orderCup = new EditText(this);
		orderCup.setHint("Enter how many espressos you want ($2 per cup)");

		layout.addView(name);
		layout.addView(customerId);
		layout.addView(orderCup);

		mainDialog.setView(layout);

		mainDialog.setButton("View and Send Order",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						try {

							customerName = name.getText().toString();
							customerIds = Integer.parseInt(customerId.getText()
									.toString());
							espressoAmount = Integer.parseInt(orderCup
									.getText().toString());

							espressoMultiplied = espressoAmount * 2;

							StringBuffer buffer = new StringBuffer();
							buffer.append("Name is" + " " + customerName
									+ "\n\n");
							buffer.append("Id is" + " " + customerIds + "\n\n");
							buffer.append("Total amount of Espresso is" + " "
									+ espressoMultiplied + " " + "dollars"
									+ "\n\n");

							sendData("Thank you" + " " + customerName,
									buffer.toString());

						} catch (Exception e) {

							AlertDialog errorDialog = new AlertDialog.Builder(
									Espresso.this).create();
							errorDialog.setIcon(R.drawable.error);
							errorDialog.setTitle("Error");
							errorDialog
									.setMessage("Please dont leave blank fields and"
											+ "\n\n"
											+ "fill in the correct data");
							errorDialog.setButton("DISMISS",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									});

							errorDialog.show();
						}

					}
				});
		mainDialog.show();

	}

	protected void sendData(String name, String details) {
		// TODO Auto-generated method stub
		AlertDialog dataDialog = new AlertDialog.Builder(Espresso.this)
				.create();
		dataDialog.setIcon(R.drawable.alert);
		dataDialog.setTitle(name);
		dataDialog.setMessage(details);
		dataDialog.setButton("Confirm and Send",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						sendEspresso = new Intent(Intent.ACTION_SEND);
						sendEspresso.setData(Uri.parse("mailto:"));
						sendEspresso.putExtra(Intent.EXTRA_EMAIL, sendTo);
						sendEspresso.putExtra(Intent.EXTRA_SUBJECT,
								"Order for Espresso");
						sendEspresso.putExtra(Intent.EXTRA_TEXT, "Order from"
								+ " " + customerName + "\n\n" + "Id is" + " "
								+ customerIds + "\n\n"
								+ "Total for Espresso in dollars is" + " "
								+ espressoMultiplied

						);
						sendEspresso.setType("message/rfc822");
						chooser = sendEspresso.createChooser(sendEspresso,
								"Send order via email");
						startActivity(chooser);
					}
				});

		dataDialog.show();
	}

}
