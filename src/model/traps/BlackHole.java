package model.traps;

import model.Cell;

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
}
