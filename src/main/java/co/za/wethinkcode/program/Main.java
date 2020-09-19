package co.za.wethinkcode.program;

import co.za.wethinkcode.controller.GameController;
import co.za.wethinkcode.exceptions.InvalidArgsException;
import co.za.wethinkcode.exceptions.ValidateArguments;
import co.za.wethinkcode.model.characters.enemies.CreateEnemies;
import co.za.wethinkcode.model.characters.heroes.*;


public class Main {
    public static void main(String[] args) {
        CreateHero createHero = new CreateHero();
        CreateEnemies createEnemies = new CreateEnemies();
        ValidateArguments validateArguments = new ValidateArguments();
        GameController gameController = new GameController();
        try {
            validateArguments.numberOfArgs(args);

            createEnemies.createEnemies();
            String platform = args[0];

            if (platform.equalsIgnoreCase("console"))
                gameController.startConsole(createHero);
            else if (platform.equalsIgnoreCase("gui"))
                gameController.startGui(createHero);
            else
                System.out.println("Platform not valid");

        } catch (InvalidArgsException e) {
            System.out.println("Expected only one argument");
        }
    }
}
