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
import android.widget.Toast;
import com.facebook.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.plus.PlusClient;
import com.primerworldapps.pointer.R;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 24.11.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends BaseActivity implements Session.StatusCallback,
                                                           GooglePlayServicesClient.ConnectionCallbacks,
                                                           GooglePlayServicesClient.OnConnectionFailedListener{
    private static final float DEFAULT_ZOOM_LEVEL = 13.5f;
    private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

    private PlusClient plusClient;
    private UiLifecycleHelper uiHelper;

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.login_activity);

        //For Google+
        plusClient = new PlusClient.Builder(this, this, this).build();
        findViewById(R.id.google_plus_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGooglePlus();
            }
        });

        //For facebook
        ((LoginButton)findViewById(R.id.facebook_login)).setReadPermissions(Arrays.asList("email"));
        uiHelper = new UiLifecycleHelper(this, this);
        uiHelper.onCreate(savedState);

        //init map or show error
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            initBackgroundMap();
        } else {
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0).show();
        }
    }

    private void loginGooglePlus() {
        if (!plusClient.isConnected()) {
            plusClient.connect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        uiHelper.onPause();
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
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESOLVE_ERR && resultCode == RESULT_OK) {
            plusClient.connect();
        }
        uiHelper.onActivityResult(requestCode, resultCode, data);
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
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisconnected() {
        //TODO
        Log.d("KVEST_TAG", "onDisconnected");
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

    @Override
    public void call(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Request me = Request.newMeRequest(session, new Request.GraphUserCallback() {

                // callback after Graph API response with user object
                @Override
                public void onCompleted(GraphUser user, Response response) {
                    if (user != null) {
                        String text = "From Facebook+:\nName: " + user.getName() + "\n";
                        text += "First Name: " + user.getFirstName() + "\n";
                        text += "FLast Name: " + user.getLastName() + "\n";
                        text += "gender:" + user.asMap().get("gender").toString() + "\n";
                        text += "email: " + user.asMap().get("email").toString() + "\n";
                        Log.i("KVEST_TAG", "Hello " + user.getName() + "!");
                        Toast.makeText(LoginActivity.this, text, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "user is \"null\"", Toast.LENGTH_LONG).show();
                    }
                }
            });
            me.executeAsync();
        } else if (state.isClosed()) {
            //TODO
            Log.d("KVEST_TAG", "state is \"closed\"");
        }
    }
}
