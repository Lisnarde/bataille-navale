package controller;

import controller.bots.Bot;
import model.*;
import model.traps.Trap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameController {
    private Game _model;

    private Bot _bot;

    public GameController(Game model, Bot bot) {
        _model = model;
        _bot = bot;
    }


    public void setPlayerName(String name) {
        _model.setPlayerName(name);
    }

    public void setGrid(int size, boolean islandMode) {
        _model.setGrid(size, islandMode);
    }

    public boolean setMaxShipsCells(int maxShip) {
        return _model.setMaxShipsCells(maxShip);
    }

    public boolean setNumberPerShip(Map<ShipTypes,Integer> numberPerShip) {
        if (_model.setNumberPerShip(numberPerShip)) {
            _bot.placeShips(_model,this, numberPerShip);
            return true;
        }
        return false;
    }

    public boolean placeTrapOnGrid(int joueur, int indexTrap, int posx, int posy) {
        Trap trap = _model.getTrapInInventory(indexTrap);
        trap.setPosition(new Cell(posx, posy));
        return _model.placeTrapOnGrid(joueur, trap);
    }

    public boolean placeShipOnGrid(int joueur, ShipTypes type, int posx, int posy, Axis axis) {
        int size = type.getSize();
        List<Cell> position = new ArrayList<>();
        for (int i=0; i<size; i++) {
            position.add( new Cell(posx + (axis == Axis.HORIZONTAL ? i : 0) , posy + (axis == Axis.VERTICAL ? i : 0)));
        }
        Ship ship = new Ship(type,position);
        return _model.placeShipOnGrid(joueur,ship);
    }

    public boolean setWeapon(int joueur, int weaponIndex) {
        return _model.setWeapon(joueur,weaponIndex);
    }

    public boolean shootOnGrid(int joueur, int posx, int posy) {
        if (_model.shootOnGrid(joueur, new Cell(posx, posy))) {
            if (joueur==0) {
                _bot.shoot(_model, this);
            }
            return true;
        }
        return false;
    }
}
