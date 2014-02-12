package com.primerworldapps.pointer;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.ContentResolver;
import android.os.Bundle;
import com.primerworldapps.pointer.accounts.AuthenticatorService;
import com.primerworldapps.pointer.contentprovider.PointerProviderMetadata;
import com.primerworldapps.pointer.network.VolleyHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 12.02.14
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class PointerApplication extends Application {
    private Account account;

    @Override
    public void onCreate() {
        super.onCreate();

        initVolley();
        setupAppAccount();
    }

    public void requestForcedSync(boolean expedited) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        if (expedited) {
            bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        }
        ContentResolver.requestSync(account, PointerProviderMetadata.AUTHORITY, bundle);
    }


    private void initVolley() {
        VolleyHelper.getInstance().init(getApplicationContext());
    }

    private void setupAppAccount() {
        AccountManager accountManager = AccountManager.get(getApplicationContext());
        Account[] accounts = accountManager.getAccountsByType(AuthenticatorService.Authenticator.ACCOUNT_TYPE);
        if (accounts.length > 0) {
            account = accounts[0];
        } else {

            account = new Account(AuthenticatorService.Authenticator.ACCOUNT_NAME,
                                  AuthenticatorService.Authenticator.ACCOUNT_TYPE);
            //usually it will contain real user info, like login/pass
            accountManager.addAccountExplicitly(account, null, null);

            //Enable AutoSync
            ContentResolver.setSyncAutomatically(account, PointerProviderMetadata.AUTHORITY, true);
        }
    }
}
