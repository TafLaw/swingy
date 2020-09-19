package co.za.wethinkcode.controller;

import co.za.wethinkcode.model.characters.heroes.CreateHero;
import co.za.wethinkcode.model.game.*;

public class GameController {
    private ConsoleGame playConsole;
    private GuiGame playGui;
    private MapController mapController;
    private Game game;

    public GameController() {
        game = new Game();
        mapController = new MapController();
        playConsole = new ConsoleGame(mapController, game, this);
        playGui = new GuiGame(mapController, game, this);
    }

    public void startConsole(CreateHero createHero){
        playConsole.play(createHero);
    }

    public void startGui(CreateHero createHero){
        playGui.play(createHero);
    }

}
