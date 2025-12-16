package view.components;
import controller.GameController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel implements GridObserver {
    private Game _model;
    private GameController _controller;

    private List<List<JButton>> _grid;

    public GridPanel(Game model, GameController controller) {
        _model = model;
        _controller = controller;
        _model.addGridObserver(this);
        int gridSize = _model.getGridSize();
        _grid = new ArrayList<>();
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
                button.putClientProperty("x",col);
                button.putClientProperty("y",row);

                button.addActionListener(actionEvent -> buttonClicked(button));

                rowButtons.add(button);
                add(button);
            }

            _grid.add(rowButtons);
        }
    }

    private void buttonClicked(JButton button) {
        int x = (int)button.getClientProperty("x");
        int y = (int)button.getClientProperty("y");
        _controller.shootOnGrid(0, x, y);
    }



    @Override
    public void updateShoot(int joueur, int posx, int posy, boolean hit) {
        JButton buttonClicked = _grid.get(posy).get(posx);
        buttonClicked.setEnabled(false);
        if (hit) {
            buttonClicked.setBackground(new Color(200,0,0));
        } else {
            buttonClicked.setBackground(new Color(0, 0, 120));
        }
    }

    @Override
    public void updateTrapActivated(int joueur, int posx, int posy) {

    }
    @Override
    public void updateSearch(int joueur, int posx, int posy, PlaceableTypes objectFound){

    }
}
