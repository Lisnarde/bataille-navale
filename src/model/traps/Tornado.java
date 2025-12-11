package model.traps;

import model.Cell;

public class Tornado extends model.traps.Trap{
    public Tornado(Cell cell){
        super(cell);
    }
    public Tornado() {
        this(new Cell(-1,-1));
    }
}
