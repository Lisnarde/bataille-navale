package model;

public class Ship extends Placeable {
    private Rotation _rotation;
    private int _length;
    private Boolean[] _hitParts;

    public Ship(Cell cell, Rotation rotation, int length) {
        _cell = cell;
        _rotation = rotation;
        _length = length;
        _hitParts = new Boolean[_length];
        for (int i=0; i<_length; i++) {
            _hitParts[i] = false;
        }
    }

    public Rotation getRotation() {
        return _rotation;
    }
    public int getLength() {
        return _length;
    }
}
