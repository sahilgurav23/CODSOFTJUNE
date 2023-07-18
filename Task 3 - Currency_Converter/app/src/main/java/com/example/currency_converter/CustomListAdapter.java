package com.example.currency_converter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private ArrayList<String> dataList;
    private LayoutInflater inflater;

    public CustomListAdapter(Context context, ArrayList<String> dataList) {
        super(context, 0, dataList);
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, parent, false);

            holder = new ViewHolder();
            holder.textViewListItem = convertView.findViewById(R.id.values);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String itemData = dataList.get(position);
        holder.textViewListItem.setText(itemData);

        return convertView;
    }

    static class ViewHolder {
        TextView textViewListItem;
    }
}
