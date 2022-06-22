package com.br.utfpr.gabryel.reservaveicular;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AppInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}