package com.example.coffeeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainList extends Activity implements OnItemClickListener {

	String[] classes = { "Cappuccino", "Espresso", "Macchiato", "Mochaccino",
			"PriceList", "OrderMultipleItems", "About", "StoreOrders" };
	/*
	 * String[] info = { "Order Cappuccino", "Order Espresso",
	 * "Order Macchiato", "View Price List", "Order Multiple Items",
	 * "About software", "Click to Store Orders" };
	 */
	ListView lvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlist);

		lvMain = (ListView) findViewById(R.id.listMain);

		// MyAdapter adapter = new MyAdapter(MainList.this, classes, info);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainList.this,
				android.R.layout.simple_list_item_1, classes);

		lvMain.setAdapter(adapter);

		lvMain.setAdapter(adapter);
		lvMain.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		String openClasses = classes[position];

		try {
			Class classy = Class
					.forName("com.example.coffeeapp." + openClasses);
			Intent startActivities = new Intent(MainList.this, classy);
			startActivity(startActivities);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public boolean onCreateOptionsMenu(Menu menu) { // TODO Auto-generated
	 * method stub
	 * 
	 * MenuInflater inflater = new MenuInflater(getApplicationContext());
	 * inflater.inflate(R.menu.main, menu);
	 * 
	 * return true; }
	 */

	/*
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId();
	 * 
	 * switch (item.getItemId()) {
	 * 
	 * case R.id.aboutUs:
	 * 
	 * AlertDialog dialog = new AlertDialog.Builder(
	 * getApplicationContext()).create(); dialog.setTitle("About us");
	 * dialog.setMessage("Located at 4 westshire road harare");
	 * dialog.setButton("OK", new DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 * TODO Auto-generated method stub
	 * 
	 * } });
	 * 
	 * dialog.show();
	 * 
	 * break;
	 * 
	 * } return true;
	 * 
	 * }
	 */

	public void onBackPressed() {

		AlertDialog dialog = new AlertDialog.Builder(MainList.this).create();
		dialog.setTitle("Exit");
		dialog.setIcon(R.drawable.alert);
		dialog.setMessage("are you sure you want to exit?");
		dialog.setButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});

		dialog.setButton2("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				finish();
			}
		});

		dialog.show();
	}

}
