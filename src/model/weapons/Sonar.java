package model.weapons;

import model.Cell;
import model.Grid;

public class Sonar extends model.weapons.Weapon{
    @Override
    public boolean execShoot(Grid grid, Cell cell) {
        grid.placeImpact(cell);
        return false;
    }

    @Override
    public WeaponTypes getWeaponType() {
        return WeaponTypes.SONAR;
    }
}
