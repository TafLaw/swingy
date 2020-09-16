package co.za.wethinkcode.controller;

import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.characters.heroes.CreateHero;
import co.za.wethinkcode.model.characters.heroes.HeroType;
import co.za.wethinkcode.model.game.GuiGame;
import co.za.wethinkcode.model.maps.InitMap;
import co.za.wethinkcode.view.GuiViews;

import javax.swing.plaf.basic.BasicViewportUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static co.za.wethinkcode.model.characters.heroes.HeroType.*;

public class GuiController {
    //private GuiGame play;
    private EnemiesFactory enemiesFactory;
    private GuiViews guiViews;
    private GuiGame guiGameObj;
    public StartScreenHandler startScreenHandler;
    public BackButtonHandler backButtonHandler;
    public  AvailableHeroesScreenHandler availableHeroesScreenHandler;
    public PlayGameHandler playGameHandler;
    public  ChoiceHandler choiceHandler;
    public  DroppedArtifactHandler droppedArtifactHandler;
    private String[] allHeroes;
    private MapController mapController;
    public HeroesFactory gameHero;
    public InitMap initMap;

    public GuiController(MapController mapController) {
        //play = new GuiGame();
        this.mapController = mapController;
        droppedArtifactHandler = new DroppedArtifactHandler();
        startScreenHandler = new StartScreenHandler();
        backButtonHandler = new BackButtonHandler();
        availableHeroesScreenHandler = new AvailableHeroesScreenHandler();
        playGameHandler = new PlayGameHandler();
        choiceHandler = new ChoiceHandler();
        guiViews = new GuiViews(startScreenHandler);

    }

    private String[] getAllHeroes() {
        return guiGameObj.getAllHeroes();
    }

    public void startScreen(GuiController guiController, GuiGame guiGame){
        guiGameObj = guiGame;
        allHeroes = this.getAllHeroes();
        guiViews.viewSetUp(guiController);
    }

    public InitMap setGameHero(String[] hero){
        this.gameHero = guiGameObj.storePlayer(hero);
        mapController.updateObjCreated();
        this.initMap = mapController.mapPanel(gameHero);

        return this.initMap;
    }

    public String heroToString(HeroesFactory hero){
        return guiGameObj.heroToString(hero);
    }

    public void movePlayer(String direction){
        System.out.println("move");
        guiGameObj.movePlayer(direction);
        mapController.mapPanel(this.gameHero);
    }

    public HeroesFactory createHero(String type, String name){
        HeroType heroType;
        switch (type){
            case "Ninja":
                heroType = Ninja;
                break;
            case "Samurai":
                heroType = Samurai;
                break;
            case "Shinobi":
                heroType= Shinobi;
                break;
            case "Ronin":
                heroType = Ronin;
                break;
            default:
                heroType = Ninja;
        }
        return guiGameObj.createHero(heroType, name);
    }

    public String run(){
        return guiGameObj.run();
    }

    public EnemiesFactory getEnemyStats() {
        return guiGameObj.metAnEnemy();
    }

    public String fight(){

        return guiGameObj.fight(guiGameObj.getVillain());
    }

    public String[] getBattleSimulation(){
        return guiGameObj.getSimulationResults().toArray(new String[]{});
    }

    public void resetMetEnemy() {
        initMap.metEnemy = false;
    }

    public void droppedArtifact(){
        if (guiGameObj.droppedArtifact)
            guiViews.droppedArtifact();
    }

//    public class moveUpHandler implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            guiViews.activeHero = gameHero;
//
//            movePlayer(e.getActionCommand());
//            guiViews.updateStats(guiViews.activeHero);
//            guiViews.mapPanel.repaint();
//
//            if (guiControllerObj.initMap.metEnemy){
//                enemyStatsScreen(guiControllerObj.getEnemyStats());
//                guiControllerObj.resetMetEnemy();
//            }
//        }
//    }

    public class StartScreenHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            guiViews.heroTypeScreen();
        }
    }

    public class AvailableHeroesScreenHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> names = new ArrayList<String>();
            for (String hero: allHeroes){
                names.add(hero.split(",")[0]);
            }

            guiViews.availableHeroesScreen(allHeroes, names.toArray(new String[]{}));
        }
    }

    public class BackButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            guiViews.backToMain();
        }
    }

    public class ChoiceHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            guiViews.heroNameScreen(e.getActionCommand());
        }
    }

    public class PlayGameHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Play once");
            guiViews.playScreen();
        }
    }

    public class DroppedArtifactHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "pick":
                    guiGameObj.pickUpWeapon();
                    guiViews.leave();
                    break;
                case "leave":
                    guiViews.leave();
            }
        }
    }

}
