package model;

import java.util.List;

public class Ship implements Placeable {
    private List<Cell> _positions;

    public Ship(List<Cell> cells) {
        _positions = cells;
    }

    @Override
    public int getSize() {
        return _positions.size();
    }
    @Override
    public Cell getCell(int index) {
        return _positions.get(index);
    }
    @Override
    public boolean hasThisPosition(Cell cell) {
        for (Cell c : _positions) {
            if (c.equals(cell)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public PlaceableTypes getType() {return PlaceableTypes.SHIP;}
}
