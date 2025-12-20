package view;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import model.GameObserver;
import model.GridObserver;
import model.traps.TrapTypes;
import model.weapons.Weapon;
import model.weapons.WeaponTypes;
import view.components.GridMode;
import view.components.GridPanel;
import view.components.TitleBanner;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements ViewPanel, GameObserver, GridObserver {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;
    private Theme _theme;

    private GridPanel _gridPanelAttack;
    private GridPanel _gridPanelReceive;

    private TitleBanner _title;
    private JPanel _panelArmes;

    private JLabel _lblPlayerLastAction;
    private JLabel _lblPlayerIntact;
    private JLabel _lblPlayerHit;
    private JLabel _lblPlayerDrowned;
    private JLabel _lblPlayerShotsWater;
    private JLabel _lblPlayerRemainingCells;
    private JLabel _lblPlayerWeapons;
    private JLabel _lblPlayerUsedWeapons;
    private JLabel _lblPlayerIslandCells;

    private JLabel _lblBotLastAction;
    private JLabel _lblBotIntact;
    private JLabel _lblBotHit;
    private JLabel _lblBotDrowned;
    private JLabel _lblBotShotsWater;
    private JLabel _lblBotRemainingCells;
    private JLabel _lblBotWeapons;
    private JLabel _lblBotUsedWeapons;
    private JLabel _lblBotIslandCells;

    public GameScreen(GameController controller, Game model, NavigationController navigationController, Theme theme) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        _theme = theme;
    }

    @Override
    public void onShow() {
        _model.addGameObserver(this);
        _model.addGridObserver(this);

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
        panelInfos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblInfosTitle = new JLabel("Statistiques de la partie", SwingConstants.CENTER);
        lblInfosTitle.setFont(_theme.titleFont());
        lblInfosTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfos.add(lblInfosTitle);
        panelInfos.add(Box.createRigidArea(new Dimension(0, 10)));


        //infos du joueur
        JPanel panelInfosPlayer = new JPanel();
        panelInfosPlayer.setLayout(new BoxLayout(panelInfosPlayer,BoxLayout.Y_AXIS));

        JLabel player = new JLabel(_model.getPlayerName(0),SwingConstants.CENTER);
        player.setFont(_theme.titleFont());
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfosPlayer.add(player);
        panelInfosPlayer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInfosPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfosPlayer.setBackground(new Color(172, 130, 211));


        _lblPlayerLastAction = createInfoLabel("Dernière action jouée : aucune");
        panelInfosPlayer.add(_lblPlayerLastAction);

        _lblPlayerIntact = createInfoLabel("Bateaux intacts : " + _model.getPlayer(0).getIntactShipsCount());
        panelInfosPlayer.add(_lblPlayerIntact);

        _lblPlayerHit = createInfoLabel("Bateaux touchés : " + _model.getPlayer(0).getHitShipsCount());
        panelInfosPlayer.add(_lblPlayerHit);

        _lblPlayerDrowned = createInfoLabel("Bateaux coulés : " + _model.getPlayer(0).getDrownedShipsCount());
        panelInfosPlayer.add(_lblPlayerDrowned);

        _lblPlayerShotsWater = createInfoLabel("Tirs dans l'eau : " + _model.getPlayer(1).getShotsInWater());
        panelInfosPlayer.add(_lblPlayerShotsWater);

        _lblPlayerRemainingCells = createInfoLabel("Cases restantes à toucher : " + _model.getPlayer(1).getRemainingShipCells());
        panelInfosPlayer.add(_lblPlayerRemainingCells);

        _lblPlayerWeapons = createInfoLabel("Armes disponibles : " + _model.getPlayer(0).getWeaponInventory());
        panelInfosPlayer.add(_lblPlayerWeapons);

        _lblPlayerUsedWeapons = createInfoLabel("Armes utilisées : " + _model.getPlayer(0).getUsedWeapons());
        panelInfosPlayer.add(_lblPlayerUsedWeapons);

        if (_model.isIslandModeActivated()) {
            _lblPlayerIslandCells = createInfoLabel("Cases d'île restantes : " + _model.getPlayer(0).getRemainingIslandCells());
            panelInfosPlayer.add(_lblPlayerIslandCells);
        }

        panelInfos.add(panelInfosPlayer);
        panelInfos.add(Box.createRigidArea(new Dimension(0, 20)));

        //infos du bot
        JPanel panelInfosBot = new JPanel();
        panelInfosBot.setLayout(new BoxLayout(panelInfosBot,BoxLayout.Y_AXIS));

        JLabel bot = new JLabel(_model.getPlayerName(1),SwingConstants.CENTER);
        bot.setFont(_theme.titleFont());
        bot.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfosBot.add(bot);
        panelInfosBot.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInfosBot.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfosBot.setBackground(new Color(147, 184, 115));



        _lblBotLastAction = createInfoLabel("Dernière action jouée : aucune");
        panelInfosBot.add(_lblBotLastAction);

        _lblBotIntact = createInfoLabel("Bateaux intacts : " + _model.getPlayer(1).getIntactShipsCount());
        panelInfosBot.add(_lblBotIntact);

        _lblBotHit = createInfoLabel("Bateaux touchés : " + _model.getPlayer(1).getHitShipsCount());
        panelInfosBot.add(_lblBotHit);

        _lblBotDrowned = createInfoLabel("Bateaux coulés : " + _model.getPlayer(1).getDrownedShipsCount());
        panelInfosBot.add(_lblBotDrowned);

        _lblBotShotsWater = createInfoLabel("Tirs dans l'eau : " + _model.getPlayer(0).getShotsInWater());
        panelInfosBot.add(_lblBotShotsWater);

        _lblBotRemainingCells = createInfoLabel("Cases restantes à toucher : " + _model.getPlayer(0).getRemainingShipCells());
        panelInfosBot.add(_lblBotRemainingCells);

        _lblBotWeapons = createInfoLabel("Armes disponibles : " + _model.getPlayer(1).getWeaponInventory());
        panelInfosBot.add(_lblBotWeapons);

        _lblBotUsedWeapons = createInfoLabel("Armes utilisées : " + _model.getPlayer(1).getUsedWeapons());
        panelInfosBot.add(_lblBotUsedWeapons);

        if (_model.isIslandModeActivated()) {
            _lblBotIslandCells = createInfoLabel("Cases d'île restantes : " + _model.getPlayer(1).getRemainingIslandCells());
            panelInfosBot.add(_lblBotIslandCells);
        }

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

        drawAllWeapons();
    }

    public void setPlayerShipsGrid(GridPanel gridPanel) {
        _gridPanelReceive = gridPanel;
    }

    private JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(_theme.normalFont());
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    private void drawAllWeapons() {
        _panelArmes.removeAll();
        for (int i=0; i<_model.getWeaponInventorySize(0); i++) {
            WeaponTypes weaponType = _model.getWeaponTypeInInventory(0,i);
            addWeapon(i,weaponType);
        }
        selectWeapon((JButton) _panelArmes.getComponent(0));
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

    private void refreshInfos() {
        _lblPlayerIntact.setText("Bateaux intacts : " + _model.getPlayer(0).getIntactShipsCount());
        _lblPlayerHit.setText("Bateaux touchés : " + _model.getPlayer(0).getHitShipsCount());
        _lblPlayerDrowned.setText("Bateaux coulés : " + _model.getPlayer(0).getDrownedShipsCount());
        _lblPlayerShotsWater.setText("Tirs dans l'eau : " + _model.getPlayer(1).getShotsInWater());
        _lblPlayerRemainingCells.setText("Cases restantes à toucher : " + _model.getPlayer(1).getRemainingShipCells());
        _lblPlayerWeapons.setText("Armes disponibles : " + _model.getPlayer(0).getWeaponInventory());
        _lblPlayerUsedWeapons.setText("Armes utilisées : " + _model.getPlayer(0).getUsedWeapons());

        _lblBotIntact.setText("Bateaux intacts : " + _model.getPlayer(1).getIntactShipsCount());
        _lblBotHit.setText("Bateaux touchés : " + _model.getPlayer(1).getHitShipsCount());
        _lblBotDrowned.setText("Bateaux coulés : " + _model.getPlayer(1).getDrownedShipsCount());
        _lblBotShotsWater.setText("Tirs dans l'eau : " + _model.getPlayer(0).getShotsInWater());
        _lblBotRemainingCells.setText("Cases restantes à toucher : " + _model.getPlayer(0).getRemainingShipCells());
        _lblBotWeapons.setText("Armes disponibles : " + _model.getPlayer(1).getWeaponInventory());
        _lblBotUsedWeapons.setText("Armes utilisées : " + _model.getPlayer(1).getUsedWeapons());
        if (_model.isIslandModeActivated()) {
            _lblPlayerIslandCells.setText("Cases d'île restantes à fouiller : " + _model.getPlayer(1).getRemainingIslandCells());
            _lblBotIslandCells.setText("Cases d'île restantes à fouiller : " + _model.getPlayer(0).getRemainingIslandCells());
        }

    }


    @Override
    public void updateShoot(int player, int posx, int posy, boolean hit) {
        if (player == 1)
            _lblPlayerLastAction.setText("Dernière action jouée : tir en ("+posx+","+posy+") → " + (hit ? "touché" : "dans l'eau"));
        else
            _lblBotLastAction.setText("Dernière action jouée : tir en ("+posx+","+posy+") → " + (hit ? "touché" : "dans l'eau"));
        refreshInfos();
    }

    @Override
    public void updateTrapActivated(int player, int posx, int posy, TrapTypes trapType) {
        if (player == 1)
            _lblPlayerLastAction.setText("Dernière action jouée : piège "+trapType+" activé");
        else
            _lblBotLastAction.setText("Dernière action jouée : piège "+trapType+" activé");
        refreshInfos();
    }

    @Override
    public void updateSearch(int player, int posx, int posy, WeaponTypes objectFound) {
        if (player == 1)
            _lblPlayerLastAction.setText("Dernière action jouée : recherche → " + objectFound);
        else
            _lblBotLastAction.setText("Dernière action jouée : recherche → " + objectFound);
        refreshInfos();
    }

    @Override
    public void updateShipCellPlaced(int player, int posx, int posy) {}

    @Override
    public void updateTrapPlaced(int player, int posx, int posy, TrapTypes trapType) {}

    @Override
    public void updateWeaponPlacedOnIsland(int player, int posx, int posy, WeaponTypes weaponType) {}

    @Override
    public void updateShipCellDrowned(int player, int posx, int posy) {
        if (player == 1)
            _lblPlayerLastAction.setText("Dernière action jouée : bateau coulé");
        else
            _lblBotLastAction.setText("Dernière action jouée : bateau coulé");
        refreshInfos();
    }

    @Override
    public void updateNoMoreShips(int player) {
        _navigationController.showEnd();
    }

    @Override
    public void updateSonarUsed(int player, int posx, int posy, int value) {

    }

    @Override
    public void updateTurnNumber(int turnNum) {
        _title.setText("BATAILLE NAVALE - TOUR N°" +turnNum);
    }

    @Override
    public void updateWeaponFound(int player, WeaponTypes weaponType) {
        if (player == 1) {
            int indexWeapon = _model.getWeaponInventorySize(0) - 1;
            addWeapon(indexWeapon, weaponType);
        }
    }

    @Override
    public void updateWeaponRemoved(int player, WeaponTypes weaponType) {
        if (player == 0) drawAllWeapons();
    }
}
