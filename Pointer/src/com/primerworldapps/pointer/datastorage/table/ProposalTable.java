package com.primerworldapps.pointer.datastorage.table;

import android.provider.BaseColumns;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 15.12.13
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public class ProposalTable implements BaseColumns {
    public static final String TABLE_NAME = "proposal";

    public static final String USERNAME_COLUMN = "user_name";
    public static final String USER_LEVEL_COLUMN = "user_level";
    public static final String LONGITUDE_COLUMN = "longitude";
    public static final String LATITUDE_COLUMN = "latitude";

    public static final String[] FULL_PROJECTION = {_ID, USERNAME_COLUMN, USER_LEVEL_COLUMN,
                                                    LONGITUDE_COLUMN, LATITUDE_COLUMN};

    public static final String CREATE_TABLE_SQL = "CREATE TABLE \"" + TABLE_NAME + "\" (\"" +
            _ID + "\" INTEGER PRIMARY KEY, \"" +
            USERNAME_COLUMN + "\" TEXT DEFAULT \"\", \"" +
            USER_LEVEL_COLUMN + "\" INTEGER DEFAULT 0, \"" +
            LONGITUDE_COLUMN + "\" REAL DEFAULT 0, \"" +
            LATITUDE_COLUMN + "\" REAL DEFAULT 0);";

    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS \"" + TABLE_NAME + "\";";
}
