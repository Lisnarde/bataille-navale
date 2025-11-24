package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IslandPart extends Placeable{
    private Cell _position;

    private Placeable _placedObject;

    public IslandPart(Cell cell) {
        _position = new Cell(cell);
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

    @Override
    public List<Cell> getCells() {
        return new ArrayList<>(Arrays.asList(_position));
    }
}
