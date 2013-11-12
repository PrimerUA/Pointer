package com.primerworldapps.pointer.auth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.util.Utils;

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
					Utils.visitUrl(AuthorizationScreen.this, getString(R.string.pointer_website_url));
				}
			});
			AboutDialog.show();
		}
		}

		return super.onOptionsItemSelected(item);
	}

}
