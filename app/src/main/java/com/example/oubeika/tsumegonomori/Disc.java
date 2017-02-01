package com.example.oubeika.tsumegonomori;

class Disc extends Point {

    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = -1;
    public static final int WALL = 2;

    private int color;

    public Disc() {
        super(0, 0);
        this.color = EMPTY;
    }

    public Disc(int x, int y, int color) {
        super(x, y);
        this.color = color;
    }
}
