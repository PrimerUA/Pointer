package com.primerworldapps.pointer.contentprovider;

import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 15.12.13
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */
public class PointerProviderMetadata {
    //Don't allow to create this class
    private PointerProviderMetadata(){}

    public static final String AUTHORITY = "com.primerworldapps.pointer.contentprovider.PointerProvider";

    public static final String CONTENT_TYPE_PROPOSAL_COLLECTION = "vnd.android.cursor.dir/vnd.primerworldapps.proposal";
    public static final String CONTENT_TYPE_PROPOSAL_SINGLE = "vnd.android.cursor.item/vnd.primerworldapps.proposal";

    public static final String PROPOSAL_ITEMS_PATH = "proposal";

    public static final Uri PROPOSAL_ITEMS_URI = Uri.parse("content://" + AUTHORITY + "/" + PROPOSAL_ITEMS_PATH);
}
