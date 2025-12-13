package model.traps;

import model.Cell;
import model.PlaceableTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class Trap implements model.Placeable{

    private Cell _position;

    public Trap(Cell cell){
        _position = cell;
    }

    @Override
    public int getSize() {return 1;}
    @Override
    public Cell getCell(int index) {return _position;}
    @Override
    public boolean hasThisPosition(Cell cell) {
        return _position.equals(cell);
    }
    @Override
    public PlaceableTypes getType() {
        return PlaceableTypes.TRAP;
    }

    public void setPosition(Cell cell) {
        _position = new Cell(cell);
    }
}
