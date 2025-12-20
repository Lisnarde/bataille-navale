package view;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import view.components.InfosPanel;
import view.components.TitleBanner;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class EndScreen extends JPanel implements ViewPanel{
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;
    private Theme _theme;

    public EndScreen(GameController controller, Game model, NavigationController navigationController, Theme theme) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        _theme = theme;
    }

    @Override
    public void onShow() {
        setLayout(new BorderLayout());

        int winner = _model.whoWon();

        //titre
        add(new TitleBanner("BATAILLE NAVALE - Bravo "+_model.getPlayerName(winner)+" !"), BorderLayout.NORTH);

        // Contenu principal
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.X_AXIS));
        add(contentPanel,BorderLayout.CENTER);

        // Génération des 2 panels joueurs
        for (int player=0; player<2; player++) {
            // Panel conteneur
            JPanel playerPanel = new JPanel(new BorderLayout());
            contentPanel.add(playerPanel);
            contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
            contentPanel.add(Box.createRigidArea(new Dimension(40, 0)));

            // Titre du panel avec pseudo
            JLabel title = new JLabel((player == winner ? "Vainqueur : " : "Perdant : ") + _model.getPlayerName(player), SwingConstants.CENTER);
            title.setFont(_theme.titleFont());
            title.setForeground(Color.BLACK);
            title.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
            playerPanel.add(title,BorderLayout.NORTH);

            // Panel des infos
            InfosPanel infosPanel = new InfosPanel(_theme, player, _model);
            playerPanel.add(infosPanel,BorderLayout.CENTER);
            playerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            playerPanel.setPreferredSize(new Dimension(400, 500));
            playerPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));

            if (player == winner){
                infosPanel.setBackground(new Color(215, 220, 128));
            }
            else {
                infosPanel.setBackground(new Color(195, 77, 77));
            }

        }

    }
}
