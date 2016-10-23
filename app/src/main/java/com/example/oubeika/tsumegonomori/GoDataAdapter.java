package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GoDataAdapter extends ArrayAdapter<GoData> {

    private LayoutInflater layoutInflater;
    private List<GoData> data = null;

    public GoDataAdapter(Context context){
        super(context, 0);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGoData(List<GoData> details) {
        this.data = details;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public GoData getItem(int pos) {
        return data.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return data.get(pos).getId();
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = layoutInflater.inflate(
                    R.layout.list_item,
                    parent,
                    false
            );
        }

        GoData godata = data.get(pos);

        ((TextView) convertView.findViewById(R.id.q_num))
                .setText(godata.getQNum());
        ((TextView) convertView.findViewById(R.id.level))
                .setText(godata.getLevel());

        return convertView;
    }
}