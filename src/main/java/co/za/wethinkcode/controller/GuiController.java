package co.za.wethinkcode.controller;

import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.characters.heroes.HeroType;
import co.za.wethinkcode.model.game.GuiGame;
import co.za.wethinkcode.model.maps.InitMap;
import co.za.wethinkcode.view.GuiViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static co.za.wethinkcode.model.characters.heroes.HeroType.*;

public class GuiController {
    private GuiViews guiViews;
    private GuiGame guiGameObj;
    public StartScreenHandler startScreenHandler;
    public BackButtonHandler backButtonHandler;
    public  AvailableHeroesScreenHandler availableHeroesScreenHandler;
    public SwitchToConsoleHandler switchToConsoleHandler;
    public PlayGameHandler playGameHandler;
    public  ChoiceHandler choiceHandler;
    public  DroppedArtifactHandler droppedArtifactHandler;
    public SaveAndExitHandler saveAndExitHandler;
    private String[] allHeroes;
    private MapController mapController;
    public HeroesFactory gameHero;
    public InitMap initMap;

    public GuiController(MapController mapController) {
        this.mapController = mapController;
        switchToConsoleHandler = new SwitchToConsoleHandler();
        droppedArtifactHandler = new DroppedArtifactHandler();
        saveAndExitHandler = new SaveAndExitHandler();
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
        guiGameObj.movePlayer(direction);
        this.initMap = mapController.mapPanel(this.gameHero);

        if (guiGameObj.newLevel){
            guiGameObj.newLevel = false;
            backToMap();
        }
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

    public void setHeroIndex(int heroIndex) {
        guiGameObj.setHeroIndex(heroIndex);
    }

    public void backToMap() {
        guiViews.rePaint();
    }

    public void cantLevelUp() {
        guiViews.cantLevelUpScreen();
    }

    public void showError() {
        guiViews.error();
    }

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
            allHeroes = getAllHeroes();
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

    public class SaveAndExitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            guiGameObj.save();
            guiViews.backToMain();
        }
    }

    public class SwitchToConsoleHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            guiViews.availableHeroesButton.removeActionListener(availableHeroesScreenHandler);
            guiViews.switchToConsoleButton.removeActionListener(switchToConsoleHandler);
            guiViews.createHeroButton.removeActionListener(startScreenHandler);
            guiViews.closeWindow();
            guiGameObj.switchToConsole();
        }
    }

}
