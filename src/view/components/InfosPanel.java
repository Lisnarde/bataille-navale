package view.components;

import model.traps.TrapTypes;
import model.weapons.WeaponTypes;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InfosPanel extends JPanel {
    public InfosPanel(Theme theme, int shootNumber, int shootInWater, int shipsDrowned, List<WeaponTypes> weaponsUsed,
                      List<TrapTypes> trapsActivated, List<WeaponTypes> weaponsFound, List<WeaponTypes> weaponsNotFound)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Infos dans le panel infos
        JLabel lbl = new JLabel("Nombre de tirs : "+shootNumber);
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Tirs dans l'eau : "+shootInWater);
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);

        lbl = new JLabel("Bateaux adverses coulés : "+shipsDrowned);
        lbl.setFont(theme.normalFont());
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        add(lbl);
    }
}
