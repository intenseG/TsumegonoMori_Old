package com.example.oubeika.tsumegonomori;

import java.util.ArrayList;

public class Board {

    public static final int BOARD_SIZE = 19;
    private int rawBoard[][] = new int[BOARD_SIZE + 2][BOARD_SIZE + 2];

    private int currentTurn;
    private int currentColor;

    public Board() {
        initBoard();
        currentTurn = 0;
        currentColor = 0;
    }

    private void initBoard() {

        for (int x = 1; x <= BOARD_SIZE; x++) {
            for (int y = 1; y <= BOARD_SIZE; y++) {
                rawBoard[x][y] = Disc.EMPTY;
            }
        }
        for (int y = 0; y < BOARD_SIZE + 2; y++) {
            rawBoard[0][y] = Disc.WALL;
            rawBoard[BOARD_SIZE + 1][y] = Disc.WALL;
        }
        for (int x = 0; x < BOARD_SIZE + 2; x++) {
            rawBoard[0][x] = Disc.WALL;
            rawBoard[BOARD_SIZE + 1][x] = Disc.WALL;
        }
    }

    public void initStone(int color, int col, int row) {

        if (isEmpty(col, row)) {
            rawBoard[col][row] = color;
        }
    }

    private boolean isEmpty(int col, int row) {

        return rawBoard[col][row] == Disc.EMPTY;
    }

    public void putStone(Point point, int[][][] movableDir) {

        int x, y;
        int dir = movableDir[currentTurn][point.x][point.y];

        ArrayList<Disc> update = new ArrayList<>();

        rawBoard[point.x][point.y] = currentColor;
        update.add(new Disc(point.x, point.y, currentColor));
    }

    public int[][] getRawBoard() {
        return rawBoard;
    }
}
