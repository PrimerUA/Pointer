package com.primerworldapps.pointer.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import com.bugsense.trace.BugSenseHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 27.11.13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseActivity extends ActionBarActivity {
    private static final String BUGSENS_APIKEY = "06d6fe32";

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        BugSenseHandler.initAndStartSession(this, BUGSENS_APIKEY);
    }
}
