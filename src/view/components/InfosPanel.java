package view.components;

import model.Game;
import model.traps.TrapTypes;
import model.weapons.WeaponTypes;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InfosPanel extends JPanel {
    public InfosPanel(Theme theme, int player, Game model)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Infos dans le panel infos
        JLabel lbl = new JLabel("Nombre de tours : " + model.getTurnNum());
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Tirs dans l'eau : " + model.getShotsInWater(player));
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Nombre de bateaux coulés : " + model.getDrownedShipsCount(player));
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Nombre de bateaux touchés : " + model.getHitShipsCount(player));
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Nombre de bateaux intact : " + model.getIntactShipsCount(player));
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Armes utilisées : " + model.getUsedWeapons(player));
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Nom des pièges activés : " + model.getActivatedTraps(player));
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        if (model.isIslandModeActivated()){
            lbl = new JLabel("Armes trouvées sur l'île : " + model.getFoundWeapons((player+1)%2));
            lbl.setFont(theme.normalFont());
            lbl.setAlignmentX(CENTER_ALIGNMENT);
            add(lbl);

            lbl = new JLabel("Armes non trouvées sur l'île : " + model.getWeaponNotFound((player+1)%2));
            lbl.setFont(theme.normalFont());
            lbl.setAlignmentX(CENTER_ALIGNMENT);
            add(lbl);

            int cellIsland = 16 - model.getRemainingIslandCells((player+1)%2);
            lbl = new JLabel("Nombre de cases fouillées sur l'île : "+ cellIsland + " / 16");
            lbl.setFont(theme.normalFont());
            lbl.setAlignmentX(CENTER_ALIGNMENT);
            add(lbl);
        }

    }
}
