package com.example.uplabdhisingh.xpressticket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity
{

    TextView pnrTextView,historyTextView, aboutTextView, exitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        pnrTextView = (TextView) findViewById(R.id.tv_pnr);
        historyTextView = (TextView) findViewById(R.id.tv_history);
        aboutTextView = (TextView) findViewById(R.id.tv_about_us);
        exitTextView = (TextView) findViewById(R.id.tv_exit);

        pnrTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intentToSecond = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intentToSecond);
            }
        });

        historyTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentToHistory = new Intent(FirstActivity.this, HistoryActivity.class);
                startActivity(intentToHistory);
            }
        });

        aboutTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        FirstActivity.this).create();

                alertDialog.setTitle("About Us");

                alertDialog.setMessage("Welcome to my Assignment Task");

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
            }
        });

        exitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
