package com.example.oubeika.tsumegonomori;

import android.provider.BaseColumns;

public final class GoDataContract {

    public GoDataContract() {}

    public static abstract class GoData implements BaseColumns {
        public static final String TABLE_NAME = "goData";
        public static final String COL_ID = "_id";
        public static final String COL_NUMBER = "number";
        public static final String COL_LEVEL = "level";
        public static final String COL_GODATA_P = "goDataP";
        public static final String COL_GODATA_A = "goDataA";
    }
}
