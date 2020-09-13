package co.za.wethinkcode.controller;

import co.za.wethinkcode.model.characters.heroes.CreateHero;
import co.za.wethinkcode.model.game.*;

public class GameController {
    private ConsoleGame playConsole;
    private GuiGame playGui;
    private MapController mapController;
    private Game game;

    public GameController() {

        //TODO: initalize everything that needs to be
        game = new Game();
        mapController = new MapController();
        playConsole = new ConsoleGame(mapController, game);
        playGui = new GuiGame(mapController, game);
    }

    public void startConsole(CreateHero createHero){
        playConsole.play(createHero);
        //TODO: FUNCTIONALITY WILL BE IN THE MODEL/GAME/CONSOLEGAME CLASS
    }

    public void startGui(CreateHero createHero){
        playGui.play(createHero);
        //TODO: FUNCTIONALITY WILL BE IN THE MODEL/GAME/GUIGAME CLASS
    }

}
