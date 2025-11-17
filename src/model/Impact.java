package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Impact extends Placeable{
    private Cell _position;
    public Impact(Cell cell) {
        _position = new Cell(cell);
    }

    @Override
    public List<Cell> getCells() {
        return new ArrayList<>(Arrays.asList(_position));
    }
}
