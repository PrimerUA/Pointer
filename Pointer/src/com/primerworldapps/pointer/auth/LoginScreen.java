package com.primerworldapps.pointer.auth;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.pointer.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class LoginScreen extends SherlockActivity {

	private Button googleButton;
	private Button facebookButton;
	private Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);

		initScreen();
	}

	private void initScreen() {
		
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.login_screen_title));
		
		googleButton = (Button) findViewById(R.id.login_screen_google);
		facebookButton = (Button) findViewById(R.id.login_screen_facebook);
		registerButton = (Button) findViewById(R.id.login_screen_reg);

		googleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		facebookButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default: {
			this.finish();
		}
		}

		return super.onOptionsItemSelected(item);
	}

}
