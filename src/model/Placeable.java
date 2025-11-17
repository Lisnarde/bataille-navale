package model;

public abstract class Placeable{
    protected Cell _cell;
    public Cell getPosition(){
        return _cell;
    }

    public void setPosition(Cell cell){
        _cell.setX(cell.getX());
        _cell.setY(cell.getY());
    }
}
