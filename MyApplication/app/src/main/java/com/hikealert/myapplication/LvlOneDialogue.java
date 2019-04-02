package com.hikealert.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LvlOneDialogue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl_one_dialogue);
    }
    public void goToConfirmPage(View view) {
        Intent intent = new Intent(this, ConfirmPage.class);
        startActivity(intent);
        onBackPressed();
    }
    public void goToEmergencyOptions(View view) {
        Intent intent = new Intent(this, emergencyOptions.class);
        startActivity(intent);
        onBackPressed();
    }
}