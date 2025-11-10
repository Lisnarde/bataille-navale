package model;

public abstract class Placeable{
    protected Position _position;
    public Position getPosition(){
        return _position;
    }

    public void setPosition(Position position){
        _position.setX(position.getX());
        _position.setY(position.getY());
    }
}
