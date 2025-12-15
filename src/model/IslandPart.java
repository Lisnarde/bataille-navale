package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IslandPart implements Placeable{
    private Cell _position;
    private Placeable _placedObject;

    public IslandPart(Cell cell) {
        _position = new Cell(cell);
        _placedObject = null;
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
        return PlaceableTypes.ISLANDPART;
    }


    public Boolean IsThereAnObject() {
        return _placedObject != null;
    }

    public Placeable getPlacedObject() {
        return _placedObject;
    }

    public void setPlacedObject(Placeable placeable) {
        _placedObject = placeable;
    }

}
