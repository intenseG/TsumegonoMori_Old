package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GoDataAdapter extends ArrayAdapter<GoData> {

    private LayoutInflater mLayoutInflater;
    private List<GoData> goData = null;

    public GoDataAdapter(Context context){
        super(context, 0);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGoData(List<GoData> details) {
        this.goData = details;
    }

    @Override
    public int getCount() {
        if (goData == null) {
            return 0;
        }
        return goData.size();
    }

    @Override
    public GoData getItem(int position) {
        if (goData == null || goData.get(position) == null) {
            return null;
        }
        return goData.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
        }

        GoData data = goData.get(position);

        if(data != null) {
            TextView q_num_level = (TextView) convertView.findViewById(R.id.q_num);
            TextView level = (TextView) convertView.findViewById(R.id.q_level);

            q_num_level.setText(goData.get(position).getQNum());
            level.setText(goData.get(position).getLevel());
        }

        return convertView;
    }
}