package com.primerworldapps.pointer.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 17.12.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class ProposalsListAdapter extends SimpleCursorAdapter {
    public ProposalsListAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to, int flags) {
        super(context, layout, cursor, from, to, flags);
    }
}
