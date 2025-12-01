package view;
import model.*;
import javax.swing.*;
import java.awt.*;

public class GraphicalView extends JFrame{
    public GraphicalView(){
        super("Bataille Navale");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        GridPanel gridPanelPlayer = new GridPanel(10);
        GridPanel gridPanelBot = new GridPanel(10);
        InfosPanel panelInfos = new InfosPanel();
        JPanel titleAndTurnNumber = new JPanel();
        JPanel panelWeapon = new JPanel();

        add(gridPanelPlayer, BorderLayout.CENTER);
        add(gridPanelBot, BorderLayout.EAST);
        add(panelInfos, BorderLayout.WEST);
        add(titleAndTurnNumber, BorderLayout.NORTH);
        add(panelWeapon, BorderLayout.SOUTH);

        setVisible(true);
    }
}
