package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

class ListAdapter extends ArrayAdapter<GoData> {

    private LayoutInflater inflater;
    private TextView q_num;
    private TextView level;
    private List<GoData> items = null;

    public ListAdapter(Context context, List<GoData> items) {
        super(context, 0, items);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        //データを取得
        GoData goData = this.getItem(pos);

        if (goData != null) {
            q_num = (TextView) convertView.findViewById(R.id.q_num);
            q_num.setText("Stage" + goData.getNumber());
            level = (TextView) convertView.findViewById(R.id.level);
            level.setText(goData.getLevel());
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public GoData getItem(int pos) {
        return items.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }
}