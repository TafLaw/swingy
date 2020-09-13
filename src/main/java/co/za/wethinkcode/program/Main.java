package co.za.wethinkcode.program;

import co.za.wethinkcode.controller.ConsoleController;
import co.za.wethinkcode.controller.GameController;
import co.za.wethinkcode.controller.GuiController;
import co.za.wethinkcode.exceptions.InvalidArgsException;
import co.za.wethinkcode.exceptions.ValidateArguments;
import co.za.wethinkcode.model.characters.enemies.AboutEnemy;
import co.za.wethinkcode.model.characters.enemies.CreateEnemies;
import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.characters.heroes.*;
import co.za.wethinkcode.model.files.ReadFile;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class Main {
    public static void main(String[] args) {
        CreateHero createHero = new CreateHero();
        CreateEnemies createEnemies = new CreateEnemies();
        ValidateArguments validateArguments = new ValidateArguments();
        GameController gameController = new GameController();

        ReadFile readFile = new ReadFile();
System.out.println("Aasa");
//                gameController.startConsole(createHero);
        try {
            readFile.readFromFile("heroes.txt");
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
