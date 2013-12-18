package com.primerworldapps.pointer.test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.contentprovider.PointerProviderMetadata;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;
import com.primerworldapps.pointer.ui.activity.ProposalsActivity;

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

        findViewById(R.id.add_mock_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMockData();
            }
        });
        findViewById(R.id.show_proposal_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, ProposalsActivity.class));
            }
        });
    }

    private void addMockData() {
        ContentValues values = new ContentValues(5);
        values.put(ProposalTable.ID_COLUMN, 1l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 1");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 1);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.461111);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.707346);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable.ID_COLUMN, 2l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 2");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 2);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.457434);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.681940);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable.ID_COLUMN, 3l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 3");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 3);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.447112);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.693098);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable.ID_COLUMN, 4l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 4");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 4);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.442840);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.703741);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());

        values = new ContentValues(5);
        values.put(ProposalTable.ID_COLUMN, 5l);
        values.put(ProposalTable.USERNAME_COLUMN , "user 5");
        values.put(ProposalTable.USER_LEVEL_COLUMN, 5);
        values.put(ProposalTable.LATITUDE_COLUMN, 46.466568);
        values.put(ProposalTable.LONGITUDE_COLUMN, 30.687433);
        Log.d("KVEST_TAG", "add=" + getContentResolver().insert(PointerProviderMetadata.PROPOSAL_ITEMS_URI, values).toString());
    }
}
