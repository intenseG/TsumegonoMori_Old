package com.example.oubeika.tsumegonomori;

class Relay {

    private GoData goData;

    Relay(GoData zahyoData){
        goData = zahyoData;
    }

    void setZahyo(){
        goData.setColP(goData.getColP());
        goData.setRowP(goData.getRowP());
        goData.setStoneColorP(goData.getStoneColorP());
        goData.setColA(goData.getColA());
        goData.setRowA(goData.getRowA());
        goData.setStoneColorA(goData.getStoneColorA());
    }
}