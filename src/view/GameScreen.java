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

        //infos du joueur
        JPanel panelInfosPlayer = new JPanel();
        panelInfosPlayer.setLayout(new BoxLayout(panelInfosPlayer,BoxLayout.Y_AXIS));

        JLabel player = new JLabel();
        player.setText(_model.getPlayerName(0));
        player.setFont(_theme.titleFont());
        panelInfosPlayer.add(player);

        panelInfosPlayer.add(createInfoLabel("Dernière action jouée : ")); //dernière action jouée
        panelInfosPlayer.add(createInfoLabel("Bateaux intacts : " + _model.getPlayer(0).getIntactShipsCount())); //nombre de bateaux du joueur intact
        panelInfosPlayer.add(createInfoLabel("Bateaux touchés : " + _model.getPlayer(0).getHitShipsCount())); //nombre de bateaux du joueur touchés (pas nombre de cases)
        panelInfosPlayer.add(createInfoLabel("Bateaux coulés : " + _model.getPlayer(0).getDrownedShipsCount()));//nombre de bateaux du joueur coulés
        panelInfosPlayer.add(createInfoLabel("Tirs dans l'eau : " + _model.getPlayer(0).getShotsInWater())); //nombre de tirs dans l'eau du joueur
        panelInfosPlayer.add(createInfoLabel("Cases bateaux touchées : " + _model.getPlayer(0).getHitCellsCount())); //nombre de cases bateau du bot touchées
        panelInfosPlayer.add(createInfoLabel("Cases restantes à toucher : " + _model.getPlayer(1).getRemainingShipCells())); //nombre de cases bateau du bot restantes à toucher
        panelInfosPlayer.add(createInfoLabel("Armes disponibles : " + _model.getPlayer(0).getWeaponInventory())); //liste d'armes encore utilisables
        panelInfosPlayer.add(createInfoLabel("Armes utilisées : " + _model.getPlayer(0).getUsedWeapons())); //liste d'armes déjà utilisées
        panelInfosPlayer.add(createInfoLabel("Cases d'île restantes : " + _model.getPlayer(0).getRemainingIslandCells())); //nombre de cases de l'île restantes à fouiller

        panelInfos.add(panelInfosPlayer);


        //infos du bot
        JPanel panelInfosBot = new JPanel();
        panelInfosBot.setLayout(new BoxLayout(panelInfosBot,BoxLayout.Y_AXIS));

        JLabel bot = new JLabel();
        bot.setText(_model.getPlayerName(1));
        bot.setFont(_theme.titleFont());
        panelInfosBot.add(bot);

        panelInfosBot.add(createInfoLabel("Dernière action jouée : ")); //dernière action jouée
        panelInfosBot.add(createInfoLabel("Bateaux intacts : " + _model.getPlayer(1).getIntactShipsCount())); //nombre de bateaux du bot intact
        panelInfosBot.add(createInfoLabel("Bateaux touchés : " + _model.getPlayer(1).getHitShipsCount())); //nombre de bateaux du bot touchés (pas nombre de cases)
        panelInfosBot.add(createInfoLabel("Bateaux coulés : " + _model.getPlayer(1).getDrownedShipsCount()));//nombre de bateaux du bot coulés
        panelInfosBot.add(createInfoLabel("Tirs dans l'eau : " + _model.getPlayer(1).getShotsInWater())); //nombre de tirs dans l'eau du bot
        panelInfosBot.add(createInfoLabel("Cases bateaux touchées : " + _model.getPlayer(1).getHitCellsCount())); //nombre de cases bateau du joueur touchées
        panelInfosBot.add(createInfoLabel("Cases restantes à toucher : " + _model.getPlayer(0).getRemainingShipCells())); //nombre de cases bateau du joueur restantes à toucher
        panelInfosBot.add(createInfoLabel("Armes disponibles : " + _model.getPlayer(1).getWeaponInventory())); //liste d'armes encore utilisables
        panelInfosBot.add(createInfoLabel("Armes utilisées : " + _model.getPlayer(1).getUsedWeapons())); //liste d'armes déjà utilisées
        panelInfosBot.add(createInfoLabel("Cases d'île restantes : " + _model.getPlayer(1).getRemainingIslandCells())); //nombre de cases de l'île restantes à fouiller

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
            WeaponTypes weaponType = _model.getWeaponTypeInInventory(0,i);
            addWeapon(i,weaponType);
        }
        selectWeapon((JButton) _panelArmes.getComponent(0));

    }

    public void setPlayerShipsGrid(GridPanel gridPanel) {
        _gridPanelReceive = gridPanel;
    }

    private JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(_theme.normalFont());
        return lbl;
    }

    private void addWeapon(int indexWeapon, WeaponTypes weaponType) {
        JButton btn = new JButton(weaponType.toString());
        _theme.buttonTheme(btn);
        btn.setPreferredSize(new Dimension(200,70));
        btn.putClientProperty("index",indexWeapon);
        btn.addActionListener(actionEvent -> selectWeapon(btn));
        _panelArmes.add(btn);
    }

    private void selectWeapon(JButton selectedButton) {
        _controller.setWeapon(0,(int)selectedButton.getClientProperty("index"));
        for (Component c : _panelArmes.getComponents()) {
            if (c instanceof JButton btn) {
                _theme.buttonTheme(btn);
            }
        }
        selectedButton.setBackground(new Color(0,0,255));
        _panelArmes.repaint();
    }


    @Override
    public void updateNoMoreShips(int player) {
        _navigationController.showEnd();
    }

    @Override
    public void updateTurnNumber(int turnNum) {
        _title.setText("BATAILLE NAVALE - TOUR N°" +turnNum);
    }

    @Override
    public void updateWeaponFound(int player, WeaponTypes weaponType) {
        int indexWeapon = _model.getWeaponInventorySize(0)-1;
        addWeapon(indexWeapon,weaponType);
    }
}
