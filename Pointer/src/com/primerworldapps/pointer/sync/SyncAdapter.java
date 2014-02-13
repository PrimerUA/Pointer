package com.primerworldapps.pointer.sync;

import android.accounts.Account;
import android.content.*;
import android.os.Bundle;
import com.android.volley.toolbox.RequestFuture;
import com.primerworldapps.pointer.contentprovider.PointerProviderMetadata;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;
import com.primerworldapps.pointer.network.VolleyHelper;
import com.primerworldapps.pointer.network.request.GetOpponentsListRequest;
import com.primerworldapps.pointer.network.response.GetOpponentsListResponse;
import com.primerworldapps.pointer.utils.Constants;

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
        GetOpponentsListRequest request = new GetOpponentsListRequest(Constants.SELF_ID, Constants.PROPOSALS_LOAD_COUNT, future, future);

        //make request
        VolleyHelper.getInstance().addRequest(request);

        //wait for response
        try {
            GetOpponentsListResponse response = future.get();
            if (response.isRequestSucceed()) {

                //clear old propositions
                contentResolver.delete(PointerProviderMetadata.PROPOSALS_URI, null, null);

                //save new propositions
                saveOpponentsList(response.proposals);
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

    private void saveOpponentsList(List<GetOpponentsListResponse.Proposal> proposals) {
        if (!proposals.isEmpty()) {
            ContentValues values[] = new ContentValues[proposals.size()];
            for (int i = 0; i < proposals.size(); ++i) {
                GetOpponentsListResponse.Proposal proposal = proposals.get(i);
                values[i] = new ContentValues(6);
                values[i].put(ProposalTable._ID, proposal.userId);
                values[i].put(ProposalTable.NAME_COLUMN, proposal.name);
                values[i].put(ProposalTable.PHOTO_COLUMN, proposal.photo);
                values[i].put(ProposalTable.AGE_COLUMN, proposal.age);
                values[i].put(ProposalTable.GENDER_COLUMN, proposal.gender);
                values[i].put(ProposalTable.GREETING_COLUMN, proposal.greeting);
            }

            contentResolver.bulkInsert(PointerProviderMetadata.PROPOSALS_URI, values);
        }
    }
}
