package com.primerworldapps.pointer.auth;

import android.os.Bundle;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;
import com.primerworldapps.pointer.R;

public class RegistrationInfoScreen extends SherlockActivity {
	
	private EditText nameEdit;
	private EditText surenameEdit;
	private EditText emailEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_info_screen);
		
		initScreen();
	}

	private void initScreen() {
		getSupportActionBar().setTitle(getString(R.string.reg_info_screen_thanks));
		
		nameEdit = (EditText) findViewById(R.id.reg_info_name);
		surenameEdit = (EditText) findViewById(R.id.reg_info_surename);
		emailEdit = (EditText) findViewById(R.id.reg_info_email);
		
		nameEdit.setEnabled(false);
		surenameEdit.setEnabled(false);
		emailEdit.setEnabled(false);
	}
}
