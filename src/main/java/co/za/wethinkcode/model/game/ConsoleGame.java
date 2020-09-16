package co.za.wethinkcode.model.game;

import co.za.wethinkcode.controller.ConsoleController;
import co.za.wethinkcode.controller.MapController;
import co.za.wethinkcode.model.characters.enemies.CreateEnemies;
import co.za.wethinkcode.model.characters.factories.*;
import co.za.wethinkcode.model.characters.heroes.*;
import co.za.wethinkcode.storage.HeroStorage;
import org.hibernate.validator.constraints.Length;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;
import java.util.logging.Level;

public class ConsoleGame extends AbGame {
    public Scanner scanner;
    public static int section;
//    private int chance;
    private boolean gameOver = false;
//    private int playerRow;
//    private int playerCol;
//    /*
//    * This will keep track of the enemy location*/
//    private int playerEnemyRow;
//    private int playerEnemyCol;

    @NotEmpty
    @Size(min = 1, max = 3)
    private String option;

    private MapController mapController;
    private ConsoleController consoleController;
    private HeroStorage heroStorage;
    private String[] direction = {"up", "down", "left", "right"};
    private Game game;

    public ConsoleGame(MapController mapController, Game game) {
        section = 1;
        scanner = new Scanner(System.in);
        this.mapController = mapController;
        heroStorage = new HeroStorage();
        option = "0";
        this.game = game;
        consoleController = new ConsoleController(this);
    }

//    public int getPlayerRow() {
//        return playerRow;
//    }
//
//    public void setPlayerRow(int playerRow) {
//        this.playerRow = playerRow;
//    }
//
//    public int getPlayerCol() {
//        return playerCol;
//    }
//
//    public void setPlayerCol(int playerCol) {
//        this.playerCol = playerCol;
//    }
//
//    public int getPlayerEnemyRow() {
//        return playerEnemyRow;
//    }
//
//    public void setPlayerEnemyRow(int playerEnemyRow) {
//        this.playerEnemyRow = playerEnemyRow;
//    }
//
//    public int getPlayerEnemyCol() {
//        return playerEnemyCol;
//    }
//
//    public void setPlayerEnemyCol(int playerEnemyCol) {
//        this.playerEnemyCol = playerEnemyCol;
//    }

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
        }while (check < 1 || check > 3);

        if (check == 1) {
            option = "0";
            this.selectHeroType();
            this.running();
        }
        else if (check == 2) {
            this.availableHeroes();
            this.running();
        }
        else System.exit(1);
    }

    public void backToMap(){
        this.clear();
        this.running();
    }
    private void running(){
        String message = null;
        while (!gameOver) {
            int check;
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
                if (!gameOver)
                    this.movePlayer(direction[check - 1]);
            }
        }
        gameOver = false;
        this.play(this.createHero);
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
//        Random random = new Random();
//        int decider = random.nextInt(101);
//        if (decider >= 0 && decider <= 50)
//        {
//            this.fight(enemiesFactory);
//            return null;
//        }
//        else {
//            mapController.playerPosition.setPlayerRow(this.playerRow);
//            mapController.playerPosition.setPlayerColumn(this.playerCol);
//            mapController.resetEnemy(this.playerEnemyRow, this.playerEnemyCol);
//            return "You successfully escaped the enemy";
//        }
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
////        this.clear();
//        section = 1;
//        String whatWasDropped = "";
//        Random random = new Random();
//        int playerHP;
//        int enemyHP;
//        int gainedExp;
//        int resetEnemyHP = enemy.getAboutEnemy().getHitPoints();
//        battleSimulation.clear();
//
//
//
//        String heroWeapon = hero.getStats().getWeapon();
//        String enemyWeapon = enemy.getArtifacts().getWeapon();
//        System.out.println(hero.getAboutHero().getHitPoints() + " " + enemy.getAboutEnemy().getHitPoints());
//        while (hero.getAboutHero().getHitPoints() > 0 && enemy.getAboutEnemy().getHitPoints() > 0){
//            playerHP = random.nextInt(hero.getWeapons().get(heroWeapon));
//            playerHP = this.attackEnemy(playerHP, enemy);
//            enemy.getAboutEnemy().setHitPoints(enemy.getAboutEnemy().getHitPoints() - playerHP);
//            battleSimulation.add("You hit "+enemy.getAboutEnemy().getName()+" by "+playerHP+" hit points. His HP is " + enemy.getAboutEnemy().getHitPoints()+ "\n");
//            if (enemy.getAboutEnemy().getHitPoints() <= 0)
//                break;
//
//            enemyHP = random.nextInt(hero.getWeapons().get(enemyWeapon));
//            enemyHP = this.attackHero(enemyHP, enemy);
//            hero.getAboutHero().setHitPoints(hero.getAboutHero().getHitPoints() - enemyHP);
//            battleSimulation.add(enemy.getAboutEnemy().getName() + " hit you by "+enemyHP+", you have "+hero.getAboutHero().getHitPoints()+" HP\n");
//            if (hero.getAboutHero().getHitPoints() <= 0)
//                break;
//        }
//        if (hero.getAboutHero().getHitPoints() <= 0) {
//            enemy.getAboutEnemy().setHitPoints(resetEnemyHP);
//            battleSimulation.add("You fought bravely, but unfortunately your hero died!\n");
//            this.gameOver(battleSimulation);
////            hero.getAboutHero().setExperience();
//        }
//        else {
//            droppedArtifact = enemyDroppedArtifact();
//            enemy.getAboutEnemy().setHitPoints(resetEnemyHP);
//            if (enemy.getAboutEnemy().getAttack() > 70)
//                gainedExp = 500;
//            else
//                gainedExp = 350;
//            battleSimulation.add("You defeated "+enemy.getAboutEnemy().getName()+" and gained "+gainedExp+ " EXP\n");
//
//            if (chance >= 0 && chance <= 2){
//                switch (chance){
//                    case 0:
//                        whatWasDropped = enemy.getArtifacts().getWeapon();
//                        break;
//                    case 1:
//                        whatWasDropped = enemy.getArtifacts().getArmor();
//                        break;
//                    case 2:
//                        whatWasDropped = enemy.getArtifacts().getHelm();
//                        break;
//                }
//                battleSimulation.add(enemy.getAboutEnemy().getName()+" dropped his "+whatWasDropped);
//            }
//
//            hero.getAboutHero().setExperience(hero.getAboutHero().getExperience() + gainedExp);
//            this.wonBattle(battleSimulation);
//        }
    }

