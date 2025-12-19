package model.traps;

import model.Cell;
import model.Grid;
import model.Player;
import model.weapons.Weapon;

public class BlackHole extends model.traps.Trap{

    public BlackHole(Cell cell){
        super(cell);
    }
    public BlackHole() {
        this(new Cell(-1,-1));
    }

    @Override
    public TrapTypes getTrapType() {
        return TrapTypes.BLACKHOLE;
    }

    @Override
    public boolean execTrap(Grid attackGrid, Grid reveiveGrid, Cell cell, Weapon weapon) {
        return attackGrid.shoot(cell, weapon);
    }
}
