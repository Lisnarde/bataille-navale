package view.components;
import controller.GameController;
import model.*;
import model.traps.TrapTypes;
import model.weapons.WeaponTypes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel implements GridObserver {
    private Game _model;
    private GameController _controller;

    private GridMode _mode;

    private List<List<JButton>> _grid;

    private Axis _axis;
    private ShipTypes _shipType;
    private int _trapIndex;
    private int _weaponIndex;

    public GridPanel(Game model, GameController controller, GridMode mode) {
        _model = model;
        _controller = controller;
        _mode = mode;
        _model.addGridObserver(this);
        _grid = new ArrayList<>();
        draw();
    }

    public void setMode(GridMode mode) {
        _mode = mode;
    }

    private void draw() {
        int gridSize = _model.getGridSize();
        int maxHeight = 800/gridSize;

        // +1 pour ajouter la ligne et colonne des labels
        setLayout(new GridLayout(gridSize + 1, gridSize + 1));

        // Première case vide (coin haut-gauche)
        add(new JLabel(""));

        // --- Ligne des colonnes : A, B, C, ...
        for (int col = 0; col < gridSize; col++) {
            JLabel label = new JLabel(String.valueOf((char) ('A' + col)), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            add(label);
        }

        // --- Grille principale
        for (int row = 0; row < gridSize; row++) {

            // Label de ligne (1, 2, 3, ...)
            JLabel rowLabel = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            rowLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(rowLabel);

            List<JButton> rowButtons = new ArrayList<>();

            for (int col = 0; col < gridSize; col++) {

                JButton button = new JButton();
                button.setBackground(new Color(50, 150, 200));
                button.setPreferredSize(new Dimension(maxHeight,maxHeight));
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (_model.getObjectTypeByPosition(0,col,row) == PlaceableTypes.ISLANDPART) {
                    button.setBackground(new Color(170,150,50));
                }

                button.putClientProperty("x",col);
                button.putClientProperty("y",row);
                button.addActionListener(actionEvent -> buttonClicked(button));

                rowButtons.add(button);
                add(button);
            }

            _grid.add(rowButtons);
        }
    }

    public void resetView() {
        for (int row=0; row< _grid.size(); row++) {
            for (int col=0; col < _grid.get(row).size(); col++) {
                if (_model.getObjectTypeByPosition(0,col,row) == PlaceableTypes.ISLANDPART) {
                    _grid.get(row).get(col).setBackground(new Color(170,150,50));
                } else {
                    _grid.get(row).get(col).setBackground(new Color(50, 150, 200));
                }
            }
        }
        repaint();
    }

    public void setTypePlacement(ShipTypes shipType, int trapIndex, int weaponIndex) {
        _shipType = shipType;
        _trapIndex = trapIndex;
        _weaponIndex = weaponIndex;
    }
    public void setAxisPlacement(Axis axis) {
        _axis = axis;
    }

    private void buttonClicked(JButton button) {
        int x = (int)button.getClientProperty("x");
        int y = (int)button.getClientProperty("y");
        switch (_mode) {
            case PLACEMENT -> {
                if (_shipType!=null) _controller.placeShipOnGrid(0,_shipType,x,y,_axis);
                else if (_trapIndex!=-1) _controller.placeTrapOnGrid(0, _trapIndex,x,y);
                else if (_weaponIndex!=-1) _controller.placeWeaponOnIsland(0,_weaponIndex,x,y);
            }
            case ATTACK -> _controller.shootOnGrid(0, x, y);
        };

    }



    @Override
    public void updateShoot(int joueur, int posx, int posy, boolean hit) {
        if ((_mode == GridMode.ATTACK && joueur==1) || _mode == GridMode.RECEIVE && joueur==0) {
            JButton buttonClicked = _grid.get(posy).get(posx);
            buttonClicked.setEnabled(false);
            if (hit) {
                buttonClicked.setBackground(new Color(200, 122, 45));
            } else {
                buttonClicked.setBackground(new Color(0, 0, 120));
            }
        }
    }

    @Override
    public void updateTrapActivated(int joueur, int posx, int posy, TrapTypes trapType) {
        if ((_mode == GridMode.ATTACK && joueur==1) || _mode == GridMode.RECEIVE && joueur==0) {
            JButton buttonClicked = _grid.get(posy).get(posx);
            buttonClicked.setEnabled(false);
            if (trapType == TrapTypes.BLACKHOLE) {
                buttonClicked.setBackground(new Color(0, 0, 0));
            } else if (trapType == TrapTypes.TORNADO) {
                buttonClicked.setBackground(new Color(84, 19, 0));
            }
        }
    }
    @Override
    public void updateSearch(int joueur, int posx, int posy, WeaponTypes objectFound){
        if ((_mode == GridMode.ATTACK && joueur==1) || _mode == GridMode.RECEIVE && joueur==0) {
            JButton buttonClicked = _grid.get(posy).get(posx);
            buttonClicked.setEnabled(false);
            if (objectFound == null) {
                buttonClicked.setBackground(new Color(140, 120, 10));
            } else {
                buttonClicked.setBackground(new Color(100, 80, 0));
            }
        }
    }

    @Override
    public void updateShipCellPlaced(int joueur, int posx, int posy) {
        if (_mode == GridMode.PLACEMENT) {
            JButton buttonClicked = _grid.get(posy).get(posx);
            buttonClicked.setEnabled(false);
            buttonClicked.setBackground(new Color(100,100,100));
        }
    }

    @Override
    public void updateTrapPlaced(int joueur, int posx, int posy, TrapTypes trapType) {
        if (_mode == GridMode.PLACEMENT) {
            JButton buttonClicked = _grid.get(posy).get(posx);
            buttonClicked.setEnabled(false);
            if (trapType == TrapTypes.BLACKHOLE) {
                buttonClicked.setBackground(new Color(0, 0, 0));
            } else if (trapType == TrapTypes.TORNADO) {
                buttonClicked.setBackground(new Color(84, 19, 0));
            }
        }
    }

    @Override
    public void updateWeaponPlacedOnIsland(int joueur, int posx, int posy, WeaponTypes weaponType) {
        if (_mode == GridMode.PLACEMENT) {
            JButton buttonClicked = _grid.get(posy).get(posx);
            buttonClicked.setEnabled(false);
            buttonClicked.setBackground(new Color(100, 80, 0));
        }
    }

    @Override
    public void updateShipCellDrowned(int joueur, int posx, int posy){
        if ( _mode == GridMode.ATTACK && joueur == 1 || _mode == GridMode.RECEIVE && joueur == 0){
            JButton button = _grid.get(posy).get(posx);
            button.setEnabled(false);
            button.setBackground(new Color(200, 0, 0));
        }
    }

    @Override
    public void updateNoMoreShips(int joueur){}
}
