/*package view;

import model.Game;
import model.PlaceableTypes;

import java.util.Scanner;

public class TerminalView {
    /*
    private Game _model;
    private GameController _controller;

    private Scanner _scan;

    public TerminalView(Game model, GameController controller) {
        _model = model;
        _controller = controller;
        _scan = new Scanner(System.in);
    }

    public void run() {
        initName();
        initGrid();
        //choix du nombre de bateaux
        //placement des bateaux
        //placement des pièges
        //Placement armes cachées si ile

        //boucle while
            //afficher num tour
            //chosir son arme
            //tirer qlq part
            //test gagné

        //afficher gagnant et stats
        //Propose recommencer
    }

    private int askIntBetween(int min, int max) {
        while(true) {
            if (_scan.hasNextInt()) {
                int e = _scan.nextInt();
                if (min<=e && e<=max) {
                    _scan.nextLine();
                    return e;
                }
            }
            _scan.nextLine();
            System.out.println("Entrée invalide");
        }
    }

    private String askString() {
        while (true) {
            String input = _scan.next();
            _scan.nextLine();
            if (input.length()>0) {
                return input;
            }
            System.out.println("Chaine vide, réessayez");
        }
    }

    private void displayGrid() {
        int size = _model.getGridSize();

        System.out.println("=== GRID (" + size + "x" + size + ") ===");

        StringBuilder first = new StringBuilder();
        first.append("  ");
        for (int y=1; y < size+1; y++) {
            first.append(y+" ");
        }

        //Rajouter ligne droite A-J

        for (int y = 0; y < size; y++) {
            StringBuilder line = new StringBuilder();

            for (int x = 0; x < size; x++) {
                PlaceableTypes type = _model.getObjectTypes(x, y, 0);

                if (type == null) {
                    line.append(". ");            // vide
                } else if (type == PlaceableTypes.SHIP) {
                    line.append("S ");            // ship
                } else if (type == PlaceableTypes.TRAP) {
                    line.append("T ");            // trap
                } else {
                    line.append("? ");            // autre type si tu en as
                }
            }

            System.out.println(line);
        }

        System.out.println("=========================");
    }




    private void initName() {
        System.out.println("Entrez votre nom : ");
        String name = askString();
        _controller.setPlayerName(name);
    }

    private void initGrid() {
        System.out.println("--- CONFIGURATION DE LA CARTE ---");
        System.out.println("Entrez la taille de la grille carrée (entre 6 et 10)");
        boolean correct = false;
        int taille = askIntBetween(6,10);

        System.out.println("Voulez vous jouer au mode île ? (y/n)");
        String input;
        while (true) {
            input = askString();
            if (input == "y" || input == "n") {
                break;
            }
            System.out.println("Incorrect");
        }
        boolean islandMode = input == "y";

        _controller.setGrid(taille, islandMode);
    }

    private void placeShips() {
        int gridSize = _model.getGridSize();
        System.out.println("--- PLACEMENT DES BATEAUX ---");
        displayGrid();
        System.out.println("1 : Porte avion (longueur : 5)");
        System.out.println("2 : Croiseur (longueur : 4)");
        System.out.println("3 : Contre-torpilleur (longueur : 3)");
        System.out.println("4 : Sous-marin (longueur : 3)");
        System.out.println("5 : Torpilleur (longueur : 2)");
        System.out.println("\nChoisissez quel bateau et où le placer : <num> <y> <x> <axis> (ex : 1 3 4 0)");
        System.out.println("Avec : num=[1-5] y=[1-10] , x=[1-10] , axis={0 ou 1} (pour vertical ou horizontal)"); //à modif pour respecter taille grille

        while (true) {
            String input = askString();
            String[] rawArgs = input.split(" ");
            if (rawArgs.length == 4) {
                try {
                    int[] args = new int[4];
                    for (int i = 0; i < 4; i++) {
                        args[i] = Integer.parseInt(rawArgs[i]);
                    }
                    if (1<=args[0] && args[0]<=5 && 1<=args[1] && args[1]<=gridSize && 1<=args[2] && args[2]<=gridSize && (args[3]==0 || args[3]==1)) {
                        if (_controller.placeShipOnGrid(args[0], args[2], args[1], args[3])) {
                            break;
                        }
                        System.out.println("Impossible de placer le bateau : une case déjà occupée");
                    }
                } finally {}
            }
            System.out.println("Incorrect");

        }


    }

     */

}
*/