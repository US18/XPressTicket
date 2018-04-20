package com.example.uplabdhisingh.xpressticket.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class HistoryContentProvider extends ContentProvider
{

    public static final int HISTORY = 100; //this is the whole table or directory
    public static final int HISTORY_ID = 101; //this is the specific row of data.

    private HistoryDbHelper historiesDbHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher()
    {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //firstly I'll be adding uri for my whole table with its id :
        uriMatcher.addURI(HistoryContract.CONTENT_AUTHORITY,HistoryContract.PATH,HISTORY);
        //and now I'll be adding uri for my specific row data :
        uriMatcher.addURI(HistoryContract.CONTENT_AUTHORITY, HistoryContract.PATH + "/#", HISTORY_ID);

        return  uriMatcher;
    }


    @Override
    public boolean onCreate()
    {
        Context context = getContext();
        historiesDbHelper = new HistoryDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder)
    {
        final SQLiteDatabase db = historiesDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);

        Cursor retCursor;

        switch (match)
        {
            case HISTORY:
                retCursor=db.query(HistoryContract.PNR_Entries.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case HISTORY_ID:
            {
                String id = uri.getPathSegments().get(1);
                String mSelection = " = ? ";
                String[] mSelectionArgs = new String[]{id};

                retCursor=db.query(HistoryContract.PNR_Entries.TABLE_NAME,
                        projection,
                        mSelection,
                        mSelectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Uri not found : " +uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        int match = sUriMatcher.match(uri);

        switch (match)
        {
            case HISTORY:
                // directory
                return "vnd.android.cursor.dir" + "/" + HistoryContract.CONTENT_AUTHORITY + "/" + HistoryContract.PATH;
            case HISTORY_ID:
                // single item type
                return "vnd.android.cursor.item" + "/" + HistoryContract.CONTENT_AUTHORITY + "/" + HistoryContract.PATH;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri,
                      @Nullable ContentValues values)
    {
        final SQLiteDatabase db = historiesDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        Uri returnUri;

        switch(match)
        {
            case HISTORY:
                long id = db.insert(HistoryContract.PNR_Entries.TABLE_NAME,null,values);
                if(id > 0)
                {
                    returnUri= ContentUris.withAppendedId(HistoryContract.PNR_Entries.CONTENT_URI,id);
                }
                else
                {
                    throw new android.database.SQLException("Failed to insert data: " +uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("No uri found: " +uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs)
    {
        final SQLiteDatabase db = historiesDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);

        int moviesDeleted;
        switch (match)
        {
            case HISTORY_ID:
                String id = uri.getPathSegments().get(1);
                moviesDeleted = db.delete(HistoryContract.PNR_Entries.TABLE_NAME,
                        "_id=?",
                        new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Not yet Implemented");
        }
        if(moviesDeleted!=0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return moviesDeleted;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs)
    {
        //Keep track of if an update occurs
        int moviesUpdated;

        // match code
        int match = sUriMatcher.match(uri);

        switch (match)
        {
            case HISTORY_ID:
                //update a single task by getting the id
                String id = uri.getPathSegments().get(1);
                //using selections
                moviesUpdated = historiesDbHelper.getWritableDatabase().update(HistoryContract.PNR_Entries.TABLE_NAME,
                        values, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (moviesUpdated != 0)
        {
            //set notifications if a task was updated
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // return number of tasks updated
        return moviesUpdated;
    }
}
