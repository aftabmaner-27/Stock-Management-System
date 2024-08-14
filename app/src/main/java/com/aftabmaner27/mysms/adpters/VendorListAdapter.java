package com.aftabmaner27.mysms.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aftabmaner27.mysms.Models.StockModel;
import com.aftabmaner27.mysms.Models.VendorModel;
import com.aftabmaner27.mysms.R;

import java.util.List;

public class VendorListAdapter extends ArrayAdapter<VendorModel> {

    public VendorListAdapter(Context context, List<VendorModel> vendorModels) {
        super(context, 0, vendorModels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VendorModel vendorModels = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_itemvendors, parent, false);
        }

        TextView vendorId = convertView.findViewById(R.id.vendor_id);
        TextView vendorName = convertView.findViewById(R.id.vendor_name);
        TextView vendorAddress = convertView.findViewById(R.id.vendor_address);
//        TextView vendorGst = convertView.findViewById(R.id.gst);
        TextView vendorContactNo = convertView.findViewById(R.id.vendor_contact);

        String getProdId = String.valueOf(vendorModels.getId());

        vendorId.setText(getProdId);
        vendorName.setText(vendorModels.getVendorname());
        vendorAddress.setText(vendorModels.getVendoraddress());
//        vendorGst.setText(vendorModels.getVendorGst());
        vendorContactNo.setText(vendorModels.getVendorContactcno());
        return convertView;
    }
}
