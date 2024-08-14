package com.aftabmaner27.mysms.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.aftabmaner27.mysms.Models.StockModel;
import com.aftabmaner27.mysms.R;

import java.util.List;

public class StockListAdapter extends ArrayAdapter<StockModel> {

    public StockListAdapter(Context context, List<StockModel> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StockModel item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_itemstock, parent, false);
        }

        TextView productId = convertView.findViewById(R.id.product_id);
        TextView itemName = convertView.findViewById(R.id.product_name);
        TextView itemNamedesc = convertView.findViewById(R.id.product_desc);
        TextView qty = convertView.findViewById(R.id.qty);
        TextView price = convertView.findViewById(R.id.price);
        TextView gst = convertView.findViewById(R.id.gst);
        TextView totalamt = convertView.findViewById(R.id.totalamt);

        String getProdId = String.valueOf(item.getId());

        productId.setText(getProdId);
        itemName.setText(item.getProductname());
        itemNamedesc.setText(item.getProductdescription());
        qty.setText(item.getQuantity());
        price.setText(item.getPrice());
         gst.setText(item.getGst());
        totalamt.setText(item.getTotalAmount());

        return convertView;
    }
}
