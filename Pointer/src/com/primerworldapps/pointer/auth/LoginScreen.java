package com.primerworldapps.pointer.auth;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.plus.PlusClient;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.util.Utils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;

public class LoginScreen extends SherlockActivity implements ConnectionCallbacks, OnConnectionFailedListener {

	private Button googleButton;
	private Button facebookButton;
	private Button registerButton;

	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

	private ProgressDialog mConnectionProgressDialog;
	private PlusClient mPlusClient;
	private ConnectionResult mConnectionResult;

	private static final String TAG = "LoginScreen";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);

		initScreen();
		initGoogle();
	}

	private void initGoogle() {
		mPlusClient = new PlusClient.Builder(this, this, this).setActions("http://schemas.google.com/AddActivity", "http://schemas.google.com/BuyActivity").build();
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
				mConnectionProgressDialog = new ProgressDialog(v.getContext());
				mConnectionProgressDialog.setMessage("Signing in...");
				if (!mPlusClient.isConnected()) {
					if (mConnectionResult == null) {
						mConnectionProgressDialog.show();
					} else {
						try {
							mConnectionResult.startResolutionForResult(LoginScreen.this, REQUEST_CODE_RESOLVE_ERR);
						} catch (SendIntentException e) {
							mConnectionResult = null;
							mPlusClient.connect();
						}
					}
				} else {
					mPlusClient.clearDefaultAccount();
					mPlusClient.disconnect();
					mPlusClient.connect();
				}
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
	protected void onStart() {
		super.onStart();
		mPlusClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mPlusClient.disconnect();
	}

	@Override
	public void onConnected(Bundle arg0) {
		mConnectionProgressDialog.dismiss();
		String accountName = mPlusClient.getAccountName();
		Toast.makeText(this, accountName + " is connected.", Toast.LENGTH_SHORT).show();

		Intent registrationInfoScreenIntent = new Intent(LoginScreen.this, RegistrationInfoScreen.class);
		registrationInfoScreenIntent.putExtra("account-name", accountName);
		startActivity(registrationInfoScreenIntent);
	}

	@Override
	public void onDisconnected() {
		Toast.makeText(this, "User disconnected.", Toast.LENGTH_SHORT).show();
		Log.d(TAG, "disconnected");
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
		if (requestCode == REQUEST_CODE_RESOLVE_ERR && responseCode == RESULT_OK) {
			mConnectionResult = null;
			mPlusClient.connect();
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (mConnectionProgressDialog.isShowing()) {
			if (result.hasResolution()) {
				try {
					result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
				} catch (SendIntentException e) {
					mPlusClient.connect();
				}
			}
		}

		mConnectionResult = result;

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
					Utils.visitUrl(LoginScreen.this, getString(R.string.pointer_website_url));
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
