package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GoDataView extends LinearLayout {

    public TextView q_num = null;
    public TextView level = null;

    public GoDataView(Context context, GoData goData) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        q_num = new TextView(context);
        q_num.setTextSize(32);
        q_num.setText("Q" + goData.getId());
        q_num.setTextColor(Color.BLACK);
        q_num.setBackgroundColor(Color.GREEN);
        this.addView(q_num);
        level = new TextView(context);
        level.setTextSize(24);
        level.setText(goData.getLevel());
        level.setTextColor(Color.BLACK);
        this.addView(level);
    }

    @Override
    public String toString(){
        return q_num.getText() + "\n" + level.getText();
    }
}
