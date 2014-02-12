package com.primerworldapps.pointer.sync;

import android.accounts.Account;
import android.content.*;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 10.02.14
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private ContentResolver contentResolver;

    /**
     * Constructor. Obtains handle to content resolver for later use.
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        contentResolver = context.getContentResolver();
    }

    /**
     * Constructor. Obtains handle to content resolver for later use.
     */
    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        contentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras,
                              String authority, ContentProviderClient providerClient,
                             SyncResult syncResult) {
        loadPropositions();
    }

    private void loadPropositions() {
        //TODO
    }
}
