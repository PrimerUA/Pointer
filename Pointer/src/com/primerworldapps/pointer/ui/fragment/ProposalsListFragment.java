package com.primerworldapps.pointer.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import com.primerworldapps.pointer.contentprovider.PointerProviderMetadata;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;
import com.primerworldapps.pointer.ui.adapter.ProposalsListAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: roman
 * Date: 2/13/14
 * Time: 9:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProposalsListFragment extends ListFragment  implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOAD_PROPOSALS_ID = 0;

    private ProposalsListAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //create and set adapter
        adapter = new ProposalsListAdapter(getActivity(), ProposalsListAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);

        //load cursor
        getLoaderManager().initLoader(LOAD_PROPOSALS_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case LOAD_PROPOSALS_ID : return new CursorLoader(getActivity(),
                                                            PointerProviderMetadata.PROPOSALS_URI,
                                                            ProposalTable.FULL_PROJECTION, null, null, null);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }
}
