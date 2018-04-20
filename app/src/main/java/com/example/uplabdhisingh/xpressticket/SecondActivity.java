package com.example.uplabdhisingh.xpressticket;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uplabdhisingh.xpressticket.adapter.StatusAdapter;
import com.example.uplabdhisingh.xpressticket.data.HistoryContract;
import com.example.uplabdhisingh.xpressticket.model.PNR_Response;
import com.example.uplabdhisingh.xpressticket.model.Passengers;
import com.example.uplabdhisingh.xpressticket.rest.ApiClient;
import com.example.uplabdhisingh.xpressticket.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity
{

    EditText pnrNumberEditText;
    Button checkStatusButton;

    ProgressBar loading;

    RecyclerView statusRV;
    StatusAdapter statusAdapter;
    List<Passengers> response1;

    String TAG = SecondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pnrNumberEditText=(EditText) findViewById(R.id.et_pnr_number);
        checkStatusButton=(Button) findViewById(R.id.btn_check);

        loading = (ProgressBar) findViewById(R.id.pb_loading);
        statusRV = (RecyclerView) findViewById(R.id.rv_status);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        statusRV.setHasFixedSize(true);
        statusRV.setLayoutManager(layoutManager);
        statusAdapter=new StatusAdapter(response1,this);
        statusRV.setAdapter(statusAdapter);

        checkStatusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String pnrUserInput = pnrNumberEditText.getText().toString();

                loading.setVisibility(View.VISIBLE);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<PNR_Response> call = apiService.getDetails(pnrUserInput);

                call.enqueue(new Callback<PNR_Response>()
                {
                    @Override
                    public void onResponse(Call<PNR_Response> call, Response<PNR_Response> response)
                    {

                        response1 = response.body().getPassengersList();
                        loading.setVisibility(View.GONE);
                        statusAdapter.setStatusData(response1);
                        statusAdapter.notifyDataSetChanged();
                        Log.d(TAG,"No. of Data Received : "+response1.size());
                    }

                    @Override
                    public void onFailure(Call<PNR_Response> call, Throwable t)
                    {
                        Log.e(TAG,t.toString());
                    }
                });

                ContentValues contentValues = new ContentValues();
                contentValues.put(HistoryContract.PNR_Entries.COLUMN_PNR,pnrUserInput);

                Uri uri = getContentResolver().insert(HistoryContract.PNR_Entries.CONTENT_URI,contentValues);

                if(uri != null)
                {
                    Toast.makeText(SecondActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
