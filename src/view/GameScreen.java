package view;

import controller.GameController;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private GraphicalView _parent;
    private GameController _controller;
    private Game _model;

    public GameScreen(GraphicalView parent, GameController controller, Game model) {
        _parent = parent;
        _controller= controller;
        _model = model;
        setLayout(new BorderLayout());

        //titre
        int turnNum = _model.getTurnNum();
        String text = "BATAILLE NAVALE - TOUR N°" + turnNum;
        setLayout(new BorderLayout());
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 46));
        add(title, BorderLayout.NORTH);

        //grille joueur
        JPanel panelPlayer = new JPanel();
        add(new GridPanel(10), BorderLayout.WEST); //model.getGridSize()

        //infos
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos,BoxLayout.Y_AXIS));

        JPanel panelInfosPlayer = new JPanel();
        panelInfosPlayer.setLayout(new BoxLayout(panelInfosPlayer,BoxLayout.Y_AXIS));

        JLabel player = new JLabel();
        //player.setText(_model.getPlayerName(0)); //rajouter un get du pseudo

        JLabel lastActionPlayedPlayer = new JLabel(); //dernière action jouée
        lastActionPlayedPlayer.setText("Dernière action jouée : ");
        lastActionPlayedPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(lastActionPlayedPlayer);

        JLabel numShipIntactPlayer =new JLabel(); //nombre de bateaux du joueur intact
        numShipIntactPlayer.setText("Votre nombre de bateaux intacts : ");
        numShipIntactPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(numShipIntactPlayer);

        JLabel numShipHitPlayer =new JLabel();  //nombre de bateaux du joueur touchés (pas nombre de cases)
        numShipHitPlayer.setText("Votre nombre de bateaux touchés : ");
        numShipHitPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(numShipHitPlayer);

        JLabel numShipDrownedPlayer =new JLabel(); //nombre de bateaux du jouer coulés
        numShipDrownedPlayer.setText("Votre nombre de bateaux coulés : ");
        numShipDrownedPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(numShipDrownedPlayer);

        JLabel numShotInWaterPlayer =new JLabel();  //nombre de tirs dans l'eau du joueur
        numShotInWaterPlayer.setText("Votre nombre de tirs dans l'eau : ");
        numShotInWaterPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(numShotInWaterPlayer);

        JLabel numCellShipHitPlayer =new JLabel();  //nombre de cases bateau du bot touchées
        numCellShipHitPlayer.setText("Nombre de cases bateaux que vous avez touchées : ");
        numCellShipHitPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(numCellShipHitPlayer);

        JLabel numIntactCellShipPlayer =new JLabel();   //nombre de cases bateau du bot restantes à toucher
        numIntactCellShipPlayer.setText("Nombre de cases bateaux qu'il vous reste à toucher : ");
        numIntactCellShipPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(numIntactCellShipPlayer);

        JLabel listWeaponUsablePlayer =new JLabel();  //liste d'armes encore utilisables
        listWeaponUsablePlayer.setText("Liste d'arme(s) à disposition : ");
        listWeaponUsablePlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(listWeaponUsablePlayer);

        JLabel listWeaponUsedPlayer =new JLabel(); //liste d'armes déjà utilisées
        listWeaponUsedPlayer.setText("Liste d'arme(s) utilisée(s) : ");
        listWeaponUsedPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(listWeaponUsedPlayer);

        JLabel numCellIslandNonSearchedPlayer =new JLabel();  //nombre de cases de l'île restantes à fouiller
        numCellIslandNonSearchedPlayer.setText("Nombre de cases d'île restantes à fouiller : ");
        numCellIslandNonSearchedPlayer.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosPlayer.add(numCellIslandNonSearchedPlayer);

        panelInfos.add(panelInfosPlayer);



        JPanel panelInfosBot = new JPanel();
        panelInfosBot.setLayout(new BoxLayout(panelInfosBot,BoxLayout.Y_AXIS));

        JLabel lastActionPlayedBot =new JLabel(); //dernière action jouée
        lastActionPlayedBot.setText("Dernière action jouée : ");
        lastActionPlayedBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(lastActionPlayedBot);

        JLabel numShipIntactBot =new JLabel(); //nombre de bateaux du bot intact
        numShipIntactBot.setText("Nombre de bateaux intacts : ");
        numShipIntactBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(numShipIntactBot);

        JLabel numShipHitBot =new JLabel();  //nombre de bateaux du bot touchés (pas nombre de cases)
        numShipHitBot.setText("Nombre de bateaux touchés : ");
        numShipHitBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(numShipHitBot);

        JLabel numShipDrownedBot =new JLabel(); //nombre de bateaux du bot coulés
        numShipDrownedBot.setText("Nombre de bateaux coulés : ");
        numShipDrownedBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(numShipDrownedBot);

        JLabel numShotInWaterBot =new JLabel();  //nombre de tirs dans l'eau du bot
        numShotInWaterBot.setText("Nombre de tirs dans l'eau : ");
        numShotInWaterBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(numShotInWaterBot);

        JLabel numCellShipHitBot =new JLabel();  //nombre de cases bateau du joueur touchées
        numCellShipHitBot.setText("Nombre de cases bateaux touchées : ");
        numCellShipHitBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(numCellShipHitBot);

        JLabel numIntactCellShipBot =new JLabel();   //nombre de cases bateau du joueur restantes à toucher
        numIntactCellShipBot.setText("Nombre de cases bateaux restantes à toucher : ");
        numIntactCellShipBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(numIntactCellShipBot);

        JLabel listWeaponUsableBot =new JLabel();  //liste d'armes encore utilisables
        listWeaponUsableBot.setText("Liste d'arme(s) à disposition : ");
        listWeaponUsableBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(listWeaponUsableBot);

        JLabel listWeaponUsedBot =new JLabel(); //liste d'armes déjà utilisées
        listWeaponUsedBot.setText("Liste d'arme(s) utilisée(s) : ");
        listWeaponUsedBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(listWeaponUsedBot);

        JLabel numCellIslandNonSearchedBot =new JLabel();  //nombre de cases de l'île restantes à fouiller
        numCellIslandNonSearchedBot.setText("Nombre de cases d'île restantes à fouiller : ");
        numCellIslandNonSearchedBot.setFont(new Font("Arial", Font.PLAIN, 16));
        panelInfosBot.add(numCellIslandNonSearchedBot);


        panelInfos.add(panelInfosBot);

        add(panelInfos, BorderLayout.CENTER);

        //grille bot
        JPanel panelBot = new JPanel();
        add(new GridPanel(10), BorderLayout.EAST);

        //armes disponibles
    }
}
