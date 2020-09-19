package co.za.wethinkcode.model.game;

import co.za.wethinkcode.controller.ConsoleController;
import co.za.wethinkcode.controller.GameController;
import co.za.wethinkcode.controller.MapController;
import co.za.wethinkcode.model.characters.enemies.CreateEnemies;
import co.za.wethinkcode.model.characters.factories.*;
import co.za.wethinkcode.model.characters.heroes.*;
import co.za.wethinkcode.storage.HeroStorage;

import javax.validation.constraints.*;
import java.util.*;

public class ConsoleGame extends AbGame {
    public Scanner scanner;
    public static int section;
    private boolean gameOver = false;
    @NotEmpty
    @Size(min = 1, max = 3)
    private String option;

    private MapController mapController;
    private ConsoleController consoleController;
    private HeroStorage heroStorage;
    private String[] direction = {"up", "down", "left", "right"};
    private Game game;

    public ConsoleGame(MapController mapController, Game game, GameController gameController) {
        section = 1;
        this.gameController = gameController;
        scanner = new Scanner(System.in);
        this.mapController = mapController;
        heroStorage = new HeroStorage();
        option = "0";
        this.game = game;
        consoleController = new ConsoleController(this);
    }

    public void play(CreateHero createHero){
        int check;
        this.createHero = createHero;
        this.clear();
        do {
            try {
                consoleController.startScreen();
                option = scanner.nextLine();

                check = Integer.parseInt(option.trim());
                this.clear();
            }catch (Exception e){
                check = 0;
                this.clear();
            }
        }while (check < 1 || check > 4);

        if (check == 1) {
            option = "0";
            this.selectHeroType();
        }
        else if (check == 2) {
            this.availableHeroes();
            this.running();
        }
        else if (check == 3){
            this.switchToGui(createHero);
            return;
        }
        else System.exit(1);
        this.play(createHero);
    }

    private void switchToGui(CreateHero createHero) {
        System.out.println("Switching to gui!");
        gameController.startGui(createHero);
        return;
    }

    public void backToMap(){
        this.clear();
        mapController.updateObjCreated();
        this.running();
    }
    private void running(){
        String message = null;
        int whereTo = 0;
        int check = 0;

        while (!gameOver) {
            do {
                try {
                    if (message != null) {
                        System.out.println(message);
                        message = null;
                    }
                    this.mapController.viewMap(this.hero);
                    if (section == 2) {
                        message = this.metAnEnemy();
                        check = 5;
                        if (gameOver)
                            break;
                     }
                    else {
                        option = scanner.nextLine();
                        check = Integer.parseInt(option.trim());
                    }
                    this.clear();
                }catch (Exception e){
                    check = -1;
                    this.clear();
                }

            } while (check < 0 || check > 4);

            if (check == 0){
                mapController.updateObjCreated();
                saveAndExit();
                break;
            }
            else {
                if (!gameOver){
                    whereTo = this.movePlayer(direction[check - 1]);
                    if (whereTo != 0)
                        break;
                }
                else  break;
            }
        }
        gameOver = false;
        if (whereTo == 1 && check != 0) {
            whereTo = 0;
            backToMap();
            return;
        }
    }

    private String metAnEnemy() {
        int check;
        Random random = new Random();
        int randomEnemy;
        do {
            randomEnemy = random.nextInt(CreateEnemies.enemiesFactory.size());
            this.consoleController.enemyStats(CreateEnemies.enemiesFactory.get(randomEnemy));
            option = scanner.nextLine();
            check = Integer.parseInt(option.trim());
        }while (check < 1 || check > 2);

        theVillain = CreateEnemies.enemiesFactory.get(randomEnemy);
        if (check == 1) {
            this.fight(theVillain);
            return null;
        }
        else
            return this.run(theVillain);

    }

