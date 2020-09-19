package co.za.wethinkcode.model.game;

import co.za.wethinkcode.controller.GameController;
import co.za.wethinkcode.controller.GuiController;
import co.za.wethinkcode.controller.MapController;
import co.za.wethinkcode.model.characters.enemies.CreateEnemies;
import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.characters.heroes.CreateHero;
import co.za.wethinkcode.model.characters.heroes.HeroType;
import co.za.wethinkcode.storage.HeroStorage;

import java.util.ArrayList;
import java.util.Random;

public class GuiGame extends AbGame{
    private GuiController guiController;
    private HeroStorage heroStorage;
    private MapController mapController;
    private Game game;
    public GuiGame(MapController mapController, Game game, GameController gameController) {
        this.gameController = gameController;
        this.mapController = mapController;
        guiController = new GuiController(this.mapController);
        heroStorage = new HeroStorage();
        this.game = game;
    }

    public void play(CreateHero createHero){
        this.createHero = createHero;

        guiController.startScreen(guiController, this);

    }

    public ArrayList<String> getSimulationResults(){
        return battleSimulation;
    }

    public HeroesFactory storePlayer(String[] hero) {
        this.hero = this.game.storePlayer(hero);
        heroInitialHP = this.hero.getAboutHero().getHitPoints();
        return this.hero;
    }

    public void movePlayer(String direction){

        game.movePlayer(mapController, this, direction);
        if (newLevel)
            levelUp();
    }

    public String[] getAllHeroes(){
        return heroStorage.allAvailableHeroes().toArray(new String[]{});
    }

    public HeroesFactory createHero(HeroType heroType, String name){
        ArrayList<String> allHeroes = heroStorage.allAvailableHeroes();
        hero = createHero.createHero(name.trim(), CreateHero.heroTypes.get(heroType.ordinal()));
        if (hero == null) {
            guiController.showError();
        }
        else {
            heroIndex = allHeroes.size() - 1;
            heroInitialHP = this.hero.getAboutHero().getHitPoints();
        }

        return hero;
    }

    public String heroToString(HeroesFactory heroesFactory){

        return heroesFactory.getHeroName().getName() + ","
                + heroesFactory.getHeroName().getType() + ","
                + heroesFactory.getAboutHero().getLevel() + ","
                + heroesFactory.getAboutHero().getHitPoints() + ","
                + heroesFactory.getAboutHero().getExperience() + ","
                + heroesFactory.getAboutHero().getDefence() + ","
                + heroesFactory.getAboutHero().getAttack() + ","
                + heroesFactory.getStats().getWeapon() + ","
                + heroesFactory.getStats().getArmor() + ","
                + heroesFactory.getStats().getHelm();
    }

    public EnemiesFactory metAnEnemy() {
        Random random = new Random();
        int randomEnemy;
        randomEnemy = random.nextInt(CreateEnemies.enemiesFactory.size());
        theVillain = CreateEnemies.enemiesFactory.get(randomEnemy);
        return theVillain;
    }

    public String fight(EnemiesFactory enemy) {
        return fightEnemy(enemy);
    }

    public EnemiesFactory getVillain(){
        return theVillain;
    }

    protected void levelUp() {

        int newLevel = hero.getAboutHero().getLevel() + 1;

        if (newLevel > 7)
            newLevel = 7;

        if (game.canLevelUp(hero)){
            hero.getAboutHero().setLevel(newLevel);
            mapController.updateObjCreated();
            saveAndExit();
        }
        else this.cantLevelUp();
    }

    private void cantLevelUp() {
        guiController.cantLevelUp();
    }

    public String run() {
        return runAway(this.mapController);
    }

    public void save() {
        this.saveAndExit();
    }

    public void switchToConsole() {
        mapController.updateObjCreated();
        gameController.startConsole(this.createHero);
    }
}
