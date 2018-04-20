package com.example.uplabdhisingh.xpressticket.rest;

import com.example.uplabdhisingh.xpressticket.model.PNR_Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("pnr-status/pnr/{pnr}/apikey/l04ypz10il/")
    Call<PNR_Response> getDetails(@Path("pnr") String pnr);
}
