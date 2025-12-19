package model.traps;

import model.Cell;
import model.Grid;
import model.Player;
import model.weapons.Weapon;

public class Tornado extends model.traps.Trap{
    public Tornado(Cell cell){
        super(cell);
    }
    public Tornado() {
        this(new Cell(-1,-1));
    }

    @Override
    public TrapTypes getTrapType() {
        return TrapTypes.TORNADO;
    }

    @Override
    public boolean execTrap(Grid attackGrid, Grid reveiveGrid, Cell cell, Weapon weapon) {
        return false;
    }
}
