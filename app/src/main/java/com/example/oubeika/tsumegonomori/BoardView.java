package com.example.oubeika.tsumegonomori;

import android.content.Context;
import android.view.View;

abstract public class BoardView extends View {

    public BoardView(Context context) {
        super(context);
    }

    public abstract void initStone(int colP, int rowP, int stoneColor);
}
