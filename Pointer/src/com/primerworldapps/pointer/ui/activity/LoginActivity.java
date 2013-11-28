package com.primerworldapps.pointer.ui.activity;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.primerworldapps.pointer.R;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 24.11.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends BaseActivity {
    private static final float DEFAULT_ZOOM_LEVEL = 13.5f;
    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.login_activity);

        initBackgroundMap();
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
