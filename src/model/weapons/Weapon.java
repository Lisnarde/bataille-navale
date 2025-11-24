package model.weapons;

import model.Cell;
import model.Placeable;
import model.PlaceableTypes;

public abstract class Weapon implements Placeable {
    @Override
    public int getSize() {return 0;}
    @Override
    public Cell getCell(int index) {return null;}
    @Override
    public boolean hasThisPosition(Cell cell) {
        return false;
    }
    @Override
    public PlaceableTypes getType() {
        return PlaceableTypes.WEAPON;
    }
}
