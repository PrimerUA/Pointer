package com.primerworldapps.pointer.auth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.util.Utils;

public class RegistrationInfoScreen extends SherlockActivity {

	private EditText nameEdit;
	private EditText surenameEdit;
	private EditText emailEdit;

	private Button websiteButton;

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

		websiteButton = (Button) findViewById(R.id.reg_info_website_button);

		websiteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Utils.visitUrl(RegistrationInfoScreen.this, getString(R.string.pointer_website_url));
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
					Utils.visitUrl(RegistrationInfoScreen.this, getString(R.string.pointer_website_url));
				}
			});
			AboutDialog.show();
		}
		}

		return super.onOptionsItemSelected(item);
	}
}
