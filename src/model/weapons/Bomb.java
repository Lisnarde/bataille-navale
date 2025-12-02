package model.weapons;

import model.Cell;
import model.Grid;

public class Bomb extends model.weapons.Weapon{
    @Override
    public void execShoot(Grid grid, Cell cell) {
        grid.placeImpact(cell);
    }
}
