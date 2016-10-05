package com.example.mormolis.listviewparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class CustomAdapter extends ArrayAdapter<Moria> {
    private static class ViewHolder {
        TextView vhSxoli;
        TextView vhMoria;
    }

    public CustomAdapter(Context context, ArrayList<Moria> alMoria) {
        super(context, R.layout.csv_raw, alMoria);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Moria cmoria = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.csv_raw, parent, false);
            viewHolder.vhSxoli = (TextView) convertView.findViewById(R.id.tv_sxoli);
            viewHolder.vhMoria = (TextView) convertView.findViewById(R.id.tv_moria);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.vhSxoli.setText(cmoria.onomaSxolis);
        viewHolder.vhMoria.setText(String.valueOf(cmoria.moria));
        // Return the completed view to render on screen
        return convertView;
    }
}



