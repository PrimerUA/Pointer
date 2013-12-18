package com.primerworldapps.pointer.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.google.android.gms.maps.SupportMapFragment;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.ui.fragment.ProposalsListFragment;
import com.primerworldapps.pointer.ui.fragment.ProposalsMapFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 17.12.13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public class ProposalsActivity extends BaseActivity implements ProposalsMapFragment.OnProposalSelect,
                                                               ProposalsListFragment.OnProposalSelect {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            try {
                transaction.add(R.id.fragment_container, new ProposalsMapFragment());
            } finally {
                transaction.commit();
            }
        } else {
            //TODO
        }
    }

    @Override
    public void onProposalSelected(long proposalId) {
        Log.d("KVEST_TAG", "selected proposal id=" + proposalId);
    }
}
