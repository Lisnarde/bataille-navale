package model.weapons;

import model.Cell;
import model.Grid;
import model.PlaceableTypes;

public class Bomb extends model.weapons.Weapon{
    @Override
    public boolean execShoot(Grid grid, Cell cell) {
        int hitCells = 0;
        for (int x=cell.getX()-1; x<cell.getX()+2; x++) {
            for (int y=cell.getY()-1; y<cell.getY()+2; y++) {
                Cell newCell = new Cell(x,y);
                if (grid.isInGrid(newCell) && !grid.isOccupiedBy(newCell, PlaceableTypes.ISLANDPART)) {
                    hitCells++;
                    grid.placeImpact(newCell);
                    boolean hit = grid.isOccupiedBy(newCell, PlaceableTypes.SHIP);
                    grid.notifyObserversShoot(x, y, hit);
                    if (grid.isOccupiedBy(newCell, PlaceableTypes.TRAP)) {
                        grid.notifyObserversTrapActivated(x, y);
                    }
                }
            }
        }
        return hitCells > 0;
    }
}
