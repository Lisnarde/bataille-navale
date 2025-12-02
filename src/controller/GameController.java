package controller;

import model.*;
import model.traps.Tornado;
import model.traps.Trap;

import java.util.Arrays;

public class GameController implements GridObserver {

    Game _model;

    public GameController(Game model) {
        _model = model;
    }

    @Override
    public void updateShoot(int posx, int posy, boolean hit) {
        if (hit) {
            System.out.println("Tir en " + posx + ";" + posy + " : bateau touché !");
        } else {
            System.out.println("Tir en " + posx + ";" + posy + " : aucun bateau touché");
        }
    }

    @Override
    public void updateTrapActivated(int posx, int posy) {
        System.out.println("piège activé en "+posx+";"+posy);
    }

    public void run() {
        _model.setPlayerName("Bast");
        _model.setGrid(10,false);
        _model.addGridObserver(this);

        Trap trap = new Tornado(new Cell(0,3));
        _model.placeTrapOnGrid(0,trap);

        Ship ship = new Ship(Arrays.asList(new Cell(0,0),new Cell(0,1),new Cell(0,2)));
        _model.placeShipOnGrid(0,ship);

        Ship ship2 = new Ship(Arrays.asList(new Cell(0,0),new Cell(0,1),new Cell(0,2)));
        _model.placeShipOnGrid(1,ship2);

        _model.shootOnGrid(1,new Cell(0,3));

        System.out.println(_model.isTheGameFinished());

        _model.shootOnGrid(0,new Cell(0,0));
        _model.shootOnGrid(0,new Cell(0,1));
        _model.shootOnGrid(0,new Cell(0,2));

        System.out.println(_model.isTheGameFinished());
    }
}