//    private boolean enemyDroppedArtifact() {
//        Random random = new Random();
//        chance = random.nextInt(6);
//        return chance < 3;
//    }

    private void wonBattle(ArrayList<String> results) {
        consoleController.wonBattle(results);
    }

    public void gameOver(ArrayList<String> results) {
        consoleController.gameOver(results);
        gameOver = true;
        mapController.updateObjCreated();
    }

//    private int attackEnemy(int playerHP, EnemiesFactory enemy){
//        int enemyDefended;
//        Random random = new Random();
//
//        if (enemy.getAboutEnemy().getDefence() > 72){
//            enemyDefended = random.nextInt(101);
//            if (enemyDefended >= 50)
//                playerHP -= 6;
//        }
//        else if (enemy.getAboutEnemy().getDefence() <= 72 && enemy.getAboutEnemy().getDefence() > 66)
//        {
//            enemyDefended = random.nextInt(101);
//            if (enemyDefended < 50 && enemyDefended > 20)
//                playerHP -= 3;
//        }
//        else if (enemy.getAboutEnemy().getDefence() <= 66){
//            enemyDefended = random.nextInt(101);
//            if (enemyDefended >= 20)
//                playerHP -= 1;
//        }
//
//        if (hero.getAboutHero().getAttack() > 65)
//            playerHP += 2;
//        else playerHP++;
//
//        if (playerHP < 0)
//            playerHP = 0;
//
//        return playerHP;
//    }
//
//    private int attackHero(int enemyHP, EnemiesFactory enemy){
//        int heroDefended;
//        Random random = new Random();
//
//        if (hero.getAboutHero().getDefence() > 72){
//            heroDefended = random.nextInt(101);
//            if (heroDefended >= 50)
//                enemyHP -= 6;
//        }
//        else if (hero.getAboutHero().getDefence() <= 72 && hero.getAboutHero().getDefence() > 66)
//        {
//            heroDefended = random.nextInt(101);
//            if (heroDefended < 50 && heroDefended > 20)
//                enemyHP -= 3;
//        }
//        else if (hero.getAboutHero().getDefence() <= 66){
//            heroDefended = random.nextInt(101);
//            if (heroDefended >= 20)
//                enemyHP -= 1;
//        }
//
//        if (enemy.getAboutEnemy().getAttack() > 65)
//            enemyHP += 2;
//        else enemyHP++;
//        if (enemyHP < 0)
//            enemyHP = 0;
//        return enemyHP;
//    }

    private void movePlayer(String direction){

        game.movePlayer(mapController, this, direction);
        if (newLevel)
            levelUp();
//        if (mapController.playerPosition.getPlayerColumn()-1 == playerCol && mapController.playerPosition.getPlayerRow() == playerRow)
//            levelUp();
//        int currentRow = mapController.playerPosition.getPlayerRow();
//        int currentCol = mapController.playerPosition.getPlayerColumn();
//
//        this.playerRow = currentRow;
//        this.playerCol = currentCol;
//
//        switch (direction) {
//            case "up":
//                if (currentRow == 0)
//                    break;
//                mapController.playerPosition.setPlayerRow(currentRow-1);
//                this.playerEnemyRow = mapController.playerPosition.getPlayerRow();
//                this.playerEnemyCol = mapController.playerPosition.getPlayerColumn();
//                break;
//            case "down":
//
//                if (mapController.initMap.getRows() == currentRow +1)
//                    break;
//                mapController.playerPosition.setPlayerRow(currentRow+1);
//                this.playerEnemyRow = mapController.playerPosition.getPlayerRow();
//                this.playerEnemyCol = mapController.playerPosition.getPlayerColumn();
//                break;
//            case "left":
//                if (currentCol == 0)
//                    break;
//                mapController.playerPosition.setPlayerColumn(currentCol-1);
//                this.playerEnemyRow = mapController.playerPosition.getPlayerRow();
//                this.playerEnemyCol = mapController.playerPosition.getPlayerColumn();
//                break;
//            case "right":
//                if (mapController.initMap.getRows() == currentCol+1)
//                    break;
//                mapController.playerPosition.setPlayerColumn(currentCol+1);
//                this.playerEnemyRow = mapController.playerPosition.getPlayerRow();
//                this.playerEnemyCol = mapController.playerPosition.getPlayerColumn();
//                break;
//            default: break;
//        }
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
        if (check == 0)
            this.play(this.createHero);
        consoleController.heroName();
        name = scanner.nextLine();


//        Set<ConstraintViolation<String>> cvs;

        hero = createHero.createHero(name.trim(), CreateHero.heroTypes.get(check - 1));
        if (hero == null) {
            option = scanner.nextLine();
            play(this.createHero);
        }
        heroInitialHP = hero.getAboutHero().getHitPoints();
        heroIndex = allHeroes.size() - 1;

        this.clear();
        this.stats();
    }

