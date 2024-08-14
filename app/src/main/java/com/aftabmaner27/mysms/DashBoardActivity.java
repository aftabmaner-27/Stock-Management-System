package com.aftabmaner27.mysms;



import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.aftabmaner27.mysms.adpters.GridAdapter;


public class DashBoardActivity extends AppCompatActivity {

    GridView gridView;

    String[] items = {"Add Stock", "Sale", "Reports", "Settings", "Account"};
    int[] images = {R.drawable.add, R.drawable.gross_domestic_product, R.drawable.product_management, R.drawable.ic_baseline_settings_24, R.drawable.profile_user};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().hide();
        gridView = findViewById(R.id.gridView);

        GridAdapter adapter = new GridAdapter(this, items, images);
        gridView.setAdapter(adapter);


        // Set OnItemClickListener to handle item clicks
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Navigate to AddStockActivity
                        Intent addStockIntent = new Intent(DashBoardActivity.this, AddStockActivity.class);
                        startActivity(addStockIntent);
                        break;
                    case 1:
                        // Navigate to ReportsActivity
                        Intent salesIntent = new Intent(DashBoardActivity.this, SalesActivity.class);
                        startActivity(salesIntent);
                        break;

                    case 2:
                        // Navigate to ReportsActivity
                        Intent reportsIntent = new Intent(DashBoardActivity.this, ReportsActivity.class);
                        startActivity(reportsIntent);
                        break;
                    case 3:
                        // Navigate to SettingsActivity
                        Intent settingsIntent = new Intent(DashBoardActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                        break;
                    case 4:
                        // Navigate to AccountActivity
                        Intent accountIntent = new Intent(DashBoardActivity.this, AccountActivity.class);
                        startActivity(accountIntent);
                        break;
                }
            }
        });
    }
}