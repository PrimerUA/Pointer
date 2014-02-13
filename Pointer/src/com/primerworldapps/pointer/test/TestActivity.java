package com.primerworldapps.pointer.test;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.primerworldapps.pointer.network.request.SetGCMRegIdRequest;
import com.primerworldapps.pointer.network.response.GetOpponentsListResponse;
import com.primerworldapps.pointer.network.response.SetGCMRegIdResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 16.12.13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class TestActivity extends FragmentActivity {
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

        findViewById(R.id.set_reg_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetGCMRegIdRequest request = new SetGCMRegIdRequest(1, "APA91bHYQAejWJJLg7ClopjPkdfg85GXdup3U0jqc0ttFEhAY5Ele-FprUye5dFhqQAgPa_Fpjt-FA6GHj7vOzyfj2LuEcOD5c4bUnAlnA36ucr9EVuVfeI76wYty64-71vrpB-xbY9bmDLjL0tajWjyNo6Zdom-JQ",
                        new Response.Listener<SetGCMRegIdResponse>() {
                            @Override
                            public void onResponse(SetGCMRegIdResponse response) {
                                Log.d("KVEST_TAG", "onResponse");
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
    }
}
