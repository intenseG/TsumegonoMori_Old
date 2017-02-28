package com.example.oubeika.tsumegonomori;

public class GoBoard {

    private int[][] board;

    public GoBoard() {

    }

    public void setStone(Point point, int newStatus) {
        board[point.x][point.y] = newStatus;
    }

}
