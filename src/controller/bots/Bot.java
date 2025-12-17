package controller.bots;

import controller.GameController;
import model.Game;
import model.ShipTypes;

import java.util.Map;

public interface Bot {
    void placeShips(Game model, GameController controller, Map<ShipTypes,Integer> numberPerShip);
    void shoot(Game model, GameController controller);
}
