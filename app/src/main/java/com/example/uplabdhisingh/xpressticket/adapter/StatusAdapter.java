package com.example.uplabdhisingh.xpressticket.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uplabdhisingh.xpressticket.R;
import com.example.uplabdhisingh.xpressticket.model.Passengers;
import java.util.ArrayList;
import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusAdapterViewHolder>
{

    private List<Passengers> passengersList;
    public Context context;

    public String TAG = StatusAdapter.class.getSimpleName();

    public StatusAdapter(List<Passengers> passengersList,Context context)
    {
        this.passengersList=passengersList;
        this.context=context;
    }


    @Override
    public StatusAdapter.StatusAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        int layoutId = R.layout.status_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId,parent,false);
        return new StatusAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusAdapterViewHolder holder, int position)
    {
        Passengers passengers = passengersList.get(position);
        String passengersName = passengers.getCurrentStatus();
        holder.passNameTV.setText("\u2022"+passengersName+"\n");
    }

    @Override
    public int getItemCount()
    {
        if(passengersList==null)
        {
            return 0;
        }
        return passengersList.size();
    }

    public class StatusAdapterViewHolder extends RecyclerView.ViewHolder
    {

        TextView passNameTV;

        public StatusAdapterViewHolder(View itemView)
        {
            super(itemView);
            passNameTV = (TextView) itemView.findViewById(R.id.tv_passengers_list);
        }
    }

    public void setStatusData(List<Passengers> passList)
    {
        passengersList=passList;
        notifyDataSetChanged();
    }
}

