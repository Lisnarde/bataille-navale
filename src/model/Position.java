package model;

public class Position {
    private int _x;
    private int _y;

    public Position(int x, int y) {
        _x = x;
        _y = y;
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
}
