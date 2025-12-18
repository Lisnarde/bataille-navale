package controller;

import controller.bots.Bot;
import model.*;
import model.traps.BlackHole;
import model.traps.Tornado;
import model.traps.Trap;
import model.traps.TrapTypes;
import model.weapons.Bomb;
import model.weapons.Sonar;
import model.weapons.Weapon;
import model.weapons.WeaponTypes;

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

    public boolean setNumberPerShip(Map<ShipTypes,Integer> numberPerShip) {
        if (_model.setNumberPerShip(numberPerShip)) {
            return true;
        }
        return false;
    }

    public void botPlaceObjects(int player) {
        _bot.placeShips(player,_model,this, _model.getNumberPerShip());
    }

    public boolean placeTrapOnGrid(int player, int indexTrap, int posx, int posy) {
        Trap trap = switch (_model.getTrapInInventory(indexTrap)) {
            case BLACKHOLE -> new BlackHole();
            case TORNADO -> new Tornado();
        };
        trap.setPosition(new Cell(posx, posy));
        return _model.placeTrapOnGrid(player, trap);
    }

    public boolean placeShipOnGrid(int player, ShipTypes type, int posx, int posy, Axis axis) {
        int size = type.getSize();
        List<Cell> position = new ArrayList<>();
        for (int i=0; i<size; i++) {
            position.add( new Cell(posx + (axis == Axis.HORIZONTAL ? i : 0) , posy + (axis == Axis.VERTICAL ? i : 0)));
        }
        Ship ship = new Ship(type,position);
        return _model.placeShipOnGrid(player,ship);
    }

    public boolean placeWeaponOnIsland(int player, int weaponIndex, int posx, int posy) {
        Weapon weapon = switch (_model.getWeaponInGlobalInventory(weaponIndex)) {
            case BOMB -> new Bomb();
            case SONAR -> new Sonar();
            default -> null;
        };
        return _model.placeWeaponOnIsland(player, weapon, new Cell(posx,posy));
    }

    public boolean setWeapon(int player, int weaponIndex) {
        return _model.setWeapon(player,weaponIndex);
    }

    public boolean shootOnGrid(int player, int posx, int posy) {
        if (_model.shootOnGrid(player, new Cell(posx, posy))) {
            if (player==0) {
                _bot.shoot(1,_model, this);
            }
            return true;
        }
        return false;
    }

    public void washGrid(int player) {
        _model.washGrid(player);
    }
}
