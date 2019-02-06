package com.mygdx.game;

public class Tile {

    private int gridX, gridY, value;
    private boolean merged;

    public Tile(int x, int y, int num) {
        gridX = x;
        gridY = y;
        value = num;
        merged = false;
    }
    //------------------------------------------

    public int getValue() {
        return value;
    }

    public void setValue(int num) {
        value = num;
    }

    //------------------------------------------

    public boolean getMerged(){
        return merged;
    }

    public void setMerged(){
        merged = true;
    }

    public void unMerge(){
        merged = false;
    }

    //------------------------------------------

    public boolean canMerge(Tile otherTile){
        return value == otherTile.getValue() && merged == false && otherTile.getMerged() == false;
    }

}