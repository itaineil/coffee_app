package com.example.ordercoffee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StoreOrders extends Activity {

	EditText customerId, name, coffeeType;
	Button addProduct, viewAllProduct, viewSpecificOrder;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storeorders);

		customerId = (EditText) findViewById(R.id.edtCustomerId);
		name = (EditText) findViewById(R.id.edtName);
		coffeeType = (EditText) findViewById(R.id.edtFavouriteCoffee);
		addProduct = (Button) findViewById(R.id.btnAddProduct);
		viewAllProduct = (Button) findViewById(R.id.btnViewAll);
		viewSpecificOrder = (Button) findViewById(R.id.btnViewSpecificOrder);

		db = openOrCreateDatabase("CoffeeDb", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS foods(id VARCHAR,name VARCHAR,coffee VARCHAR);");

		addProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (customerId.getText().toString().trim().length() == 0
						|| name.getText().toString().trim().length() == 0
						|| coffeeType.getText().toString().trim().length() == 0) {

					showMessage("Error", "Please enter all values");
					return;
				}

				// Inserting record
				db.execSQL("INSERT INTO foods VALUES('" + customerId.getText()
						+ "','" + name.getText() + "','" + coffeeType.getText()
						+ "');");
				showMessage("Success", "Record added");
				clearAll();

			}
		});

		viewAllProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Cursor c = db.rawQuery("SELECT * FROM foods", null);
				// Checking if no records found
				if (c.getCount() == 0) {
					showMessage("Error", "No records found");
					return;
				}
				// Appending records to a string buffer
				StringBuffer buffer = new StringBuffer();
				while (c.moveToNext()) {
					buffer.append("id: " + c.getString(0) + "\n");
					buffer.append("name: " + c.getString(1) + "\n");
					buffer.append("coffee: " + c.getString(2) + "\n\n");
				}
				// Displaying all records
				showMessage("Customer Details", buffer.toString());
			}

		});

		viewSpecificOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				 if(customerId.getText().toString().trim().length()==0)
			        {
			            showMessage("Error", "Please enter Customer Number");
			            return;
			        }
			    // Searching roll number
			        Cursor c=db.rawQuery("SELECT * FROM foods WHERE id='"+customerId.getText()+"'", null);
			        if(c.moveToFirst())
			        {
			        // Displaying record if found
			            name.setText(c.getString(1));
			            coffeeType.setText(c.getString(2));
			        }
			        else
			        {
			            showMessage("Error", "Invalid Customer Number");
			            clearAll();
			        }
			}
		});
	}

	private void clearAll() {
		// TODO Auto-generated method stub
		customerId.setText("");
		name.setText("");
		coffeeType.setText("");
	}

	private void showMessage(String name, String details) {
		// TODO Auto-generated method stub
		AlertDialog dialog = new AlertDialog.Builder(StoreOrders.this).create();
		dialog.setIcon(R.drawable.alert);
		dialog.setTitle(name);
		dialog.setMessage(details);

		dialog.setButton("DISMISS", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		dialog.show();
	}
}