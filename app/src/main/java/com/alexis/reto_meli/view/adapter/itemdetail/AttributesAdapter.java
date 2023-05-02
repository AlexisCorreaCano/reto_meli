package com.alexis.reto_meli.view.adapter.itemdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexis.domain.model.Attribute;
import com.alexis.reto_meli.R;


import java.util.ArrayList;

public class AttributesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Attribute> attributeArrayList;

    public AttributesAdapter(Context context, ArrayList<Attribute> attributeArrayList) {
        this.context = context;
        this.attributeArrayList = attributeArrayList;
    }

    @Override
    public int getCount() {
        return attributeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return attributeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.attributes_list_item, null);
        TextView tv_attributes_name = view.findViewById(R.id.tv_attributes_name);
        TextView tv_attributes_value = view.findViewById(R.id.tv_attributes_value);

        tv_attributes_name.setText(attributeArrayList.get(position).name);
        tv_attributes_value.setText(attributeArrayList.get(position).value_name);
        return view;
    }
}
