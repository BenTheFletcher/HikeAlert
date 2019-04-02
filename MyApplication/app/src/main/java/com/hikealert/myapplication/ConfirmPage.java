package com.hikealert.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_page);

            Calendar c = Calendar.getInstance();
            System.out.println("Current time => "+c.getTime());

            SimpleDateFormat myTime = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat myDate = new SimpleDateFormat("MM/dd/yyyy");
            String formattedTime = myTime.format(c.getTime());
            String formattedDate = myDate.format(c.getTime());

            //Toast.makeText(this, "This page will close automatically", Toast.LENGTH_LONG).show();
            final Toast toast = Toast.makeText(this, "This page will close automatically", Toast.LENGTH_LONG);

            TextView txtView = (TextView) findViewById(R.id.textView4);
            txtView.setText("Current Time:\n"+formattedTime + "\n\nCurrent Date:\n" + formattedDate);
            txtView.setTextSize(40);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.show();
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            onBackPressed();
            }
        }, 5000);
        }
}