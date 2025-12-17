import controller.GameController;
import controller.bots.RandomBot;
import model.Game;
import view.GraphicalView;
import view.TerminalView;

public class Main2 {
    public static void main(String[] args){
        Game game = new Game();
        GameController gamecontroller = new GameController(game,new RandomBot());
        GraphicalView graphicalView = new GraphicalView(gamecontroller, game);
    }
}
