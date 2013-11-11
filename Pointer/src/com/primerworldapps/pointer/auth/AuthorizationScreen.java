package com.primerworldapps.pointer.auth;

import com.actionbarsherlock.app.SherlockActivity;
import com.primerworldapps.pointer.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class AuthorizationScreen extends SherlockActivity {
	
	private Button pointButton;
	private Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authorization_screen);
		
		initScreen();
	}

	private void initScreen() {
		
		getSupportActionBar().setTitle(getString(R.string.auth_screen_welcome));
		
		pointButton = (Button) findViewById(R.id.auth_screen_point);
		registerButton = (Button) findViewById(R.id.auth_screen_reg);
		
		pointButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AuthorizationScreen.this, LoginScreen.class));
			}
		});
		
		registerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AuthorizationScreen.this, RegisterScreen.class));
			}
		});
	}

}
