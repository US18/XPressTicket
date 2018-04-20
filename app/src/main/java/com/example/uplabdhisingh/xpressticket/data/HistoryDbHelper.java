package com.example.uplabdhisingh.xpressticket.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "history.db";
    public static final int DATABASE_VERSION = 1;

    public HistoryDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String CREATE_TABLE =
                "CREATE TABLE " + HistoryContract.PNR_Entries.TABLE_NAME +
                        "(" +
                        HistoryContract.PNR_Entries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        HistoryContract.PNR_Entries.COLUMN_PNR + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + HistoryContract.PNR_Entries.TABLE_NAME);
        onCreate(db);
    }
}
