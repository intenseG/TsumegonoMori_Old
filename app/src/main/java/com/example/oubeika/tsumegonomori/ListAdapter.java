package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

class ListAdapter extends ArrayAdapter<GoData> {

    private LayoutInflater inflater;
    GoData goData;
    //private TextView q_num;
    //private TextView level;
    //private Context context = null;
    //private List<GoData> items = null;

    private class ViewHolder {
        TextView q_num;
        TextView level;
    }

    public ListAdapter(Context context, List<GoData> items) {
        super(context, 0, items);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.context = context;
        //this.items = items;
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.q_num = (TextView) convertView.findViewById(R.id.q_num);
            holder.level = (TextView) convertView.findViewById(R.id.level);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //データを取得
        goData = getItem(pos);

        if (goData != null) {
            holder.q_num.setText("Stage" + goData.getNumber());
            holder.level.setText(goData.getLevel());
        }
        return convertView;

            /*TextView q_num = (TextView) view.findViewById(R.id.q_num);
            TextView level = (TextView) view.findViewById(R.id.level);

            holder = new ViewHolder();
            holder.q_num = q_num;
            holder.level = level;
            view.setTag(holder);

        } else {
            // 初めて表示されるときにつけておいたtagを元にviewを取得する
            holder = (ViewHolder) view.getTag();
        }
        holder.q_num.setText(goData.getId());
        holder.level.setText(goData.getLevel());
*/
    }
}