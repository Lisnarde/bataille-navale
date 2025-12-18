package view;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import model.GameObserver;
import model.weapons.Weapon;
import model.weapons.WeaponTypes;
import view.components.GridMode;
import view.components.GridPanel;
import view.components.TitleBanner;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements ViewPanel, GameObserver {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;
    private Theme _theme;

    private GridPanel _gridPanelAttack;
    private GridPanel _gridPanelReceive;

    private TitleBanner _title;
    private JPanel _panelArmes;

    public GameScreen(GameController controller, Game model, NavigationController navigationController, Theme theme) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        _theme = theme;
    }

    @Override
    public void onShow() {
        _model.addGameObserver(this);
        TerminalView terminalView = new TerminalView(_model,_controller);

        setLayout(new BorderLayout());

        //titre
        _title = new TitleBanner("BATAILLE NAVALE - TOUR N°" +_model.getTurnNum());
        _title.setFont(new Font("Arial", Font.BOLD, 46));
        add(_title, BorderLayout.NORTH);

        // Panel Content
        JPanel panelContent = new JPanel();
        panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.X_AXIS));
        add(panelContent, BorderLayout.CENTER);

        //grille attaque
        _gridPanelAttack = new GridPanel(_model, _controller, GridMode.ATTACK);
        _gridPanelAttack.setPreferredSize(new Dimension(400,400));
        panelContent.add(_gridPanelAttack);

        //infos
        JPanel panelInfos = new JPanel();
        panelInfos.setLayout(new BoxLayout(panelInfos,BoxLayout.Y_AXIS));

        JPanel panelInfosPlayer = new JPanel();
        panelInfosPlayer.setLayout(new BoxLayout(panelInfosPlayer,BoxLayout.Y_AXIS));

        JLabel player = new JLabel();
        player.setText(_model.getPlayerName(0));
        panelInfosPlayer.add(player);

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

        JLabel bot = new JLabel();
        bot.setText(_model.getPlayerName(1));
        panelInfosBot.add(bot);

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

        panelContent.add(panelInfos);

        //grille receive
        _gridPanelReceive.setPreferredSize(new Dimension(400,400));
        _gridPanelReceive.setMode(GridMode.RECEIVE);
        panelContent.add(_gridPanelReceive);

        //armes disponibles
        _panelArmes = new JPanel();
        _panelArmes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        add(_panelArmes,BorderLayout.SOUTH);

        for (int i=0; i<_model.getWeaponInventorySize(0); i++) {
            String weaponName = _model.getWeaponNameInInventory(0,i);
            JButton btn = new JButton(weaponName);
            _theme.buttonTheme(btn);
            btn.setPreferredSize(new Dimension(200,70));
            btn.putClientProperty("index",i);
            btn.addActionListener(actionEvent -> _controller.setWeapon(0,(int)btn.getClientProperty("index")));
            _panelArmes.add(btn);
        }
    }

    public void setPlayerShipsGrid(GridPanel gridPanel) {
        _gridPanelReceive = gridPanel;
    }


    @Override
    public void updateNoMoreShips(int joueur) {
        _navigationController.showEnd();
    }

    @Override
    public void updateTurnNumber(int turnNum) {
        _title.setText("BATAILLE NAVALE - TOUR N°" +turnNum);
    }

    @Override
    public void updateWeaponFound(int joueur, WeaponTypes weaponType) {
        int indexWeapon = _model.getWeaponInventorySize(0)-1;
        String weaponName = _model.getWeaponNameInInventory(0, indexWeapon);
        JButton btn = new JButton(weaponName);
        _theme.buttonTheme(btn);
        btn.setPreferredSize(new Dimension(200,70));
        btn.putClientProperty("index",indexWeapon);
        btn.addActionListener(actionEvent -> _controller.setWeapon(0,(int)btn.getClientProperty("index")));
        _panelArmes.add(btn);
    }
}
