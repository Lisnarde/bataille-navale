package view;

import controller.GameController;
import model.*;
import model.traps.TrapTypes;
import model.weapons.WeaponTypes;

import java.util.Scanner;

public class TerminalView implements GridObserver {
    private Game _model;
    private GameController _controller;

    public TerminalView(Game model, GameController controller) {
        _model = model;
        _controller = controller;
        _model.addGridObserver(this);
    }

    @Override
    public void updateShoot(int joueur, int posx, int posy, boolean hit) {
        System.out.println("Joueur "+joueur+" s'est fait tiré dessus en "+posx+" ; "+posy+ " : "+(hit ? "bateau touché" : ""));
    }

    @Override
    public void updateTrapActivated(int joueur, int posx, int posy, TrapTypes trapType) {
        System.out.println("Piège "+trapType+" de joueur "+joueur+" activé en "+posx+";"+posy);
    }

    @Override
    public void updateSearch(int joueur, int posx, int posy, WeaponTypes objectFound) {
        System.out.println("Le joueur "+joueur+" se fait fouiller en "+posx+";"+posy+" : "+(objectFound != null ? objectFound.name() : "Rien trouvé"));
    }

    @Override
    public void updateShipCellPlaced(int joueur, int posx, int posy) {
        System.out.println("Joueur "+joueur+" place une case de bateau à "+posx+";"+posy);
    }

    @Override
    public void updateTrapPlaced(int joueur, int posx, int posy, TrapTypes trapType) {
        System.out.println("Piège "+trapType.name()+" placé par joueur "+joueur+" à "+posx+";"+posy);
    }

    @Override
    public void updateWeaponPlacedOnIsland(int joueur, int posx, int posy, WeaponTypes weaponType) {
        System.out.println("Arme "+weaponType+" cachée sur l'île par le joueur "+joueur+" à "+posx+";"+posy);
    }

    @Override
    public void updateShipCellDrowned(int joueur, int posx, int posy){
        System.out.println("Une cellule d’un bateau du joueur " + joueur +
                " a été coulée en (" + posx + "," + posy + ")");
    }

    @Override
    public void updateNoMoreShips(int joueur) {
        System.out.println("Le joueur "+joueur+" n'a plus de bateaux !");
    }
}