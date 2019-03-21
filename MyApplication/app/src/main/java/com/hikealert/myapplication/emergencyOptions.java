package com.hikealert.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class emergencyOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_options);
    }
    public void goToMainMenu (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goToConfirmPage (View view) {
        Intent intent = new Intent(this, ConfirmPage.class);
        startActivity(intent);
    }
    public void goToLvlOneDialogue (View view) {
        Intent intent = new Intent(this, LvlOneDialogue.class);
        startActivity(intent);
    }
    public void goToLvlTwoDialogue (View view) {
        Intent intent = new Intent(this, LvlTwoDialogue.class);
        startActivity(intent);
    }
    public void goToLvlThreeDialogue (View view) {
        Intent intent = new Intent(this, LvlThreeDialogue.class);
        startActivity(intent);
    }
}
