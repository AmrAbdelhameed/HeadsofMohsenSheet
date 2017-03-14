package com.example.amr.headsofmohsensheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amr on 26/08/2016.
 */

public class custom extends BaseAdapter {
    public Context context;
    public ArrayList<String> texts;
    public ArrayList<String> hints;
    public LayoutInflater layoutInflater;

    public custom(Context context, ArrayList<String> texts, ArrayList<String> hints) {
        this.texts = texts;
        this.hints = hints;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public class Holder {
        public TextView textView;
        public TextView textView2;
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.text1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView.setText("Name : " + texts.get(position));
        holder.textView2.setText("Total Score : " + hints.get(position));
        return convertView;
    }

}