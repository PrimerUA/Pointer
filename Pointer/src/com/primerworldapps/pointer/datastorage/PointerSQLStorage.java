package com.primerworldapps.pointer.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 15.12.13
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class PointerSQLStorage extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pointer_db";
    private static final int DATABASE_VERSION = 1;

    public PointerSQLStorage(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProposalTable.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Nothing to do yet
    }
}
