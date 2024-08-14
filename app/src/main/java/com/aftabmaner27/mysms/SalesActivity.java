package com.aftabmaner27.mysms;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SalesActivity extends AppCompatActivity {
    private EditText productNameEdt, productDescpritonEdt, priceEdt, qunatityEdt;
    private TextView btnAddStock, btnUpdateStock,qty_Ammount, totalAmmountEdt;
    private Spinner spnGSTList;
    private String gstRateInput = "0";
    private String getproductId = "0";
    private String getdbquantity = "0";


    private ListView listView;

    private ArrayAdapter<String> adapter;

    private AutoCompleteTextView autoCompleteTextView;

    private SimpleCursorAdapter cursorAdapter;
    private DBHandler dbHandler;

    private String[] mArrSpnGSTList = {"0%", "5%", "12%", "18%", "28%"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        getSupportActionBar().hide();


        dbHandler = new DBHandler(SalesActivity.this);
//        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        listView = findViewById(R.id.listView);
        productNameEdt = findViewById(R.id.Product_Name);
        productDescpritonEdt = findViewById(R.id.Product_Description);
        qunatityEdt = findViewById(R.id.Qunatity);
        priceEdt = findViewById(R.id.Price);
        totalAmmountEdt = findViewById(R.id.Total_Ammount);
        qty_Ammount = findViewById(R.id.totalqty_Amt);

        btnAddStock = findViewById(R.id.btn_Save);
//        btnUpdateStock = findViewById(R.id.btn_Update);

        spnGSTList = findViewById(R.id.spn_gst);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mArrSpnGSTList);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spnGSTList.setAdapter(adapter);

        // Set the spinner's item selected listener
        spnGSTList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                gstRateInput = selectedItem;
                calculateGST();
//                Toast.makeText(parent.getContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);

        ArrayAdapter<String> finalAdapter = adapter;
        productNameEdt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString();
                List<String> suggestions = dbHandler.getSuggestions(query);
                finalAdapter.clear();
                finalAdapter.addAll(suggestions);
                finalAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
//                productNameEdt.setText(s);
//                listView.setVisibility(View.GONE);
            }
        });

        // Add item click listener for ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                setProductDetails(selectedItem);
                listView.setVisibility(View.GONE);
            }
        });


        btnAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellProduct();
            }
        });
    }

    private void SellProduct() {

        String mProductName = productNameEdt.getText().toString();
        String mProductDescription = productDescpritonEdt.getText().toString();
        String mQuantity = qunatityEdt.getText().toString();
        String mPrice = priceEdt.getText().toString();
        String mtotalAmmount = totalAmmountEdt.getText().toString();
        String[] TotalAmtArray = mtotalAmmount.split(":");
        String TltAmtStr = TotalAmtArray[1];


        if (mProductName.isEmpty()) {
            productNameEdt.setError("Product Name must not be empty");
        } else if (mProductDescription.isEmpty()) {
            productDescpritonEdt.setError("Product Description must not be empty");
        } else if (mQuantity.isEmpty()) {
            qunatityEdt.setError("Quantity must not be empty");
        } else if (mPrice.isEmpty()) {
            priceEdt.setError("Price must not be empty");
        } else {

            String strQty = qunatityEdt.getText().toString();
            int enterQty = Integer.parseInt(strQty);
            int StockQty = Integer.parseInt(getdbquantity);
            if(enterQty>StockQty){
                Toast.makeText(this, "Quantity is greater than Stock Quantity..", Toast.LENGTH_LONG).show();
            }else {
                int finalquantity = StockQty - enterQty;
                String mUpdatedQty = String.valueOf(finalquantity);

                dbHandler.updateStock(Integer.valueOf(getproductId), mProductName, mProductDescription, mUpdatedQty, mPrice, gstRateInput, TltAmtStr);

                Toast.makeText(SalesActivity.this, "Sell Successfully", Toast.LENGTH_SHORT).show();
                productNameEdt.setText("");
                productDescpritonEdt.setText("");
                qunatityEdt.setText("");
                priceEdt.setText("");
                totalAmmountEdt.setText("");
            }
        }
    }


    private void calculateGST() {
//        String baseAmountStr = priceEdt.getText().toString();
        String[] gstRateArray = gstRateInput.split("%");
        String gstRateStr = gstRateArray[0];

        String mQuantity = qunatityEdt.getText().toString();
        String mPrice = priceEdt.getText().toString();

        if (!mQuantity.isEmpty() && (!mPrice.isEmpty())) {
            int inqty = Integer.parseInt(mQuantity);
            int inprice = Integer.parseInt(mPrice);
            int totalamt = inqty * inprice;
            String mTotalprice = String.valueOf(totalamt);
            qty_Ammount.setText("Total Qty Ammount:"+ mTotalprice);
        }else{
            qty_Ammount.setText("Total Qty Ammount: "+"0.00");
        }
        String baseAmountStr = qty_Ammount.getText().toString();

        String[] gstAmtArray = baseAmountStr.split(":");
        String gstAmtStr = gstAmtArray[1];


        if (!gstAmtStr.isEmpty() && !gstRateStr.isEmpty()) {
            double baseAmount = Double.parseDouble(gstAmtStr);
            double gstRate = Double.parseDouble(gstRateStr);

            double gstAmount = baseAmount * (gstRate / 100);
            double totalAmount = baseAmount + gstAmount;

//            totalAmmountEdt.setText(String.format("GST Amount: %.2f", gstAmount));
            totalAmmountEdt.setText(String.format("Total Amount: %.2f", totalAmount));
        }


    }

    @SuppressLint("Range")
    private void setProductDetails(String productName) {
        // Retrieve product details from the database based on the product name
        try {
            Cursor cursor = dbHandler.getSugestStock(productName);
            if (cursor != null && cursor.moveToFirst()) {
                // Assuming your cursor has columns: product_name, product_description, quantity, price
                getproductId = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("productdiscription"));
                getdbquantity = cursor.getString(cursor.getColumnIndex("quantity"));
                @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));
//                @SuppressLint("Range") String gst = cursor.getString(cursor.getColumnIndex("price"));

                // Set the EditText fields with the retrieved details
                productNameEdt.setText(productName);
                productDescpritonEdt.setText(description);
//                qunatityEdt.setText(quantity);
                priceEdt.setText(price);

                // Recalculate GST based on the retrieved price
//                calculateGST();
            }

            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}