package com.primerworldapps.pointer.auth;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.util.Utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.auth_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_legalnotices: {
			// String LicenseInfo =
			// GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(getApplicationContext());
			// AlertDialog.Builder LicenseDialog = new
			// AlertDialog.Builder(this);
			// LicenseDialog.setTitle(getString(R.string.menu_legalnotices));
			// LicenseDialog.setMessage(LicenseInfo);
			// LicenseDialog.show();
		}
		case R.id.menu_auth_about: {
			AlertDialog.Builder AboutDialog = new AlertDialog.Builder(this);
			AboutDialog.setTitle(getString(R.string.app_about_title));
			AboutDialog.setMessage(getString(R.string.app_about_project));
			AboutDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			AboutDialog.setNeutralButton(R.string.app_about_website, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Utils.visitUrl(RegisterScreen.this, getString(R.string.pointer_website_url));
				}
			});
			AboutDialog.show();
		}
		case R.id.homeAsUp: {
			finish();
		}
		}

		return super.onOptionsItemSelected(item);
	}

}
