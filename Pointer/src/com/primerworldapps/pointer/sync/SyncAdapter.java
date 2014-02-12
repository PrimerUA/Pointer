package com.primerworldapps.pointer.sync;

import android.accounts.Account;
import android.content.*;
import android.os.Bundle;
import com.android.volley.toolbox.RequestFuture;
import com.primerworldapps.pointer.network.VolleyHelper;
import com.primerworldapps.pointer.network.request.GetOpponentsListRequest;
import com.primerworldapps.pointer.network.response.GetOpponentsListResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
        RequestFuture<GetOpponentsListResponse> future = RequestFuture.newFuture();
        GetOpponentsListRequest request = new GetOpponentsListRequest(2, 3, future, future);

        //make request
        VolleyHelper.getInstance().addRequest(request);

        //wait for response
        try {
            GetOpponentsListResponse response = future.get();
            if (response.isRequestSucceed()) {
                saveOpponentsList(response.profiles);
            } else {
                //TODO
                // handle the error
            }
        } catch (InterruptedException e) {
            //TODO
            // handle the error
        } catch (ExecutionException e) {
            //TODO
            // handle the error
        }
    }

    private void saveOpponentsList(List<GetOpponentsListResponse.Profile> profiles) {
        //TODO
    }
}
