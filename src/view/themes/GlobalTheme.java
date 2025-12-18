package view.themes;

import javax.swing.*;
import java.awt.*;

public class GlobalTheme implements Theme {
    private Font _normalFont;
    private Font _boldFont;
    private Font _titleFont;

    public GlobalTheme() {
        _normalFont = new Font("Arial",Font.PLAIN, 20);
        _boldFont = new Font("Arial",Font.BOLD, 20);
        _titleFont = new Font("Arial", Font.BOLD, 32 );
    }

    public Font normalFont() {
        return _normalFont;
    }
    public Font boldFont() {
        return _boldFont;
    }
    public Font titleFont(){return _titleFont;}

    public void buttonTheme(AbstractButton button) {
        button.setFont(boldFont());
        button.setBackground(new Color(100, 100, 210));
        button.setForeground(Color.WHITE);
    }

    public void buttonConfigMinusTheme(AbstractButton button) {
        button.setFont(boldFont());
        button.setBackground(new Color(234, 60, 60));
        button.setForeground(Color.WHITE);
    }
    public void buttonConfigPlusTheme(AbstractButton button) {
        button.setFont(boldFont());
        button.setBackground(new Color(56, 221, 53));
        button.setForeground(Color.WHITE);
    }
}
