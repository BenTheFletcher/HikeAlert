package com.hikealert.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

            /*SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss\nMM-dd-yyyy");
            String formattedDate = df.format(c.getTime());
            // formattedDate have current date/time
            Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

            // Now we display formattedDate value in TextView
            //TextView txtView = new TextView(this);
            TextView txtView = (TextView) findViewById(R.id.textView4);
            txtView.setText("Current Time:\n"+formattedDate);
            txtView.setGravity(Gravity.CENTER);*/
            SimpleDateFormat myTime = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat myDate = new SimpleDateFormat("MM-dd-yyyy");
            String formattedTime = myTime.format(c.getTime());
            String formattedDate = myDate.format(c.getTime());
            // formattedDate have current date/time
            Toast.makeText(this, formattedTime, Toast.LENGTH_SHORT).show();


            // Now we display formattedDate value in TextView
            TextView txtView = (TextView) findViewById(R.id.textView4);
            txtView.setText("Current Time:\n"+formattedTime + "\n\nCurrent Date:\n" + formattedDate);
            txtView.setTextSize(30);
            //setContentView(txtView);
        }

}


