package com.primerworldapps.pointer.test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.primerworldapps.pointer.PointerApplication;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.network.VolleyHelper;
import com.primerworldapps.pointer.network.request.GetOpponentsListRequest;
import com.primerworldapps.pointer.network.request.SetGCMRegIdRequest;
import com.primerworldapps.pointer.network.response.GetOpponentsListResponse;
import com.primerworldapps.pointer.network.response.SetGCMRegIdResponse;

/**
 * Created with IntelliJ IDEA. User: Kvest Date: 16.12.13 Time: 22:42 To change
 * this template use File | Settings | File Templates.
 */
public class TestActivity extends SherlockFragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		getSupportActionBar().setTitle(getString(R.string.app_name));

		findViewById(R.id.manual_update).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((PointerApplication) getApplication()).requestForcedSync(true);
			}
		});

	}
}
