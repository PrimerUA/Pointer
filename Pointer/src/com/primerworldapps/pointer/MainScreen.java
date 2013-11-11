package com.primerworldapps.pointer;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.primerworldapps.pointer.auth.AuthorizationScreen;

public class MainScreen extends SherlockActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);

		initScreen();
		
		//если не авторизирован, то
		startActivity(new Intent(MainScreen.this, AuthorizationScreen.class));
		
		//TODO: иначе открываем этот экран
	}

	private void initScreen() {
		
	}
}
