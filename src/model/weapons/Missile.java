package model.weapons;

import model.Cell;
import model.Grid;
import model.PlaceableTypes;

public class Missile extends model.weapons.Weapon{
    @Override
    public void execShoot(Grid grid, Cell cell) {
        grid.placeImpact(cell);
        boolean hit = grid.isOccupiedBy(cell, PlaceableTypes.SHIP);
        grid.notifyObserversShoot(cell.getX(), cell.getY(), hit);
        if (grid.isOccupiedBy(cell,PlaceableTypes.TRAP)) {
            grid.notifyObserversTrapActivated(cell.getX(), cell.getY());
        }
    }
}
