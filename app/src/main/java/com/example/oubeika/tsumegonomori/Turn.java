package com.example.oubeika.tsumegonomori;

public class Turn {

    private int turn;

    public Turn() {
        turn = GameConst.BLACK;
    }

    public Turn(int turn) {
        this.turn = turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public void changeTurn() {
        turn = 3 - turn;
    }
}
