package com.primerworldapps.pointer.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
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
    private static final int MAP_ID = 0;
    private static final int LIST_ID = 1;

    private ProposalsMapFragment mapFragment;
    private ProposalsListFragment listFragment;
    private int shownFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            try {
                transaction.add(R.id.fragment_container, getMapFragment());
                shownFragment = MAP_ID;
            } finally {
                transaction.commit();
            }
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment instanceof ProposalsMapFragment) {
                mapFragment = (ProposalsMapFragment)fragment;
                shownFragment = MAP_ID;
            } else if (fragment instanceof ProposalsListFragment) {
                listFragment = (ProposalsListFragment)fragment;
                shownFragment = LIST_ID;
            }
        }
    }

    private ProposalsMapFragment getMapFragment() {
        if (mapFragment == null) {
            mapFragment = new ProposalsMapFragment();
        }

        return mapFragment;
    }

    private ProposalsListFragment getListFragment() {
        if (listFragment == null) {
            listFragment = new ProposalsListFragment();
        }

        return listFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        switch (shownFragment) {
            case MAP_ID : MenuItemCompat.setShowAsAction(menu.add(0, LIST_ID, 0, getString(R.string.list)).setIcon(android.R.drawable.ic_menu_view), MenuItem.SHOW_AS_ACTION_IF_ROOM); break;
            case LIST_ID : MenuItemCompat.setShowAsAction(menu.add(0, MAP_ID, 0, getString(R.string.map)).setIcon(android.R.drawable.ic_dialog_map), MenuItem.SHOW_AS_ACTION_IF_ROOM); break;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MAP_ID : showMap(); return true;
            case LIST_ID : showList(); return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMap() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        try {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left/*R.animator.flip_right_in, R.animator.flip_right_out,
                                            R.anim.flip_left_in, R.anim.flip_left_out*/);
            transaction.replace(R.id.fragment_container, getMapFragment());
            shownFragment = MAP_ID;
        } finally {
            transaction.commit();
        }
        supportInvalidateOptionsMenu();
    }

    private void showList() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        try {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right/*R.anim.flip_right_in, R.anim.flip_right_out,
                    R.animator.flip_left_in, R.animator.flip_left_out */);
            transaction.replace(R.id.fragment_container, getListFragment());
            shownFragment = LIST_ID;
        } finally {
            transaction.commit();
        }
        supportInvalidateOptionsMenu();
    }

    @Override
    public void onProposalSelected(long proposalId) {
        Log.d("KVEST_TAG", "selected proposal id=" + proposalId);
    }
}
