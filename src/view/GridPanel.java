package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel{
    private List<List<JButton>> _grid;

    public GridPanel(int gridSize){
        _grid = new ArrayList<>();
        setLayout(new GridLayout(gridSize, gridSize));

        for(int i = 1; i < gridSize+1; i++){
            List<JButton> row = new ArrayList<>();
            for(int j = 1; j < gridSize+1; j++){
                JButton button = new JButton(i + "," + j);
                row.add(button);
                add(button);
            }
            _grid.add(row);
        }
    }
}
