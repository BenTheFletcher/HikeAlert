package com.hikealert.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        onBackPressed();
    }
    public void goToConfirmPage (View view) {
        Intent intent = new Intent(this, ConfirmPage.class);
        startActivity(intent);
    }
    public void goToLvlOneDialogue (View view) {
        Intent intent = new Intent(this, LvlOneDialogue.class);
        startActivity(intent);
        onBackPressed();
    }
    public void goToLvlTwoDialogue (View view) {
        Intent intent = new Intent(this, LvlTwoDialogue.class);
        startActivity(intent);
        onBackPressed();
    }
    public void goToLvlThreeDialogue (View view) {
        Intent intent = new Intent(this, LvlThreeDialogue.class);
        startActivity(intent);
        onBackPressed();
    }
}
