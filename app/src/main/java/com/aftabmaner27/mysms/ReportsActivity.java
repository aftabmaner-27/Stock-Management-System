package com.aftabmaner27.mysms;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.aftabmaner27.mysms.Reports.StockReportActivity;
import com.aftabmaner27.mysms.Reports.VendorReportActivity;


public class ReportsActivity extends AppCompatActivity {

    LinearLayout view_StockReport,view_SalesReport,view_VendorReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        getSupportActionBar().hide();

        view_StockReport = findViewById(R.id.view_StockReport);
        view_SalesReport = findViewById(R.id.view_SalesReport);
        view_VendorReport = findViewById(R.id.view_VendorReport);

        view_StockReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportsActivity.this, StockReportActivity.class);
                startActivity(i);
            }
        });
        view_VendorReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportsActivity.this, VendorReportActivity.class);
                startActivity(i);
            }
        });
    }
}