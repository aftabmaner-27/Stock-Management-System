package com.aftabmaner27.mysms.Reports;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aftabmaner27.mysms.DBHandler;
import com.aftabmaner27.mysms.Models.StockModel;
import com.aftabmaner27.mysms.Models.VendorModel;
import com.aftabmaner27.mysms.R;
import com.aftabmaner27.mysms.adpters.StockListAdapter;
import com.aftabmaner27.mysms.adpters.VendorListAdapter;

import java.util.ArrayList;
import java.util.List;

public class VendorReportActivity extends AppCompatActivity {


    private DBHandler dbHelper;
    private ListView listView;
    private VendorListAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vendor_report);

        getSupportActionBar().hide();

        dbHelper = new DBHandler(this);
        listView = findViewById(R.id.myVendorList);

        List<VendorModel> items = getVendorsFromDatabase();
        adapter = new VendorListAdapter(this, items);
        listView.setAdapter(adapter);

    }


    private List<VendorModel> getVendorsFromDatabase() {
        List<VendorModel> items = new ArrayList<>();
        Cursor cursor = dbHelper.getAllVendor();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.VENDOR_ID_COL));
                String vendorname = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.VENDOR_NAME_COL));
                String vendoraddress = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.VENDOR_ADDRESS_COL));
                String vendorgst = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.VENDOR_GSTNO_COL));
                String vendorcontactno = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.VENDOR_CONTACTNO_COL));
                items.add(new VendorModel(id, vendorname,vendoraddress,vendorgst,vendorcontactno));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return items;
    }
}