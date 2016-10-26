package com.example.ordercoffee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		Thread thread = new Thread() {

			public void run() {

				try {

					sleep(4000);

				} catch (Exception e) {

					e.printStackTrace();

				} finally {

					Intent intent = new Intent(SplashScreen.this,MainList.class);
					startActivity(intent);
					finish();

				}

			}

		};
		thread.start();

	}

}
