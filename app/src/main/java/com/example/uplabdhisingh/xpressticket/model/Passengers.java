package com.example.uplabdhisingh.xpressticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passengers
{

    @SerializedName("current_status")
    @Expose
    private String current_status;

    public Passengers()
    {}

    public Passengers(String current_status)
    {
        this.current_status=current_status;
    }

    public void setCurrentStatus(String currentTrainStatus)
    {
        current_status=currentTrainStatus;
    }

    public String getCurrentStatus()
    {
        return current_status;
    }
}
