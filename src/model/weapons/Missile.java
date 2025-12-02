package model.weapons;

import model.Cell;
import model.Grid;

public class Missile extends model.weapons.Weapon{
    @Override
    public void execShoot(Grid grid, Cell cell) {
        grid.placeImpact(cell);
    }
}
