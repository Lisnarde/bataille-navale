package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Impact implements Placeable{
    private Cell _position;
    public Impact(Cell cell) {
        _position = new Cell(cell);
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
        return PlaceableTypes.IMPACT;
    }
}
