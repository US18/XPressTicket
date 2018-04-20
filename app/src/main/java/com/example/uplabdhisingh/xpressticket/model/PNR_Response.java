package com.example.uplabdhisingh.xpressticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PNR_Response
{
    @SerializedName("pnr")
    @Expose
    private String pnr;

    @SerializedName("total_passengers")
    @Expose
    private String total_passengers;

    @SerializedName("doj")
    @Expose
    private String doj;

    @SerializedName("passengers")
    @Expose
    private List<Passengers> passengersList=null;

    public PNR_Response()
    {
    }
    public PNR_Response(String pnr, String total_passengers,String doj, List<Passengers> passengersList)
    {
        super();
        this.pnr=pnr;
        this.total_passengers=total_passengers;
        this.doj=doj;
        this.passengersList=passengersList;
    }

    public String getPnr()
    {
        return pnr;
    }

    public void setPnr(String pnr1)
    {
        pnr=pnr1;
    }

    public String getDOJ()
    {
        return doj;
    }

    public void setDOJ(String dojInput)
    {
        doj=dojInput;
    }

    public String getTotalPassengers()
    {
        return total_passengers;
    }
    public void setTotalPassengers(String total_passengersInput)
    {
        total_passengers=total_passengersInput;
    }

    public List<Passengers> getPassengersList()
    {
        return passengersList;
    }
    public void setPassengersInfo(ArrayList<Passengers> passengersInfo)
    {
        passengersList=passengersInfo;
    }

}
