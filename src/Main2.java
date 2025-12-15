import controller.GameController;
import model.Game;
import view.GraphicalView;

public class Main2 {
    public static void main(String[] args){
        Game game = new Game();
        GameController gamecontroller = new GameController(game);
        GraphicalView graphicalView = new GraphicalView(gamecontroller, game);
    }
}
