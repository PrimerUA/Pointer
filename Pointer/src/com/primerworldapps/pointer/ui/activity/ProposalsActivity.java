package com.primerworldapps.pointer.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.google.android.gms.maps.SupportMapFragment;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.ui.fragment.ProposalsListFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 17.12.13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public class ProposalsActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            try {
                transaction.add(R.id.fragment_container, new ProposalsListFragment());
            } finally {
                transaction.commit();
            }
        } else {
            //TODO
        }
    }
}
