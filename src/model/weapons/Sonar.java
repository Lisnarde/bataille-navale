package model.weapons;

import model.Cell;
import model.Grid;

public class Sonar extends model.weapons.Weapon{
    @Override
    public void execShoot(Grid grid, Cell cell) {
        grid.placeImpact(cell);
    }
}
