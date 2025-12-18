package controller.bots;

import controller.GameController;
import model.Game;
import model.ShipTypes;

import java.util.Map;

public interface Bot {
    void placeShips(int player, Game model, GameController controller, Map<ShipTypes,Integer> numberPerShip);
    void shoot(int player, Game model, GameController controller);
}
