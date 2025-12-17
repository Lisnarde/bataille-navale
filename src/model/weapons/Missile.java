package model.weapons;

import model.Cell;
import model.Grid;
import model.PlaceableTypes;
import model.traps.Trap;
import model.traps.TrapTypes;

public class Missile extends model.weapons.Weapon{
    @Override
    public boolean execShoot(Grid grid, Cell cell) {
        grid.placeImpact(cell);
        if (grid.isOccupiedBy(cell,PlaceableTypes.ISLANDPART)) {return false;}
        boolean hit = grid.isOccupiedBy(cell, PlaceableTypes.SHIP);
        grid.notifyObserversShoot(cell.getX(), cell.getY(), hit);

        if (grid.isOccupiedBy(cell,PlaceableTypes.TRAP)) {
            grid.notifyObserversTrapActivated(cell.getX(), cell.getY(), ((Trap)grid.getObjectByPosition(cell)).getTrapType());
        }
        return true;
    }

    @Override
    public WeaponTypes getWeaponType() {
        return WeaponTypes.MISSILE;
    }
}
