package model;

public class Cell {
    private int _x;
    private int _y;

    public Cell(int x, int y) {
        _x = x;
        _y = y;
    }
    public Cell(Cell cell) {
        this(cell.getX(), cell.getY());
    }

    public int getX() {
        return _x;
    }
    public int getY() {
        return _y;
    }

    public void setX(int x) {
        this._x = x;
    }
    public void setY(int y) {
        this._y = y;
    }

    public boolean equals(Cell other) {
        return getX() == other.getX() && getY() == other.getY();
    }
}
