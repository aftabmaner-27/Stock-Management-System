package com.aftabmaner27.mysms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    LinearLayout mBackUp, mRestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        mBackUp = findViewById(R.id.view_Backup);
        mRestore = findViewById(R.id.view_Restore);


        mBackUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open Google Drive
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.docs");
                if (intent != null) {
                    startActivity(intent);
                } else {
                    // If the app is not installed, open the Play Store to download it
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.docs"));
                    startActivity(intent);
                }
            }
        });

        mRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open Google Drive
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.docs");
                if (intent != null) {
                    startActivity(intent);
                } else {
                    // If the app is not installed, open the Play Store to download it
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.docs"));
                    startActivity(intent);
                }
            }
        });
    }


}
