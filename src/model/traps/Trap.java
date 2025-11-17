package model.traps;

import model.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class Trap extends model.Placeable{

    private Cell _position;

    public Trap(Cell cell){
        _position = cell;
    }

    @Override
    public List<Cell> getCells(){
        return new ArrayList<>(Arrays.asList(_position));
    }
}
