package com.example.uplabdhisingh.xpressticket.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uplabdhisingh.xpressticket.R;
import com.example.uplabdhisingh.xpressticket.data.HistoryContract;

public class HistoryAdapter
        extends RecyclerView.Adapter<HistoryAdapter.HistoryAdapterViewHolder>
{
    private final Context mContext;
    private Cursor pnr_cursor;
    private static final String TAG = HistoryAdapter.class.getSimpleName();


    public HistoryAdapter(Context context)
    {
        mContext=context;
    }

    @Override
    public HistoryAdapter.HistoryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context=parent.getContext();
        int layoutID=R.layout.pnr_histories_list;
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(layoutID,parent,false);
        return new HistoryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistoryAdapterViewHolder holder, int position)
    {
        pnr_cursor.moveToPosition(position);

        int idIndex = pnr_cursor.
                getColumnIndex(HistoryContract.PNR_Entries._ID);
        int pnrIndex = pnr_cursor.
                getColumnIndex(HistoryContract.PNR_Entries.COLUMN_PNR);

        final int id = pnr_cursor.getInt(idIndex);
        String pnr = pnr_cursor.getString(pnrIndex);

        holder.itemView.setTag(id);
        holder.pnrHistoryTextView.setText(pnr);
    }

    @Override
    public int getItemCount()
    {
        if(pnr_cursor==null)
        {
            return 0;
        }
        return pnr_cursor.getCount();
    }

    public void swapCursor(Cursor newCursor)
    {
        pnr_cursor=newCursor;
        notifyDataSetChanged();
    }

    public class HistoryAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView pnrHistoryTextView;

        public HistoryAdapterViewHolder(View itemView)
        {
            super(itemView);
            pnrHistoryTextView = (TextView) itemView.findViewById(R.id.tv_histories_list);
        }
    }
}
