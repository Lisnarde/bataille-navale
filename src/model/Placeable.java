package model;

import java.util.List;

public abstract class Placeable{
    private PlaceableTypes _type;



    public List<Cell> getCells() {
        return null;
    }

    public PlaceableTypes getType() {
        return _type;
    }
}
