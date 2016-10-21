package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GoDataAdapter extends ArrayAdapter<GoData> {

    private LayoutInflater mLayoutInflater;
    private List<GoData> data = null;

    public GoDataAdapter(Context context){
        super(context, 0);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGoData(List<GoData> details) {
        this.data = details;
    }

/*    @Override
    public int getCount() {
        if (infoList == null) {
            return 0;
        }
        return infoList.size();
    }

    @Override
    public GoDataInfo getItem(int position) {
        if (infoList == null || infoList.get(position) == null) {
            return null;
        }
        return infoList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }*/

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){
            convertView = mLayoutInflater.inflate(
                    R.layout.list_item,
                    parent,
                    false
            );
            holder = new ViewHolder();
            holder.q_num = (TextView) convertView.findViewById(R.id.q_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GoData godata = getItem(position);

        if (godata != null) {
            holder.q_num.setText(godata.getQNum());
        }

        return convertView;
    }
}

class ViewHolder {
    TextView q_num;
}