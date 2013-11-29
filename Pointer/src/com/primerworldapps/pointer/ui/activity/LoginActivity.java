package com.primerworldapps.pointer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.model.people.Person;
import com.primerworldapps.pointer.R;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 24.11.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends BaseActivity implements GooglePlayServicesClient.ConnectionCallbacks,
                                                           GooglePlayServicesClient.OnConnectionFailedListener{
    private static final float DEFAULT_ZOOM_LEVEL = 13.5f;
    private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

    private PlusClient plusClient;

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.login_activity);

        plusClient = new PlusClient.Builder(this, this, this)
                                   .setVisibleActivities("http://schemas.google.com/AddActivity", "http://schemas.google.com/BuyActivity")
                                   .build();
        findViewById(R.id.google_plus_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGooglePlus();
            }
        });

        initBackgroundMap();
    }

    private void loginGooglePlus() {
        if (!plusClient.isConnected()) {
            plusClient.connect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {
            plusClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        plusClient.disconnect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESOLVE_ERR && resultCode == RESULT_OK) {
            plusClient.connect();
        }
        //TODO
        //uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
            } catch (IntentSender.SendIntentException e) {
                plusClient.connect();
            }
        } else {
            //TODO
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        //TODO
        //plusClient.getCurrentPerson()
        String text = "From Google+:\nName: " + plusClient.getCurrentPerson().getName().getFormatted() + "\n";
        text += "First Name: " + plusClient.getCurrentPerson().getName().getGivenName() + "\n";
        text += "FLast Name: " + plusClient.getCurrentPerson().getName().getFamilyName() + "\n";
        text += "gender:" + plusClient.getCurrentPerson().getGender() + "\n";
        text += "email: " + plusClient.getAccountName() + "\n";
        Log.d("KVEST_TAG", text);
    }

    @Override
    public void onDisconnected() {
        Log.d("KVEST_TAG", "onDisconnected");
        //TODO
    }

    private void initBackgroundMap() {
        //get last known position
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        String provider = lm.getBestProvider(criteria, true);
        Location location = provider != null ? lm.getLastKnownLocation(provider) : null;

        //go to last known location
        if (location != null) {
            GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.background_map)).getMap();
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), DEFAULT_ZOOM_LEVEL));
        }
    }
}
