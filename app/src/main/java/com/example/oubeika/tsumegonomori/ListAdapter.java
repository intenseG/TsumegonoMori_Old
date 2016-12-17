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
    private TextView q_num;
    private TextView level;
    private Context context = null;
    private List<GoData> items = null;

 /*   private class ViewHolder {
        TextView q_num;
        TextView level;
    }*/

    public ListAdapter(Context context, List<GoData> items) {
        super(context, 0, items);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public GoData getItem(int pos) {
        return items.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent) {

        //return new GoDataView(context, items.get(pos));

        //View view = convertView;
        //ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        //データを取得
        final GoData goData = this.getItem(pos);

        if (goData != null) {
            q_num = (TextView) convertView.findViewById(R.id.q_num);
            q_num.setText("Stage" + goData.getId());
            level = (TextView) convertView.findViewById(R.id.level);
            level.setText(goData.getLevel());
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