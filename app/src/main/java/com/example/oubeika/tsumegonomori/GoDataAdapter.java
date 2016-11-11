package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GoDataAdapter extends ArrayAdapter<GoData> {

    private LayoutInflater layoutInflater;
    private List<GoData> goDataList = new ArrayList<GoData>();

    public GoDataAdapter(Context context){
        super(context, 0);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

/*    public void setGoDataList(List<GoData> details) {
        this.dataList = details;
    }*/

/*
    @Override
    public int getCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @Override
    public GoData getItem(int pos) {
        if (data == null || data.get(pos) == null) {
            return null;
        }
        return data.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
*/

   /* @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = layoutInflater.inflate(
                    R.layout.list_item,
                    parent,
                    false
            );
        }

        GoData godata = dataList.get(pos);

        ((TextView) convertView.findViewById(R.id.q_num))
                .setText("Q" + godata.getQNum());
        ((TextView) convertView.findViewById(R.id.level))
                .setText(godata.getLevel());

        return convertView;
    }*/
}