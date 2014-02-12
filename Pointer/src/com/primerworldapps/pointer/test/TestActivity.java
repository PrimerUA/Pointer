package com.primerworldapps.pointer.test;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.primerworldapps.pointer.PointerApplication;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.contentprovider.PointerProviderMetadata;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;
import com.primerworldapps.pointer.network.VolleyHelper;
import com.primerworldapps.pointer.network.request.GetOpponentsListRequest;
import com.primerworldapps.pointer.network.response.GetOpponentsListResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 16.12.13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        findViewById(R.id.get_opponents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetOpponentsListRequest request = new GetOpponentsListRequest(2, 3, new Response.Listener<GetOpponentsListResponse>() {
                    @Override
                    public void onResponse(GetOpponentsListResponse response) {
                        if (response.isRequestSucceed()) {
                            for (GetOpponentsListResponse.Profile profile : response.profiles) {
                                Log.d("KVEST_TAG", profile.userId + "-" + profile.name + " : " + profile.greeting);
                            }
                        } else {
                            Log.d("KVEST_TAG", "response is not Succeed");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("KVEST_TAG", "onErrorResponse");
                    }
                });
                VolleyHelper.getInstance().addRequest(request);
            }
        });

        findViewById(R.id.manual_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ((PointerApplication) getApplication()).requestForcedSync(true);
            }
        });
    }

    private void addMockData() {
        ContentValues values = new ContentValues(5);
        values.put(ProposalTable._ID, 1l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 1");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 1);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.461111);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.707346);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable._ID, 2l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 2");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 2);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.457434);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.681940);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable._ID, 3l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 3");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 3);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.447112);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.693098);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable._ID, 4l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 4");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 4);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.442840);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.703741);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable._ID, 5l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 5");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 5);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.466568);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.687433);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());
    }
}
