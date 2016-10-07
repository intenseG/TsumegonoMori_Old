package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GoDataAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<GoData> mGoDataArrayList;

    public GoDataAdapter(Context context){
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setmTaskArrayList(ArrayList<GoData> goDataArrayList){
        mGoDataArrayList = goDataArrayList;
    }

    @Override
    public int getCount() {
        return mGoDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGoDataArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_2, null);
        }

        TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
        TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);

        textView1.setText(mGoDataArrayList.get(position).getColP());
        textView2.setText(mGoDataArrayList.get(position).getRowP());

        return convertView;
    }
}
