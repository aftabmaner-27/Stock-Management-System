package com.aftabmaner27.mysms;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class AccountActivity extends AppCompatActivity {

    LinearLayout viewAddVender,viewAddCustomer;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().hide();

        dbHandler=new DBHandler(this);

        viewAddVender=findViewById(R.id.view_AddVender);
        viewAddCustomer=findViewById(R.id.view_Customer);

        viewAddVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpDialogAddVendor();
            }
        });

        viewAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpDialogAddCustomer();
            }
        });


    }

    private void PopUpDialogAddCustomer() {

        // Create a custom layout inflater
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.customer_master_dialog, null);

        // Build the dialog using AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();

        // Initialize the views in the custom layout
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText Customer_Name = dialogLayout.findViewById(R.id.Customer_Name);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText Customer_Address = dialogLayout.findViewById(R.id.Customer_Address);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText Customer_GSTno = dialogLayout.findViewById(R.id.Customer_GSTno);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText CustomerContactno = dialogLayout.findViewById(R.id.CustomerContactno);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView btn_SaveCustomer = dialogLayout.findViewById(R.id.btn_SaveCustomer);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView btnClosePopup = dialogLayout.findViewById(R.id.btnClosePopup);

        // Set button click listener
        btn_SaveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String StrVendor_Name = Customer_Name.getText().toString();
                String StrVendor_Address = Customer_Address.getText().toString();
                String StrVendor_GSTno = Customer_GSTno.getText().toString();
                String StrVendorContactno = CustomerContactno.getText().toString();

                if(StrVendor_Name.isEmpty()&&StrVendor_Address.isEmpty()&&StrVendor_GSTno.isEmpty()&&StrVendorContactno.isEmpty()){
                    Toast.makeText(AccountActivity.this, "Please Enter All filed are Mandatory.", Toast.LENGTH_LONG).show();
                }else{

//                    dbHandler.addNewVendor(StrVendor_Name,StrVendor_Address,StrVendor_GSTno,StrVendorContactno);
                    Toast.makeText(AccountActivity.this, "Customer details Add Successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();


    }

    private void PopUpDialogAddVendor() {
        // Create a custom layout inflater
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.vendor_master_dialog, null);

        // Build the dialog using AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogLayout);
        final AlertDialog dialog = builder.create();

        // Initialize the views in the custom layout
        EditText Vendor_Name = dialogLayout.findViewById(R.id.Vendor_Name);
        EditText Vendor_Address = dialogLayout.findViewById(R.id.Vendor_Address);
        EditText Vendor_GSTno = dialogLayout.findViewById(R.id.Vendor_GSTno);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText VendorContactno = dialogLayout.findViewById(R.id.VendorContactno);
        TextView btn_SaveVendor = dialogLayout.findViewById(R.id.btn_SaveVendor);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView btnClosePopup = dialogLayout.findViewById(R.id.btnClosePopup);

        // Set button click listener
        btn_SaveVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String StrVendor_Name = Vendor_Name.getText().toString();
                String StrVendor_Address = Vendor_Address.getText().toString();
                String StrVendor_GSTno = Vendor_GSTno.getText().toString();
                String StrVendorContactno = VendorContactno.getText().toString();

                if(StrVendor_Name.isEmpty()&&StrVendor_Address.isEmpty()&&StrVendor_GSTno.isEmpty()&&StrVendorContactno.isEmpty()){
                    Toast.makeText(AccountActivity.this, "Please Enter All filed are Mandatory.", Toast.LENGTH_LONG).show();
                }else{

                    dbHandler.addNewVendor(StrVendor_Name,StrVendor_Address,StrVendor_GSTno,StrVendorContactno);
                    Toast.makeText(AccountActivity.this, "Vendor details Add Successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();

    }
}