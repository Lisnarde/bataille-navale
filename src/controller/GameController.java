package controller;

import model.*;
import model.traps.Trap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {
    Game _model;

    public GameController(Game model) {
        _model = model;
    }


    public void setPlayerName(String name) {
        _model.setPlayerName(name);
    }

    public void setGrid(int size, boolean islandMode) {
        _model.setGrid(size, islandMode);
    }

    public boolean placeTrapOnGrid(int joueur, int indexTrap, int posx, int posy) {
        Trap trap = _model.getTrapInInventory(indexTrap);
        trap.setPosition(new Cell(posx, posy));
        return _model.placeTrapOnGrid(joueur, trap);
    }

    public boolean placeShipOnGrid(int joueur, ShipTypes type, int posx, int posy, Axis axis) {
        int size = 0;
        switch (type) {
            case AircraftCarrier -> size = 5;
            case Cruiser -> size = 4;
            case Destroyer -> size = 4;
            case Submarine -> size = 3;
            case Torpedo -> size = 2;
        }
        List<Cell> position = new ArrayList<>();
        for (int i=0; i<size; i++) {
            position.add( new Cell(posx + (axis == Axis.HORIZONTAL ? i : 0) , posy + (axis == Axis.VERTICAL ? i : 0)));
        }
        Ship ship = new Ship(position);
        return _model.placeShipOnGrid(joueur,ship);
    }

    public boolean setWeapon(int joueur, int weaponIndex) {
        return _model.setWeapon(joueur,weaponIndex);
    }

    public boolean shootOnGrid(int joueur, int posx, int posy) {
        return _model.shootOnGrid(joueur, new Cell(posx, posy));
    }
}
