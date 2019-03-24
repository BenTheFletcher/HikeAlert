package com.hikealert.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
    public void goToEmergencyOptions (View view) {
        Intent intent = new Intent(this, emergencyOptions.class);
        startActivity(intent);
    }
    public void goTouserMap (View view){
        Intent intent = new Intent (this, Map_User.class);
        startActivity(intent);
    }
    public void goToAboutPage (View view){
        Intent intent = new Intent (this, AboutPage.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
