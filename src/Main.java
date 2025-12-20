import controller.GameController;
import controller.bots.SmartBot;
import model.Game;
import view.GraphicalView;
import view.TerminalView;

public class Main {
    public static void main(String[] args){
        Game game = new Game();
        GameController gamecontroller = new GameController(game, new SmartBot());
        GraphicalView graphicalView = new GraphicalView(gamecontroller, game);
    }
}
