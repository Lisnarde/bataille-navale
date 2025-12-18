package view.themes;

import javax.swing.*;
import java.awt.*;

public class GlobalTheme implements Theme {
    private Font _normalFont;
    private Font _boldFont;

    public GlobalTheme() {
        _normalFont = new Font("Arial",Font.PLAIN, 20);
        _boldFont = new Font("Arial",Font.BOLD, 20);
    }

    public Font normalFont() {
        return _normalFont;
    }
    public Font boldFont() {
        return _boldFont;
    }

    public void buttonTheme(AbstractButton button) {
        button.setFont(boldFont());
        button.setBackground(new Color(100, 100, 210));
        button.setForeground(Color.WHITE);

    }
}
