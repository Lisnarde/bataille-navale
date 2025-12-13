package model.weapons;

import model.Cell;
import model.Grid;
import model.PlaceableTypes;

public class Bomb extends model.weapons.Weapon{
    @Override
    public void execShoot(Grid grid, Cell cell) {
        for (int x=cell.getX()-1; x<cell.getX()+2; x++) {
            for (int y=cell.getY()-1; y<cell.getY()+2; y++) {
                Cell newCell = new Cell(x,y);
                if (grid.isInGrid(newCell)) {
                    grid.placeImpact(newCell);
                    boolean hit = grid.isOccupiedBy(newCell, PlaceableTypes.SHIP);
                    grid.notifyObserversShoot(x, y, hit);
                    if (grid.isOccupiedBy(newCell, PlaceableTypes.TRAP)) {
                        grid.notifyObserversTrapActivated(x, y);
                    }
                }
            }
        }
    }
}
