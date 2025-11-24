package model;

public interface Placeable{

    public int getSize();
    public Cell getCell(int index);
    public boolean hasThisPosition(Cell cell);
    public PlaceableTypes getType();
}
