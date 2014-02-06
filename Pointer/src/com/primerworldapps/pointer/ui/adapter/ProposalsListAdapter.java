package com.primerworldapps.pointer.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 17.12.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class ProposalsListAdapter extends SimpleCursorAdapter {
    private static final String[] FROM = {ProposalTable._ID, ProposalTable.USERNAME_COLUMN, ProposalTable.USER_LEVEL_COLUMN, ProposalTable.LATITUDE_COLUMN, ProposalTable.LONGITUDE_COLUMN };
    private static final int[] TO = {R.id.proposal_id, R.id.user_name, R.id.user_level, R.id.latitude, R.id.longitude };

    public ProposalsListAdapter(Context context, int flags) {
        super(context, R.layout.proposals_list_item, null, FROM, TO, flags);
    }
}
