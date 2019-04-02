package com.hikealert.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
    }
    public void goToMainMenu (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        onBackPressed();
    }
}
