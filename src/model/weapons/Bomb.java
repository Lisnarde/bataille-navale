package model.weapons;

import model.Cell;
import model.Grid;
import model.PlaceableTypes;
import model.traps.Trap;

public class Bomb extends model.weapons.Weapon{
    @Override
    public boolean execShoot(Grid grid, Cell cell) {
        int hitCells = 0;

        hitCells += applyImpact(grid, cell);
        hitCells += applyImpact(grid, new Cell(cell.getX(), cell.getY() - 1));
        hitCells += applyImpact(grid, new Cell(cell.getX(), cell.getY() + 1));
        hitCells += applyImpact(grid, new Cell(cell.getX() - 1, cell.getY()));
        hitCells += applyImpact(grid, new Cell(cell.getX() + 1, cell.getY()));

        return hitCells > 0;


        /*
        for (int x=cell.getX()-1; x<cell.getX()+2; x++) {
            for (int y=cell.getY()-1; y<cell.getY()+2; y++) {
                Cell newCell = new Cell(x,y);
                if (grid.isInGrid(newCell) && !grid.isOccupiedBy(newCell, PlaceableTypes.ISLANDPART)) {
                    hitCells++;
                    grid.placeImpact(newCell);
                    boolean hit = grid.isOccupiedBy(newCell, PlaceableTypes.SHIP);
                    grid.notifyObserversShoot(x, y, hit);
                    if (grid.isOccupiedBy(newCell, PlaceableTypes.TRAP)) {
                        grid.notifyObserversTrapActivated(x, y, ((Trap)grid.getObjectByPosition(cell)).getTrapType());
                    }
                }
            }
        }
        return hitCells > 0;
        */
    }

    private int applyImpact(Grid grid, Cell c) {
        if (!grid.isInGrid(c) || grid.isOccupiedBy(c, PlaceableTypes.ISLANDPART)) {
            return 0;
        }

        grid.placeImpact(c);

        boolean hit = grid.isOccupiedBy(c, PlaceableTypes.SHIP);
        grid.notifyObserversShoot(c.getX(), c.getY(), hit);

        if (grid.isOccupiedBy(c, PlaceableTypes.TRAP)) {
            Trap trap = (Trap) grid.getObjectByPosition(c);
            grid.notifyObserversTrapActivated(c.getX(), c.getY(), trap.getTrapType());
        }

        return 1;
    }


    @Override
    public WeaponTypes getWeaponType() {
        return WeaponTypes.BOMB;
    }
}
