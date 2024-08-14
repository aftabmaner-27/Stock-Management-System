package com.aftabmaner27.mysms;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "smsdb";
    private static final int DB_VERSION = 5;
    private static final String TABLE_NAME = "tblstocks";
    public static final String ID_COL = "id";
    public static final String PRODUCT_NAME_COL = "productname";
    public static final String PRODUCT_DESCRIPTION_COL = "productdiscription";
    public static final String QUANTITY_COL = "quantity";
    public static final String PRICE_COL = "price";
    public static final String GST_COL = "gst";
    public static final String TOTAL_AMMOUNT_COL = "totalamount";


    private static final String VENDOR_TABLE_NAME = "tblvendor";
    public static final String VENDOR_ID_COL = "id";
    public static final String VENDOR_NAME_COL = "vendorname";
    public static final String VENDOR_ADDRESS_COL = "vendoraddress";
    public static final String VENDOR_GSTNO_COL = "vendorgstno";
    public static final String VENDOR_CONTACTNO_COL = "vendorcontactno";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_NAME_COL + " TEXT,"
                + PRODUCT_DESCRIPTION_COL + " TEXT,"
                + QUANTITY_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + GST_COL + " TEXT,"
                + TOTAL_AMMOUNT_COL + " TEXT)";

        db.execSQL(query);

        String mvendor = "CREATE TABLE " + VENDOR_TABLE_NAME + " ("
                + VENDOR_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VENDOR_NAME_COL + " TEXT,"
                + VENDOR_ADDRESS_COL + " TEXT,"
                + VENDOR_GSTNO_COL + " TEXT,"
                + VENDOR_CONTACTNO_COL + " TEXT)";
        db.execSQL(mvendor);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VENDOR_TABLE_NAME);
        onCreate(db);
    }

    public void addNewStock(String productname, String productdiscription, String quantity, String price, String gst,String totalamount) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PRODUCT_NAME_COL, productname);
        values.put(PRODUCT_DESCRIPTION_COL, productdiscription);
        values.put(QUANTITY_COL, quantity);
        values.put(PRICE_COL, price);
        values.put(GST_COL, gst);
        values.put(TOTAL_AMMOUNT_COL, totalamount);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public boolean updateStock(Integer id ,String productname, String productdiscription, String quantity, String price, String gst,String totalamount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME_COL, productname);
        values.put(PRODUCT_DESCRIPTION_COL, productdiscription);
        values.put(QUANTITY_COL, quantity);
        values.put(PRICE_COL, price);
        values.put(GST_COL, gst);
        values.put(TOTAL_AMMOUNT_COL, totalamount);
        db.update(TABLE_NAME, values, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }



    @SuppressLint("Range")
    public List<String> getSuggestions(String query) {
        List<String> suggestions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + PRODUCT_NAME_COL + " FROM " + TABLE_NAME + " WHERE " + PRODUCT_NAME_COL + " LIKE ?", new String[]{query + "%"});
        if (cursor.moveToFirst()) {
            do {
                suggestions.add(cursor.getString(cursor.getColumnIndex(PRODUCT_NAME_COL)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return suggestions;
    }



    public Cursor getAllStock() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getSugestStock(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + PRODUCT_NAME_COL + " LIKE ?", new String[]{productName + "%"});
        return cursor;
    }


    public void addNewVendor(String vendorname, String vendoraddress, String vendorgstno, String vendorcontactno) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VENDOR_NAME_COL, vendorname);
        values.put(VENDOR_ADDRESS_COL, vendoraddress);
        values.put(VENDOR_GSTNO_COL, vendorgstno);
        values.put(VENDOR_CONTACTNO_COL, vendorcontactno);

        db.insert(VENDOR_TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllVendor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + VENDOR_TABLE_NAME, null);
    }


    // Method to retrieve data from the database
    public ArrayList<String> getAllVendorName() {
        ArrayList<String> namesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + VENDOR_NAME_COL + " FROM " + VENDOR_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                namesList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return namesList;
    }
}
