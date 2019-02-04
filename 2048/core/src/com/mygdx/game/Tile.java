package com.mygdx.game;

public class Tile {

    private int gridX,gridY,value;
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

    //------------------------------------------

    public void setValue(int num) {
        value = num;
    }

    public int setGridX(int x) {
        return gridX;
    }

    public int setGridY(int y) {
        return gridY;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    //------------------------------------------

    public boolean getMerged(){
        return merged;
    }

    public void merge(){
        merged = true;
    }

    public void unMerge(){
        merged = false;
    }

    //------------------------------------------

    public boolean canMerge(Tile otherTile){
        return value == otherTile.getValue() && merged == false && otherTile.getMerged() == false;
    }

    public int mergeWith(Tile otherTile){
        if (canMerge(otherTile)){
            value *= 2;
            merged = true;
            return value;
        }
        return 1;
    }

}