    private String run(EnemiesFactory enemiesFactory) {
        section = 1;
        String result = runAway(mapController);

        if (result.equalsIgnoreCase("fight")) {
            this.fight(enemiesFactory);
            return null;
        }
        else
            return "You successfully escaped the enemy";
    }

    private void fight(EnemiesFactory enemy) {
        section = 1;
        String result = fightEnemy(enemy);
        switch (result){
            case "lost" :
                this.gameOver(battleSimulation);
                break;
            case "won" :
                this.wonBattle(battleSimulation);
                break;
        }
    }

    private void wonBattle(ArrayList<String> results) {
        consoleController.wonBattle(results);
    }

    public void gameOver(ArrayList<String> results) {
        consoleController.gameOver(results);
        gameOver = true;
        mapController.updateObjCreated();
    }

    private int movePlayer(String direction){
        int result = 0;
        boolean checkLevelUp = false;

        game.movePlayer(mapController, this, direction);
        if (newLevel){
            checkLevelUp = levelUp();
            if (checkLevelUp)
                result = 1;
            else result = 2;
        }

        return result;
    }

    private void selectHeroType() {
        int check;
        String name;
        ArrayList<String> allHeroes = heroStorage.allAvailableHeroes();

        do {
            try {
                consoleController.selectHeroType();
                option = scanner.nextLine();
                check = Integer.parseInt(option.trim());
                this.clear();
            }catch (Exception e){
                check = -1;
                this.clear();
            }

        }while (check < 0 || check > 4);
        if (check == 0) {
            this.play(this.createHero);
            return;
        }
        consoleController.heroName();
        name = scanner.nextLine();


        hero = createHero.createHero(name.trim(), CreateHero.heroTypes.get(check - 1));
        if (hero == null) {
            option = scanner.nextLine();
            return;
        }
        heroInitialHP = hero.getAboutHero().getHitPoints();
        heroIndex = allHeroes.size() - 1;

        this.clear();
        this.stats();
    }

    private void availableHeroes(){
        int check;
        String chosenHero;
        ArrayList<String> allHeroes = heroStorage.allAvailableHeroes();
        if (allHeroes.size() == 0){
            consoleController.noHeroesAvailable();
            option = scanner.nextLine();
            this.play(this.createHero);
        }
        else {
            do {
                try {
                    consoleController.availableHeroes(allHeroes);
                    option = scanner.nextLine();
                    this.clear();
                    check = Integer.parseInt(option.trim());

                } catch (Exception e) {
                    check = -1;
                    this.clear();
                }
            } while (check < 0 || check > allHeroes.size());

            this.heroIndex = check - 1;
            if (check == 0)
                this.play(this.createHero);
            else{
                chosenHero = allHeroes.get(check - 1);
                this.storePlayer(chosenHero.split(","));
                heroInitialHP = this.hero.getAboutHero().getHitPoints();
            }
        }
    }

    private void storePlayer(String[] hero){
        this.hero = this.game.storePlayer(hero);
    }

    private void stats(){
        option = "0";
        int check;
        do {
            try {
                consoleController.myHeroStats(hero);
                option = scanner.nextLine();
                this.clear();
                check = Integer.parseInt(option.trim());

            }catch (Exception e){
                check = 0;
                this.clear();
            }
        }while (check < 1 || check > 3);

        if (check == 1)
            this.running();
        else if(check == 2)
            ;
        else System.exit(1);
    }

    protected boolean levelUp() {
        boolean leveledUp = true;
        newLevel = false;
        int newLevel = hero.getAboutHero().getLevel() + 1;

        if (newLevel > 7)
            newLevel = 7;

        if (game.canLevelUp(hero)){
            hero.getAboutHero().setLevel(newLevel);
            mapController.updateObjCreated();
            this.clear();
            saveAndExit();
        }
        else {
            leveledUp = false;
            this.cantLevelUp();
        }
        return leveledUp;
    }

    private void cantLevelUp() {
        mapController.updateObjCreated();
        consoleController.cantLevelUp();

    }

    private void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
