package com.example.uplabdhisingh.xpressticket;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.uplabdhisingh.xpressticket.adapter.HistoryAdapter;
import com.example.uplabdhisingh.xpressticket.data.HistoryContract;
import com.example.uplabdhisingh.xpressticket.data.HistoryDbHelper;

public class HistoryActivity extends AppCompatActivity
        implements
        LoaderManager.LoaderCallbacks<Cursor>
{

    RecyclerView historiesRV;
    HistoryAdapter historyAdapter;
    public SQLiteDatabase mDb;
    private static final int HISTORY_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historiesRV = (RecyclerView) findViewById(R.id.rv_histories);
        historyAdapter = new HistoryAdapter(this);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        historiesRV.setLayoutManager(layoutManager1);

        historiesRV.setHasFixedSize(true);

        HistoryDbHelper dbHelper = new HistoryDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        historiesRV.setAdapter(historyAdapter);

        getSupportLoaderManager().initLoader(HISTORY_LOADER_ID,null,this);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args)
    {

        return new AsyncTaskLoader<Cursor>(this)
        {
            Cursor histories = null;

            @Override
            protected void onStartLoading()
            {
                if(histories!=null)
                {
                    deliverResult(histories);
                } else
                {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground()
            {
                try
                {

                    return getContentResolver().
                            query(HistoryContract.PNR_Entries.CONTENT_URI,
                                    null,
                                    null,
                                    null,
                                    HistoryContract.PNR_Entries.COLUMN_PNR);

                } catch (Exception e)
                {

                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data)
            {
                histories = data;
                histories.moveToFirst();
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        historyAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        historyAdapter.swapCursor(null);
    }
}
