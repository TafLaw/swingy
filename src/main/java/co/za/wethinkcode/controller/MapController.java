package co.za.wethinkcode.controller;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.game.ConsoleGame;
import co.za.wethinkcode.model.maps.InitMap;
import co.za.wethinkcode.model.maps.PlayerPosition;
import co.za.wethinkcode.view.MapView;


public class MapController {
    private MapView mapView;
    public PlayerPosition playerPosition;
    public InitMap initMap;
    private HeroesFactory heroesFactory;
    private int objCreated = 0;

    public MapController() {
        mapView = new MapView();
    }

    public void viewMap(HeroesFactory hero){
        if (objCreated == 0){
            setMap(hero);
        }
        else {
            initMap.updatePlayerPosition(playerPosition.getPlayerRow(), playerPosition.getPlayerColumn());
            if (initMap.metEnemy && mapView.section == 1) {
                ConsoleGame.section = 2;
            }
        }
        mapView.viewMap(playerPosition, initMap, hero);
    }

    public InitMap mapPanel(HeroesFactory hero){
        if (objCreated == 0)
            setMap(hero);
        else initMap.updatePlayerPosition(playerPosition);

        return this.initMap;
    }

    /**
     * This will allow new map creation on Game over case or level up case
     * **/
    public void updateObjCreated(){
        objCreated = 0;
    }

    private void setMap(HeroesFactory hero) {
        this.heroesFactory = hero;
        initMap = new InitMap(hero.getAboutHero().getLevel());

        playerPosition = new PlayerPosition(initMap.getRows(), initMap.getColumns());
        initMap.playerPosition(playerPosition.getPlayerRow(), playerPosition.getPlayerColumn());
        initMap.setRandomEnemies();
        initMap.setPlayerPosition(playerPosition);
        objCreated++;
    }

    public void resetEnemy(int row, int col){
        initMap.resetEnemy(row, col);
    }


}
