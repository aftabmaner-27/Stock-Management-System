package com.aftabmaner27.mysms.Reports;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.aftabmaner27.mysms.DBHandler;
import com.aftabmaner27.mysms.Models.StockModel;
import com.aftabmaner27.mysms.R;
import com.aftabmaner27.mysms.adpters.StockListAdapter;

import java.util.ArrayList;
import java.util.List;

public class StockReportActivity extends AppCompatActivity {

    private DBHandler dbHelper;
    private ListView listView;
    private StockListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_report);
        getSupportActionBar().hide();

        dbHelper = new DBHandler(this);
        listView = findViewById(R.id.myStockList);

        List<StockModel> items = getItemsFromDatabase();
        adapter = new StockListAdapter(this, items);
        listView.setAdapter(adapter);
    }

    private List<StockModel> getItemsFromDatabase() {
        List<StockModel> items = new ArrayList<>();
        Cursor cursor = dbHelper.getAllStock();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.ID_COL));
                String productname = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.PRODUCT_NAME_COL));
                String productdesc = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.PRODUCT_DESCRIPTION_COL));
                String quantity = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.QUANTITY_COL));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.PRICE_COL));
                String gst = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.GST_COL));
                String totalamont = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.TOTAL_AMMOUNT_COL));
                items.add(new StockModel(id, productname,productdesc,quantity,price,gst,totalamont));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return items;
    }
}