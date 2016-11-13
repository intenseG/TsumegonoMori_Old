package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GoDataAdapter extends BaseAdapter {

    private Context context;
    private List<GoData> goDataList = new ArrayList<>();

    private class ViewHolder {
        TextView q_num;
        TextView q_level;
    }

    public GoDataAdapter(Context context, List<GoData> goDataList){
        this.context = context;
        this.goDataList = goDataList;
    }

    @Override
    public int getCount() {
        return goDataList.size();
    }

    @Override
    public Object getItem(int pos) {
        return goDataList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        //データを取得
        GoData goData = goDataList.get(pos);

        if (view == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, parent, false);

            TextView q_num = (TextView) view.findViewById(R.id.q_num);
            TextView q_level = (TextView) view.findViewById(R.id.level);

            holder = new ViewHolder();
            holder.q_num = q_num;
            holder.q_level = q_level;
            view.setTag(holder);
        } else {
            // 初めて表示されるときにつけておいたtagを元にviewを取得する
            holder = (ViewHolder) view.getTag();
        }
        holder.q_num.setText(goData.getId());
        holder.q_level.setText(goData.getQ_level());

        return view;
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