package com.primerworldapps.pointer.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.primerworldapps.pointer.contentprovider.PointerProviderMetadata;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 17.12.13
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class ProposalsMapFragment extends SupportMapFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final long UNKNOWN_PROPOSAL_ID = -1;
    private static final int LOAD_PROPOSALS_ID = 0;
    private static final float DEFAULT_ZOOM_LEVEL = 13.5f;

    private Map<String, Long> markerIds = new HashMap<String, Long>();
    private OnProposalSelect onProposalSelect;
    private boolean mapAvailable;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mapAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS;
        if (mapAvailable) {
            initMap();
        }

        //load cursor with points
        getActivity().getSupportLoaderManager().initLoader(LOAD_PROPOSALS_ID, null, this);
    }

    private void initMap() {
        //get last known position
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        String provider = lm.getBestProvider(criteria, true);
        Location location = provider != null ? lm.getLastKnownLocation(provider) : null;

        GoogleMap map = getMap();

        //go to last known location
        if (location != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), DEFAULT_ZOOM_LEVEL));
        }

        //set up map
        map.setIndoorEnabled(true);
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                long proposalId = getSelectedProposalId(marker.getId());
                if (proposalId != UNKNOWN_PROPOSAL_ID && onProposalSelect != null) {
                    onProposalSelect.onProposalSelected(proposalId);
                }

            }
        });
    }

    private long getSelectedProposalId(String markerId) {
        if (markerIds.containsKey(markerId)) {
            return markerIds.get(markerId);
        } else {
            return UNKNOWN_PROPOSAL_ID;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onProposalSelect = (OnProposalSelect) activity;
        } catch (ClassCastException cce) {}
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case LOAD_PROPOSALS_ID : return new CursorLoader(getActivity(), PointerProviderMetadata.PROPOSAL_ITEMS_URI,
                                                             ProposalTable.FULL_PROJECTION, null, null, null);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        updateProposals(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        GoogleMap map = getMap();
        if (map != null) {
            map.clear();
        }
    }

    private void updateProposals(Cursor cursor) {
        if (!mapAvailable) {
            return;
        }

        GoogleMap map = getMap();

        //clear old data
        map.clear();
        markerIds.clear();

        int idIndex = cursor.getColumnIndex(ProposalTable._ID);
        int latitudeIndex = cursor.getColumnIndex(ProposalTable.LATITUDE_COLUMN);
        int longitudeIndex = cursor.getColumnIndex(ProposalTable.LONGITUDE_COLUMN);
        int usernameIndex = cursor.getColumnIndex(ProposalTable.USERNAME_COLUMN);
        int userLevelIndex = cursor.getColumnIndex(ProposalTable.USER_LEVEL_COLUMN);

        //add new proposals
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final MarkerOptions options = new MarkerOptions()
                                            .position(new LatLng(cursor.getDouble(latitudeIndex), cursor.getDouble(longitudeIndex)))
                                            .title(cursor.getString(usernameIndex) + "[" + cursor.getInt(userLevelIndex) + "]")
                                            .draggable(false);
            Marker marker = map.addMarker(options);
            markerIds.put( marker.getId(), cursor.getLong(idIndex));

            cursor.moveToNext();
        }
    }

    public interface OnProposalSelect {
        public void onProposalSelected(long proposalId);
    }
}
