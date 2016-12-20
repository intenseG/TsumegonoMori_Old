package com.example.oubeika.tsumegonomori;

class ColorStorage {

    private int[] data = new int[3];

    public int get(int color) {
        return data[color + 1];
    }

    public void set(int color, int value) {
        data[color + 1] = value;
    }
}
