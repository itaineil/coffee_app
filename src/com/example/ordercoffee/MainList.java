package com.example.ordercoffee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainList extends Activity implements OnItemClickListener {

	String[] classes = { "Cappuccino", "Espresso", "Macchiato", "Mochaccino","PriceList","OrderMultipleItems", "About","StoreOrders"};
	ListView lvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlist);

		lvMain = (ListView) findViewById(R.id.listMain);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainList.this,android.R.layout.simple_list_item_1,classes);
		lvMain.setAdapter(adapter);

		lvMain.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		String openClasses = classes[position];

		try {
			Class classy = Class.forName("com.example.ordercoffee."
					+ openClasses);
			Intent startActivities = new Intent(MainList.this, classy);
			startActivity(startActivities);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
