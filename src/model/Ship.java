package model;

import java.util.List;

public class Ship extends Placeable {
    private List<Cell> _positions;

    public Ship(List<Cell> cells) {
        _positions = cells;
    }

    @Override
    public List<Cell> getCells() {
        return _positions;
    }

    public int getLength() {
        return _positions.size();
    }

    public boolean isTheShipHere(Cell position) {
        for (Cell cell : _positions) {
            if (cell.equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHitPart(Cell position) {
        for (Cell cell : _positions) {
            if (cell.equals(position) && cell.isHit()) {
                return true;
            }
        }
        return false;
    }
}
