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

    public static final String NAME_COLUMN = "name";
    public static final String PHOTO_COLUMN = "photo";
    public static final String GENDER_COLUMN = "gender";
    public static final String GREETING_COLUMN = "greeting";
    public static final String AGE_COLUMN = "age";

    public static final String[] FULL_PROJECTION = {_ID, NAME_COLUMN, PHOTO_COLUMN,
                                                    GENDER_COLUMN, GREETING_COLUMN, AGE_COLUMN};

    public static final String CREATE_TABLE_SQL = "CREATE TABLE \"" + TABLE_NAME + "\" (\"" +
            _ID + "\" INTEGER PRIMARY KEY, \"" +
            NAME_COLUMN + "\" TEXT DEFAULT \"\", \"" +
            PHOTO_COLUMN + "\" TEXT DEFAULT \"\", \"" +
            GENDER_COLUMN + "\" INTEGER DEFAULT 0, \"" +
            GREETING_COLUMN + "\" TEXT DEFAULT \"\", \"" +
            AGE_COLUMN + "\" INTEGER DEFAULT 0);";

    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS \"" + TABLE_NAME + "\";";
}
