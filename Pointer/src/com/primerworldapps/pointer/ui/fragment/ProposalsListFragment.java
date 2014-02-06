package com.primerworldapps.pointer.ui.fragment;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.contentprovider.PointerProviderMetadata;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;
import com.primerworldapps.pointer.ui.adapter.ProposalsListAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 17.12.13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class ProposalsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOAD_PROPOSALS_ID = 0;

    private ProposalsListAdapter adapter;
    private OnProposalSelect onProposalSelect;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //create and set adapter

        adapter = new ProposalsListAdapter(getActivity(), ProposalsListAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onProposalSelect != null) {
                    onProposalSelect.onProposalSelected(id);
                }
            }
        });

        //load cursor
        getActivity().getSupportLoaderManager().initLoader(LOAD_PROPOSALS_ID, null, this);
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
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }

    public interface OnProposalSelect {
        public void onProposalSelected(long proposalId);
    }
}