//    public static void validate(String value) {
//
//        Set<ConstraintViolation<String>> cvs = validator.validate(value);
//
//        for (ConstraintViolation<String> cv : cvs) {
//            System.out.println(cv.getPropertyPath() + ": " + cv.getMessage());
//        }
//        System.exit(1);
//    }

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
            chosenHero = allHeroes.get(check - 1);
            this.storePlayer(chosenHero.split(","));
            heroInitialHP = this.hero.getAboutHero().getHitPoints();
        }
    }

    private void storePlayer(String[] hero){
        this.hero = this.game.storePlayer(hero);
//        HeroName heroName = new HeroName();
//        AboutHero aboutHero = new AboutHero();
//        Stats heroStats = new Stats();
//        this.hero = new HeroesFactory.Build().heroName(heroName).aboutHero(aboutHero).artifacts(heroStats).build();
//
//        this.hero.getHeroName().setName(hero[0]);
//        this.hero.getHeroName().setType(hero[1]);
//        this.hero.getAboutHero().setLevel(Integer.parseInt(hero[2]));
//        this.hero.getAboutHero().setHitPoints(Integer.parseInt(hero[3]));
//        this.hero.getAboutHero().setExperience(Integer.parseInt(hero[4]));
//        this.hero.getAboutHero().setDefence(Integer.parseInt(hero[5]));
//        this.hero.getAboutHero().setAttack(Integer.parseInt(hero[6]));
//        this.hero.getStats().setWeapon(hero[7]);
//        this.hero.getStats().setArmor(hero[8]);
//        this.hero.getStats().setHelm(hero[9]);

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

        if (check == 1);
        else if(check == 2)
            this.play(this.createHero);
        else System.exit(1);
    }

    @Override
    protected void levelUp() {
        newLevel = false;
        int newLevel = hero.getAboutHero().getLevel() + 1;

        if (game.canLevelUp(hero)){
            hero.getAboutHero().setLevel(newLevel);
            mapController.updateObjCreated();
            this.clear();
            saveAndExit();
            backToMap();
        }
        else this.cantLevelUp();
    }

    private void cantLevelUp() {
        mapController.updateObjCreated();
        consoleController.cantLevelUp();

    }

//    public void pickUpWeapon() {
//        switch (chance){
//            case 0:
//                this.increaseAttack(hero);
//                break;
//            case 1:
//                this.increaseDefence(hero);
//                break;
//            case 2:
//                this.increaseHP(hero);
//                break;
//        }
//    }

    private void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void backToMain() {
        this.play(this.createHero);
    }
}
