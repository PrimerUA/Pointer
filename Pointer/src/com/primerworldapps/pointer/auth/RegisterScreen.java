package com.primerworldapps.pointer.auth;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.pointer.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends SherlockActivity {
	
	private EditText inviteEdit;
	private Button checkButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_screen);
		
		initScreen();
	}

	private void initScreen() {
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.reg_screen_step_1));
		
		inviteEdit = (EditText) findViewById(R.id.reg_screen_invite);
		checkButton = (Button) findViewById(R.id.reg_screen_check);
		
		checkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), getString(R.string.reg_screen_incorrect_toast), Toast.LENGTH_SHORT).show();
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
