package model.weapons;

import model.Cell;
import model.Grid;
import model.PlaceableTypes;
import model.Ship;
import model.traps.Trap;
import model.traps.TrapTypes;

public class Missile extends model.weapons.Weapon{
    @Override
    public boolean execShoot(Grid grid, Cell cell) {
        if (grid.isOccupiedBy(cell,PlaceableTypes.ISLANDPART)) {return false;}
        grid.placeImpact(cell);
        boolean hit = grid.isOccupiedBy(cell, PlaceableTypes.SHIP);
        grid.notifyObserversShoot(cell.getX(), cell.getY(), hit);

        if (grid.isOccupiedBy(cell,PlaceableTypes.TRAP)) {
            grid.notifyObserversTrapActivated(cell.getX(), cell.getY(), ((Trap)grid.getObjectByPosition(cell)).getTrapType());
        }
        if (hit) {
            Ship ship = (Ship) grid.getObjectByPosition(cell);
            if (grid.isTheShipDrowned(ship)) {
                for (int i = 0; i < ship.getSize(); i++) {
                    Cell c = ship.getCell(i);
                    grid.notifyObserversShipCellDrowned(c.getX(), c.getY());
                }
                if (grid.allShipsDead()) {
                    grid.notifyObserversNoMoreShip();
                }
            }
        }
        return true;
    }

    @Override
    public WeaponTypes getWeaponType() {
        return WeaponTypes.MISSILE;
    }
}
