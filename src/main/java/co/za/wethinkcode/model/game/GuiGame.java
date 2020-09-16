package co.za.wethinkcode.model.game;

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

//import static co.za.wethinkcode.model.characters.heroes.HeroType.*;

public class GuiGame extends AbGame{
    private CreateHero createHero;
    private GuiController guiController;
    private HeroStorage heroStorage;
    //private HeroesFactory hero;
    private MapController mapController;
    private Game game;
    private int playerRow;
    private int playerCol;
    private int playerEnemyRow;
    private int playerEnemyCol;

    public GuiGame(MapController mapController, Game game) {
        this.mapController = mapController;
        guiController = new GuiController(this.mapController);
        heroStorage = new HeroStorage();
        this.game = game;
    }

    public void play(CreateHero createHero){
        this.createHero = createHero;

        guiController.startScreen(guiController, this);
        System.out.println("Gui GamePlay");
    }

    public ArrayList<String> getSimulationResults(){
        return battleSimulation;
    }

    public HeroesFactory storePlayer(String[] hero) {
        this.hero = this.game.storePlayer(hero);
        System.out.println(this.hero);
        return this.hero;
    }

    public void movePlayer(String direction){
        game.movePlayer(mapController, this, direction);
    }

    public HeroesFactory getha(){
        return this.hero;
    }

    public String[] getAllHeroes(){
        return heroStorage.allAvailableHeroes().toArray(new String[]{});
    }

    public HeroesFactory createHero(HeroType heroType, String name){
//        System.out.println(Ninja.ordinal());
        hero = createHero.createHero(name.trim(), CreateHero.heroTypes.get(heroType.ordinal()));
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
        //this.guiController.enemyStats(CreateEnemies.enemiesFactory.get(randomEnemy));
        theVillain = CreateEnemies.enemiesFactory.get(randomEnemy);
        return theVillain;
    }

    public String fight(EnemiesFactory enemy) {
        System.out.println("AESR :"+this.hero);
        return fightEnemy(enemy);
    }

    public EnemiesFactory getVillain(){
        return theVillain;
    }

    @Override
    protected void levelUp() {

    }

    public String run() {
        return runAway(this.mapController);
    }
}
