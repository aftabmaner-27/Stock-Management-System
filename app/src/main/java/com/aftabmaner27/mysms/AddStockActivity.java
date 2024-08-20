package com.aftabmaner27.mysms;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.aftabmaner27.mysms.Models.VendorModel;

import java.util.ArrayList;
import java.util.List;

public class AddStockActivity extends AppCompatActivity {

    private EditText productNameEdt, productDescpritonEdt, priceEdt, qunatityEdt;
    private TextView btnAddStock, btnUpdateStock, qty_Ammount, totalAmmountEdt;
    private Spinner spnGSTList,spnVendorList,navegitespn_gst;
    private String gstRateInput = "0";
    private Switch btnSwtMinusGST;
    private DBHandler dbHandler;
    ArrayAdapter<String> vendoradapter ;
    private ToggleButton toggleButton;

    private String[] mArrSpnGSTList = {"0%", "5%", "12%", "18%", "28%"};

    private String[] mArrSpnVendorList = {"vendor1", "vendor2", "vendor3", "vendor4"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        getSupportActionBar().hide();


        dbHandler = new DBHandler(AddStockActivity.this);
        toggleButton = findViewById(R.id.toggleButton);
        btnSwtMinusGST = findViewById(R.id.btnSwtMinusGST);
        productNameEdt = findViewById(R.id.Product_Name);
        productDescpritonEdt = findViewById(R.id.Product_Description);
        qunatityEdt = findViewById(R.id.Qunatity);
        priceEdt = findViewById(R.id.Price);
        totalAmmountEdt = findViewById(R.id.Total_Ammount);
        qty_Ammount = findViewById(R.id.qty_Ammount);

        btnAddStock = findViewById(R.id.btn_Save);
        btnUpdateStock = findViewById(R.id.btn_Update);

        spnGSTList = findViewById(R.id.spn_gst);
        navegitespn_gst = findViewById(R.id.navegitespn_gst);
        spnVendorList = findViewById(R.id.spn_vendors);





        // Get data from the database
        ArrayList<String> namesList = dbHandler.getAllVendorName();

        // Create an ArrayAdapter using the string array and a default spinner layout



//        if(namesList.isEmpty()){
//            Toast.makeText(this, "Please add vendor 1st...", Toast.LENGTH_SHORT).show();
//        }else{
             vendoradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namesList);
//        }








        // Specify the layout to use when the list of choices appears
        vendoradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spnVendorList.setAdapter(vendoradapter);


        spnVendorList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        // Set an OnClickListener to handle toggle changes
      /*  toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                Toast.makeText(AddStockActivity.this, "Toggle ON", Toast.LENGTH_SHORT).show();
            } else {
                // The toggle is disabled
                Toast.makeText(AddStockActivity.this, "Toggle OFF", Toast.LENGTH_SHORT).show();
            }
        });*/



        btnSwtMinusGST.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked) {
                        btnSwtMinusGST.setChecked(true);
                        navegitespn_gst.setVisibility(View.VISIBLE);
                        spnGSTList.setVisibility(View.GONE);
                    } else {
                        btnSwtMinusGST.setChecked(false);
                        navegitespn_gst.setVisibility(View.GONE);
                        spnGSTList.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });







        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mArrSpnGSTList);

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








        btnAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValidateEditText();
            }
        });


    }

    private List<VendorModel> getVendorsFromDatabase() {
        List<VendorModel> items = new ArrayList<>();
        Cursor cursor = dbHandler.getAllVendor();

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

    private void getValidateEditText() {
        String mProductName = productNameEdt.getText().toString();
        String mProductDescription = productDescpritonEdt.getText().toString();
        String mQuantity = qunatityEdt.getText().toString();
        String mPrice = priceEdt.getText().toString();
        String mtotalAmmount = totalAmmountEdt.getText().toString();
        String[] TotalAmtArray = mtotalAmmount.split(":");
        String TltAmtStr = TotalAmtArray[1];


        if (mProductName.isEmpty()) {
            productNameEdt.setError("Product Name must not be empty");
//            productNameEdt.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_error));
//            productNameEdt.setHint("Product Name must not be empty");
//            productNameEdt.setHintTextColor(ContextCompat.getColor(this, R.color.sms_color_required_blue));

        } else if (mProductDescription.isEmpty()) {
            productDescpritonEdt.setError("Product Description must not be empty");
//            productDescpritonEdt.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_error));

        } else if (mQuantity.isEmpty()) {
            qunatityEdt.setError("Quantity must not be empty");
//            productDescpritonEdt.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_error));

        } else if (mPrice.isEmpty()) {
            priceEdt.setError("Price must not be empty");
//            productDescpritonEdt.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_error));
        } else {

            dbHandler.addNewStock(mProductName, mProductDescription, mQuantity, mPrice, gstRateInput, TltAmtStr);

            Toast.makeText(AddStockActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
            productNameEdt.setText("");
            productDescpritonEdt.setText("");
            qunatityEdt.setText("");
            priceEdt.setText("");

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
            totalAmmountEdt.setText(String.format("GST Total Amount: %.2f", totalAmount));


        }
    }
}